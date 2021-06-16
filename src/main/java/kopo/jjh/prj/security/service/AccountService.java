package kopo.jjh.prj.security.service;

import kopo.jjh.prj.domain.repository.AccountRepository;
import kopo.jjh.prj.security.domain.Account;
import kopo.jjh.prj.security.dto.AccountForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class AccountService  {
    private static final Logger logger = LogManager.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private JavaMailSender mailSender;

    @Transactional
    public Long createUser(AccountForm form) throws MessagingException, UnsupportedEncodingException {
        Account account = form.toEntity();
        accountRepository.save(account);
        account.increaseoverlabCount();
        account.increaseoverlabCount1();
        log.info("Entity="+form.toEntity());
        return account.getUsername_no();
    }
    //회원리스트


    @Transactional
    public List<AccountForm> getuserList(Pageable pageable) {
        List<Account> userList = accountRepository.findAll();
        List<AccountForm> accountFormList = new ArrayList<>();

        for (Account account : userList) {
            AccountForm accountForm = AccountForm.builder()
                    .username_no(account.getUsername_no())
                    .author(account.getAuthor())
                    .email(account.getEmail())
                    .name(account.getName())
                    .username(account.getUsername())
                    .homeCnt(account.getHomeCnt())
                    .build();
            accountFormList.add(accountForm);
        }
        return accountFormList;
    }

    @Transactional
    public void updateUser(Long username_no, AccountForm accountForm) {
        Optional<Account> account = accountRepository.findById(username_no);

        account.ifPresent(selectUser -> {


            selectUser.setUsername_no(accountForm.getUsername_no());

            selectUser.setUsername(accountForm.getUsername());
            selectUser.setName(accountForm.getName());
            selectUser.setAuthor(accountForm.getAuthor());
        });
    }
//회원수정 로직
   /* @Transactional
    public Long updateUser(AccountForm form) {
        Account account = accountRepository.save();

        AccountForm accountForm = AccountForm.builder()
                .username_no(account.getUsername_no())
                .username(account.getUsername())
                .nam(account.getNam())
                .author(account.getAuthor())

                .build();

        return accountForm.getUsername_no();
    }
    
    */

    @Transactional
    public AccountForm gethomeCnt(Long id) {
        Account account = accountRepository.findById(id).get();

        AccountForm accountForm = AccountForm.builder()

                .username_no(account.getUsername_no())
                .username(account.getUsername())
                .name(account.getName())

                .author(account.getAuthor())

                .build();

        account.increaseHomeCount();

        return accountForm;
    }
    @Transactional
    public AccountForm getoverlabCnt(Long id) {
        Account account = accountRepository.findById(id).get();

        AccountForm accountForm = AccountForm.builder()
                .username_no(account.getUsername_no())
                .overlabcnt(account.getOverlabcnt())
                .build();
        return accountForm;
    }

    @Transactional
    public int delete(long username_no) {
        Optional<Account> oUser = accountRepository.findById(username_no);
        if (oUser.isPresent()) {
            accountRepository.delete(oUser.get());
            return 1;
        }
        return 0;
    }


    public void updateAuthKey(Map<String, String> map) {
    }


    public void updateAuthStatus(AccountForm form) {  form.updateAuthStatus()
    ;}






}

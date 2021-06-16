package kopo.jjh.prj.config;

import kopo.jjh.prj.security.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
@Slf4j
public class AccountContext extends User {
    private static final Logger logger = LogManager.getLogger(AccountContext.class);
    private final Account account;

    public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {

        super(account.getUsername(), account.getPassword(), authorities);
        this.account = account;
        log.info("암호화2");
    }

    public Account getAccount() {

        log.info("암호화3");
        return account;
    }
}
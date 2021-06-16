package kopo.jjh.prj.security.dto;


import kopo.jjh.prj.security.domain.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class AccountForm {

    private Long username_no; //회원번호
    private String username; //실제이름
    private String name; //유저id
    public String password;
    private String email;
    private String author; //유저닉네임(이름)
    private int homeCnt;
    private int overlabcnt;
    private int overlabcnt1;
    private int authstatus;
    private String authkey;
    private String role;
    private String certified;
    private int count;

    @Builder
    public AccountForm(Long username_no, String username,int count,int homeCnt,int overlabcnt,int overlabcnt1, String password,int authstatus,String authkey, String email, String author, String role, String name , String certified) {
        this.name = name;
        this.authkey = authkey;
        this.authstatus = authstatus;
        this.homeCnt = homeCnt;
        this.overlabcnt=overlabcnt;
        this.overlabcnt1=overlabcnt1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.author = author;
        this.username_no = username_no;
        this.role = role;
        this.certified = certified;
        this.count = count;
    }

    public Account toEntity(){
        return Account.builder()
                .username_no(username_no)
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .authkey(authkey)
                .authstatus(authstatus)
                .email(email)
                .homeCnt(homeCnt)
                .overlabcnt(overlabcnt)
                .overlabcnt1(overlabcnt1)
                .name(name)
                .author(author)
                .role(role)
                .count(count)
                .certified(certified)
                .build();


    }


    public void updateAuthStatus(){this.authstatus ++;}
}

package kopo.jjh.prj.security.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
// 다른 패키지에서 생성자 함부로 생성하지 마세요!
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements Serializable {
    private static final long serialVersionUID = -633875127201861683L;
    @Id
    @Column(name = "username_no")
    // SQL 에서 자동생성되도록 돕는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long username_no;  //회원번호
    private String author; //별명
    private String username; //아이디
    private String password; //패스워드
    private String email; //이메일
    private String name; //이름
    private int homeCnt = 0; //홈페이지 조회수,로그인횟수
    private int overlabcnt = 0; //아이디 중복확인
    private int overlabcnt1 = 0; //이메일 중복확인
    private int authstatus;
    private String certified;
    private String authkey;
    private int count;
    private String role; //권한

    @Builder
    public Account(Long username_no,String certified,int overlabcnt,int count,int overlabcnt1,int authstatus, String username, String authkey,String password,String email, String name, int homeCnt, String role , String author) {
        this.username_no = username_no;
        this.certified = certified;
        this.authkey = authkey;
        this.overlabcnt = overlabcnt;
        this.overlabcnt1 = overlabcnt1;
        this.authstatus = authstatus;
        this.count=count;
        this.username = username;
        this.password = password;
        this.email = email;
        this.author = author;
        this.homeCnt = homeCnt;
        this.role = role;
        this.name = name;
    }

    public Account(String username, String certified, List<GrantedAuthority> roles) {
    }

    public void increaseHomeCount() {
        this.homeCnt ++;
    }
    public void increaseoverlabCount() {this.overlabcnt ++;}
    public void increaseoverlabCount1() {this.overlabcnt1 ++;}

    public Account update(String name, String author,  String username ,Long username_no) {
    this.name = name;
    this.author = author;
    this.username = username;
    this.username_no = username_no;
    return this;
    }
}
package kopo.jjh.prj.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kopo.jjh.prj.security.domain.Account;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * Class Description
 * 댓글
 *
 * @author 정지훈
 * @since 2021
 */

@Entity
@Table(name="COMMENTS")
@EqualsAndHashCode(of="COMMNETS_NO")
@Getter
@Setter
@ToString(exclude = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // @lombock 어노테이션 : 파라미터를 받지 않는 생성자를 만들어준다.
@AllArgsConstructor //  @lombock 어노테이션 : 모든 속성에 대해서 생성자를 만들어 낸다.
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENTS_NO")
    private Long id;

    @NonNull
    @Column(name = "CONTENT")
    private String content;


    @NonNull
    @Column(name = "DEL_YN")
    @ColumnDefault(value = "false")
    private Boolean deleted;

    @NonNull
    @Column(name="CREATED_BY")
    private String created_by;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="POST_NO")
    private Board post;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_NO")
    private Account user;

    //User엔티티와  Post엔티티를 연결하는 함수
    public void changeAuthor(Account author) {
        this.user = author;
    }
    public void changePost(Board post) {
        this.post = post;
    }

}
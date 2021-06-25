package kopo.jjh.prj.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name="tbl_webboards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class Board {


    public static kopo.jjh.prj.domain.entity.Board Board;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable=false)
    private int hitCnt; //게시판조회수
    
    @Column
    private Long fileId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Board(Long id, String author, String title, String content, Long fileId , int hitCnt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.hitCnt = hitCnt;
    }

    public void increaseViewCount() {
        this.hitCnt ++;
    }

}
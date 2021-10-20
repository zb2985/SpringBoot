package kopo.jjh.prj.dto;

import kopo.jjh.prj.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private Long fileId;
    private int hitCnt;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int categori;
    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .fileId(fileId)
                .hitCnt(hitCnt)
                .categori(categori)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String author, String title, String content, Long fileId, int hitCnt ,LocalDateTime createdDate, LocalDateTime modifiedDate ,int categori) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.hitCnt = hitCnt;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.categori = categori;
    }
}
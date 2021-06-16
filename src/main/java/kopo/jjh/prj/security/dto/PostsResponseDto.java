package kopo.jjh.prj.security.dto;

import kopo.jjh.prj.security.domain.Account;
import lombok.Getter;

//PostResponseDTO
@Getter
public class PostsResponseDto {
    private Long username_no;
    private String username;
    private String author;
    private String name;

    public PostsResponseDto(Account entity){
        this.username= entity.getUsername();
        this.author = entity.getAuthor();
        this.username_no = entity.getUsername_no();
        this.name = entity.getName();
    }
}
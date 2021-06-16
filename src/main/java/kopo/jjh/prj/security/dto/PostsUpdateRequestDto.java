package kopo.jjh.prj.security.dto;

import lombok.Builder;

public class PostsUpdateRequestDto {
    private String username;
    private  String name;
    private String author;
@Builder
    public PostsUpdateRequestDto(String username,String name , String author){
    this.author=author;
    this.name=name;
    this.username=username;
}
}

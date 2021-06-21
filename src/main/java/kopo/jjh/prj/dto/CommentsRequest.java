package kopo.jjh.prj.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CommentsRequest {
    @NotBlank
    private String content;

    @NotBlank
    private Long userNo;

    @NotBlank
    private Long postNo;

    @NotBlank
    private String created_by;
}
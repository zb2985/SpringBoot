package kopo.jjh.prj.dto;

import kopo.jjh.prj.domain.entity.InfoFile;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InfoFileDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public InfoFile toEntity() {
        InfoFile build = InfoFile.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public InfoFileDto(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
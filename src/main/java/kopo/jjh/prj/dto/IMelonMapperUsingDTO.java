package kopo.jjh.prj.dto;

import java.util.List;

public interface IMelonMapperUsingDTO {
    public int insertSong(List<MelonDTO> pList, String colNM) throws Exception;

    public List<MelonDTO> getSongList(String colNm) throws Exception;
    public List<MelonDTO> getSingerSongCnt(String colNm) throws Exception;

}

package kopo.jjh.prj.service;

import kopo.jjh.prj.dto.MelonDTO;

import java.util.List;

public interface IMelonServiceUsingDTO {
public int collectMelonSong() throws Exception;
public List<MelonDTO> getSongList() throws Exception;

public List<MelonDTO> getSingerSongCnt() throws  Exception;

}

package kopo.jjh.prj.service;

import kopo.jjh.prj.dto.MovieDTO;

import java.util.List;

public interface IMovieRankService {
    List<MovieDTO> getMovieRank(MovieDTO pDTO) throws Exception;
}

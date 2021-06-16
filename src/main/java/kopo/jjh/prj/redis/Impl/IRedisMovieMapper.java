package kopo.jjh.prj.redis.Impl;

import kopo.jjh.prj.dto.MovieDTO;

import java.util.List;

public interface IRedisMovieMapper {
    public boolean getExists(String key) throws Exception;
    public List<MovieDTO> getMovieRank(String key)throws Exception;
    public int setMovieRank(String key,List<MovieDTO> pList)throws Exception;
    public boolean setTimeOutHour(String key,int Hours) throws  Exception;
 }



package kopo.jjh.prj.service.impl;

import kopo.jjh.prj.dto.MovieDTO;
import kopo.jjh.prj.mapper.IMovieMapper;
import kopo.jjh.prj.redis.Impl.IRedisMovieMapper;
import kopo.jjh.prj.service.IMovieRankService;
import kopo.jjh.prj.service.IMovieService;
import kopo.jjh.prj.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("MovieRankService")
public class MovieRankService implements IMovieRankService {
    @Resource(name = "MovieService")
    private IMovieService movieService;
    @Resource(name = "MovieMapper")
    private IMovieMapper mysqlMovieMapper;
    @Resource(name = "RedisMovieMapper")
    private IRedisMovieMapper redisMovieMapper;

    @Override
    public List<MovieDTO> getMovieRank(MovieDTO pDTO) throws Exception {
        List<MovieDTO> rList = null;
        String key = "CVG_RANK_" + DateUtil.getDateTime("yyyyMMdd");
        if (redisMovieMapper.getExists(key)) {
            rList = redisMovieMapper.getMovieRank(key);
            if (rList == null) {
                rList = new ArrayList<MovieDTO>();
            }
            redisMovieMapper.setTimeOutHour(key, 1);

        } else {
            rList = mysqlMovieMapper.getMovieInfo(pDTO);
            if (rList == null) {
                rList = new ArrayList<MovieDTO>();
            }
            if (rList.size() == 0) {
                movieService.getMovieInfoFromWEB();
                rList = mysqlMovieMapper.getMovieInfo(pDTO);


                if (rList == null) {
                    rList = new ArrayList<MovieDTO>();
                }
            }
            if (rList.size() > 0) {
                redisMovieMapper.setMovieRank(key, rList);
                redisMovieMapper.setTimeOutHour(key, 1);
            }
        }
        return rList;
    }

}

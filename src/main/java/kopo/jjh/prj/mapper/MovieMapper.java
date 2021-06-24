package kopo.jjh.prj.mapper;
/*

import kopo.jjh.prj.dto.MovieDTO;
import kopo.jjh.prj.mapper.IMovieMapper;
import kopo.jjh.prj.util.CmmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MovieMapper")
public class MovieMapper implements IMovieMapper {

    @Autowired
    public RedisTemplate<String, Object> redisDB;
    @Override
    public int InsertMovieInfo (MovieDTO pDTO) throws Exception{

        int res = 0;
        String rank_chkeck_time = CmmUtil.nvl(pDTO.getRank_chkeck_time());

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MovieDTO.class));
        redisDB.opsForList().leftPush(rank_chkeck_time,pDTO);
        res=1;
        return res;

    }

    

    @Override
    public List<MovieDTO> getMovieInfo(MovieDTO pDTO) throws  Exception{
        List<MovieDTO> rList = null;
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MovieDTO.class));
        if(redisDB.hasKey(pDTO.getRank_chkeck_time()))
        {
            rList = (List) redisDB.opsForList().range(pDTO.getRank_chkeck_time(), 0, -1);

        }

        return rList;
    }




}

 */
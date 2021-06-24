/*
package kopo.jjh.prj.redis;

import kopo.jjh.prj.dto.MovieDTO;
import kopo.jjh.prj.redis.Impl.IRedisMovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component("RedisMovieMapper")
public class RedisMovieMapper implements IRedisMovieMapper {
    @Autowired
    public RedisTemplate<String, Object> redisDB;
    @Override
    public boolean getExists(String key) throws Exception{
        return redisDB.hasKey(key);
    }
@Override
    public List<MovieDTO> getMovieRank(String key) throws  Exception{
        List<MovieDTO> rList = null;
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MovieDTO.class));
        if(redisDB.hasKey(key)){
            rList = (List) redisDB.opsForList().range(key, 0, -1);
        }
        return rList;
}
@Override
    public int setMovieRank(String key,List<MovieDTO> pList)throws Exception {
    int res = 0;
    redisDB.setKeySerializer(new StringRedisSerializer());
    redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MovieDTO.class));
    if (this.getExists(key)) {
        redisDB.delete(key);

    }
    Iterator<MovieDTO> it = pList.iterator();
    while(it.hasNext()){
        MovieDTO pDTO = (MovieDTO) it.next();
        redisDB.opsForList().rightPush(key,pDTO);
        pDTO = null;
    }
    res=1;
    return res;
}
@Override
    public boolean setTimeOutHour(String roomKey,int hours)throws Exception{
        return redisDB.expire(roomKey,hours, TimeUnit.HOURS);
}


}

 */
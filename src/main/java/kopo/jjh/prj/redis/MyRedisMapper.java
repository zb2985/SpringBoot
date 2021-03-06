
package kopo.jjh.prj.redis;


import kopo.jjh.prj.controller.CChatController;
import kopo.jjh.prj.dto.MyJsonDTO;
import kopo.jjh.prj.redis.Impl.IMyRedisMapper;
import kopo.jjh.prj.socket.ChatMessage;
import kopo.jjh.prj.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component("MyRedisMapper")
@Slf4j
public class MyRedisMapper implements IMyRedisMapper{
    private static final Logger logger = LogManager.getLogger(MyRedisMapper.class);
    @Autowired
    public RedisTemplate<String, Object> redisDB;
    @Autowired
    private MyRedisService MyredisService;
    private ChatMessage chatMessage;

    @Autowired
    public CChatController chat;
    @Override
    public void doSaveData(JSONObject news, JSONObject newss , JSONObject exchange)  {
        log.info(this.getClass().getName() + ".getCacheData service start!");

        String redisKey = "오늘의 단어";
        String saveData = news.toString();
        String redisKey1 = "뉴스 전체 목록";
        String saveData1 = newss.toString();
        String redisKey2 = "환율기록";
        String saveData2 = exchange.toString();
        String CovidKey = "코로나저장";
        String saveData3 ="getCovid.toString()";
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new StringRedisSerializer());

        if (redisDB.hasKey(redisKey)) {
            String res = (String) redisDB.opsForValue().get(redisKey);

            log.info("res:" + res);

        } else {
            redisDB.opsForValue().set(redisKey, saveData);
            redisDB.expire(redisKey, 2, TimeUnit.DAYS);
            log.info("No data");
        }
        if (redisDB.hasKey(redisKey1)) {
            String res1 = (String) redisDB.opsForValue().get(redisKey1);

            log.info("res:" + res1);

        } else {
            redisDB.opsForValue().set(redisKey1, saveData1);
            redisDB.expire(redisKey1, 2, TimeUnit.DAYS);
            log.info("No data");
        }
        if (redisDB.hasKey(redisKey2)) {
            String res2 = (String) redisDB.opsForValue().get(redisKey2);

            log.info("res:" + res2);

        } else {
            redisDB.opsForValue().set(redisKey2, saveData2);
            redisDB.expire(redisKey2, 2, TimeUnit.DAYS);
            log.info("No data");
        }
        if (redisDB.hasKey(CovidKey)) {
            String res = (String) redisDB.opsForValue().get(CovidKey);

            log.info("res:" + res);

        } else {
            redisDB.opsForValue().set(CovidKey, saveData3);
            redisDB.expire(CovidKey, 2, TimeUnit.DAYS);
            log.info("No data");
        }
    }



    @Override
    public void doSaveDataforList() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".doSaveDataforList start!");

        String redisKey = "Test02-List";

        /*
         * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
         */
        redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
        redisDB.setValueSerializer(new StringRedisSerializer()); // String 타입

        if (redisDB.hasKey(redisKey)) {

            // Redis에 저장된 데이터 전체 가져오기
            // 데이터 인덱스는 0부터 시작하며, 세번째 인자값은 -1로 설정하면 모두 가져옴
            List<String> pList = (List) redisDB.opsForList().range(redisKey, 0, -1);

            Iterator<String> it = pList.iterator();

            while (it.hasNext()) {
                String data = (String) it.next();

                log.info("data : " + data);

            }

        } else {

            // List를 배열과 같이 여러 개의 데이터가 생성되기 때문에 반복문을 통해 데이터 저장
            for (int i = 0; i < 10; i++) {

                // 오름차순 저장
//				redisDB.opsForList().rightPush(redisKey, "[" + i + "] 번째 데이터입니다.");

                // 내림차순 저장
                redisDB.opsForList().leftPush(redisKey, "[" + i + "] 번째 데이터입니다.");

            }

            // 저장되는 데이터의 유효기간(TTL)은 5시간으로 정의
            redisDB.expire(redisKey, 5, TimeUnit.HOURS);

            log.info("Save Data!!");

        }
    }
    /**
     * DTO를 활용한 JSON 데이터 저장
     */
    @Override
    public void doSaveDataforListJSON() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".doSaveDataforList start!");

        String redisKey = "오늘의 단어";

        /*
         * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
         */
        redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입

        // DTO를 JSON 구조로 변경
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MyJsonDTO.class));

        MyJsonDTO pDTO = null;

        if (redisDB.hasKey(redisKey)) {

            // Redis에 저장된 데이터 전체 가져오기ss
            // 데이터 인덱스는 0부터 시작하며, 세번째 인자값은 -1로 설정하면 모두 가져옴
            List<MyJsonDTO> pList = (List) redisDB.opsForList().range(redisKey, 0, -1);

            Iterator<MyJsonDTO> it = pList.iterator();

            while (it.hasNext()) {
                MyJsonDTO rDTO = (MyJsonDTO) it.next();

                if (rDTO == null) {
                    rDTO = new MyJsonDTO();

                }



            }

        } else {

            pDTO = new MyJsonDTO();

            pDTO.setMessage("이협건");



            redisDB.opsForList().rightPush(redisKey, pDTO);

            pDTO = null;

            pDTO = new MyJsonDTO();

            pDTO.setMessage("홍길동");



            redisDB.opsForList().rightPush(redisKey, pDTO);

            pDTO = null;

            // 저장되는 데이터의 유효기간(TTL)은 100분으로 정의
            redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

            log.info("Save Data!!");

        }

    }

    /**
     * HashTable 저장
     */
    @Override
    public void doSaveDataforHashTable() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".doSaveDataforHashTable start!");

        String redisKey = "Test03-HashTable";

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new StringRedisSerializer());

        if (redisDB.hasKey(redisKey)) {

            String name = CmmUtil.nvl((String) redisDB.opsForHash().get(redisKey, "name"));
            String email = CmmUtil.nvl((String) redisDB.opsForHash().get(redisKey, "email"));
            String addr = CmmUtil.nvl((String) redisDB.opsForHash().get(redisKey, "addr"));

            log.info("name : " + name);
            log.info("email : " + email);
            log.info("addr : " + addr);

        } else {

            redisDB.opsForHash().put(redisKey, "name", "이협건");
            redisDB.opsForHash().put(redisKey, "email", "hglee67@kopo.ac.kr");
            redisDB.opsForHash().put(redisKey, "addr", "서울시 강서구");

            // 저장되는 데이터의 유효기간(TTL)은 100분으로 정의
            redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

            log.info("Save Data!!");

        }

    }

    @Override
    public void doSaveDataforSet() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".doSaveDataforSet start!");

        String redisKey = "Test03-Set";

        /*
         * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
         */
        redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
        redisDB.setValueSerializer(new StringRedisSerializer());

        if (redisDB.hasKey(redisKey)) {

            Set rSet = (Set) redisDB.opsForSet().members(redisKey);

            Iterator<String> it = rSet.iterator();

            while (it.hasNext()) {
                String value = CmmUtil.nvl((String) it.next());

                log.info("value" + value);

            }

        } else {

            redisDB.opsForSet().add(redisKey, "1번째 저장합니다.");
            redisDB.opsForSet().add(redisKey, "2번째 저장합니다.");
            redisDB.opsForSet().add(redisKey, "3번째 저장합니다.");
            redisDB.opsForSet().add(redisKey, "4번째 저장합니다.");

            redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

            log.info("Save Data!!");

        }
    }

    @Override
    public void doSaveDataforZSet() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".doSaveDataforSet start!");

        String redisKey = "Test04-Zset";

        /*
         * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
         */
        redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
        redisDB.setValueSerializer(new StringRedisSerializer());

        if (redisDB.hasKey(redisKey)) {

            // 저장된 전체 레코드 수
            long cnt = redisDB.opsForZSet().size(redisKey);

            Set rSet = (Set) redisDB.opsForZSet().range(redisKey, 0, cnt);

            if (rSet == null) {
                rSet = new LinkedHashSet<String>();
            }

            Iterator<String> it = rSet.iterator();

            while (it.hasNext()) {
                String value = CmmUtil.nvl((String) it.next());

                log.info("value" + value);

            }

        } else {

            // 3번째 파라미터는 저장 순서 가중치이며, 작을수록 앞에 저장됨
            redisDB.opsForZSet().add(redisKey, "1번째 저장합니다.", 1);
            redisDB.opsForZSet().add(redisKey, "2번째 저장합니다.", 2);
            redisDB.opsForZSet().add(redisKey, "3번째 저장합니다.", 3);
            redisDB.opsForZSet().add(redisKey, "4번째 저장하지만, 2번째 위치로 가고 싶다.", 1.1);

            redisDB.expire(redisKey, 100, TimeUnit.MINUTES);

            log.info("Save Data!!");

        }
    }

}
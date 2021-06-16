/*
package kopo.jjh.prj.redis;


import kopo.jjh.prj.redis.Impl.IMyRedisMapper;
import kopo.jjh.prj.redis.Impl.IMyRedisServcie;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("MyRedisService")
public class MyRedisService implements IMyRedisServcie {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MyRedisService.class);
@Resource(name= "MyRedisMapper")
private IMyRedisMapper myRedisMapper;



@Override
    public void doSaveData() throws Exception{
    log.info(this.getClass().getName()+".doSaveData Start!");
myRedisMapper.doSaveData();
log.info(this.getClass().getName()+".doSaveData End!");
    }
@Override
    public void doSaveDataforList() throws Exception {
    log.info(this.getClass().getName()+"리스트구조실습");
    myRedisMapper.doSaveDataforList();
    log.info(this.getClass().getName()+"완료");
}

    @Override
    public void doSaveDataforListJSON() throws Exception {

        log.info(this.getClass().getName() + ".doSaveDataforListJSON Start!");

        myRedisMapper.doSaveDataforListJSON();

        log.info(this.getClass().getName() + ".doSaveDataforListJSON End!");

    }

    @Override
    public void doSaveDataforHashTable() throws Exception {

        log.info(this.getClass().getName() + ".doSaveDataforHashTable Start!");

        myRedisMapper.doSaveDataforHashTable();

        log.info(this.getClass().getName() + ".doSaveDataforHashTable End!");

    }

    @Override
    public void doSaveDataforSet() throws Exception {

        log.info(this.getClass().getName() + ".doSaveDataforSet Start!");

        myRedisMapper.doSaveDataforSet();

        log.info(this.getClass().getName() + ".doSaveDataforSet End!");

    }

    @Override
    public void doSaveDataforZSet() throws Exception {

        log.info(this.getClass().getName() + ".doSaveDataforZSet Start!");

        myRedisMapper.doSaveDataforZSet();

        log.info(this.getClass().getName() + ".doSaveDataforZSet End!");

    }
}



 */
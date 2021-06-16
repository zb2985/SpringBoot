package kopo.jjh.prj.redis.Impl;



public interface IMyRedisServcie {

    public void doSaveData() throws Exception;

    public void doSaveDataforList() throws Exception;

    public void doSaveDataforListJSON() throws Exception;

    public void doSaveDataforHashTable() throws Exception;

    public void doSaveDataforSet() throws Exception;

    public void doSaveDataforZSet() throws Exception;

}


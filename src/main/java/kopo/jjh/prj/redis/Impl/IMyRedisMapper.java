package kopo.jjh.prj.redis.Impl;


import org.json.simple.JSONObject;

public interface IMyRedisMapper {

   public void doSaveData(JSONObject news, JSONObject newss, JSONObject exchange) throws Exception
  ;

    public void doSaveDataforList() throws Exception;

    public void doSaveDataforListJSON() throws Exception;

    public void doSaveDataforHashTable() throws Exception;

    public void doSaveDataforSet() throws Exception;

    public void doSaveDataforZSet() throws Exception;


}




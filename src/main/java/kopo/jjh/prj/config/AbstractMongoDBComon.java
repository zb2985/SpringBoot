/*package kopo.jjh.prj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class AbstractMongoDBComon {
    @Autowired
    private MongoTemplate mongodb;

    protected boolean createCollection(String colNm)throws Exception{
        return createCollection(colNm, "");

    }
    protected boolean createCollection(String colNm,String index)throws Exception{

        String[] indexArr = { index };
        return createCollection(colNm, indexArr);

    }


}
*/
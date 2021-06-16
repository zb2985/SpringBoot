/*import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import kopo.jjh.prj.config.AbstractMongoDBComon;
import kopo.jjh.prj.dto.IMelonMapperUsingDTO;
import kopo.jjh.prj.dto.MelonDTO;
import kopo.jjh.prj.util.CmmUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component("MelonMapperUsingDTO")
public class MelonMapperUsingDTO extends AbstractMongoDBComon implements IMelonMapperUsingDTO {
    @Autowired
    private MongoTemplate mongodb;
    @Override
    public int insertSong(List<MelonDTO>pList, String colNm)throws Exception {
        int res = 0;

    if(pList == null) {
        pList = new LinkedList<MelonDTO>();
    }
    super.createCollection(colNm,"collectTime");
    MongoCollection<Document> col = mongodb.getCollection(colNm);
        Iterator<MelonDTO> it = pList.iterator();
        while (it.hasNext()) {

            MelonDTO = rDTO = it.next();
            if (rDTO == null) {
                rDTO = new MelonDTO();
            }
            String collectTime = CmmUtil.nvl(rDTO.getCollectTime());
            String song = CmmUtil.nvl(rDTO.getSong());
            String singer = CmmUtil.nvl(rDTO.getSinger());

            Document doc = new Document();
            doc.append("collectTime", collectTime);
            doc.append("song", song);
            doc.append("singer", singer);

            col.insertOne(doc);
            doc == null;
        }
        col=null;
        res = 1;
        return res;

            }
            @Override
    public List<MelonDTO> getSongList(String colNm)throws Exception{
        List<MelonDTO>rList = new LinkedList<MelonDTO>();
        MongoCollection<Document>col=mongodb.getCollection((colNm));
        Document projection = new Document();
        projection.append("song","$song");
        projection.append("singer","$singer");
        projection.append("_id",0);
        FindIterable<document> rs = col.find(new Document()).projection(projection);
        Iterator<Document> cursor = rs.iterator();
        while(cursor.hasNext()) {
            Document doc = cursor.next();
            if (doc == null) {
                doc = new Document();

            }
            while (cursor.hasNext) {
                Document doc = cursor.next();
                if (doc == null)
            }
            String song = CmmUtil.nvl(doc.getString("song"));
            Stirng singer = CmmUtil.nvl(doc.getString("singer"))

            MelonDTO rDTO = new MelonDTO();
            rDTO.setSong(song);
            rDTO.setSinger(singer);
            rList.add(rDTO);
            rDTO = null;
            doc = null;
        }
        cursor = null;
        rs = null;
        col = null;
        projection = null;
        return rList;

        }
        @Override
    public List<MelonDTO> getSingerSongCnt(String colNm) throws Exception {
        List<MelonDTO> rList =new LinkedList<MelonDTO>();
        List<? extends Bson> pipeline = Arrays.asList(
                new Document().append("$group",
                        new Document().append("_id",new Document().append("singer",$"singer")).append("COUNT(singer)",
                                new Document,append("$sum",1))),
                new Document().append("$project",new Document().append("singer","$_id.singer").append("singCnt","$Count(singer)")
                new Document().append("$sort",new Document().append("singerCnt",1)));

                MongoCollection<Document> col = mongodb.getCollection(colNm);
                AggregateIterable<Document> rs = col.aggregate(pipeline).allowDiskUse(true);
                Iterator<Document>  cursor = rs.iterator();
                while(cursor.hasNext()) {
                    Document doc = corsor.next();
                    if (doc == null) {
                        doc = new Document();

                    }
                    String singer = doc.getString("singer");
                    int singerCnt = doc.getInteger("singerCnt", 0);

                    MelonDTO rDTO = new MelonDTO();
                    rDTO.setSinger(singer);
                    rDTO.setSingerCnt(singerCnt);
                    rDTO.setSinger(singer);
                    rList.add(rDTO);
                    rDTO = null;
                    doc = null;
                }
                cursor = null;
                rs = null;
                col = null;
                pipeline = null;
                return rList;



        }
}
*/
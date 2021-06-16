/*package kopo.jjh.prj.service.impl;

import kopo.jjh.prj.dto.IMelonMapperUsingDTO;
import kopo.jjh.prj.dto.MelonDTO;
import kopo.jjh.prj.mongo.MelonMapper;
import kopo.jjh.prj.service.IMelonServiceUsingDTO;
import kopo.jjh.prj.util.CmmUtil;
import kopo.jjh.prj.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service("MelonServiceUsingDTO")
public class MelonServiceUsingDTO implements IMelonServiceUsingDTO {
    @Resource(name = "MelonMapperUsingDTO")
    private IMelonServiceUsingDTO implements IMelonServiceUsingDTO{
        @Resource(name = "MelonMapperUsingDTO")
                private IMelonMapperUsingDTO melonMapper;

    }
    @Override
    public int collectMelonSong() throws Exception{
        int res = 0;
        List<MelonDTO> pList = new LinkedList<MelonDTO>();
        String url = "https://www.melon.com/chart/index.html";
        Document doc = null;
        doc = Jsoup.connect(url).get();
        Elements element=doc.select("div.service_list_song");
        Iterator<Element> songList = element.select("div.wrap_song_info").iterator();
        while(songList.hasNext()){Element songInfo = songList.next();
        String song = CmmUtil.nvl(songInfo.select("div.ecllipsis.rank01 a").text());
        String singer = CmmUtil.nvl(songInfo.select("div.ellipsis.rank02 a").eq(0).text());
        if((song.length()>0)&&(singer.length()>0)){
            MelonDTO pDTO = new MelonDTO();
            pDTO.setCollectTime(DateUtil.getDateTime("yyyyMMddhhmmss"));
            pDTO.setSong(song);
            pDTO.setSinger(singer);
            pList.add(pDTO);
            pDTO = null;

        }
        doc = null;
        String colNm = "MELON_"+ DateUtil.getDateTime("yyyyMMdd");

        melonMapper.insertSong(pList,colNm);
        return res;
@Override
        public List<MelonDTO> getSongList() this Exception{
String colNm = "MELON_"+DateUtil.getDateTime("yyyyMMdd");
List<MelonDTO> rList = melonMapper.getSongList(colNm);
if(rList==null){rList = new LinkedList<MelonDTO>();

            }
return rList;
    }

}


 */
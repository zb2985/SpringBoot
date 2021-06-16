package kopo.jjh.prj.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="MyDB")
public class MelonSingerDTO {
    @Field("rank")
    private int rank; // 순위
    @Field("singer")
    private String singer; // 가수
    @Field("song_cnt")
    private int song_cnt; // 랭크에 올라간 노래의 수

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getSong_cnt() {
        return song_cnt;
    }

    public void setSong_cnt(int song_cnt) {
        this.song_cnt = song_cnt;
    }

}

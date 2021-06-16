package kopo.jjh.prj.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="MyDB")
public class MelonRankDTO {
    @Field("song")
    private String song; // 노래제목
    @Field("singer")
    private String singer; // 가수
    @Field("current_rank")
    private int current_rank; // 현재 랭킹
    @Field("pre_rank")
    private int pre_rank; // 이전 랭킹
    @Field("change_rank")
    private String change_rank; // 변경된 랭킹차이

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getCurrent_rank() {
        return current_rank;
    }

    public void setCurrent_rank(int current_rank) {
        this.current_rank = current_rank;
    }

    public int getPre_rank() {
        return pre_rank;
    }

    public void setPre_rank(int pre_rank) {
        this.pre_rank = pre_rank;
    }

    public String getChange_rank() {
        return change_rank;
    }

    public void setChange_rank(String change_rank) {
        this.change_rank = change_rank;
    }

}
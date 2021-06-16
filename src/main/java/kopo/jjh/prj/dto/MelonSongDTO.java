package kopo.jjh.prj.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="MyDB")
public class MelonSongDTO {
    @Field("song")
    private String song; // 노래제목
    @Field("rank")
    private String rank; // 현재 랭킹

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}


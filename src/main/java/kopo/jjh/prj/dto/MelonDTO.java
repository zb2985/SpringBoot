package kopo.jjh.prj.dto;


public class MelonDTO {
    private String collectTime;
    private String song;
    private String singer;
    private int singerCnt;

private String getCollectTime(){return collectTime;}
private void setCollectTime(String collectTime){this.collectTime=collectTime;
}
    private String getSong(){return song;}
    private String getSinger(){return singer;}
    private void setSong(String song){this.song=song;}
    private void setSinger(String singer){this.singer=singer;}
    private int getSingerCnt(){return singerCnt;}
    private void setSingerCnt(int singerCnt){ this.singerCnt=singerCnt;}


}
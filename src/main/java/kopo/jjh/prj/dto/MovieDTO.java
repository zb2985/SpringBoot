
package kopo.jjh.prj.dto;


public class MovieDTO {
    private String rank_chkeck_time;
    private String seq;
    private String movie_rank;
    private String move_nm;
    private String movie_reserve;
    private String score;
    private String open_day;
    private String reg_id;
    private String chg_id;
    private String chg_dt;

public String getRank_chkeck_time(){
    return rank_chkeck_time;
}
public String getSeq(){return seq
;}

public String getMovie_rank(){return movie_rank;}

public String getMove_nm(){return move_nm;}

public String getMovie_reserve(){return movie_reserve;}
public String getScore(){return score;     }
public String getOpen_day(){return open_day;}
public String   getReg_id(){return reg_id;}
public String getChg_id(){return chg_id;  }
public String getChg_dt(){return chg_dt;}

    public void setRank_chkeck_time(String yyyyMMdd) {
    }

    public void setMovie_rank(String substring) {
    }

    public void setMovie_nm(String trim) {
    }

    public void setMovie_reserve(String trim) {
    }

    public void setScore(String trim) {
    }

    public void setOpen_day(String trim) {
    }

    public void setReg_id(String admin) {
    }
}



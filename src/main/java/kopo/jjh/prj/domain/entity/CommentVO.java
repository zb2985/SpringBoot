package kopo.jjh.prj.domain.entity;

import java.util.Date;

public class CommentVO {

    private int cno;
    private Long bno;
    private String content;
    private String writer;
    private Date Reg_date;

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public Long getBno() {
        return bno;
    }

    public void setBno(Long bno) {
        this.bno = bno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getReg_Date() {
        return Reg_date;
    }

    public void setReg_Date(Date Reg_date) {
        this.Reg_date = Reg_date;
    }

}


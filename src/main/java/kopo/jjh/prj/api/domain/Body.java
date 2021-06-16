package kopo.jjh.prj.api.domain;

import lombok.Data;

import java.util.List;

@Data
public class Body {
    public List<Item> items;
    public int numOfRows;
    public int pageNo;
    public int totalCount;
}
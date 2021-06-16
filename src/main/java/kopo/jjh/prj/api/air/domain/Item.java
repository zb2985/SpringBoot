package kopo.jjh.prj.api.air.domain;

import lombok.Data;

@Data
public class Item {
    public String airlineNm;
    public String arrAirportNm;
    public String arrPlandTime;
    public String depAirportNm;
    public String depPlandTime;
    public String economyCharge;
    public String prestigeCharge;
    public String vihicleId;
}

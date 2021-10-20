package kopo.jjh.prj.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ConvertInfoDto {
    @NotBlank
    private String receiveCountry;

    @Min(0)
    @Max(10000)
    private double sendAmount;
}
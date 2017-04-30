package com.example.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CityTemperatureInfo {
    private Integer id;
    private String name;
    private TemperatureDetail minimum;
    private TemperatureDetail maximum;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class TemperatureDetail {
        private Double temp;
        private Date date;
    }
}

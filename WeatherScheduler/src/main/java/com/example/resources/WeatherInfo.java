package com.example.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "weatherinfo")
@IdClass(value = WeatherInfo.WeatherInfoId.class)
public class WeatherInfo implements Serializable {
    @Id
    private Integer id;
    private String name;
    private Double temp;
    @Id
    private Long timestamp;

    @Data
    @NoArgsConstructor
    public static class WeatherInfoId implements Serializable {
        private Integer id;
        private Long timestamp;
    }
}

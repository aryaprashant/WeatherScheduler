package com.example.repository;

import com.example.resources.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, WeatherInfo.WeatherInfoId> {

    List<WeatherInfo> findByIdOrderByTimestampAsc(Integer id);

    WeatherInfo findTopByIdOrderByTempAscTimestampAsc(Integer id);

    WeatherInfo findTopByIdOrderByTempDescTimestampDesc(Integer id);
}

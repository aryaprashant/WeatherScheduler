package com.example.service;

import com.example.repository.WeatherInfoRepository;
import com.example.resources.CityTemperatureInfo;
import com.example.resources.WeatherInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties("weather")
@Getter
@Setter
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private String apiKey;
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherInfoRepository weatherInfoRepository;

    @Override
    public WeatherInfo getCurrentWeatherInfo(Integer cityId) {
        ResponseEntity<Map> mapResponseEntity;
        Long timeStamp;
        try {
            timeStamp = Instant.now().toEpochMilli();
            mapResponseEntity = restTemplate.getForEntity(BASE_URL + "?id=" + cityId + "&appid=" + apiKey, Map.class);
        } catch (HttpClientErrorException e) {
            log.error("Invalid cityId=" + cityId);
            return null;
        }
        Map map = mapResponseEntity.getBody();
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setName((String) map.get("name"));
        weatherInfo.setId((Integer) map.get("id"));
        weatherInfo.setTemp((Double) ((Map) map.get("main")).get("temp") - 273.15);
        weatherInfo.setTimestamp(timeStamp); // not using weather api timestamp, as the value doesn't match current time
        return weatherInfo;
    }

    @Override
    public WeatherInfo saveWeatherInfo(Integer id) {
        return weatherInfoRepository.save(getCurrentWeatherInfo(id));
    }

    @Override
    public List<WeatherInfo> getWeatherInfoHistory(Integer id) {
        return weatherInfoRepository.findByIdOrderByTimestampAsc(id);
    }

    @Override
    public CityTemperatureInfo getCityTemperatureInfo(Integer id) {
        WeatherInfo min = weatherInfoRepository.findTopByIdOrderByTempAscTimestampAsc(id);
        WeatherInfo max = weatherInfoRepository.findTopByIdOrderByTempDescTimestampDesc(id);
        CityTemperatureInfo cityTemperatureInfo = new CityTemperatureInfo();
        cityTemperatureInfo.setId(min.getId());
        cityTemperatureInfo.setName(min.getName());
        cityTemperatureInfo.setMinimum(new CityTemperatureInfo.TemperatureDetail(min.getTemp(), new Date(min.getTimestamp())));
        cityTemperatureInfo.setMaximum(new CityTemperatureInfo.TemperatureDetail(max.getTemp(), new Date(max.getTimestamp())));
        return cityTemperatureInfo;
    }
}

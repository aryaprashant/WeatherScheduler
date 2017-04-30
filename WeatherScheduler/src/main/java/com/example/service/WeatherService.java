package com.example.service;

import com.example.resources.CityTemperatureInfo;
import com.example.resources.WeatherInfo;

import java.util.List;

public interface WeatherService {

    WeatherInfo getCurrentWeatherInfo(Integer cityId);

    WeatherInfo saveWeatherInfo(Integer id);

    List<WeatherInfo> getWeatherInfoHistory(Integer id);

    CityTemperatureInfo getCityTemperatureInfo(Integer id);
}

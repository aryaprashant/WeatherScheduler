package com.example.controller;

import com.example.resources.CityTemperatureInfo;
import com.example.resources.WeatherInfo;
import com.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    public static final Integer BANGALORE_ID = 1277333;

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/city/{cityId}/weather", method = RequestMethod.GET)
    public WeatherInfo getWeatherDetails(@PathVariable("cityId") Integer cityId) {
        return weatherService.getCurrentWeatherInfo(cityId);
    }

    @RequestMapping(value = "/weatherhistory", method = RequestMethod.GET)
    public List<WeatherInfo> getWeatherDetails() {
        return weatherService.getWeatherInfoHistory(BANGALORE_ID);
    }

    @RequestMapping(value = "/weatherinfo", method = RequestMethod.GET)
    public CityTemperatureInfo getCityTemperature() {
        return weatherService.getCityTemperatureInfo(BANGALORE_ID);
    }
}

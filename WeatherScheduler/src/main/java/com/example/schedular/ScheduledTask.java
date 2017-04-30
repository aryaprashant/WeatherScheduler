package com.example.schedular;

import com.example.controller.WeatherController;
import com.example.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private WeatherService weatherService;

    @Scheduled(fixedRate = 10000)
    public void getCurrentWeatherDetails() {
        log.info("Running scheduled task at " + new Date());
        weatherService.saveWeatherInfo(WeatherController.BANGALORE_ID);
    }
}

package com.emerson.demo.openweatherapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "open-weather-api")
@Data
public class OpenWeatherApiConfig {

    private String appid;
    private String defaultUnits;

}

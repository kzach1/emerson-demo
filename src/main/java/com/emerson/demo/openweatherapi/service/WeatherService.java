package com.emerson.demo.openweatherapi.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import com.emerson.demo.openweatherapi.client.OpenWeatherAPI;
import com.emerson.demo.openweatherapi.config.OpenWeatherApiConfig;
import com.emerson.demo.openweatherapi.domain.Weather;
import com.emerson.demo.openweatherapi.factories.WeatherDescriptionFactory;
import com.google.common.base.Preconditions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(OpenWeatherApiConfig.class)
public class WeatherService {

    private final OpenWeatherAPI weatherAPI;
    private final OpenWeatherApiConfig apiConfig;

    public String getCurrentWeather(Double lat, Double lon) {
        Preconditions.checkArgument(lat != null && lat >= -90.0 && lat <= 90.0, "Invalid lat parameters: " + lat);
        Preconditions.checkArgument(lon != null && lon >= -180.0 && lon <= 180.0, "Invalid lon parameters: " + lon);
        Weather currentWeather = weatherAPI.currentWeather(lat, lon, apiConfig.getAppid(), apiConfig.getDefaultUnits());
        return WeatherDescriptionFactory.buildWeatherDescription(currentWeather);
    }

}

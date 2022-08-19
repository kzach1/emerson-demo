package com.emerson.demo.openweatherapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.emerson.demo.openweatherapi.domain.Weather;

@FeignClient(
        name = "${open-weather-api.weatherapi.name}",
        url = "${open-weather-api.weatherapi.url}"
)
public interface OpenWeatherAPI {

    @GetMapping(value = "/weather")
    Weather currentWeather(@RequestParam Double lat, @RequestParam Double lon, @RequestParam String appid,
            @RequestParam String units);

}

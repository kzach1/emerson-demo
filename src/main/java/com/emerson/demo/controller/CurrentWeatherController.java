package com.emerson.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.demo.openweatherapi.domain.Coordinates;
import com.emerson.demo.openweatherapi.service.GeocodingService;
import com.emerson.demo.openweatherapi.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class CurrentWeatherController {

    private static final Logger logger = LoggerFactory.getLogger(CurrentWeatherController.class);

    private final GeocodingService geocodingService;
    private final WeatherService weatherService;

    @GetMapping
    ResponseEntity<String> currentWeather(@RequestParam(required = false) String city,
            @RequestParam(required = false) String state, @RequestParam(required = false) String country,
            @RequestParam(required = false) String zipCode, @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {

        Coordinates loc;
        if (lat != null && lon != null) {
            loc = new Coordinates(lat, lon);
        } else if (StringUtils.isNotBlank(zipCode)) {
            loc = geocodingService.getCoordinates(zipCode);
        } else if (StringUtils.isNotBlank(city)) {
            loc = geocodingService.getCoordinates(city, state, country);
        } else {
            return ResponseEntity.badRequest().body("At least one of latitude/longitude, zip code or city must be provided");
        }
        return ResponseEntity.ok(weatherService.getCurrentWeather(loc.getLat(), loc.getLon()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericExceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}

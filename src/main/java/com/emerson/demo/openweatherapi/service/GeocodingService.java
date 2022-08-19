package com.emerson.demo.openweatherapi.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.emerson.demo.openweatherapi.client.GeocodingAPI;
import com.emerson.demo.openweatherapi.config.OpenWeatherApiConfig;
import com.emerson.demo.openweatherapi.domain.Coordinates;
import com.emerson.demo.openweatherapi.domain.GeocodingDirectResponseDto;
import com.emerson.demo.openweatherapi.domain.GeocodingZipResponseDto;
import com.google.common.base.Preconditions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(OpenWeatherApiConfig.class)
public class GeocodingService {

    private static final Logger logger = LoggerFactory.getLogger(GeocodingService.class);

    private final Integer LIMIT = 1;

    private final GeocodingAPI geocodingAPI;
    private final OpenWeatherApiConfig apiConfig;

    public Coordinates getCoordinates(String city, String state, String country) {
        Preconditions.checkArgument(StringUtils.isNotBlank(city), "City must be provided for geocoding services.");
        StringBuilder sb = new StringBuilder(city);
        if (StringUtils.isNotBlank(state)) {
            sb.append(", ").append(city);
        }
        if (StringUtils.isNotBlank(country)) {
            sb.append(", ").append(country);
        }
        String location = sb.toString();
        List<GeocodingDirectResponseDto> response =
                geocodingAPI.directCoordinates(location, apiConfig.getAppid(), LIMIT);
        GeocodingDirectResponseDto geocodingResponse = response.get(0);

        Preconditions.checkArgument(geocodingResponse.getLat() != null && geocodingResponse.getLon() != null,
                "Unable to find location for city/state/country " + city + "/" + state + "/" + country);
        return Coordinates.builder()
                .lat(geocodingResponse.getLat())
                .lon(geocodingResponse.getLon())
                .build();
    }

    public Coordinates getCoordinates(String zipCode) {
        Preconditions.checkArgument(StringUtils.isNotBlank(zipCode), "Must provide valid zip code.");
        GeocodingZipResponseDto geocodingResponse = geocodingAPI.zipcodeCoordinates(zipCode, apiConfig.getAppid());
        Preconditions.checkArgument(geocodingResponse.getLat() != null && geocodingResponse.getLon() != null,
                "Unable to find location for zip code: " + zipCode);
        return Coordinates.builder()
                .lat(geocodingResponse.getLat())
                .lon(geocodingResponse.getLon())
                .build();
    }
}

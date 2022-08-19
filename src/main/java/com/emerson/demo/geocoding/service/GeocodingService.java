package com.emerson.demo.geocoding.service;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.emerson.demo.geocoding.client.GeocodingAPI;
import com.emerson.demo.geocoding.domain.Coordinates;
import com.emerson.demo.geocoding.domain.GeocodingDirectResponseDto;
import com.emerson.demo.geocoding.domain.GeocodingZipResponseDto;
import com.google.common.base.Preconditions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeocodingService {

    @Value("${open-weather-api.appid")
    private String appid;

    private final Integer LIMIT = 1;

    private final GeocodingAPI geocodingAPI;

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
        List<GeocodingDirectResponseDto> response = geocodingAPI.getGeoCoordinates(location, appid, LIMIT);
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
        GeocodingZipResponseDto geocodingResponse = geocodingAPI.getGeoCoordinates(zipCode, appid);
        Preconditions.checkArgument(geocodingResponse.getLat() != null && geocodingResponse.getLon() != null,
                "Unable to find location for zip code: " + zipCode);
        return Coordinates.builder()
                .lat(geocodingResponse.getLat())
                .lon(geocodingResponse.getLon())
                .build();
    }
}

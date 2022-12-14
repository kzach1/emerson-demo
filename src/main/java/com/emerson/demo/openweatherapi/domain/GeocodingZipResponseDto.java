package com.emerson.demo.openweatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingZipResponseDto {

    String zip;
    String name;
    Double lat;
    Double lon;
    String country;

}

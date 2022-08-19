package com.emerson.demo.openweatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDescription {

    private Integer id;
    private String main;
    private String description;
    private String icon;

}

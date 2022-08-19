package com.emerson.demo.openweatherapi.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    private List<WeatherDescription> weather;
    private WeatherMain main;
    private Coordinates coord;

}

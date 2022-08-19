package com.emerson.demo.openweatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMain {

    private Float temp;
    private Float feelsLike;
    private Float tempMin;
    private Float tempMax;
    private Float pressure;
    private Float humidity;

}

package com.emerson.demo.openweatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Coordinates {

    Double lat;
    Double lon;

}

package com.emerson.demo.openweatherapi.domain;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingDirectResponseDto {

   String name;
   Map<String, String> localNames;
   Double lat;
   Double lon;
   String country;
   String state;

}

package com.emerson.demo.openweatherapi.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.emerson.demo.openweatherapi.domain.GeocodingDirectResponseDto;
import com.emerson.demo.openweatherapi.domain.GeocodingZipResponseDto;

@FeignClient(
        name = "${open-weather-api.geocoding.name}",
        url = "${open-weather-api.geocoding.url}"
)
public interface GeocodingAPI {

    @GetMapping(value = "/direct")
    List<GeocodingDirectResponseDto> directCoordinates(@RequestParam String q, @RequestParam String appid,
            @RequestParam(required = false) Integer limit);

    @GetMapping(value = "/zip")
    GeocodingZipResponseDto zipcodeCoordinates(@RequestParam String zip, @RequestParam String appid);

}

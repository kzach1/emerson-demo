package com.emerson.demo.geocoding.client;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.emerson.demo.geocoding.domain.GeocodingDirectResponseDto;
import com.emerson.demo.geocoding.domain.GeocodingZipResponseDto;

@FeignClient(
        name = "${open-weather-api.geocoding.name}",
        url = "${open-weather-api.geocoding.url}"
)
public interface GeocodingAPI {

    @GetMapping(value = "/direct")
    List<GeocodingDirectResponseDto> getGeoCoordinates(@RequestParam String q, @RequestParam String appid,
            @RequestParam(required = false) Integer limit);

    @GetMapping(value = "/zip")
    GeocodingZipResponseDto getGeoCoordinates(@RequestParam String zip, @RequestParam String appid);

}

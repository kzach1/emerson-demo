package com.emerson.demo.openweatherapi.factories;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.emerson.demo.openweatherapi.domain.Weather;
import com.emerson.demo.openweatherapi.domain.WeatherDescription;

public class WeatherDescriptionFactory {

    public static String buildWeatherDescription(Weather currentWeather) {

        Float currentTemperature = currentWeather.getMain().getTemp();
        Float feelsLike = currentWeather.getMain().getFeelsLike();
        List<WeatherDescription> weatherDescriptions = currentWeather.getWeather();
        StringBuilder sb = new StringBuilder();

        if (currentTemperature != null) {
            sb.append("Current temperature: ").append(currentTemperature.intValue()).append(" degrees F. ");
        }
        if (CollectionUtils.isNotEmpty(weatherDescriptions)) {
            List<String> descriptions = currentWeather.getWeather().stream()
                    .map(WeatherDescription::getDescription)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            descriptions.forEach(d -> sb.append(StringUtils.capitalize(d)).append(". "));
        }
        if (feelsLike != null) {
            sb.append("Feels like ").append(feelsLike.intValue()).append(" degrees F.");
        }
        return sb.toString();
    }

}

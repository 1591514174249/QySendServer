package org.lxc.qysendserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WeatherConfig {
    @Value("${weather.key}")
    private String weatherKey;
    @Value("${weather.city}")
    private String weatherCity;
    @Value("${weather.extensions}")
    private String weatherExtensions;
}

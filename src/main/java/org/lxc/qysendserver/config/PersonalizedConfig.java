package org.lxc.qysendserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PersonalizedConfig {
    @Value("${personalized.push.enable-greeting:true}")
    private boolean enableGreeting;

    @Value("${personalized.push.enable-weather-tip:true}")
    private boolean enableWeatherTip;

    @Value("${personalized.push.enable-clothing-suggestion:true}")
    private boolean enableClothingSuggestion;

    @Value("${personalized.push.enable-daily-message:true}")
    private boolean enableDailyMessage;

    @Value("${personalized.push.cron}")
    private String cron;

    @Value("${personalized.push.city}")
    private String city;

    @Value("${personalized.push.daily-message:愿你的每一天都充满阳光和希望！}")
    private String dailyMessage;
}

package org.lxc.qysendserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PersonalizedConfig {
    // 推送内容配置
    @Value("${personalized.push.enable-greeting: true}")
    private boolean enableGreeting;
    
    @Value("${personalized.push.enable-weather-tip: true}")
    private boolean enableWeatherTip;
    
    @Value("${personalized.push.enable-clothing-suggestion: true}")
    private boolean enableClothingSuggestion;
    
    @Value("${personalized.push.enable-daily-message: true}")
    private boolean enableDailyMessage;
    
    // 推送时间配置
    @Value("${personalized.push.cron: 0 40 7 * * *}")
    private String pushCron;
    
    // 城市配置
    @Value("${personalized.push.city: 320100}")
    private String city;
    
    // 个性化消息
    @Value("${personalized.push.daily-message: 愿你的每一天都充满阳光和希望！}")
    private String dailyMessage;
}
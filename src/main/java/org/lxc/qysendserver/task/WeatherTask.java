package org.lxc.qysendserver.task;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.lxc.qysendserver.config.WeComBotConfig;
import org.lxc.qysendserver.config.WeatherConfig;
import org.lxc.qysendserver.config.PersonalizedConfig;
import org.lxc.qysendserver.entity.WeatherEntity;
import org.lxc.qysendserver.service.WeComBotService;
import org.lxc.qysendserver.util.HttpClientUtil;
import org.lxc.qysendserver.util.WeatherGreetingUtil;
import org.lxc.qysendserver.util.WeatherIconUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class WeatherTask {
    @Autowired
    private WeatherConfig weatherConfig;
    @Autowired
    private WeComBotConfig weComBotConfig;
    @Autowired
    private PersonalizedConfig personalizedConfig;
    @Autowired
    private HttpClientUtil httpClientUtil;
    @Autowired
    private WeComBotService weComBotService;


    @Scheduled(cron = "${personalized.push.cron}", zone = "Asia/Shanghai")
    public void taskWeather() {
        try {
            // 获取天气信息
            WeatherEntity weather = getWeatherInfo();
            
            // 生成Markdown格式内容
            String markdownContent = generateMarkdownContent(weather);
            
            // 使用Markdown格式发送
            weComBotService.sendMarkdownMessage(markdownContent);
        } catch (Exception e) {
            throw new RuntimeException("天气推送失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 调用WeatherScript服务获取天气信息
     */
    private WeatherEntity getWeatherInfo() throws Exception {
        String weatherScriptUrl = "http://localhost:9001/weather/info";
        JSONObject weatherReq = new JSONObject();
        weatherReq.put("key", weatherConfig.getWeatherKey());
        weatherReq.put("city", personalizedConfig.getCity());
        weatherReq.put("extensions", weatherConfig.getWeatherExtensions());
        
        String weatherStr = httpClientUtil.postJson(weatherScriptUrl, weatherReq, null);
        return JSON.parseObject(weatherStr, WeatherEntity.class);
    }
    
    /**
     * 生成Markdown格式的天气报告内容
     */
    private String generateMarkdownContent(WeatherEntity weather) {
        StringBuilder markdownContent = new StringBuilder();
        markdownContent.append("### 🌤️ 今日天气报告\n\n");
        
        // 添加问候语
        if (personalizedConfig.isEnableGreeting()) {
            String greeting = WeatherGreetingUtil.getGreeting();
            markdownContent.append("**").append(greeting).append("**\n\n");
        }
        
        // 添加天气信息
        String temperatureEmoji = WeatherIconUtil.getTemperatureEmoji(weather.getTemperature());
        markdownContent.append("**城市**: " + weather.getCity() + "\n");
        markdownContent.append("**天气**: " + weather.getWeatherIcon() + " " + weather.getWeather() + "\n");
        markdownContent.append("**温度**: " + temperatureEmoji + " " + weather.getTemperature() + "°C\n");
        markdownContent.append("**风向**: " + weather.getWinddirection() + "\n");
        markdownContent.append("**风速**: " + weather.getWindpower() + "\n");
        markdownContent.append("**湿度**: " + weather.getHumidity() + "%\n");
        markdownContent.append("**更新时间**: " + weather.getReporttime() + "\n\n");
        
        markdownContent.append("---\n");
        
        // 添加天气小提示
        if (personalizedConfig.isEnableWeatherTip()) {
            String weatherTip = WeatherGreetingUtil.getWeatherTip(weather.getWeather(), weather.getTemperature());
            markdownContent.append("💡 天气小提示：" + weatherTip + "\n");
        }
        
        // 添加穿衣建议
        if (personalizedConfig.isEnableClothingSuggestion()) {
            String clothingSuggestion = WeatherGreetingUtil.getClothingSuggestion(weather.getTemperature());
            markdownContent.append("👕 穿衣建议：" + clothingSuggestion + "\n");
        }
        
        // 添加每日寄语
        if (personalizedConfig.isEnableDailyMessage()) {
            markdownContent.append("\n🌟 今日寄语：" + personalizedConfig.getDailyMessage());
        }
        
        return markdownContent.toString();
    }
}

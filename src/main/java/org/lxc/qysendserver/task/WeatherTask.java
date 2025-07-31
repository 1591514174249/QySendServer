package org.lxc.qysendserver.task;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lxc.qysendserver.config.WeComBotConfig;
import org.lxc.qysendserver.config.WeatherConfig;
import org.lxc.qysendserver.entity.WeatherEntity;
import org.lxc.qysendserver.util.HttpClientUtil;
import org.lxc.qysendserver.vo.WeatherRsp;
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
    private HttpClientUtil httpClientUtil;


    @Scheduled(cron = "0 40 7 * * *")
    public void taskWeather() {
        try {
            String urlWeather = "https://restapi.amap.com/v3/weather/weatherInfo?Key=" + weatherConfig.getWeatherKey() + "&city=" + weatherConfig.getWeatherCity() + "&extensions=" + weatherConfig.getWeatherExtensions();
            String urlQy = weComBotConfig.getSendUrl();
            WeatherEntity weather;
            WeatherRsp weatherRsp;
            String weatherStr = httpClientUtil.get(urlWeather, null, null);
            weatherRsp = JSON.parseObject(weatherStr, WeatherRsp.class);
            weather = weatherRsp.getLives()[0];
            String str2Qy = weather.toString();
            // 使用正则表达式替换
            String formatted = str2Qy
                    .replaceAll("WeatherEntity\\{", "")
                    .replaceAll("}", "")
                    .replaceAll("([^=,]+)='([^']*)'", "$1：$2")
                    .replaceAll("([^=,]+)=([^,]*)", "$1：$2")
                    .replaceAll(", ", "\n");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", formatted);
            httpClientUtil.postJson(urlQy, jsonObject, null);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

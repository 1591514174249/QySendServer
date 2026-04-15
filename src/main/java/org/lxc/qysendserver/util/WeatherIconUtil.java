package org.lxc.qysendserver.util;

import java.util.HashMap;
import java.util.Map;

public class WeatherIconUtil {
    private static final Map<String, String> WEATHER_ICON_MAP = new HashMap<>();

    static {
        // 初始化天气图标映射
        WEATHER_ICON_MAP.put("晴", "☀️");
        WEATHER_ICON_MAP.put("多云", "⛅");
        WEATHER_ICON_MAP.put("阴", "☁️");
        WEATHER_ICON_MAP.put("小雨", "🌦️");
        WEATHER_ICON_MAP.put("中雨", "🌧️");
        WEATHER_ICON_MAP.put("大雨", "⛈️");
        WEATHER_ICON_MAP.put("雷阵雨", "⚡");
        WEATHER_ICON_MAP.put("阵雨", "🌦️");
        WEATHER_ICON_MAP.put("雪", "❄️");
        WEATHER_ICON_MAP.put("雾", "🌫️");
        WEATHER_ICON_MAP.put("霾", "😷");
        WEATHER_ICON_MAP.put("沙尘暴", "🌪️");
    }

    /**
     * 根据天气类型获取对应的天气图标
     * @param weather 天气类型
     * @return 天气图标
     */
    public static String getWeatherIcon(String weather) {
        return WEATHER_ICON_MAP.getOrDefault(weather, "🌤️");
    }

    /**
     * 根据温度获取温度对应的表情
     * @param temperature 温度
     * @return 温度表情
     */
    public static String getTemperatureEmoji(int temperature) {
        if (temperature >= 35) {
            return "🥵";
        } else if (temperature >= 28) {
            return "☀️";
        } else if (temperature >= 20) {
            return "😊";
        } else if (temperature >= 10) {
            return "😐";
        } else if (temperature >= 0) {
            return "🥶";
        } else {
            return "❄️";
        }
    }
}
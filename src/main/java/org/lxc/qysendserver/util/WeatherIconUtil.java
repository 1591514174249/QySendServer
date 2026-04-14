package org.lxc.qysendserver.util;

public class WeatherIconUtil {

    private static final String SUNNY = "☀️";
    private static final String CLOUDY = "☁️";
    private static final String OVERCAST = "☁️";
    private static final String RAINY = "🌧️";
    private static final String THUNDER = "⛈️";
    private static final String SNOWY = "❄️";
    private static final String FOGGY = "🌫️";
    private static final String WINDY = "💨";
    private static final String UNKNOWN = "🌤️";

    /**
     * 根据天气描述获取对应的表情图标
     */
    public static String getWeatherIcon(String weather) {
        if (weather == null || weather.isEmpty()) {
            return UNKNOWN;
        }

        switch (weather) {
            case "晴":
                return SUNNY;
            case "多云":
            case "阴":
                return CLOUDY;
            case "小雨":
            case "中雨":
            case "大雨":
            case "阵雨":
                return RAINY;
            case "雷阵雨":
            case "雷阵雨并伴有冰雹":
                return THUNDER;
            case "小雪":
            case "中雪":
            case "大雪":
            case "阵雪":
                return SNOWY;
            case "雾":
            case "霾":
            case "沙尘暴":
            case "浮尘":
            case "扬沙":
                return FOGGY;
            case "雾霾":
                return FOGGY;
            case "大风":
            case "飓风":
            case "龙卷风":
                return WINDY;
            default:
                return UNKNOWN;
        }
    }

    /**
     * 根据温度获取温度表情图标
     */
    public static String getTemperatureEmoji(Integer temperature) {
        if (temperature == null) {
            return "🌡️";
        }

        if (temperature <= -10) {
            return "🥶";
        } else if (temperature > -10 && temperature < 5) {
            return "❄️";
        } else if (temperature >= 5 && temperature < 15) {
            return "☁️";
        } else if (temperature >= 15 && temperature < 25) {
            return "🌤️";
        } else if (temperature >= 25 && temperature < 32) {
            return "☀️";
        } else {
            return "🔥";
        }
    }
}

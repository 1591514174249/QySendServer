package org.lxc.qysendserver.util;

import java.util.Calendar;

public class WeatherGreetingUtil {

    /**
     * 根据当前时间获取问候语
     */
    public static String getGreeting() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 12) {
            return "早上好呀～";
        } else if (hour >= 12 && hour < 14) {
            return "中午好呀～";
        } else if (hour >= 14 && hour < 18) {
            return "下午好呀～";
        } else if (hour >= 18 && hour < 22) {
            return "晚上好呀～";
        } else {
            return "夜深了，早点休息哦～";
        }
    }

    /**
     * 根据天气和温度获取天气小提示
     */
    public static String getWeatherTip(String weather, Integer temperature) {
        if (weather == null) {
            return "注意天气变化，合理安排出行。";
        }

        String tip;
        if (weather.contains("雨")) {
            tip = "今天有雨，记得带伞哦～";
        } else if (weather.contains("雪")) {
            tip = "今天有雪，注意防滑保暖～";
        } else if (weather.contains("雾") || weather.contains("霾")) {
            tip = "今天有雾，出行注意安全～";
        } else if (weather.contains("晴") && temperature != null && temperature > 30) {
            tip = "今天晴天且温度较高，注意防暑～";
        } else if (weather.contains("晴") && temperature != null && temperature < 10) {
            tip = "今天晴天但温度较低，注意保暖～";
        } else if (weather.contains("阴")) {
            tip = "今天阴天，心情也要保持明亮哦～";
        } else {
            tip = "注意天气变化，合理安排出行。";
        }

        return tip;
    }

    /**
     * 根据温度获取穿衣建议
     */
    public static String getClothingSuggestion(Integer temperature) {
        if (temperature == null) {
            return "根据天气情况适时增减衣物。";
        }

        if (temperature < 0) {
            return "天气寒冷，建议穿着羽绒服、棉衣等厚外套，佩戴围巾手套。";
        } else if (temperature >= 0 && temperature < 10) {
            return "天气较凉，建议穿着大衣、夹克、毛衣等保暖衣物。";
        } else if (temperature >= 10 && temperature < 20) {
            return "天气温和，建议穿着薄外套、卫衣、长袖衬衫等。";
        } else if (temperature >= 20 && temperature < 28) {
            return "天气温暖，建议穿着衬衫、薄T恤、牛仔裤等。";
        } else if (temperature >= 28 && temperature < 33) {
            return "天气较热，建议穿着短袖、短裤、薄裙等清凉衣物。";
        } else {
            return "天气炎热，注意防暑，多补充水分。";
        }
    }
}

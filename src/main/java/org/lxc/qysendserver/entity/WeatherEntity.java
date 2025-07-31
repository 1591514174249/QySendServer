package org.lxc.qysendserver.entity;

import lombok.Data;

@Data
public class WeatherEntity {
    private String province;
    private String city;
    private String adcode;
    private String weather;
    private Integer temperature;
    private String winddirection;
    private String windpower;
    private String humidity;
    private String reporttime;
    private String temperature_float;
    private String humidity_float;


    @Override
    public String toString() {
        return "WeatherEntity{" +
                "省份='" + province + '\'' +
                ", 城市='" + city + '\'' +
                ", 天气='" + weather + '\'' +
                ", 温度=" + temperature +
                ", 风向='" + winddirection + '\'' +
                ", 风速='" + windpower + '\'' +
                ", 湿度='" + humidity + '\'' +
                ", 时间='" + reporttime + '\'' +
                '}';
    }
}

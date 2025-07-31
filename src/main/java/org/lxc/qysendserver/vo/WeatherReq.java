package org.lxc.qysendserver.vo;

import lombok.Data;

@Data
public class WeatherReq {
    private String key;
    private String city;
    private String extensions;
}

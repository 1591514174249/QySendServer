package org.lxc.qysendserver.vo;

import lombok.Data;
import org.lxc.qysendserver.entity.WeatherEntity;

@Data
public class WeatherRsp {
    private String status;
    private String count;
    private String info;
    private String infocode;
    private WeatherEntity[] lives;
}

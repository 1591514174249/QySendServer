package org.lxc.qysendserver.task;

import cn.hutool.core.date.ChineseDate;
import com.alibaba.fastjson2.JSONObject;
import org.lxc.qysendserver.config.WeComBotConfig;
import org.lxc.qysendserver.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@Component
public class CommemorationTask {
    @Autowired
    private WeComBotConfig weComBotConfig;
    @Autowired
    private HttpClientUtil httpClientUtil;


    @Scheduled(cron = "0 0 9 * * 1")
    public void taskCommemoration() {
        try {
            String urlQy = weComBotConfig.getSendUrl();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = sdf.format(new Date());
            ChineseDate lunarDate = new ChineseDate(new Date());
            String formatted = "阳历日期: " + nowDate + "\n"
                    + "农历日期: " + lunarDate + "\n"
                    + "她的生日: 十一月二十 \n"
                    + "我的生日: 六月初八\n"
                    + "爸爸生日: \n"
                    + "妈妈生日: \n"
                    + "恋爱纪念日: 十月七日(2016) \n"
                    + "结婚纪念日: 五月一日(2025) \n";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", formatted);
            httpClientUtil.postJson(urlQy, jsonObject, null);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

package org.lxc.qysendserver.config;

import lombok.Data;
import org.lxc.qysendserver.service.WeComBotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Data
public class WeComBotConfig {
    @Value("${wecom.bot.webhook-url}")
    private String webhookUrl;

    @Value("${wecom.send-url}")
    private String sendUrl;
}

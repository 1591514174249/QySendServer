package org.lxc.qysendserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.lxc.qysendserver.config.WeComBotConfig;
import org.lxc.qysendserver.service.WeComBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class WeComBotServiceImpl implements WeComBotService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WeComBotConfig config;

    /**
     * 发送文本消息
     * @param content 消息内容
     * @param mentionedList 需要@的成员列表
     * @param mentionedMobileList 需要@的成员手机号列表
     * @return 发送结果
     */
    public Map<String, Object> sendTextMessage(String content,
                                               String[] mentionedList,
                                               String[] mentionedMobileList) {
        Map<String, Object> message = new HashMap<>();
        message.put("msgtype", "text");

        Map<String, Object> text = new HashMap<>();
        text.put("content", content);
        if (mentionedList != null && mentionedList.length > 0) {
            text.put("mentioned_list", mentionedList);
        }
        if (mentionedMobileList != null && mentionedMobileList.length > 0) {
            text.put("mentioned_mobile_list", mentionedMobileList);
        }
        message.put("text", text);

        return sendMessage(message);
    }

    /**
     * 发送Markdown消息
     * @param content Markdown格式内容
     * @return 发送结果
     */
    public Map<String, Object> sendMarkdownMessage(String content) {
        Map<String, Object> message = new HashMap<>();
        message.put("msgtype", "markdown");

        Map<String, String> markdown = new HashMap<>();
        markdown.put("content", content);
        message.put("markdown", markdown);

        return sendMessage(message);
    }

    /**
     * 发送图片消息
     * @param base64Content 图片内容的base64编码
     * @param md5 图片内容的md5值
     * @return 发送结果
     */
    public Map<String, Object> sendImageMessage(String base64Content, String md5) {
        Map<String, Object> message = new HashMap<>();
        message.put("msgtype", "image");

        Map<String, String> image = new HashMap<>();
        image.put("base64", base64Content);
        image.put("md5", md5);
        message.put("image", image);

        return sendMessage(message);
    }

    private Map<String, Object> sendMessage(Map<String, Object> message) {
        try {
            String requestBody = objectMapper.writeValueAsString(message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            return restTemplate.postForObject(config.getWebhookUrl(), entity, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize message", e);
        }
    }
}

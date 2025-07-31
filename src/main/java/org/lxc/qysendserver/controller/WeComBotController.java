package org.lxc.qysendserver.controller;

import org.lxc.qysendserver.service.WeComBotService;
import org.lxc.qysendserver.vo.ImageMessageRequest;
import org.lxc.qysendserver.vo.MarkdownMessageRequest;
import org.lxc.qysendserver.vo.TextMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/wecom-bot")
public class WeComBotController {
    @Autowired
    private WeComBotService botService;

    @Autowired
    public WeComBotController(WeComBotService botService) {
        this.botService = botService;
    }

    @PostMapping("/send-text")
    public Map<String, Object> sendTextMessage(@RequestBody TextMessageRequest request) {
        return botService.sendTextMessage(
                request.getContent(),
                request.getMentionedList(),
                request.getMentionedMobileList()
        );
    }

    @PostMapping("/send-markdown")
    public Map<String, Object> sendMarkdownMessage(@RequestBody MarkdownMessageRequest request) {
        return botService.sendMarkdownMessage(request.getContent());
    }

    @PostMapping("/send-image")
    public Map<String, Object> sendImageMessage(@RequestBody ImageMessageRequest request) {
        return botService.sendImageMessage(request.getBase64Content(), request.getMd5());
    }
}

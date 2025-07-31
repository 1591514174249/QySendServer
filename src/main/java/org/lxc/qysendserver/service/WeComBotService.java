package org.lxc.qysendserver.service;

import java.util.Map;

public interface WeComBotService {
    Map<String, Object> sendTextMessage(String content, String[] mentionedList, String[] mentionedMobileList);
    Map<String, Object> sendMarkdownMessage(String content);
    Map<String, Object> sendImageMessage(String base64Content, String md5);
}

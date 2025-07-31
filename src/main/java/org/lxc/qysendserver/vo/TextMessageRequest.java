package org.lxc.qysendserver.vo;

import lombok.Data;

@Data
public class TextMessageRequest {
    private String content;
    private String[] mentionedList;
    private String[] mentionedMobileList;
}

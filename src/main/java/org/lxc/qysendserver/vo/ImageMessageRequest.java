package org.lxc.qysendserver.vo;

import lombok.Data;

@Data
public class ImageMessageRequest {
    private String base64Content;
    private String md5;
}

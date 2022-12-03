package com.pastebin.pojo;

import com.pastebin.enums.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PasteResponse {

    private Response response;
    private Paste content;
    private long responseTime;
    
}

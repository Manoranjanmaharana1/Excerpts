package com.pastebin.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Paste {
    

    private String uniqueId;
    private String description;
    private String title;
    private long lmd;
    private String content;
}

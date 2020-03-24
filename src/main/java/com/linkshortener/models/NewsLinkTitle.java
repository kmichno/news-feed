package com.linkshortener.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class NewsLinkTitle implements Serializable {

    @Id
    private String id;

    private String title;

    private String content;

    public NewsLinkTitle(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

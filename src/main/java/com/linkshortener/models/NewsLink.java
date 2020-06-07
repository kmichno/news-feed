package com.linkshortener.models;

import com.linkshortener.api.LinkObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class NewsLink implements Serializable {

    @Id
    private String id;

    private String title;

    private String shortUrl;

    private String longUrl;

    private int numberAllEntries = 0;

    public NewsLink(String id, String shortUrl, String longUrl) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public NewsLink(String id, String title, LinkObject link) {
        this.id = id;
        this.title = title;
        this.shortUrl = link.getShortUrl();
        this.longUrl = link.getLongUrl();
        this.numberAllEntries = link.getNumberAllEntries();
    }

}

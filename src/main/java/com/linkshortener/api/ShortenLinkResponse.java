package com.linkshortener.api;

import com.linkshortener.models.NewsLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ShortenLinkResponse {

    private NewsLink link;
}

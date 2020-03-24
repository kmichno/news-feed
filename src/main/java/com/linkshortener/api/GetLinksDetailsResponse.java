package com.linkshortener.api;

import com.linkshortener.models.NewsLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GetLinksDetailsResponse {

    public List<NewsLink> links;
}

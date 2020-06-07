package com.linkshortener.controllers;

import com.linkshortener.api.*;
import com.linkshortener.models.LinkList;
import com.linkshortener.models.NewsLink;
import com.linkshortener.models.NewsLinkTitle;
import com.linkshortener.repositories.NewsLinkTitleRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class LinkController {

    private RestTemplate restTemplate = new RestTemplate();

    private final String LINK_SHORTENER_SERVER = "http://localhost:8080/";

    private final String SHORTEN_LINK_SERVICE = "link/short";

    @Autowired
    private NewsLinkTitleRepository newsLinkTitleRepository;

    @GetMapping("/links")
    public GetLinksDetailsResponse getLinks() {
        String fooResourceUrl
                = "http://localhost:8080/links";
        ResponseEntity<LinkList> linkListResponse = restTemplate.getForEntity(fooResourceUrl, LinkList.class);
        List<LinkObject> links = linkListResponse.getBody().getLinks();
        Map<String, LinkObject> linksMap = links.stream().collect(Collectors.toMap(LinkObject::getId, link->link));
        List<NewsLinkTitle> newsLinkTitleList = newsLinkTitleRepository.findAll();

        List<NewsLink> newsLinks = newsLinkTitleList.stream().filter(x-> linksMap.containsKey(x.getId())).map(x-> new NewsLink(x.getId(), x.getTitle(), linksMap.get(x.getId()))).collect(Collectors.toList());
        return new GetLinksDetailsResponse(newsLinks);
    }

    @GetMapping("/link/{id}")
    public NewsLinkTitle getLinkById(@PathVariable("id") String id) throws Exception{
        return newsLinkTitleRepository.findById(id).orElseThrow(Exception::new);
    }

    @PostMapping("/link/edit/{id}")
    public void editLinkById(@PathVariable("id") String id, @RequestBody EditLinkRequest editLinkRequest) throws Exception{
        NewsLinkTitle newsLinkTitle = new NewsLinkTitle(id, editLinkRequest.getTitle());
        newsLinkTitleRepository.save(newsLinkTitle);
    }

    @PostMapping("/link/delete/{id}")
    public void deleteLinkById(@PathVariable("id") String id) {
        newsLinkTitleRepository.deleteById(id);
        String fooResourceUrl
                = "http://localhost:8080/link/delete/"+id;
        restTemplate.delete(fooResourceUrl);
    }

    @PostMapping("link/short")
    public ShortenLinkResponse shortenLink(@RequestBody ShortenLinkExtendedRequest shortenLinkExtendedRequest) {
        val shortenLinkRequest = new ShortenLinkRequest(shortenLinkExtendedRequest.getLongUrl());
        HttpEntity<ShortenLinkRequest> request = new HttpEntity<>(shortenLinkRequest);
        LinkObject link = restTemplate.postForObject(LINK_SHORTENER_SERVER + SHORTEN_LINK_SERVICE, request, LinkObject.class);

        NewsLinkTitle newsLinkTitle = new NewsLinkTitle(link.getId(), shortenLinkExtendedRequest.getTitle());
        newsLinkTitleRepository.save(newsLinkTitle);
        return prepareShortenLinkResponse(link, newsLinkTitle);
    }

    private ShortenLinkResponse prepareShortenLinkResponse(LinkObject link, NewsLinkTitle newsLinkTitle) {
        NewsLink newsLink = new NewsLink(newsLinkTitle.getId(), newsLinkTitle.getTitle(), link);
        return new ShortenLinkResponse(newsLink);
    }

    //    @GetMapping("/statistic/{linkId}")
//    public List<Statistic> getAllStatisticsByLinkId(@PathVariable String linkId) {
//        return statisticRepository.findAllByLinkId(linkId);
//    }

}
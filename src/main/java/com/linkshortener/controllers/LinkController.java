package com.linkshortener.controllers;

import com.linkshortener.api.LinkObject;
import com.linkshortener.api.ShortenLinkRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LinkController {

//    @GetMapping("/links")
//    public GetLinksResponse getLinks() {
//        List<LinkObject> links = linkRepository.findAll();
//        return new GetLinksResponse(links);
//    }
//
//    @GetMapping("/link/{id}")
//    public LinkObject getLinkById(@PathVariable String id) throws Exception{
//        return linkRepository.findById(id).orElseThrow(Exception::new);
//    }
//
//    @DeleteMapping("/link/delete/{id}")
//    public void deleteLinkById(@PathVariable String id) {
//        linkRepository.deleteById(id);
//    }

    @RequestMapping(path = "link/short", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json; charset=utf-8")
    public LinkObject shortenLink(@RequestBody ShortenLinkRequest shortenLinkRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/link/short";
        HttpEntity<ShortenLinkRequest> request = new HttpEntity<>(shortenLinkRequest);
        return restTemplate.postForObject(fooResourceUrl, request, LinkObject.class);
    }

//    @GetMapping("/statistic/{linkId}")
//    public List<Statistic> getAllStatisticsByLinkId(@PathVariable String linkId) {
//        return statisticRepository.findAllByLinkId(linkId);
//    }

}
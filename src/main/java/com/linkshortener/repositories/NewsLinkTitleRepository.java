package com.linkshortener.repositories;

import com.linkshortener.models.NewsLink;
import com.linkshortener.models.NewsLinkTitle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NewsLinkTitleRepository extends MongoRepository<NewsLinkTitle, String> {


}
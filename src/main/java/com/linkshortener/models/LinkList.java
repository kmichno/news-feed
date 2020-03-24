package com.linkshortener.models;

import com.linkshortener.api.LinkObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LinkList {
    private List<LinkObject> links;

    public LinkList() {
        links = new ArrayList<>();
    }
}
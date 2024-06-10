package com.serch.webscapers.services;


import com.serch.webscapers.Repository.WebPageRespository;
import com.serch.webscapers.models.WebPages;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
@Service
public class WebScraperServices {
    @Autowired
    private WebPageRespository repository;

    @SneakyThrows
    public void scraperAndSave(String url) {

        Document document = Jsoup.connect(url).get();
        String title = document.title();
        String description = document.select("meta[name=description]").attr("content");
        String picture = document.select("meta[property=og:image]").attr("content");
        String domain = getDomainFromUrl(url);

        WebPages webpage = new WebPages();
        webpage.setUrl(url);
        webpage.setDescription(description);
        webpage.setTitle(title);
        webpage.setPicture(picture);
        webpage.setDomain(domain);
        repository.save(webpage);
    }

    private String getDomainFromUrl(String url) {
        String domain = url.replaceFirst("^(https?://)?(www\\.)?", "");
        int index = domain.indexOf('/');
        if (index != -1) {
            domain = domain.substring(0, index);
        }
        return domain;
    }

    public List<String> getAllLink(String url) {
        List<String> result = new ArrayList<>();
        WebPages findWenPage =   repository.findByUrl(url);
        if (findWenPage != null) {
            return  new ArrayList<>();
        }
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");  // Fixed the typo here
            links.stream().parallel().forEach(link -> {
                String linkHref = link.attr("href");
                if (linkHref.startsWith("/")) {
                    linkHref = "https://" + getDomainFromUrl(url) + linkHref;
                }
                if (!result.contains(linkHref)) {
                    result.add(linkHref);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}

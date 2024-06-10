package com.serch.webscapers.controlers;

import com.serch.webscapers.Repository.WebPageRespository;
import com.serch.webscapers.models.WebPages;
import com.serch.webscapers.services.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import com.serch.webscapers.services.WebScraperServices;
@RestController
public class WebPageControles {
    @Autowired
    WebPageRespository reposirty;

    @Autowired
    WebScraperServices WebScraperServices;

    @Autowired
    SpiderService spiderService;
    @GetMapping("/api/search")
    public List<WebPages> search(@RequestParam("query") String query) {
        List<WebPages> ListSearch = new ArrayList<>();
        Iterable<WebPages> result = reposirty.findByText(query);
        for (WebPages webpage : result) {
            ListSearch.add(webpage);
        }
        return ListSearch;
    }
    @GetMapping("/api/webScraper")
    public void serchurl(@RequestParam("url") String url) {
        WebScraperServices.scraperAndSave(url);

    }
    @GetMapping("/api/start")
    public void serchAlllink() {
        spiderService.start();

    }
}

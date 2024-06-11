package com.serch.webscapers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.serch.webscapers.services.WebScraperServices;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpiderService {
    @Autowired
    WebScraperServices services;

    public void start(){
        String init = "https://elpais.com/";
        List<String> linkAll = new ArrayList<>();
        linkAll = services.getAllLink(init);
        linkAll.stream().forEach(link -> {
                    services.scraperAndSave(link);
                    scrapeAndScraperLinks(link);
                }
                );
    }
    public void scrapeAndScraperLinks(String url){
        List<String> linkAll = new ArrayList<>();
        linkAll = services.getAllLink(url);
        linkAll.stream().parallel().forEach(link -> {
            services.scraperAndSave(link);
            scrapeAndScraperLinks(url);
                }
        );
    }
}

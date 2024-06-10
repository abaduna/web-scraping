package com.serch.webscapers.jobs;

import com.serch.webscapers.services.SpiderService;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebScrapersJob {
    @Autowired
    private SpiderService service;

    @Scheduled(cron = "0 0 4 * * * ")
    public void executeJob(){
        service.start();
    }
}

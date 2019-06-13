package com.crawler.controller;

import com.crawler.api.CrawlService;
import com.crawler.model.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/url")
public class CrawlController {

    @Autowired
    private CrawlService crawlService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Metadata getMetadata(@RequestParam String url) throws IOException {
        return crawlService.getMetadata(url+"/issues");
    }
}

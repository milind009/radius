package com.crawler.api;

import com.crawler.model.Metadata;
import com.crawler.utils.CrawlerUtils;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class CrawlService {
    private static Logger logger = LoggerFactory.getLogger(CrawlService.class);

    public Metadata getMetadata(String url) throws IOException {
        logger.info("Getting metadata for url :" + url);
        int issuesIn24hrs = 0, issuesInLessThan7Days = 0, issuesInMoreThan7Days = 0, flag = 0;
        int totalPages = CrawlerUtils.getTotalPages(url, "Mozilla/5.0", 0);

        for(int i = 0; i < totalPages; i++){
            if(flag == 1){
                issuesInMoreThan7Days += CrawlerUtils.getRemainingIssuesGreaterThan7Days(i, totalPages, CrawlerUtils.getUrlForPage(url, i + 1), "Mozilla/5.0", 0);
                break;
            }

            List<Element> elements = CrawlerUtils.getAllIssuesInPage(CrawlerUtils.getUrlForPage(url, i + 1), "Mozilla/5.0", 0);
            for(int j = 0 ; j < elements.size() ; j++){
                long days = CrawlerUtils.getDurationOfIssue(elements.get(j));

                if(days < 1){
                    issuesIn24hrs++;
                }
                else if(days < 7){
                    issuesInLessThan7Days++;
                }
                else{
                    flag = 1;
                    issuesInMoreThan7Days += (25 - j);
                    break;
                }
            }
        }

        return CrawlerUtils.getMetadata(issuesIn24hrs, issuesInLessThan7Days, issuesInMoreThan7Days);
    }

}

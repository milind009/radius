package com.crawler.utils;

import com.crawler.model.Metadata;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CrawlerUtils {

    // function to get the DOM of the current page
    private static Document getDocument(String url, String agent, int timeout) throws IOException {
         return Jsoup.connect(url).userAgent(agent).timeout(timeout).get();
    }

    // function to get total number of pages in the issues tab
    public static int getTotalPages(String url, String agent, int timeout) throws IOException{
        Document doc = getDocument(url, agent, timeout);
        String totalPages = doc.getElementsByTag("em").attr("data-total-pages");

        return totalPages == null ? 0 : Integer.parseInt(totalPages);
    }

    // function to get the elements of the all the issues in the page
    public static List<Element> getAllIssuesInPage(String url, String agent, int timeout) throws IOException{
        List<Element> elements = new ArrayList<>();
        Document doc = getDocument(url, agent, timeout);
        elements.addAll(doc.body().getElementsByClass("opened-by"));
        elements.removeIf(e -> e.parent().hasClass("pinned-item-desc"));
        return elements;
    }

    //function to get issues greater than 7 days once it is detected in any page between first and the last page
    public static int getRemainingIssuesGreaterThan7Days(int currentPage, int totalPages, String url, String agent, int timeout) throws IOException{
         int issues = 25 * (totalPages - currentPage - 1);
         issues += getAllIssuesInPage(url, agent, timeout).size();
         return issues;
    }

    // function to get the URL for a page of issues
    public static String getUrlForPage(String baseUrl, int page){
        return baseUrl + "?page=" + page + "&q=is%3Aissue+is%3Aopen";
    }

    // function to get the duration for which the issue has been open
    public static long getDurationOfIssue(Element element){
        Elements s = element.getElementsByTag("relative-time");
        LocalDateTime t = LocalDateTime.parse(s.attr("datetime").substring(0, 19)).plusHours(5).plusMinutes(30);
        Duration d = Duration.between(t, LocalDateTime.now());
        return d.toDays();
    }

    // function to form an object of metadata
    public static Metadata getMetadata(int issuesIn24hrs, int issuesInLessThan7Days, int issuesInMoreThan7Days){
        Metadata metadata = new Metadata();
        metadata.issuesLessThan24hrs = issuesIn24hrs;
        metadata.issuesLessThan7Days = issuesInLessThan7Days;
        metadata.issuesMoreThan7Days = issuesInMoreThan7Days;
        metadata.totalIssues = issuesIn24hrs + issuesInLessThan7Days + issuesInMoreThan7Days;
        return metadata;
    }
}

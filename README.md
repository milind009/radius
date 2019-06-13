# radius
github issues 
Solution - 

Created a MVC using spring framework which uses Jsoup library in Java in the backend to scrape data for github issues. 

Logic - 
CrawlController listens to any HTTPrequest which takes the url as a parameter from the UI.
CrawlController then hits CrawlService with the URL of the issues page for the given repository.
CrawlService then calculates the total open issues, issues opened in the last 24 hours, issues opened between 24 hours and 7 days and issues opened more than 7 days.
CrawlService uses CrawlUtils to get the elements for all the issues. CrawlService then extracts the date the issue was opened from the element for each issue. 
It runs a loop for each page and each element until it finds a page where an issue was opened more than 7 days ago.
It then calculates total number of issues till the end.

Improvements-
1) Used a lot of constants. Instead could create a class in the config package which declares all the constants.
2) Exception Handling. Exceptions could not be handled with proper error messages.
3) Web scraping also has issues from scalibility point of view since each website has limiting number of requests for a period of time
4) UI could be improved a bit 

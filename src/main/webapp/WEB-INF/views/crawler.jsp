<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GitHub Data</title>
</head>
<body>
<p id = "para">
<label>Enter url:</label>
    <input type = "text" id = "url_input">
<button type = "submit" id = "submit_btn">Submit</button>
</p>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script src = "<c:url value="/resources/crawler.js" />"></script>
</body>
</html>
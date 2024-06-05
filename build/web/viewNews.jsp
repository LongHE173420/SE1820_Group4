<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View News</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <h1>View News</h1>
    </header>
    <nav>
        <a href="news">Back to List</a>
    </nav>
    <div class="container">
        <h2>${news.title}</h2>
        <p><strong>Description:</strong> ${news.description}</p>
        <p><strong>Author:</strong> ${news.author}</p>
        <p><strong>Date:</strong> ${news.date}</p>
        <img src="${news.img}" alt="News Image" style="max-width: 400px;">
    </div>
</body>
</html>

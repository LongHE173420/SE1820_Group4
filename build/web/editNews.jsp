<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit News</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <header>
            <h1>Edit News</h1>
        </header>
        <nav>
            <a href="news">Back to List</a>
        </nav>
        <div class="container">
            <form action="news" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="newsID" value="${news.newsID}">

                <label for="accountID">Account ID:</label>
                <input type="text" id="accountID" name="accountID" value="${news.accountID}" required>

                <label for="title">Title:</label>
                <input type="text" id="title" name="title" value="${news.title}" required>

                <label for="description">Description:</label>
                <textarea id="description" name="description" required>${news.description}</textarea>

                <label for="img">Image:</label>
                <input type="file" id="img" name="img">
                <img src="${news.img}" alt="News Image" style="max-width: 200px;">

                <label for="author">Author:</label>
                <input type="text" id="author" name="author" value="${news.author}" required>

                <label for="date">Date:</label>
                <input type="date" id="date" name="date" value="${news.date}" required>

                <button type="submit">Update News</button>
            </form>
        </div>
    </body>
</html>

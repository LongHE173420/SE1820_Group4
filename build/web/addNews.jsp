<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add News</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <a >Add news</a>
    </header>
    <nav>
        <a href="news">Back to List</a>
    </nav>
    <div class="container">
        <form action="news" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add">

            <label for="accountID">Account ID:</label>
            <input type="text" id="accountID" name="accountID" required>

            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="img">Image:</label>
            <input type="file" id="img" name="img">

            <label for="author">Author:</label>
            <input type="text" id="author" name="author" required>

            <label for="date">Date:</label>
            <input type="date" id="date" name="date" required>

            <button type="submit">Add News</button>
        </form>
    </div>
</body>
</html>

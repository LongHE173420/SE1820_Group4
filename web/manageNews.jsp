<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage News</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <h1>Manage News</h1>
    </header>
    <nav>
        <a href="news?action=add">Add News</a>
    </nav>
    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Author</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${newsList}" var="news">
                    <tr>
                        <td>${news.title}</td>
                        <td>${news.description}</td>
                        <td>${news.author}</td>
                        <td>${news.date}</td>
                        <td>
                            <a href="news?action=view&newsID=${news.newsID}">View</a>
                            <a href="news?action=edit&newsID=${news.newsID}">Edit</a>
                            <a href="news?action=delete&newsID=${news.newsID}" onclick="return confirm('Are you sure you want to delete this news?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

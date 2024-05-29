<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xem Chi tiết Tin tức</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            line-height: 1.6;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
            color: #333; /* Màu sắc chữ tiếng Việt */
        }
        .news-detail {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
        }
        .news-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .news-meta {
            color: #666;
            font-size: 16px;
            margin-bottom: 10px;
        }
        .news-description {
            font-size: 18px;
            color: #333;
        }
        .back-link {
            margin-top: 20px;
            text-align: center;
        }
        .back-link a {
            color: #007bff;
            text-decoration: none;
        }
        .back-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
  <body>
    <h1>Xem Chi tiết Tin tức</h1>

    <div class="news-detail">
        <div class="news-title">${news.title}</div>
        <div class="news-meta">Người viết: ${news.author} / ${news.date}</div>
        <div class="news-description">${news.description}</div>
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/news">Quay lại danh sách tin tức</a>
        </div>
    </div>

</body>


</body>
</html>

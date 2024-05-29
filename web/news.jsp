<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Tin tức</title>
    <style>
        .news-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-between;
        }
        .news-item {
            width: 48%;
            border: 1px solid #ddd;
            padding: 15px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
        }
        .news-item img {
            width: 100%;
            height: auto;
        }
        .news-title {
            font-size: 18px;
            font-weight: bold;
            margin: 10px 0;
        }
        .news-meta {
            color: #666;
            font-size: 14px;
            margin-bottom: 10px;
        }
        .news-description {
            font-size: 16px;
            color: #333;
        }
    </style>
</head>
<body>
    <h1>Quản lý Tin tức</h1>

    <!-- Form để Thêm hoặc Cập nhật Tin tức -->
    <form action="news" method="post">
        <input type="hidden" name="action" value="${empty news ? 'add' : 'update'}">
        <c:if test="${!empty news}">
            <input type="hidden" name="newsID" value="${news.newsID}">
        </c:if>
        <label>ID Tài khoản:</label>
        <input type="text" name="accountID" value="${empty news ? '' : news.accountID}"><br>
        <label>Tiêu đề:</label>
        <input type="text" name="title" value="${empty news ? '' : news.title}"><br>
        <label>Mô tả:</label>
        <input type="text" name="description" value="${empty news ? '' : news.description}"><br>
        <label>Hình ảnh (URL):</label>
        <input type="text" name="img" value="${empty news ? '' : news.img}"><br>
        <label>Tác giả:</label>
        <input type="text" name="author" value="${empty news ? '' : news.author}"><br>
        <label>Ngày:</label>
        <input type="date" name="date" value="${empty news ? '' : news.date}"><br>
        <input type="submit" value="${empty news ? 'Thêm' : 'Cập nhật'}">
    </form>
    <br>

    <!-- Danh sách Tin tức -->
    <div class="news-container">
        <c:forEach var="news" items="${newsList}">
            <div class="news-item">
                <img src="${news.img}" alt="${news.title}">
                <div class="news-title">${news.title}</div>
                <div class="news-meta">Người viết: ${news.author} / ${news.date}</div>
                <div class="news-description">${news.description}</div>
                <div>
                    <a href="news?action=delete&newsID=${news.newsID}">Xóa</a>
                    <a href="news?action=edit&newsID=${news.newsID}">Chỉnh sửa</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>

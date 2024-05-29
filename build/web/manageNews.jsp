<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Tin tức</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            line-height: 1.6;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
            color: #333; /* Màu sắc chữ tiếng Việt */
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
        .news-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-between;
        }
        .news-item {
            width: 48%;
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 15px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
        }
        .news-item img {
            width: 100%;
            height: auto;
            border-radius: 5px;
            margin-bottom: 15px;
        }
        .news-title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
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
        .form-container {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
            margin-top: 20px;
        }
        .form-container label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-container input[type="text"],
        .form-container input[type="date"],
        .form-container input[type="submit"] {
            width: calc(100% - 12px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-container input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        .form-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
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
    <h1>Quản lý Tin tức</h1>

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
                    <a href="viewNews.jsp?newsID=${news.newsID}">Xem chi tiết</a>
                </div>
            </div>
        </c:forEach>
    </div>

    <form action="news" method="post" class="form-container">
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
        <input type="submit" value="${empty news ? 'Thêm tin tức' : 'Cập nhật tin tức'}">
    </form>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/news">Quay lại danh sách tin tức</a>
    </div>

</body>
</html>

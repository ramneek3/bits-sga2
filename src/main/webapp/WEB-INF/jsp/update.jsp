<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Book</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Update Book Details</h1>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/update/${book.id}" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" value="${book.title}" required>
            </div>
            
            <div class="form-group">
                <label for="isbn">ISBN:</label>
                <input type="text" id="isbn" name="isbn" value="${book.isbn}" required>
            </div>
            
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" value="${book.price}" step="0.01" required>
            </div>
            
            <div class="form-group">
                <label for="author">Author:</label>
                <select id="author" name="author.id" required>
                    <c:forEach var="author" items="${authors}">
                        <option value="${author.id}" ${author.id == book.author.id ? 'selected' : ''}>${author.name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <button type="submit" class="btn">Update Book</button>
            <a href="${pageContext.request.contextPath}/" class="btn" style="background-color: #95a5a6;">Cancel</a>
        </form>
    </div>
</body>
</html>

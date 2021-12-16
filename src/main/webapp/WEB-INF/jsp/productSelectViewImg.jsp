<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored = "false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>제품 목록</h1> <br>
        <table class="table table-striped">
            <thead>
                <tr><th>제품명</th><th>수량</th><th>생산일</th></tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${product}">
                    <tr><td>${s.name}</td><td>${s.qty}</td><td>${s.yyyymmdd}</td></tr>
                </c:forEach>
            </tbody>
        </table>
    
</body>
</html>
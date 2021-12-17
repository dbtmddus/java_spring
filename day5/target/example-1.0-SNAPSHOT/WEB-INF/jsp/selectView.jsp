<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored = "false" %>

<!DOCTYPE html> <html>
<head>
<meta charset="UTF-8">
    <title>전체보기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1 align="center">
            전체보기
        </h1>
        <br>
        <br>
        <table class="table">
            <thead>
                <tr><th>이름</th><th>나이</th><th>생일</th></tr>
            </thead>
            <tbody>
                <c:forEach var="st" items="${stdArr}">
                    <tr><td>${st.name}</td><td>${st.age}</td><td>${st.birth}</td></tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
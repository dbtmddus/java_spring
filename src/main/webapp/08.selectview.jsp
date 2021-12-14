<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored = "false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>    
</head>
<body>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr><th>이름</th><th>나이</th><th>생일</th></tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${stdArr}">
                    <tr><td>${s.name}</td><td>${s.age}</td><td>${s.birth}</td></tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
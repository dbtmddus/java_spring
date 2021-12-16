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
    <h3>컴퓨터:<img src="${comImg}"></h3>
    <h3>나:<img src="${userImg}"></h3>
    <h3>결과:${result}</h3>
</body>
</html>
<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored = "false" %>

<c:set var="name" value="임꺽정" />
<c:set var="age" value="20" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>jstl 테스트</title>
    </head>
<body>
	<h1>이름:${name}</h1>
    <h1>나이:${age}</h1>
    <hr>
    <c:if test="${name=='홍길동'}">
        <h1>홍길동입니다.</h1>
    </c:if>
    <c:if test="${name=='이순신'}">
        <h1>이순신입니다.</h1>
    </c:if>
    <hr>
    
    <c:choose>
        <c:when test="${name=='이순신'}">
            <h1>이순신입니다.</h1>
        </c:when>
        <c:when test="${name=='홍길동'}">
            <h1>홍길동입니다.</h1>
        </c:when>
        <c:otherwise>
            <h1>기타...</h1>
        </c:otherwise>
    </c:choose>
    
    <hr>
    <c:forEach var="n" begin="1" end="10" step="1">
        <h3>${n}</h3>
    </c:forEach>
    
</body>
</html>



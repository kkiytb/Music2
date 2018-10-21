<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>设置Session</title>
</head>
<body>
  <%
    int n = 5;
    pageContext.setAttribute("number", n++);//n=5
    request.setAttribute("number", n++);//n=6
    session.setAttribute("number", n++);//n=7
    application.setAttribute("number", n++);//n=8
  %>
</body>
  <p>默认: ${number}</p>
  <p>page: ${pageScope.number}</p>
  <p>requset: ${requestScope.number}</p>
  <p>session: ${sessionScope.number}</p>
  <p>application: ${applicationScope.number}</p>
</html>
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
    double d = Math.random();
    session.setAttribute("num1", d);
  %>
  <p>读取Session数据: ${sessionScope.num1}</p>
    <%--在Session中添加第二个变量num2 --%>
  <c:set scope="session" value="${sessionScope.num1 + 1}" var="anumber"></c:set>
  <p>验证: ${sessionScope.anumber}</p>
</body>
</html>
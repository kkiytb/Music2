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
  <h1>读取Session</h1>
  <p>num1: ${sessionScope.num1}</p>
  <p>anumber: ${sessionScope.anumber}</p>
  <p>${num1}</p>
  <p>${anumber}</p>
</body>
</html>
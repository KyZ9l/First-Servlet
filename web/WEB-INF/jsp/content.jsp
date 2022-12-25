<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 28.10.2022
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp"%>
<div>
    <span>Content. Русский</span>
    <p>Size: ${requestScope.flights.size()}</p>
    <p>Id: ${requestScope.flights[1].id}</p>
    <p>Map id: ${sessionScope.flightsMap[1]}</p>
    <p>JSESION id: ${cookie["JSESSIONID"].value}</p>
    <p>Header id: ${header["Cookie"]}</p>
    <p>Param id: ${param.id}</p>
    <p>Param test: ${param.test}</p>
    <p>Empty list: ${not empty requestScope.flights }</p>


</div>

<%@include file="footer.jsp"%>
</body>

<head>
    <title>Title</title>
</head>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div></div>
<h2>Visualiser le PDF</h2>
    <a href="${pageContext.request.contextPath}/pdf" target="_blank">Ouvrir le PDF</a>

    <h2>PDF intégré</h2>
    <iframe src="${pageContext.request.contextPath}/pdf" width="600" height="500"></iframe>

			</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String pdfPath = "Document/contrat.pdf"; // chemin relatif dans le répertoire du serveur web
    response.sendRedirect(pdfPath);
%>

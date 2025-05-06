<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des factures de ventes</title>
<link rel="stylesheet" href="styleWSSM.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-dark sticky-top flex-md-nowrap p-0">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"><img src="images/logo.png" alt="Logo wavy" /></a>
  <h1 class="text-center text-white">WS SYSTEME MANAGEMENT</h1>
  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">Sign out</a>
    </li>
  </ul>
</nav>

<br><br>

<div class="container">
    <h2 class="text-center btn btn-dark">Factures de ventes</h2>

    <div class="text-right mb-3">
        <a href="actionFactureVente?action=ajouter" class="btn btn-primary">+ Ajouter une facture</a>
    </div>

    <table class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Total TTC</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="facture" items="${factures}">
            <tr>
                <td>${facture.id}</td>
                <td>${facture.nom}</td>
                <td>${facture.montant_ttc}</td>
                <td>
                    <a href="factureVente?action=voir&id=${facture.id}" class="btn btn-info btn-sm">Voir</a>
                    <a href="factureVente?action=copier&id=${facture.id}" class="btn btn-warning btn-sm">Copier</a>
                    <a href="factureVente?action=visualiser&id=${facture.id}" class="btn btn-secondary btn-sm">PDF</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
  <h1 class="text-center text-white"></h1>
</nav>
</body>
</html>

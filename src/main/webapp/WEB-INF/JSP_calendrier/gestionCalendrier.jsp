<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP CALENDRIER GESTION                                  ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer type contrat</title>
<link rel="stylesheet" href="styleWSSM.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">    
	<!-- Bootstrap Font Icon CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"><meta charset="utf-8" />
	
</head>
<body>
	<nav class="navbar navbar-dark sticky-top flex-md-nowrap p-0">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"><img
			src="images/logo.png" alt="Logo wavy" /> </a>
		<h1 class="text-center text-white">WS SYSTEME MANAGEMENT</h1>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="#">Sign
					out</a></li>
		</ul>
	</nav>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="accueil">Accueil</a></li>
				<li class="nav-item"><a class="nav-link" href="gestionTva">Tva</a></li>
				<li class="nav-item"><a class="nav-link" href="gestionMetier">Métier</a></li>
				<li class="nav-item active"><a class="nav-link" href="gestionCalendrier">Calendrier</a></li>
				<li class="nav-item"><a class="nav-link" href="gestionUtilisateur">Utilisateur</a></li>
			</ul>
		</div>
	</nav>
	
<div class="container">
		<div class="row  justify-content-center">
			<h1 class="text-center" >CALENDRIER <a href="gestionCalendrier?annee=${annee}&type=moins" class="btn btn-primary btn-sm mb-3"><<</a>${annee}<a href="gestionCalendrier?annee=${annee}&type=plus" class="btn btn-primary btn-sm mb-3">>></a>    <a href="gestionCalendrier?mois=${mois}&annee_mois=${annee}&type=moins" class="btn btn-primary btn-sm mb-3"><<</a>${mois}<a href="gestionCalendrier?mois=${mois}&annee_mois=${annee}&type=plus" class="btn btn-primary btn-sm mb-3">>></a></h1>
		</div>
		<div class="row">
        <p> </p>
        <c:forEach var="global" items="${CalendrierListGlobal}">
				<p><span class = "text-info">${ global.jours_ouvres } jours ouvrés jours; </span> <span class = " text-secondary">${ global.jours_non_ouvres } non ouvré(s); </span><span class = " text-secondary">dont  ${ global.jours_feries } jour(s) fériés</span></p>	
				</c:forEach>			
        
		</div>
	
		<div class="row">
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th class="col-md-1">Lundi</th>
					<th class="col-md-1">Mardi</th>
					<th class="col-md-1">Mercredi</th>
					<th class="col-md-1">Jeudi</th>
					<th class="col-md-1">Vendredi</th>
					<th class="col-md-1">Samedi</th>
					<th class="col-md-1">Dimanche</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach var="Calendrier" items="${CalendrierList}">
					<tr>
						<th class="h-100 ${ Calendrier.classe_lundi }"> <c:out value="${ Calendrier.valeur_lundi }" /></th>
						<th class="h-100 ${ Calendrier.classe_mardi }"> <c:out value="${ Calendrier.valeur_mardi }" /></th>
						<th class="h-100 ${ Calendrier.classe_mercredi }"> <c:out value="${ Calendrier.valeur_mercredi }" /></th>
						<th class="h-100 ${ Calendrier.classe_jeudi }"> <c:out value="${ Calendrier.valeur_jeudi }" /></th>
						<th class="h-100 ${ Calendrier.classe_vendredi }"> <c:out value="${ Calendrier.valeur_vendredi }" /></th>
						<th class="h-100 ${ Calendrier.classe_samedi }"> <c:out value="${ Calendrier.valeur_samedi }" /></th>
						<th class="h-100 ${ Calendrier.classe_dimanche }"> <c:out value="${ Calendrier.valeur_dimanche }" /></th>
				</tr>
				</c:forEach>				
			</tbody>

		</table>
	</div>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP IMPORT ECRITURE BANQUE GESTION                      ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer import banque</title>
<link rel="stylesheet" href="styleWSSM.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<!-- Bootstrap Font Icon CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<meta charset="utf-8" />

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
				<li class="nav-item active"><a class="nav-link"
					href="gestionTva">banque</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">

		<div class="row">
			<h1 class="text-center">Gestion import relevés bancaires</h1>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-6">
		<form id="uploadForm" action="importEcritureBanque_import" method="post"  enctype="multipart/form-data">
    <div class="form-group text-left">
        <input type="file" name="fichier" id="fichier" style="display:none;" onchange="if(this.files.length > 0) { document.getElementById('uploadForm').submit(); }">
        <button type="button" value="import" onclick="document.getElementById('fichier').click();">importer relevé</button>
    </div>
</form>
				</div>

				<div class="col-md-6">
					<!-- Barre de recherche à droite avec zone de sélection -->
					<form class="form-inline float-md-right" method="GET"
						action="gestionEcritureBanque_import">
						<div class="form-group mr-2">
							<select class="form-control" id="critereEcritureBanque_import"
								name="critereEcritureBanque_import">
								<option ${sel_nom} value="nom_import">nom import</option>
								<option ${sel_nbr} value="nbr_lignes">nbr de lignes</option>
								<option ${sel_date} value="date_import">date import</option>
								<option ${sel_user} value="user_import">utilisateur</option>
							</select>
						</div>
						<div class="form-group mr-2">
							<input class="form-control" type="searchEcritureBanque_import"
								name="searchEcritureBanque_import" placeholder="Recherche"
								aria-label="Search" value="${searchEcritureBanque_import}">
						</div>
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit" value="rechercher" >Rechercher</button>
					</form>
				</div>

			</div>
		</div>

		<table class="table table-striped">
			<thead class="thead-dark justify-content-center">
				<tr>
					<th class="col-md-2"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=DATE&triEcritureBanque_import_date=${triEcritureBanque_import_date}"><i
							class="${triEcritureBanque_import_date}" /></i></a>Le</th>
							<th class="col-md-3"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=NOM&triEcritureBanque_import_nom=${triEcritureBanque_import_nom}"><i
							class="${triEcritureBanque_import_nom}" /></i></a>nom</th>
					<th class="col-md-2"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=USER&triEcritureBanque_import_user=${triEcritureBanque_import_user}"><i
							class="${triEcritureBanque_import_user}" /></i></a>Par</th>
							<th class="col-md-1"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=LIGNES&triEcritureBanque_import_lignes=${triEcritureBanque_import_lignes}"><i
							class="${triEcritureBanque_import_lignes}" /></i></a>Lignes</th>
					<th class="col-md-2">Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="ecriture" items="${EcritureBanque_importList}">
					<tr>
						<td ><c:out value="${ ecriture.date_import }" /></td>
						<td ><c:out value="${ ecriture.nom_import }" /></td>
						<td ><c:out value="${ ecriture.user_import }" /></td>
						<td ><c:out value="${ ecriture.nbr_ligne }" /></td>
						<td><a href="visualisationEcritureBanque_import?id_ecritureBanque=${ ecriture.id }"
							class="btn btn-outline-secondary" data-toggle="tooltip"
							data-placement="top" title="VISUALISER"><i class="bi bi-eye"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-end">
				<c:set var="noBegin" value="${currentPage-pasPage+1}" />
				<c:choose>
					<c:when test="${noBegin le 0}">
						<c:set var="noBegin" value="${1}" />
					</c:when>
				</c:choose>
				<c:set var="maxPage" value="${noBegin+pasPage}" />
				<c:choose>
					<c:when test="${maxPage ge noOfPages}">
						<c:set var="maxPage" value="${noOfPages}" />
					</c:when>
				</c:choose>
				<li class="page-item"><c:choose>
						<c:when test="${currentPage gt 1}">
							<a class="page-link"
								href="gestionTva?currentPage=${currentPage - 1}">Previous</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose></li>

				<c:forEach begin="${noBegin}" end="${maxPage}" var="i">
					<c:choose>
						<c:when test="${currentPage != i}">
							<li class="page-item"><a class="page-link"
								href="gestionTva?currentPage=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link"
								href="gestionTva?currentPage=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt noOfPages}">
					<li class="page-item"><a class="page-link"
						href="gestionTva?currentPage=${currentPage + 1}">Next</a></li>
				</c:if>

			</ul>
		</nav>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
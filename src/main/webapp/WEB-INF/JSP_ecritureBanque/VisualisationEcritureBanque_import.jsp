<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP IMPORT ECRITURE BANQUE VISUALISATION                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualiser import banque</title>
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
			<h1 class="text-center">Visulation import relevés bancaires</h1>
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
						action="visualisationEcritureBanque_import">
						<div class="form-group mr-2">
							<select class="form-control" id="critereVisualisationEcritureBanque_import"
								name="critereVisualisationEcritureBanque_import">
								<option ${sel_numero_ligne} value="numero_ligne">numéro de ligne</option>
								<option ${sel_date_ecriture} value="date_ecriture">date écriture</option>
								<option ${libelle_ecriture} value="libelle_ecriture">libellé écriture</option>
							</select>
						</div>
						<div class="form-group mr-2">
							<input class="form-control" type="searchVisualisationEcritureBanque_import"
								name="searchVisualisationEcritureBanque_import" placeholder="Recherche"
								aria-label="Search" value="${searchVisualisationEcritureBanque_import}">
						</div>
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit" value="rechercher" >Rechercher</button>
					</form>
				</div>

			</div>
		</div>

		<table class="table table-striped">
			<thead class="thead-dark justify-content-center">
				<tr>
					<th class="col-md-1"><a
						href="visualisationEcritureBanque_import?triVisualisationEcritureBanque_import=NUMERO_LIGNE&triVisualisationEcritureBanque_numero_ligne=${triVisualisationEcritureBanque_numero_ligne}"><i
							class="${triVisualisationEcritureBanque_numero_ligne}" /></i></a>N°ligne</th>
							<th class="col-md-2"><a
						href="visualisationEcritureBanque_import?triVisualisationEcritureBanque_import=DATE_ECRITURE&triVisualisationEcritureBanque_date_ecriture=${triVisualisationEcritureBanque_date_ecriture}"><i
							class="${triVisualisationEcritureBanque_date_ecriture}" /></i></a>Date</th>
							<th class="col-md-5"><a
						href="visualisationEcritureBanque_import?triVisualisationEcritureBanque_import=LIBELLE_ECRITURE&triVisualisationEcritureBanque_libelle_ecriture=${triVisualisationEcritureBanque_libelle_ecriture}"><i
							class="${triVisualisationEcritureBanque_libelle_ecriture}" /></i></a>Libelle</th>
					<th class="col-md-2"><a
						href="visualisationEcritureBanque_import?triVisualisationEcritureBanque_import=DEBIT&triVisualisationEcritureBanque_debit=${triVisualisationEcritureBanque_debit}"><i
							class="${triVisualisationEcritureBanque_debit}" /></i></a>DEBIT</th>
							<th class="col-md-2"><a
						href="visualisationEcritureBanque_import?triVisualisationEcritureBanque_import=CREDIT&triVisualisationEcritureBanque_credit=${triVisualisationEcritureBanque_credit}"><i
							class="${triVisualisationEcritureBanque_credit}" /></i></a>CREDIT</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="ecriture" items="${EcritureBanque_importList}">
					<tr>
						<td ><c:out value="${ ecriture.numero_ligne }" /></td>
						<td ><c:out value="${ ecriture.date_ecriture }" /></td>
						<td ><c:out value="${ ecriture.libelle_ecriture }" /></td>
						<td ><c:out value="${ ecriture.debit }" /></td>
						<td ><c:out value="${ ecriture.credit}" /></td>
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
								href="visualisationEcritureBanque_import?currentPage=${currentPage - 1}">Previous</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose></li>

				<c:forEach begin="${noBegin}" end="${maxPage}" var="i">
					<c:choose>
						<c:when test="${currentPage != i}">
							<li class="page-item"><a class="page-link"
								href="visualisationEcritureBanque_import?currentPage=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link"
								href="visualisationEcritureBanque_import?currentPage=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt noOfPages}">
					<li class="page-item"><a class="page-link"
						href="visualisationEcritureBanque_import?currentPage=${currentPage + 1}">Next</a></li>
				</c:if>

			</ul>
		</nav>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
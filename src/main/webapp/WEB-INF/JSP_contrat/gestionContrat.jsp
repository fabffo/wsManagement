<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP CONTRATCLIENT GESTION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer contrat ${type_entite}</title>
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
					href="gestionContrat?type_entite=CLIENT">Contrat client</a></li>
				<li class="nav-item"><a class="nav-link"
					href="gestionContrat?type_entite=FOURNISSEUR">Contrat fournisseur</a></li>
			<li class="nav-item"><a class="nav-link"
					href="gestionContrat?type_entite=SALARIE">Contrat salarié</a></li>
			<li class="nav-item"><a class="nav-link"
					href="gestionContrat?type_entite=PRESTATAIRE">Contrat prestataire</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">

		<div class="row">
			<h1 class="text-center">
				GESTION CONTRAT ${type_entite}
				<form method="GET"  class="btn d-inline">
					<select name="tag_statut" id="tag_statut" class="${tag_statut}"
						class="form-control d-inline-block w-auto ml-2 "
						onchange="this.form.submit()">
						<c:forEach var="listStatuts" items="${listStatuts}">
							<option value="${listStatuts.nom}"
								class="${listStatuts.nom}" ${listStatuts.selected}>${listStatuts.nom}</option>
						</c:forEach>
					</select>
				</form>
				<form method="GET"  class="btn d-inline">
					<select name="tag_statut" id="tag_statut" class="${tag_statut}"
						class="form-control d-inline-block w-auto ml-2 "
						onchange="this.form.submit()">
						<c:forEach var="lesStatuts" items="${lesStatuts}">
							<option value="${lesStatuts.nom}"
								class="${lesStatuts.nom}" ${lesStatuts.nom == tag_statut ? 'selected' : ''}>${lesStatuts.nom}</option>
						</c:forEach>
					</select>
				</form>
			</h1>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<!-- Bouton à gauche -->

					<a class="btn btn-outline-dark  mb-1"
						href="ajoutContrat?action=AJOUTER"><i
						class="bi bi-plus-square" data-toggle="tooltip"
						data-placement="top" title="AJOUTER"></i></a>
				</div>
				<div class="col-md-6">
					<!-- Barre de recherche à droite avec zone de sélection -->
					<form class="form-inline float-md-right" method="GET"
						action="gestionContrat">
						<div class="form-group mr-2">
							<select class="form-control" id="critereContrat"
								name="critereContrat">
								<option ${sel_version} value="avenantClient.id">N°contrat</option>
								<option ${sel_type_contrat} value="avenantClient.type_contrat">Type
									de contrat</option>
								<option ${sel_client} value="societe.raison_sociale">Client</option>
								<option ${sel_statut} value="avenantClient.statut">Statut</option>
							</select>
						</div>
						<div class="form-group mr-2">
							<input class="form-control" type="searchContrat"
								name="searchContrat" placeholder="Recherche"
								aria-label="Search" value="${searchContrat}">
						</div>
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
					</form>
				</div>
			</div>

		</div>
		<table class="table table-striped">
			<thead class="thead-dark justify-content-center">
				<tr>
					<th class="col-md-1"><a
						href="gestionContrat?triContrat=ID&triContrat_id=${triContrat_id}"><i
							class="${triContrat_id}" /></i></a>N°</th>

							<th class="col-md-3"><a
						href="gestionContrat?triContrat=NOM_CONTRAT&triContrat_id=${triContrat_nom_contrat}"><i
							class="${triContrat_nom_contrat}" /></i></a>Nom</th>

					<c:if test="${tag_statut == 'Tout'}">
						<th class="col-md-1"><a
							href="gestionContrat?triContrat=STATUT&triContrat_statut=${triContrat_statut}"><i
								class="${triContrat_statut}" /></i></a>Statut</th>
					</c:if>

					<th class="col-md-1"><a
						href="gestionContrat?triContrat=TYPE_CONTRAT&triContrat_type_contrat=${triContrat_type_contrat}"><i
							class="${triContrat_type_contrat}" /></i></a>Type</th>

							<th class="col-md-1"><a>
						</a>Pdf</th>

					<th class="col-md-2"><a
						href="gestionContrat?triContrat=CLIENT&triContrat_client=${triContrat_client}"><i
							class="${triContrat_client}" /></i></a>Client</th>

					<th class="col-md-3">Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="contrat" items="${ContratList}">
					<tr >
						<td class="align-middle"><c:out
								value="${ contrat.id }_${ contrat.version }" /></td>

						<td class="align-middle"><c:out
								value="${ contrat.nom_contrat }" /></td>

						<c:if test="${tag_statut == 'Tout'}">
							<td class="align-middle align-center"><i
								class="btn ${contrat.statut} ">${contrat.statut}</i></td>
						</c:if>

						<td class="align-middle"><c:out
								value="${ contrat.type_contrat }" /></td>

						<td class="align-middle align-center"><a
							href="${ contrat.document }" class="btn Terminé" target="_blank"> <i
								class="bi bi-file-earmark-pdf" data-toggle="tooltip"
								data-placement="top" title="Contrat"></i></a></td>

						<td class="align-middle"><c:out
								value="${ contrat.client }" /></td>

						<td class="align-middle align-center"><a
							href="majContrat?id=${ contrat.id }&version=${ contrat.version }&currentPage=${currentPage}"
							class="btn En-cours"><i class="bi bi-pencil-square"
								data-toggle="tooltip" data-placement="top" title="MAJ"></i></a> <a
							href="copieContrat?id=${ contrat.id }&version=${ contrat.version }&currentPage=${currentPage}"
							class="btn En-cours"><i class="bi bi-book-half"
								data-toggle="tooltip" data-placement="top" title="COPIER"></i></a> <a
							href="visualisationContrat?id=${ contrat.id }&version=${ contrat.version }"
							class="btn btn-outline-secondary" data-toggle="tooltip"
							data-placement="top" title="VISUALISER"><i class="bi bi-eye"></i></a>

							<a
							href="annulationContrat?id=${ contrat.id }&version=${ contrat.version }"
							class="btn btn-orange"><i class="bi bi-x "
								data-toggle="tooltip" data-placement="top" title="ANNULER"></i></a>

							<a
							href="finContrat?id=${ contrat.id }&version=${ contrat.version }"
							class="btn Terminé"><i class="bi bi-check "
								data-toggle="tooltip" data-placement="top" title="TERMINER"></i></a>

							<a
							href="versionContrat?id=${ contrat.id }&version=${ contrat.version }"
							class="btn En-cours"><i class="bi bi-file-earmark-plus "
								data-toggle="tooltip" data-placement="top"
								title="Faire un avenant"></i></a>

								<a
							href="renommageContrat?id=${ contrat.id }&version=${ contrat.version }&currentPage=${currentPage}"
							class="btn En-cours"><i class="bi bi-pencil-fill"
								data-toggle="tooltip" data-placement="top" title="Renommer contrat"></i></a>

								</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

    </div>


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
								href="gestionContrat?currentPage=${currentPage - 1}">Previous</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose></li>

				<c:forEach begin="${noBegin}" end="${maxPage}" var="i">
					<c:choose>
						<c:when test="${currentPage != i}">
							<li class="page-item"><a class="page-link"
								href="gestionContrat?currentPage=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link"
								href="gestionContrat?currentPage=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt noOfPages}">
					<li class="page-item"><a class="page-link"
						href="gestionContrat?currentPage=${currentPage + 1}">Next</a></li>
				</c:if>

			</ul>
		</nav>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
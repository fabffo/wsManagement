<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP TVA GESTION                                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer tva</title>
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
					href="gestionTva">Tva</a></li>
				<li class="nav-item"><a class="nav-link" href="gestionMetier">Métier</a></li>
				<li class="nav-item"><a class="nav-link"
					href="gestionCalendrier">Calendrier</a></li>
				<li class="nav-item"><a class="nav-link"
					href="gestionUtilisateur">Utilisateur</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">

		<div class="row">
			<h1 class="text-center">Gestion tva</h1>
		</div>

		<div class="container">
			<div class="row">

				<div class="col-md-6">

					<a class="btn En-cours btn-link btn p-2"
						href="ajoutTva?action=AJOUTER"><i class="bi bi-plus-square"
						data-toggle="tooltip" data-placement="top" title="AJOUTER"></i></a>
				</div>

				<div class="col-md-6">
    <form class="form-inline float-md-right" method="GET" action="gestionTva">
       <!-- Bouton avec une image (icône de réinitialisation) -->
        <button type="submit" name="action" value="AZERO" class="btn En-cours btn-link p-2" title="Réinitialiser">
            <i class="bi bi-arrow-clockwise"></i> <!-- Remplacez cette icône par une image si nécessaire -->
        </button>
        <!-- Barre de recherche à droite avec zone de sélection -->
        <div class="form-group mr-2">
            <select class="form-select" id="critereTva" name="critereTva">
                <option ${sel_nom} value="nom">Nom</option>
                <option ${sel_libelle} value="libelle">Libellé</option>
                <option ${sel_taux} value="taux">Taux</option>
            </select>
        </div>

        <div class="form-group mr-2">
            <input class="form-control" type="searchTva" name="searchTva" placeholder="Recherche"
                   aria-label="Search" value="${searchTva}">
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
						href="gestionTva?triTva=id&triTva_id=${triTva_id}"><i
							class="${triTva_id}" /></i></a>Id</th>
					<th class="col-md-1"><a
						href="gestionTva?triTva=nom&triTva_nom=${triTva_nom}"><i
							class="${triTva_nom}" /></i></a>Nom</th>
					<th class="col-md-3"><a
						href="gestionTva?triTva=libelle&triTva_libelle=${triTva_libelle}"><i
							class="${triTva_libelle}" /></i></a>Libelle</th>
					<th class="col-md-2"><a
						href="gestionTva?triTva=taux&triTva_taux=${triTva_taux}"><i
							class="${triTva_taux}" /></i></a>Taux</th>
					<th class="col-md-3">Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="tva" items="${TvaList}">
					<tr>
						<td class="h-100"><c:out value="${ tva.id }" /></td>
						<td class="h-80"><c:out value="${ tva.nom }" /></td>
						<td class="h-40"><c:out value="${ tva.libelle }" /></td>
						<td class="h-20"><c:out value="${ tva.taux }" /></td>
						<td><a
							href="majTva?id=${ tva.id }&currentPage=${currentPage}"
							class="btn En-cours btn-link p-2"><i class="bi bi-pencil-square"
								data-toggle="tooltip" data-placement="top" title="MAJ"></i></a> <a
							href="copieTva?id=${ tva.id }&currentPage=${currentPage}"
							class="btn En-cours btn-link p-2"><i class="bi bi-book-half"
								data-toggle="tooltip" data-placement="top" title="COPIER"></i></a> <a
							href="visualisationTva?id=${ tva.id }"
							class="btn Visualisé btn-link btn p-2" data-toggle="tooltip"
							data-placement="top" title="VISUALISER"><i class="bi bi-eye"></i></a>

							<a href="suppressionTva?id=${ tva.id }"
							class="btn Terminé btn-link btn p-2"><i class="bi bi-trash"
								data-toggle="tooltip" data-placement="top" title="SUPPRIMER"></i></a></td>
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
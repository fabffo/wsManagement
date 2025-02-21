<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP TYPE SOCIETE GESTION                                ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer type societe</title>
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
				<li class="nav-item active"><a class="nav-link" href="gestionTypeSociete">Type societe</a></li>
				<li class="nav-item "><a class="nav-link" href="gestionTypePrix">Type prix</a></li>
				<li class="nav-item "><a class="nav-link" href="gestionTypeDocument">Type document</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">

		<div class="row">
			<h1 class="text-center">Liste des types de societe</h1>
		</div>
		
<div class="container">
  <div class="row">
    <div class="col-md-6">
      <!-- Bouton à gauche -->
       
      <a class="btn btn-outline-dark  mb-3"
					href="ajoutTypeSociete?action=AJOUTER"><i class="bi bi-plus-square" data-toggle="tooltip" data-placement="top" title="AJOUTER"></i></a>
   
    </div>
    <div class="col-md-6">
      <!-- Barre de recherche à droite avec zone de sélection -->
      <form class="form-inline float-md-right" method="GET" action="gestionTypeSociete">
        <div class="form-group mr-2">
          <select class="form-control" id="critereTypeSociete" name="critereTypeSociete">
            <option ${sel_nom} value="nom">nom</option>
            <option  ${sel_libelle} value="libelle">Libelle</option>
          </select>
        </div>
        <div class="form-group mr-2">
          <input class="form-control" type="searchTypeSociete" name = "searchTypeSociete" placeholder="Recherche" aria-label="Search" value="${searchTypeSociete}">
        </div>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
      </form>
    </div>
  </div>
</div>
		<table class="table table-striped">
			<thead class="thead-dark justify-content-center">
				<tr>
					<th class="col-md-1"><a href="gestionTypeSociete?triTypeSociete=ID&triTypeSociete_id=${triTypeSociete_id}"><i class="${triTypeSociete_id}"/></i></a>Id</th>
					<th class="col-md-1"><a href="gestionTypeSociete?triTypeSociete=NOM&tri_nomTypeSociete=${tri_nomTypeSociete}"><i class="${tri_nomTypeSociete}"/></i></a>Nom</th>
					<th class="col-md-3"><a href="gestionTypeSociete?triTypeSociete=LIBELLE&tri_libelleTypeSociete=${tri_libelleTypeSociete}"><i class="${tri_libelleTypeSociete}"/></i></a>Libelle</th>
					<th class="col-md-3">Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="typeSociete" items="${TypeSocieteList}">
					<tr>
						<td><c:out value="${ typeSociete.id }" /></td>
						<td><c:out value="${ typeSociete.nom }" /></td>
						<td><c:out value="${ typeSociete.libelle }" /></td>
						<td><a
							href="majTypeSociete?id=${ typeSociete.id }&currentPage=${currentPage}"
							class="btn btn-outline-dark"><i class="bi bi-pencil-square" data-toggle="tooltip" data-placement="top" title="MAJ"></i></a>
							
							 <a
							href="copieTypeSociete?id=${ typeSociete.id }&currentPage=${currentPage}"
							class="btn btn-outline-dark"><i class="bi bi-book-half" data-toggle="tooltip" data-placement="top" title="COPIER"></i></a> 
							
							<a
							href="visualisationTypeSociete?id=${ typeSociete.id }"
							class="btn btn-outline-secondary" data-toggle="tooltip" data-placement="top" title="VISUALISER"><i class="bi bi-eye"></i></a> 
							
							<a
							href="suppressionTypeSociete?id=${ typeSociete.id }"
							class="btn btn-outline-danger"><i class="bi bi-trash" data-toggle="tooltip" data-placement="top" title="SUPPRIMER"></i></a></td>
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
								href="gestionTypeSociete?currentPage=${currentPage - 1}">Previous</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</li>

				<c:forEach begin="${noBegin}" end="${maxPage}" var="i">
				<c:choose>
					<c:when test="${currentPage != i}">
						<li class="page-item"><a class="page-link"
						href="gestionTypeSociete?currentPage=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item active"><a class="page-link"
						href="gestionTypeSociete?currentPage=${i}">${i}</a></li>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${currentPage lt noOfPages}">
					<li class="page-item"><a class="page-link"
						href="gestionTypeSociete?currentPage=${currentPage + 1}">Next</a></li>
				</c:if>
				
			</ul>
		</nav>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
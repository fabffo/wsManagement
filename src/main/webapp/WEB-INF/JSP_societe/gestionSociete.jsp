<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP SOCIETE GESTION                                     ///
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
				<li class="nav-item"><a class="nav-link" href="gestionCollaborateur">Collaborateur</a></li>
				<c:if test="${typeSociete =='SOCIETE'}">
					<li class="nav-item active"><a class="nav-link" href="gestionSociete?typeSociete=SOCIETE">Societe</a></li>
					<li class="nav-item"><a class="nav-link" href="gestionSociete?typeSociete=CLIENT">Client</a></li>
					<li class="nav-item"><a class="nav-link" href="gestionSociete?typeSociete=FOURNISSEUR">Fournisseur</a></li>
				</c:if>
				<c:if test="${typeSociete =='CLIENT'}">
					<li class="nav-item "><a class="nav-link" href="gestionSociete?typeSociete=SOCIETE">Societe</a></li>
					<li class="nav-item active"><a class="nav-link" href="gestionSociete?typeSociete=CLIENT">Client</a></li>
					<li class="nav-item"><a class="nav-link" href="gestionSociete?typeSociete=FOURNISSEUR">Fournisseur</a></li>
				</c:if>
				<c:if test="${typeSociete =='FOURNISSEUR'}">
					<li class="nav-item "><a class="nav-link" href="gestionSociete?typeSociete=SOCIETE">Societe</a></li>
					<li class="nav-item"><a class="nav-link" href="gestionSociete?typeSociete=CLIENT">Client</a></li>
					<li class="nav-item active"><a class="nav-link" href="gestionSociete?typeSociete=FOURNISSEUR">Fournisseur</a></li>
				</c:if>
			</ul>
		</div>
	</nav>
	<div class="container">

		<div class="row">
			<h1 class="text-center">GESTION ${typeSociete} </h1>
		</div>
		
<div class="container">
  <div class="row">
    <div class="col-md-6">
      <!-- Bouton à gauche -->
       
      <a class="btn btn-outline-dark  mb-3"
					href="ajoutSociete?action=AJOUTER"><i class="bi bi-plus-square" data-toggle="tooltip" data-placement="top" title="AJOUTER"></i></a>
   
    </div>
    <div class="col-md-6">
      <!-- Barre de recherche à droite avec zone de sélection -->
      <form class="form-inline float-md-right" method="GET" action="gestionSociete">
        <div class="form-group mr-2">
          <select class="form-control" id="critereSociete" name="critereSociete">
            <option ${sel_raison_sociale} value="societe.raison_sociale">Raison_sociale</option>
            <option  ${sel_contact_principal} value="contact.prenom">prenom</option>
            <option ${sel_email} value="contact.email1">Email</option>
            <option ${sel_telephone} value="contact.telephone1">Telephone</option>
          </select>
        </div>
        <div class="form-group mr-2">
          <input class="form-control" type="searchSociete" name = "searchSociete" placeholder="Recherche" aria-label="Search" value="${searchSociete}">
        </div>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
      </form>
    </div>
  </div>
</div>
		<table class="table table-striped">
			<thead class="thead-dark justify-content-center">
				<tr>
					<th class="col-md-1"><a href="gestionSociete?triSociete=ID&triSociete_id=${triSociete_id}"><i class="${triSociete_id}"/></i></a>Id</th>
					<th class="col-md-2"><a href="gestionSociete?triSociete=RAISON_SOCIALE&tri_raison_socialeSociete=${tri_raison_socialeSociete}"><i class="${tri_raison_socialeSociete}"/></i></a>Raison_sociale</th>
					<th class="col-md-2"><a href="gestionSociete?triSociete=CONTACT_PRINCIPAL&tri_contact_principalSociete=${tri_contact_principalSociete}"><i class="${tri_contact_principalSociete}"/></i></a>contact_principal</th>
					<th class="col-md-2"><a href="gestionSociete?triSociete=EMAIL&tri_emailSociete=${tri_emailSociete}"><i class="${tri_emailSociete}"/></i></a>Email</th>
					<th class="col-md-1"><a href="gestionSociete?triSociete=TELEPHONE&tri_telephoneSociete=${tri_telephoneSociete}"><i class="${tri_telephoneSociete}"/></i></a>Telephone</th>
					<th class="col-md-2">Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="societe" items="${SocieteList}">
					<tr>
						<td class="h-100"><c:out value="${ societe.id }" /></td>
						<td class="h-80"><c:out value="${ societe.raison_sociale }" /></td>
						<td class="h-40"><c:out value="${ societe.contact_principal }" /></td>
						<td class="h-20"><c:out value="${ societe.email }" /></td>
						<td class="h-20"><c:out value="${ societe.telephone }" /></td>
						<td><a
							href="majSociete?id=${ societe.id }&currentPage=${currentPage}"
							class="btn btn-outline-dark"><i class="bi bi-pencil-square" data-toggle="tooltip" data-placement="top" title="MAJ"></i></a>
							
							 <a
							href="copieSociete?id=${ societe.id }&currentPage=${currentPage}"
							class="btn btn-outline-dark"><i class="bi bi-book-half" data-toggle="tooltip" data-placement="top" title="COPIER"></i></a> 
							
							<a
							href="visualisationSociete?id=${ societe.id }"
							class="btn btn-outline-secondary" data-toggle="tooltip" data-placement="top" title="VISUALISER"><i class="bi bi-eye"></i></a> 
							
							<a
							href="suppressionSociete?id=${ societe.id }"
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
								href="gestionSociete?currentPage=${currentPage - 1}">Previous</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</li>

				<c:forEach begin="${noBegin}" end="${maxPage}" var="i">
				<c:choose>
					<c:when test="${currentPage != i}">
						<li class="page-item"><a class="page-link"
						href="gestionSociete?currentPage=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item active"><a class="page-link"
						href="gestionSociete?currentPage=${i}">${i}</a></li>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${currentPage lt noOfPages}">
					<li class="page-item"><a class="page-link"
						href="gestionSociete?currentPage=${currentPage + 1}">Next</a></li>
				</c:if>
				
			</ul>
		</nav>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
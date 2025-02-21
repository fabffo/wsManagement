<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP PARAMETRE CRUD MULTIZONE                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8">
<title>${parametre_nom_programme}${param.parametre_nom}</title>
<link rel="stylesheet" href="styleWSSM.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-dark sticky-top flex-md-nowrap p-0">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"><img
			src="images/logo.png" alt="Logo wavy" /> </a>
		<h1 class="text-center text-white">WS SYSTEME MANAGEMENT"</h1>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="#"></a></li>
		</ul>
	</nav>
	<br>
	<br>

	<div class="container">
		<div class="row">
			<div
				class="col-lg-${largeur_ecran}-md-${largeur_ecran} col-sm-${largeur_ecran} container justify-content-center card">

				<h1 class="text-center btn btn-dark">${parametre_nom_programme}
					${param.parametre_nom} ${param.entite} ${param.classe_id}</h1>
				<form method="POST"
					action="crudParametreMultiligne?parametre_nom_programme=${parametre_nom_programme}&classe_id=${param.classe_id}">

					<!-- Boucle sur les éléments de la map 'validations' -->
					<c:forEach var="row" items="${rows}">
						<div class="form-group row">
							<c:forEach var="fieldConfig" items="${row}">
								<div class="col-${fieldConfig.largeur_libelle}">
									<label for="${fieldConfig.nom_champ}" >${fieldConfig.nom_champ}</label>
									<c:choose>
										<c:when test="${fieldConfig.type_zone == 'input'}">
											<input type="${fieldConfig.type}" id="${fieldConfig.nom_champ}"
												name="${fieldConfig.nom_champ}" class="form-control"
												value="${fieldConfig.valeur}"
												<c:if test="${fieldConfig.required}">required</c:if>
												<c:if test="${fieldConfig.readonly}">readonly</c:if>>
										</c:when>
										<c:otherwise>
											<select name="${fieldConfig.nom_champ}" class="form-control" <c:if test="${fieldConfig.disabled}">disabled</c:if>>
											<c:forEach var="option" items="${fieldConfig.listSelect}">
												<option
													value="${option.id}" ${fieldConfig.valeur == option.id ? 'selected' : ''}>
													${option.nom}</option>
											</c:forEach>
										</select>
										</c:otherwise>
									</c:choose>
									<span class="erreur text-danger">${form.erreurs[fieldConfig.nom_champ]}</span>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Valider</button>
						<a
							href=gestionParametre?parametre_nom=${parametre_nom}&ACTION=ANNULER
							class="btn btn-danger">Annuler </a>
					</div>
				</form>

			</div>
		</div>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>

</html>
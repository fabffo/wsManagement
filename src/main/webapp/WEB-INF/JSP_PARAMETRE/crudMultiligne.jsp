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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
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
					${parametre_nom} ${type_entite} ${param.classe_id}</h1>
				<form method="POST"
					action="crudParametreMultiligne?parametre_nom_programme=${parametre_nom_programme}&classe_id=${param.classe_id}"
					enctype="multipart/form-data">

					<!-- Boucle sur les éléments de la map 'validations' -->
					<c:forEach var="row" items="${rows}">
						<div class="form-group row">
							<c:forEach var="fieldConfig" items="${row}">
								<div class="col-${fieldConfig.largeur_libelle}">
									<c:if test="${fieldConfig.type_zone == 'pdf'}">

										<input type="hidden" class="form-control"
											id="${fieldConfig.nom_champ}" name="${fieldConfig.nom_champ}"
											value="${not empty document ? document : fieldConfig.valeur}" readonly>

									</c:if>
									<label for="${fieldConfig.nom_champ}">${fieldConfig.nom_champ}</label>
									<c:choose>
										<c:when test="${fieldConfig.type_zone == 'input'}">
											<input type="${fieldConfig.type}" step="${fieldConfig.step}"
												id="${fieldConfig.nom_champ}"
												name="${fieldConfig.nom_champ}" class="form-control"
												value="${fieldConfig.valeur}"
												<c:if test="${fieldConfig.required}">required</c:if>
												<c:if test="${fieldConfig.readonly}">readonly</c:if>
												<c:if test="${fieldConfig.disabled}">disabled</c:if>
												<c:if test="${fieldConfig.hidden}">type="hidden"</c:if>>
										</c:when>
										<c:when test="${fieldConfig.type_zone == 'pdf'}">
											<div class="file-container">
												<!-- Bouton pour choisir un fichier -->
												<button type="button" class="btn btn-primary btn-uniform"
													id="uploadBtn" data-bs-toggle="tooltip"
													title="Choisir un fichier">
													<i class="bi bi-plus"></i>
												</button>

												<!-- Input File caché -->
												<input type="file" name="fichier" id="fichier"
													style="display: none;">

												<!-- Zone pour afficher le nom du fichier sélectionné -->
												<span id="fileName" class="file-name"></span>

												<!-- Bouton pour importer -->
												<button type="submit" name="action" value="uploadFile"
													class="btn btn-success btn-uniform"
													data-bs-toggle="tooltip" title="Importer">
													<i class="bi bi-arrow-down"></i>
												</button>

												<!-- Bouton pour supprimer le fichier -->
												<button id="removeFile" class="btn btn-danger btn-uniform"
													style="display: none;">
													<i class="bi bi-x"></i>
												</button>

												<!-- Icône PDF cliquable si un fichier est déjà enregistré -->
												<c:if
													test="${not empty document or not empty fieldConfig.valeur}">
													<a
														href="${not empty document ? document : fieldConfig.valeur}"
														target="_blank"> <img src="images/pdf.png" alt="PDF"
														class="pdf-thumbnail">
													</a>
												</c:if>
											</div>
										</c:when>
										<c:otherwise>
											<select name="${fieldConfig.nom_champ}" class="form-control"
												<c:if test="${fieldConfig.disabled}">disabled</c:if>>
												<c:forEach var="option" items="${fieldConfig.listSelect}">
													<option value="${option.id}"
														${fieldConfig.valeur == option.id ? 'selected' : ''}>
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
<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Script pour gérer la sélection et suppression du fichier -->
<script>
	document
			.addEventListener(
					"DOMContentLoaded",
					function() {
						var tooltipTriggerList = [].slice
								.call(document
										.querySelectorAll('[data-bs-toggle="tooltip"]'));
						var tooltipList = tooltipTriggerList.map(function(
								tooltipTriggerEl) {
							return new bootstrap.Tooltip(tooltipTriggerEl);
						});

						document.getElementById("uploadBtn").addEventListener(
								"click", function() {
									document.getElementById("fichier").click();
								});

						document
								.getElementById("fichier")
								.addEventListener(
										"change",
										function() {
											let file = this.files[0];
											if (file) {
												document
														.getElementById("fileName").textContent = file.name;
												document
														.getElementById("removeFile").style.display = "inline-flex";
											}
										});

						document
								.getElementById("removeFile")
								.addEventListener(
										"click",
										function() {
											document.getElementById("fichier").value = "";
											document.getElementById("fileName").textContent = "";
											this.style.display = "none";
										});
					});
</script>
</html>
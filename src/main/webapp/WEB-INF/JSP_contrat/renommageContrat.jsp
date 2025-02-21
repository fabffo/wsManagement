<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP CONTRATCLIENT VERSION                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Renommage contrat</title>
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
		<h1 class="text-center text-white">WS SYSTEME MANAGEMENT</h1>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="#"></a></li>
		</ul>
	</nav>
	<br>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-6-md-6 col-sm-6 container justify-content-center card">
				<h1 class="text-center btn btn-dark">RENOMMER CONTRAT ${ contrat.id }_${ contrat.version}
				</h1>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<form method="POST"
			action="renommageContrat?id=${ contrat.id }&version=${ contrat.version }"
			enctype="multipart/form-data">


			<div class="row">

				<div
					class="col-lg-8-md-8 col-sm-8 container justify-content-center card">
					<div class="form-group row">
						<div class="col-8">
							Nom contrat <input type="text" class="form-control"
								name="nom_contrat"
								value=<c:out value="${contrat.nom_contrat}"/>> <span
								class="erreur text-danger ">${form.erreurs['nom_contrat']}</span>
						</div>
						<div class="col-3">
							Statut <select name="statut" id="statut"
								class="col3 form-control">
								<option value="${contrat.statut}" selected>${contrat.statut}</option>
								<c:forEach var="listStatut" items="${listStatut}">
									<option value="${listStatut}">${listStatut}</option>
								</c:forEach>
							</select> <span class="erreur text-danger ">${form.erreurs['statut']}</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-6">
							Client <select name="clientInfo" id="clientInfo" disabled="disabled"
								class="col-10 form-control">
								<option
									value="${contrat.id_client}_${contrat.client}"
									selected>${contrat.client}</option>
								<c:forEach var="listClient" items="${listClient}">
									<option value="${listClient.id}_${listClient.raison_sociale}">${listClient.raison_sociale}</option>
								</c:forEach>
							</select> <span class="erreur text-danger ">${form.erreurs['client']}</span>
						</div>
						<div class="col-5">
							Type <select name="type_contrat" class="col-10 form-control" disabled="disabled">
								<option value="${contrat.type_contrat}" selected>${contrat.type_contrat}</option>
								<c:forEach var="listTypeContratSociete"
									items="${listTypeContratSociete}">
									<option value="${listTypeContratSociete.libelle}">${listTypeContratSociete.libelle}</option>
								</c:forEach>
							</select> <span class="erreur text-danger ">${form.erreurs['type_contrat']}</span>
						</div>
					</div>

					<div class="form-group row">
						<div class="col-5">
							Owner <select name="collaborateurInfo" id="collaborateurInfo" disabled="disabled"
								class="col-8 form-control">
								<option
									value="${contrat.id_referent_collaborateur}_${contrat.collaborateur}"
									selected>${contrat.collaborateur}</option>
								<c:forEach var="listCollaborateur" items="${listCollaborateur}">
									<option
										value="${listCollaborateur.id}_${listCollaborateur.nom}">${listCollaborateur.nom}</option>
								</c:forEach>
							</select> <span class="erreur text-danger ">${form.erreurs['collaborateur']}</span>
						</div>
						<div class="col-5">
							Date de démarrage<input type="date" class="form-control" disabled="disabled"
								name="date_demarrage" value=${contrat.date_demarrage}>
							<span class="erreur text-danger ">${form.erreurs['date_demarrage']}</span>
						</div>
					</div>
					<div class="comment-section">
						Commentaire
						<div class="form-group">
							<textarea class="form-control" name="commentaire" rows="3" disabled="disabled">${contrat.commentaire}</textarea>
							<span class="erreur text-danger ">${form.erreurs['commentaire']}</span>
						</div>
					</div>
				</div>


				<div
					class="col-lg-4-md-4 col-sm-4 container justify-content-center card">
					<div class="container mt-5">
						<div class="row justify-content-center">
							<div class="col-md-10">
								<div class="comment-section">
									<div class="form-group">contrat</div>
											<c:if test="${ contrat.document != 'blank' }">
												<!-- Contenu à afficher si href n'est pas égal à 'blank' -->
												<div class="form-group text-center">
													<a
														href="${contrat.cheminRelatif}${contrat.document}"
														target="_blank" id="pdfLink" class="hidden"> <img
														src="images/pdf.png" alt="PDF" class="pdf-thumbnail">
													</a>
													<p>${contrat.cheminRelatif}${contrat.document}
														?${System.currentTimeMillis()}</p>
													<input type="hidden" class="form-control" id="document"
														name="document" value="${contrat.document}" readonly>
												</div>
											</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>

			<div class="box-footer align-self-center">
				<button type="submit" name="action" value="submitForm"
					class="btn btn-primary">Valider</button>
				<a href=gestionContrat?ACTION=ANNULER class="btn btn-danger">Annuler
				</a>
			</div>
		</form>
	</div>


</body>

<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
	<h1 class="text-center text-white"></h1>
</nav>
</body>

</html>
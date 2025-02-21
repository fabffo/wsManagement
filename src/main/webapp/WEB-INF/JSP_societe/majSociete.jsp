<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP SOCIETE MAJ                                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maj type contrat</title>
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
			<li class="nav-item text-nowrap"><a class="nav-link" href="#"><c:out
						value="${param.id}" /></a></li>
		</ul>
	</nav>
	<br>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-6-md-6 col-sm-6 container justify-content-center card">
				<h1 class="text-center btn btn-dark">MAJ SOCIETE ${societe.id}</h1>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<form method="POST" action="majSociete?id=${societe.id}">
			<div class="row">

				<div
					class="col-lg-7-md-7 col-sm-7 container justify-content-center card">

					<div class="form-group row">

						<div class="col-5">
							Raison_sociale<input type="text" class="form-control"
								name="raison_sociale"
								value=<c:out value="${societe.raison_sociale}"/>> <span
								class="erreur text-danger ">${form.erreurs['raison_sociale']}</span>
						</div>
						<div class="col-5">
							Type <input type="text" class="form-control" name="type"
								value=<c:out value="${societe.type}"/>> <span
								class="erreur text-danger ">${form.erreurs['type']}</span>
						</div>

					</div>

					<div class="form-group row">
						<div class="col-12">
							Adresse
							<textarea class="form-control" name="adresse" rows="1" cols="12"
								style="align-content: left";><c:out
									value="${societe.adresse}" /> </textarea>
							<span class="erreur text-danger ">${form.erreurs['adresse']}</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-3">
							Code postal<input type="text" name="code_postal"
								class="form-control"
								value=<c:out value="${societe.code_postal}" />> <span
								class="erreur text-danger ">${form.erreurs['code_postal']}</span>
						</div>
						<div class="col-5">
							Ville<input type="text" name="ville" class="form-control"
								value=<c:out value="${societe.ville}" />> <span
								class="erreur text-danger ">${form.erreurs['ville']}</span>
						</div>
						<div class="col-4">
							Pays<input type="text" name="pays" class="form-control"
								value=<c:out value="${societe.pays}" />> <span
								class="erreur text-danger ">${form.erreurs['pays']}</span>
						</div>
					</div>



				</div>

				<div
					class="col-lg-5-md-5 col-sm-5 container justify-content-center card">
					<div class="form-group row">
						<div class="col-4">
							Siren<input type="text" name="siren" class="form-control"
								value=<c:out value="${societe.siren}" />> <span
								class="erreur text-danger ">${form.erreurs['siren']}</span>
						</div>
						<div class="col-8">
							Siret<input type="text" name="siret" class="form-control"
								value=<c:out value="${societe.siret}" />> <span
								class="erreur text-danger ">${form.erreurs['siret']}</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-4">
							Code tva<input type="text" name="code_tva" class="form-control"
								value=<c:out value="${societe.code_tva}" />> <span
								class="erreur text-danger ">${form.erreurs['code_tva']}</span>
						</div>
						<div class="col-8">
							Code naf<input type="text" name="code_naf"
								class="form-control" value=<c:out value="${societe.code_naf}" />>
							<span class="erreur text-danger ">${form.erreurs['code_naf']}</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="metier" class="col-4 col-form-label">metier</label>
						<div class="col-8">
							<input type="text" name="metier" class="form-control"
								value=<c:out value="${societe.metier}" />> <span
								class="erreur text-danger ">${form.erreurs['metier']}</span>
						</div>
					</div>



				</div>
				<div
					class="col-lg-12-md-12 col-sm-12 container justify-content-center card">
					<div class="form-group row">
					<div class="col-12">
						Contact <select name="contact_principal" class="form-control"
							>
							<c:forEach var="listContact" items="${listContact}">
								<option ${listContact.pgmcreation}>${listContact.resume_contact}</option>
							</c:forEach>
						</select> <span class="erreur text-danger ">${form.erreurs['contact']}</span>
					</div>
					</div>
				</div>
			</div>

			<div class="box-footer align-self-center">
				<button type="submit" class="btn btn-primary">Valider</button>
				<a href=gestionSociete?ACTION=ANNULER class="btn btn-danger">Annuler
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
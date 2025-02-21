<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP CONTACT SUPPRESSION                                 ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suppression contact</title>
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
	<br><br>
	<div class="container">
		<form method="POST" action="suppressionContact?typeSociete=${typeSociete}&id=${contact.id}">
			<div class="row">
				<div class="container">
					<div class="row">
						<div
							class="col-lg-10-md-10 col-sm-10 container justify-content-center card">
							<h1 class="text-center btn btn-dark">SUPPRESSION CONTACT :${contact.id}</h1>
						</div>

					</div>
				</div>
				<br>
				<div
					class="col-lg-7-md-7 col-sm-7 container justify-content-center card">
					<div class="form-group row">
							<div class="col-2">
								Civilité <input type="text" class="form-control" name="civilite"
									value=<c:out value="${contact.civilite}"/> disabled="disabled"> <span
									class="erreur text-danger ">${form.erreurs['civilite']}</span>
							</div>
							<div class="col-5">
								Nom<input type="text" class="form-control" name="nom"
									value=<c:out value="${contact.nom}"/> disabled="disabled"> <span
									class="erreur text-danger ">${form.erreurs['nom']}</span>
							</div>
							<div class="col-5">
								Prenom <input type="text" class="form-control" name="prenom"
									value=<c:out value="${contact.prenom}"/> disabled="disabled"> <span
									class="erreur text-danger ">${form.erreurs['prenom']}</span>
							</div>

						</div>

						<div class="form-group row">
							<div class="col-12">
								Adresse
								<textarea class="form-control" name="adresse" rows="1" cols="12"
									style="align-content: left"; disabled="disabled"><c:out
										value="${contact.adresse}" /></textarea>
								<span class="erreur text-danger ">${form.erreurs['adresse']}</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-3">
								Code postal<input type="text" name="code_postal"
									class="form-control"
									value=<c:out value="${contact.code_postal}" /> disabled="disabled"><span
									class="erreur text-danger ">${form.erreurs['code_postal']}</span>
							</div>
							<div class="col-5">
								Ville<input type="text" name="ville" class="form-control"
									value=<c:out value="${contact.ville}" /> disabled="disabled"> <span
									class="erreur text-danger ">${form.erreurs['ville']}</span>
							</div>
							<div class="col-4">
								Pays<input type="text" name="pays" class="form-control"
									value=<c:out value="${contact.pays}" /> disabled="disabled"> <span
									class="erreur text-danger ">${form.erreurs['pays']}</span>
							</div>
						</div>
				</div>
				<div
					class="col-lg-5-md-5 col-sm-5 container justify-content-center card">
					<div class="form-group row">
						<div class="col-6">
							Société <select name="societe" class="form-control">
								<option selected>${contact.societe}</option>
								<c:forEach var="listSociete" items="${listSociete}">
									<option>${listSociete.raison_sociale}</option>
								</c:forEach>
							</select>
						</div>
						</div>
						<div class="form-group row">
							<div class="col-4">
								Telephone<input type="text" name="telephone"
									class="form-control"
									value=<c:out value="${contact.telephone}" /> disabled="disabled"> <span
									class="erreur text-danger ">${form.erreurs['telephone']}</span>
							</div>
							<div class="col-8">
								Email<input type="text" name="email" class="form-control"
									value=<c:out value="${contact.email}" /> disabled="disabled">  <span
									class="erreur text-danger ">${form.erreurs['email']}</span>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-4">
								Telephone 2<input type="text" name="telephone2"
									class="form-control"
									value=<c:out value="${contact.telephone_secondaire}" /> disabled="disabled">
								<span class="erreur text-danger ">${form.erreurs['telephone2']}</span>
							</div>
							<div class="col-8">
								Email 2<input type="text" name="email2"
									class="form-control"
									value=<c:out value="${contact.email_secondaire}" /> disabled="disabled"> <span
									class="erreur text-danger ">${form.erreurs['email2']}</span>
							</div>
						</div>

				</div>
				</div>
				<div class="box-footer align-self-center">
					<button type="submit" class="btn btn-primary">Valider</button>
					<a href=gestionContact?ACTION=ANNULER class="btn btn-danger">Annuler
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
<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP Contrat SUPPRESSION                           ///
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
				<h1 class="text-center btn btn-danger">SUPPRESSION Contrat ${Contrat.id}</h1>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<form method="POST" action="suppressionContrat?id=${Contrat.id}">
			<div class="row">

				<div
					class="col-lg-7-md-7 col-sm-7 container justify-content-center card">

					<div class="form-group row">
						<div class="col-2">Civilité
							<input type="text" class="form-control" name="statut"
								value=<c:out value="${Contrat.statut}"/> disabled="disabled" > <span
								class="erreur text-danger ">${form.erreurs['statut']}</span>
						</div>
						<div class="col-5">
							Id_avenant<input type="text" class="form-control" name="version"
								value=<c:out value="${Contrat.version}"/> disabled="disabled" > <span
								class="erreur text-danger ">${form.erreurs['version']}</span>
						</div>
						<div class="col-5">Type_contrat
							<input type="text" class="form-control" name="type_contrat"
								value=<c:out value="${Contrat.type_contrat}"/> disabled="disabled" > <span
								class="erreur text-danger ">${form.erreurs['type_contrat']}</span>
						</div>
						
					</div>

					<div class="form-group row">
						<div class="col-12">Date_debut
							<textarea class="form-control" name="date_debut" rows="1" cols="12" style="align-content:left" disabled="disabled"><c:out value="${Contrat.date_debut}"/>  </textarea> <span
								class="erreur text-danger ">${form.erreurs['date_debut']}</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-3">
							Code postal<input type="text" name="date_fin" class="form-control" disabled="disabled"
								value=<c:out value="${Contrat.date_fin}" /> > <span
								class="erreur text-danger ">${form.erreurs['date_fin']}</span>
						</div>
						<div class="col-5">
							Id_avenant_contrat<input type="text" name="version_contrat" class="form-control" disabled="disabled"
								value=<c:out value="${Contrat.version_contrat}" /> > <span
								class="erreur text-danger ">${form.erreurs['version_contrat']}</span>
						</div>
						<div class="col-4">
							Prix_ht<input type="text" name="prix_ht" class="form-control" disabled="disabled"
								value=<c:out value="${Contrat.prix_ht}" /> > <span
								class="erreur text-danger ">${form.erreurs['prix_ht']}</span>
						</div>
					</div>
					
					<div class="form-group row">
						<div class="col-4">
							Date_signature<input type="text" name="date_signature" class="form-control" disabled="disabled"
								value=<c:out value="${Contrat.date_signature}" /> > <span
								class="erreur text-danger ">${form.erreurs['date_signature']}</span>
						</div>
						<div class="col-8">
							Id_societe<input type="text" name="id_societe" class="form-control" disabled="disabled"
								value=<c:out value="${Contrat.id_societe}" /> > <span
								class="erreur text-danger ">${form.erreurs['id_societe']}</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-4">
						Date_signature secondaire<input type="text" name="date_signature2" class="form-control" disabled="disabled"
								value=<c:out value="${Contrat.nom_contrat}" /> > <span
								class="erreur text-danger ">${form.erreurs['date_signature2']}</span>
						</div>
						<div class="col-8">
							Id_societe secondaire<input type="text" name="id_societe2" class="form-control" disabled="disabled" 
								value=<c:out value="${Contrat.id_societe_secondaire}" />> <span
								class="erreur text-danger ">${form.erreurs['id_societe2']}</span>
						</div>
					</div>
					
				</div>

				<div
					class="col-lg-5-md-5 col-sm-5 container justify-content-center card">
					<div class="form-group row">
						<label for="duree_contrat" class="col-4 col-form-label">né(e)le</label>
						<div class="col-8">
							<input type="text" class="form-control"
								name="duree_contrat" disabled="disabled"
								value=<c:out value="${Contrat.duree_contrat}"/>  >
							<span class="erreur text-danger ">${form.erreurs['duree_contrat']}</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="id_type_prix" class="col-4 col-form-label">id_type_prix</label>
						<div class="col-8">
							<input type="text" name="id_type_prix" class="form-control" disabled="disabled"
								value=<c:out value="${Contrat.id_type_prix}" /> > <span
								class="erreur text-danger ">${form.erreurs['id_type_prix']}</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="document" class="col-4 col-form-label">contrat</label>
						<div class="col">
							<input name="document" class="col-8 form-control" disabled="disabled" value="${Contrat.document}"  >
						</div>
					</div>
					<div class="form-group row">
						<label for="type_Contrat" class="col-4 col-form-label">en</label>
						<div class="col">
						<input name="document" class="col-8 form-control" disabled="disabled" value="${Contrat.type_Contrat}"  >
						</div>
					</div>
					<div class="form-group row">
						<label for="Signataire_oui_non" class="col-4 col-form-label">Signataire contrat</label>
						<div class="col">
						<input name="document" class="col-8 form-control" value="${signataire} " disabled="disabled" >
						</div>
					</div>
					<div class="form-group row">
						<label for="utilisateur" class="col-4 col-form-label">droits IT</label>
						<div class="col">
						<input name="document" class="col-8 form-control" value="${Contrat.utilisateur}" disabled="disabled" >
						</div>
					</div>		
					Activité
						<div class="form-group row">
						<input name="document" class="col-12 form-control" value="${Contrat.societe}" disabled="disabled" >
					</div>
				</div>

			</div>


			<div class="box-footer align-self-center">
				<button type="submit" class="btn btn-primary">Valider</button>
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
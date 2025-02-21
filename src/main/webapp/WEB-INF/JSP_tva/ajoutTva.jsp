<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP TVA CREATION                                        ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout tva</title>
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
			<li class="nav-item text-nowrap"><a class="nav-link" href="#">Signout</a></li>
		</ul>
	</nav>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-6-md-6 col-sm-6 container justify-content-center card">
				<h1 class="text-center btn btn-dark">Ajout tva</h1>
				<div class="card-body">
					<form method="POST" action="ajoutTva">
						<div class="form-group row">
							<label for="nom " class="col-auto col-form-label">Nom</label>
							<div class="col">
								<input type="text" class="form-control" name="nom" required
									step ="" placeholder="nom" value=${tva.nom} > <span
									class="erreur text-danger ">${form.erreurs['nom']}</span>
							</div>
						</div>
						<div class="form-group row">
							<label for="libelle" class="col-auto col-form-label">Libelle</label>
							<div class="col">
								<input type="text" class="form-control" name="libelle"
									placeholder="" value=${tva.libelle}> <span
									class="erreur text-danger ">${form.erreurs['libelle']}</span>
							</div>
						</div>
						<div class="form-group row">
							<label for="taux" class="col-2 col-form-label">taux</label>
							<div class="col">
								<input type="number" step="0.01" placeholder="0,00"
									class="form-control" name="taux" placeholder=""
									value=${tva.taux}> <span class="erreur text-danger ">${form.erreurs['taux']}</span>
							</div>
						</div>
						<div class="box-footer">
							<button type="submit" class="btn btn-primary">Valider</button>
							<a href=gestionTva?ACTION=ANNULER class="btn btn-danger">Annuler
							</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
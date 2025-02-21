<!/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP ACCUEIL                                             ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="styleWSSM.css" />
<link rel="stylesheet" href="menu-style.css" />
<script src="menu-script.js" defer></script>
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
	<div class="container d-flex flex-wrap align-items-center">

		<div class="row">
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-gear-fill couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Général</h5>
					<div class="btn-group" role="group">
						<a class="btn btn-dark m-1" href="gestionTva" role="button">Tva</a>
						<a class="btn btn-dark m-1" href="gestionCalendrier" role="button">Calendrier</a>
					</div>
					<div class="btn-group" role="group">
						<a class="btn btn-dark m-1" href="gestionMetier" role="button">Métier</a>
						<a class="btn btn-dark m-1" href="gestionUtilisateur"
							role="button">Utilisateur</a>
					</div>
				</div>
			</div>
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-gear-fill couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Type contrat</h5>
					<div class="btn-group" role="group">
						<a href="gestionTypeContratCollaborateur" class="btn btn-dark m-1"
							role="button">Collaborateur</a>
					</div>
					<div class="btn-group" role="group">
						<a href="gestionTypeContratSociete" class="btn btn-dark m-1"
							role="button">Société</a>
					</div>
				</div>
			</div>
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-person-circle couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Type</h5>
					<div class="btn-group" role="group">
						<a href="gestionTypeSociete" class="btn btn-dark m-1"
							role="button">Société</a><br> <a href="gestionTypePrix"
							class="btn btn-dark m-1" role="button">Prix</a>
					</div>
					<div class="btn-group" role="group">
						<a href="gestionTypeDocument" class="btn btn-dark m-1"
							role="button">Document</a>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-person-circle couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Entité</h5>

					<div class="btn-group" role="group">
						<a href="gestionCollaborateur" class="btn btn-dark m-1"
							role="button">Collaborateur</a><br> <a
							href="gestionSociete?typeSociete=SOCIETE"
							class="btn btn-dark m-1" role="button">Société</a>
					</div>
					<div class="btn-group" role="group">

						<a href="gestionSociete?typeSociete=CLIENT"
							class="btn btn-dark m-1" role="button">Client</a> <a
							class="btn btn-dark m-1"
							href="gestionSociete?typeSociete=FOURNISSEUR" role="button">Fournisseur</a>
					</div>
				</div>
			</div>
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-person-circle couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Contact</h5>
					<div class="btn-group" role="group">
						<a href="gestionContact?typeSociete=SOCIETE"
							class="btn btn-dark m-1" role="button">Société</a> <a
							href="gestionContact?typeSociete=CLIENT" class="btn btn-dark m-1"
							role="button">Client</a>
					</div>
					<div class="btn-group" role="group">
						<a href="gestionContact?typeSociete=FOURNISSEUR"
							class="btn btn-dark m-1" role="button">Fournisseur</a>
					</div>

				</div>
			</div>
		</div>
		<div class="row">

			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-file-earmark-text couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Contrat</h5>
					<div class="btn-group" role="group">
						<a href="gestionContrat?type_entite=CLIENT" class="btn btn-dark me-1"
							role="button">Client</a>
							<a href="gestionContrat?type_entite=FOURNISSEUR"
							class="btn btn-dark me-1" role="button">Fournisseur</a>
					</div>
					<div class="btn-group" role="group">
						<a href="gestionContrat?type_entite=SALARIE"
							class="btn btn-dark me-1" role="button">Salarié</a>
							<a href="gestionContrat?type_entite=COLLABORATEUR"
							class="btn btn-dark me-1" role="button">Prestataire</a>
					</div>
				</div>
			</div>
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-calendar-week-fill couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Mission</h5>
					<div class="btn-group" role="group">
						<a href="gestionMission?type_entite=CLIENT" class="btn btn-dark me-1"
							role="button">Client</a>
							<a href="gestionCra"
							class="btn btn-dark me-1" role="button">CRA</a>
					</div>
					<div class="btn-group" role="group">
						<a href="gestionMission?type_entite=SALARIE"
							class="btn btn-dark me-1" role="button">Salarié</a>
							<a href="gestionMission?type_entite=COLLABORATEUR"
							class="btn btn-dark me-1" role="button">Prestataire</a>
					</div>
				</div>
			</div>
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-gear-fill bi--5xl couleurWavy"></i>
					</h1>
					<h4
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						banque
						</h5>

						<div class="btn-group" role="group">
							<a href="gestionEcritureBanque_import" class="btn btn-dark m-1" role="button">relevés</a>
						</div>
						<div class="btn-group" role="group">
							<a href="rapprochementEcritureBanque" class="btn btn-dark m-1" role="button">rapprochement</a>
						</div>
				</div>
			</div>
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-calendar-week-fill couleurWavy"></i>
					</h1>
					<h4
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Gérer impôt
						</h5>

						<div class="btn-group" role="group">
							<a href="gestionParametre?parametre_nom=Tva" class="btn btn-dark m-1" role="button">salaires</a><a
								href="#link" class="btn btn-dark m-1" role="button">bénéfices</a>
						</div>
						<div class="btn-group" role="group">
							<a href="#link" class="btn btn-dark m-1" role="button">dividendes</a><a
								href="#link" class="btn btn-dark m-1" role="button">tva</a>
						</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-calendar-week-fill couleurWavy"></i>
					</h1>
					<h5
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Gérer charges</h5>

					<div class="btn-group" role="group">
						<a href="gestionFactureAchat" class="btn btn-dark m-1" role="button">services</a><a
							href="#link" class="btn btn-dark m-1" role="button">frais</a>
					</div>
					<div class="btn-group" role="group">
						<a href="#link" class="btn btn-dark m-1" role="button">mensuelles</a><a
							href="#link" class="btn btn-dark m-1" role="button">salaires</a>
					</div>
				</div>
			</div>
			<div class="card text-center m-3 bg-light border rounded"
				style="width: 15rem;">
				<div class="card-body mr-2 pr-2 ">
					<h1 class="card-title col d-flex justify-content-center">
						<i class="bi bi-gear-fill bi--5xl couleurWavy"></i>
					</h1>
					<h4
						class="card-subtitle mb-2 text-black col d-flex justify-content-center">
						Gérer ventes
						</h5>
						<p class="card-text"></p>
						<div class="btn-group" role="group">
							<a href="#link" class="btn btn-dark m-1" role="button">services</a><a
								href="#link" class="btn btn-dark m-1" role="button">frais</a>
						</div>
						<div class="btn-group" role="group">
							<a href="#link" class="btn btn-dark m-1" role="button">résultat</a>
						</div>
				</div>
			</div>

		</div>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>


	<!-- Icons -->
	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
	<script>
      feather.replace()
    </script>
</body>
</html>

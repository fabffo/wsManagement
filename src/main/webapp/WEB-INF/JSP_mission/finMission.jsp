<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP MISSION FIN                                         ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Annulation mission</title>
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
				<h1 class="text-center btn btn-dark">FIN MISSION ${type_entite} ${mission.id}</h1>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<form method="POST" action="finMission"
			enctype="multipart/form-data">

			<div class="row">
				<div
					class="col-lg-12-md-12 col-sm-12 container justify-content-center card">
						<div class="form-group row">
						<div class="col-10">
							Contrat <input type="text" class="form-control" name="contrat" readonly
								value=<c:out value="${contrat.commentaire}"/> >
								 <span class="erreur text-danger ">${form.erreurs['contrat']}</span>
						</div>
						<div class="col-2">
							Statut <input type="text" class="form-control" name="statut" readonly
								value=<c:out value="${mission.statut}"/> > <span
								class="erreur text-danger ">${form.erreurs['statut']}</span>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-5">
							Nom <input type="text" class="form-control" name="nom"
								value=<c:out value="${mission.nom}"/> readonly> <span
								class="erreur text-danger ">${form.erreurs['nom']}</span>
						</div>
						<div class="col-5">
							Complément <input type="text" class="form-control"
								name="complement" value=<c:out value="${mission.complement}"/> readonly>
							<span class="erreur text-danger ">${form.erreurs['complement']}</span>
						</div>
						<div class="col-2">
							Date de démarrage<input type="date" class="form-control"
								name="date_demarrage" value=${mission.date_demarrage} readonly> <span
								class="erreur text-danger ">${form.erreurs['date_demarrage']}</span>
						</div>
					</div>
					<div class="form-group row">
					<div class="col-3">
							prix HT <input type="text" class="form-control"
								name="prix_ht" value=<c:out value="${mission.prix_ht}"/> readonly>
							<span class="erreur text-danger ">${form.erreurs['prix_ht']}</span>
						</div>
						<div class="col-3">
							Type de prix
							<textarea class="form-control"
								name="typePrix"  rows="1" readonly>${typePrix.libelle}</textarea>
							 <span class="erreur text-danger ">${form.erreurs['type_prix']}</span>
						</div>
						<div class="col-3">
							Tva <textarea class="form-control"
								name="tva"  rows="1" readonly>${tva.libelle}</textarea>
							<span class="erreur text-danger ">${form.erreurs['tva']}</span>
						</div>
						<div class="col-3">
							nbr factures <input type="text" class="form-control"
								name="nbr_facture" value=<c:out value="${mission.nbr_facture}"/> readonly>
							<span class="erreur text-danger ">${form.erreurs['nbr_facture']}</span>
						</div>
					</div>
					<div class="comment-section">
							Commentaire
						<div class="form-group">
							<textarea class="form-control" name="commentaire" rows="3" readonly> ${mission.commentaire} </textarea>
							<span class="erreur text-danger ">${form.erreurs['commentaire']}</span>
						</div>
					</div>
				</div>

			</div>

			<div class="box-footer align-self-center">
				<button type="submit" name="action" value="submitForm"
					class="btn btn-primary">Valider</button>
				<a href=gestionMission?ACTION=ANNULER class="btn btn-danger">Annuler
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
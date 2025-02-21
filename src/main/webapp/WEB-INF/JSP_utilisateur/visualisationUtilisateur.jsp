<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP TYPE SOCIETE VISUALISATION                          ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualisation type mission</title>
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
			<li class="nav-item text-nowrap"><a class="nav-link" href="#">Sign
					out</a></li>
		</ul>
	</nav>
	<br>
	<br>
	<div class="row flex-lg-nowrap w-80">
		<div
			class="col-6 container justify-content-center card">
			<h1 class="text-center btn btn-secondary">Visualisation type mission</h1>
			<div class="card-body">
				<form method="POST" action="gestionUtilisateur?action=VISUALISER">
					<div class="form-group row">
						<label for="id" class="col-auto col-form-label">Nom</label>
						<div class="col">
							<input type="text" class="form-control" name="id"
								value=<c:out value="${utilisateur.id}"/> disabled="disabled" />
						</div>
					</div>
					<div class="form-group row">
						<label for="nom" class="col-auto col-form-label">Nom</label>
						<div class="col">
							<input type="text" class="form-control" name="nom"
								value=<c:out value="${utilisateur.nom}"/> disabled="disabled" />
						</div>
					</div>
					<div class="form-group row">
						<label for="libelle" class="col-auto col-form-label">libelle</label>
						<div class="col">
							<textarea type="text" class="form-control" name="libelle"
								disabled="disabled"><c:out
									value="${utilisateur.libelle}" /></textarea>
						</div>
					</div>
					
					<div class="box-footer">
						<a href=gestionUtilisateur?ACTION=ANNULER class="btn btn-danger">Annuler
						</a>
					</div>
				</form>
			</div>
		</div>
		<div
			class="col-4 container justify-content-right align-self-end card h-25">
			<h1 class="text-center">Suivi </h1>
			<div class="card-body h-25">
			<div class="col">
			<p>	créé par <c:out value="${utilisateur.usercreation}"/> le 
				<c:out value="${utilisateur.datecreation}"/>
				par le pgm <c:out value="${utilisateur.pgmcreation}" />
					</p>
					</div>
								<div class="col">
			<p>	modifié par <c:out value="${utilisateur.usermodification}"/> le 
				<c:out value="${utilisateur.datemodification}"/>
				par le pgm <c:out value="${utilisateur.pgmmodification}" />
					</p>
					</div>
			</div>		</div>

	</div>
	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
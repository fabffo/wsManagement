<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP TYPEDOCUMENT VISUALISATION                                   ///
////    Cr�� par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifi� par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualisation type de document</title>
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
			<h1 class="text-center btn btn-secondary">Visualisation type de document</h1>
			<div class="card-body">
				<form method="POST" action="gestionTypeDocument?action=VISUALISER">
					<
					<div class="form-group row">
						<label for="contrat" class="col-sm-3 col-form-label">Contrat</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="contrat"
								placeholder="" value=${typeDocument.contrat}> <span
								class="erreur text-danger ">${form.erreurs['contrat']}</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="cheminRelatif " class="col-sm-3 col-form-label">Chemin
							relatif</label>
						<div class="col-sm-9">

							<input type="text" class="form-control" name="cheminRelatif"
								placeholder="" value=${typeDocument.cheminRelatif}> <span
								class="erreur text-danger ">${form.erreurs['cheminRelatif']}</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="cheminAbsolu" class="col-sm-3 col-form-label">Chemin
							absolu</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="cheminAbsolu"
								placeholder="" value=${typeDocument.cheminAbsolu}> <span
								class="erreur text-danger ">${form.erreurs['cheminAbsolu']}</span>
						</div>
					</div>
					<div class="box-footer">
						<a href=gestionTypeDocument?ACTION=ANNULER class="btn btn-primary">Valider
						</a>
						<a href=gestionTypeDocument?ACTION=ANNULER class="btn btn-danger">Annuler
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
			<p>	cr�� par <c:out value="${typeDocument.usercreation}"/> le 
				<c:out value="${typeDocument.datecreation}"/>
				par le pgm <c:out value="${typeDocument.pgmcreation}" />
					</p>
					</div>
								<div class="col">
			<p>	modifi� par <c:out value="${typeDocument.usermodification}"/> le 
				<c:out value="${typeDocument.datemodification}"/>
				par le pgm <c:out value="${typeDocument.pgmmodification}" />
					</p>
					</div>
			</div>		</div>

	</div>
	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP GESTION FACTURE ACHAT                               ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer facture achat</title>
<link rel="stylesheet" href="styleWSSM.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
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
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="accueil">Accueil</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="gestionTva">banque</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">

		<div class="row">
			<h1 class="text-center">Gestion facture achat</h1>
		</div>



    <div class="pdf-importation-container p-4 mt-2 mb-2">
        <h2 class="text-center">Importer une facture PDF</h2>

    <!-- Formulaire d'importation -->
    <form method="POST" action="importFactureAchat" enctype="multipart/form-data" id="uploadForm">
        <div class="form-row align-items-center">
            <div class="col-md-2">
                <label for="entite" class="sr-only">Entité</label>
                <select class="form-control" id="entite" name="entite" required>
                    <option value="">Sélectionner une entité</option>
                    <option value="Client">Client</option>
                    <option value="Fournisseur">Fournisseur</option>
                    <option value="Prestataire">Prestataire</option>
                </select>
            </div>

            <div class="col-md-2">
                <label for="nomEntite" class="sr-only">Nom de l'entité</label>
                <select class="form-control" id="nomEntite" name="nomEntite" required>
                    <option value="">Sélectionner un nom d'entité</option>
                    <option value="SocieteA">Societe A</option>
                    <option value="SocieteB">Societe B</option>
                    <option value="SocieteC">Societe C</option>
                </select>
            </div>

            <div class="col-md-2">
                <label for="montantTTC" class="sr-only">Montant TTC</label>
                <input type="number" class="form-control" id="montantTTC" name="montantTTC" placeholder="Montant TTC" required>
            </div>

            <div class="col-md-2">
                <label for="date" class="sr-only">Date</label>
                <input type="date" class="form-control" id="date" name="date" required>
            </div>

            <!-- Champ caché pour stocker le nom du fichier généré -->
            <input type="hidden" id="fileName" name="fileName">

            <div class="col-md-4">
                <!-- Champ de fichier masqué -->
                <input type="file" class="form-control-file d-none" id="file" name="file" accept="application/pdf" required>

                <!-- Bouton unique pour sélection et soumission -->
                <button type="button" class="btn btn-primary btn-block" onclick="validateAndTriggerFileSelection()">Sélectionner et importer le fichier PDF</button>
            </div>
        </div>
    </form>
</div>

<script>
    // Fonction pour valider les champs obligatoires avant de déclencher la sélection du fichier
    function validateAndTriggerFileSelection() {
        // Récupérer les valeurs des champs obligatoires
        var entite = document.getElementById("entite").value;
        var nomEntite = document.getElementById("nomEntite").value;
        var montantTTC = document.getElementById("montantTTC").value;
        var date = document.getElementById("date").value;

        // Vérifier que tous les champs obligatoires sont remplis
        if (entite && nomEntite && montantTTC && date) {
            // Déclencher la boîte de dialogue pour sélectionner le fichier
            document.getElementById('file').click();
        } else {
            alert("Veuillez remplir tous les champs obligatoires avant de sélectionner un fichier.");
        }
    }

    // Lorsque le fichier est sélectionné, générer le nom du fichier et soumettre le formulaire
    document.getElementById('file').addEventListener('change', function() {
        // Vérifier si un fichier a bien été sélectionné
        if (this.files && this.files.length > 0) {
            // Récupérer les valeurs des autres champs
            var entite = document.getElementById("entite").value;
            var nomEntite = document.getElementById("nomEntite").value;
            var montantTTC = document.getElementById("montantTTC").value;
            var date = document.getElementById("date").value;

            // Générer le nom de fichier
            var fileName = entite + "_" + nomEntite + "_" + montantTTC + "_" + date + ".pdf";

            // Assigner le nom généré au champ caché
            document.getElementById("fileName").value = fileName;

            // Soumettre automatiquement le formulaire
            document.getElementById('uploadForm').submit();
        }
    });
</script>



		<div class="container">
			<div class="row">
				<div class="col-md-6">
		<form id="uploadForm" action="importEcritureBanque_import" method="post"  enctype="multipart/form-data">
    <div class="form-group text-left">
        <input type="file" name="fichier" id="fichier" style="display:none;" onchange="if(this.files.length > 0) { document.getElementById('uploadForm').submit(); }">
        <button type="button" value="import" onclick="document.getElementById('fichier').click();">importer relevé</button>
    </div>
</form>
				</div>

				<div class="col-md-6">
					<!-- Barre de recherche à droite avec zone de sélection -->
					<form class="form-inline float-md-right" method="GET"
						action="gestionEcritureBanque_import">
						<div class="form-group mr-2">
							<select class="form-control" id="critereEcritureBanque_import"
								name="critereEcritureBanque_import">
								<option ${sel_nom} value="nom_import">nom import</option>
								<option ${sel_nbr} value="nbr_lignes">nbr de lignes</option>
								<option ${sel_date} value="date_import">date import</option>
								<option ${sel_user} value="user_import">utilisateur</option>
							</select>
						</div>
						<div class="form-group mr-2">
							<input class="form-control" type="searchEcritureBanque_import"
								name="searchEcritureBanque_import" placeholder="Recherche"
								aria-label="Search" value="${searchEcritureBanque_import}">
						</div>
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit" value="rechercher" >Rechercher</button>
					</form>
				</div>

			</div>
		</div>

		<table class="table table-striped">
			<thead class="thead-dark justify-content-center">
				<tr>
					<th class="col-md-2"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=DATE&triEcritureBanque_import_date=${triEcritureBanque_import_date}"><i
							class="${triEcritureBanque_import_date}" /></i></a>Le</th>
							<th class="col-md-3"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=NOM&triEcritureBanque_import_nom=${triEcritureBanque_import_nom}"><i
							class="${triEcritureBanque_import_nom}" /></i></a>nom</th>
					<th class="col-md-2"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=USER&triEcritureBanque_import_user=${triEcritureBanque_import_user}"><i
							class="${triEcritureBanque_import_user}" /></i></a>Par</th>
							<th class="col-md-1"><a
						href="gestionEcritureBanque_import?triEcritureBanque_import=LIGNES&triEcritureBanque_import_lignes=${triEcritureBanque_import_lignes}"><i
							class="${triEcritureBanque_import_lignes}" /></i></a>Lignes</th>
					<th class="col-md-2">Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="ecriture" items="${EcritureBanque_importList}">
					<tr>
						<td ><c:out value="${ ecriture.date_import }" /></td>
						<td ><c:out value="${ ecriture.nom_import }" /></td>
						<td ><c:out value="${ ecriture.user_import }" /></td>
						<td ><c:out value="${ ecriture.nbr_ligne }" /></td>
						<td><a href="visualisationEcritureBanque_import?id_ecritureBanque=${ ecriture.id }"
							class="btn btn-outline-secondary" data-toggle="tooltip"
							data-placement="top" title="VISUALISER"><i class="bi bi-eye"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-end">
				<c:set var="noBegin" value="${currentPage-pasPage+1}" />
				<c:choose>
					<c:when test="${noBegin le 0}">
						<c:set var="noBegin" value="${1}" />
					</c:when>
				</c:choose>
				<c:set var="maxPage" value="${noBegin+pasPage}" />
				<c:choose>
					<c:when test="${maxPage ge noOfPages}">
						<c:set var="maxPage" value="${noOfPages}" />
					</c:when>
				</c:choose>
				<li class="page-item"><c:choose>
						<c:when test="${currentPage gt 1}">
							<a class="page-link"
								href="gestionTva?currentPage=${currentPage - 1}">Previous</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose></li>

				<c:forEach begin="${noBegin}" end="${maxPage}" var="i">
					<c:choose>
						<c:when test="${currentPage != i}">
							<li class="page-item"><a class="page-link"
								href="gestionTva?currentPage=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link"
								href="gestionTva?currentPage=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt noOfPages}">
					<li class="page-item"><a class="page-link"
						href="gestionTva?currentPage=${currentPage + 1}">Next</a></li>
				</c:if>

			</ul>
		</nav>
	</div>

	<nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
		<h1 class="text-center text-white"></h1>
	</nav>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Détail de la section avec bouton et formulaire -->
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <a class="btn En-cours btn-link btn p-2" href="crudParametre?parametre_nom_programme=ajout&entite_id=0&parametre_nom=${parametre_nom}">
                <i class="bi bi-plus-square" data-toggle="tooltip" data-placement="top" title="AJOUTER"></i>
            </a>
        </div>
        <div class="col-md-9">
            <form class="form-inline float-md-right" method="GET" action="gestionParametre">
                <!-- Champ caché pour garantir currentPage=1 -->
    			<input type="hidden" name="currentPage" value="1">
                <!-- Bouton avec une image (icône de réinitialisation) -->
                <button type="submit" name="action" value="AZERO" class="btn En-cours btn-link p-2" title="Réinitialiser">
                    <i class="bi bi-arrow-clockwise"></i>
                </button>
                <!-- Barre de recherche à droite avec zone de sélection -->
                <div class="form-group mr-2">
                    <select class="form-select" id="${critere_recherche_libelle}" name="${critere_recherche_libelle}">
    					<c:forEach var="listZonesDeRecherche" items="${listZonesDeRecherche}">
        				<option ${listZonesDeRecherche.selected} value="${listZonesDeRecherche.nom}">${listZonesDeRecherche.nom}</option>
    					</c:forEach>
                    </select>
                </div>
                <div class="form-group mr-2">
                    <input class="form-control" type="searchValeur" name="searchValeur" placeholder="Recherche" aria-label="Search" value="${searchValeur}">
                </div>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
            </form>
        </div>
    </div>
</div>

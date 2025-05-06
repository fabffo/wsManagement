<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Tableau de gestion Parametre -->
<table class="table table-striped">
    <thead class="thead-dark justify-content-center">
        <tr>
        <c:forEach var="tableLigneTh" items="${listEcranGestion_tableLigneTh}">
        <th class="col-md-${tableLigneTh.largeur_colonne}"><a href="${tableLigneTh.ref}">
                <i class="${tableLigneTh.zone_i}"></i></a>${tableLigneTh.nom_colonne}</th>
         </c:forEach>
         <th class="col-md-3">Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="entite" items="${listEntite}">
            <tr>
                <c:forEach var="entry" items="${entite}">
               <c:choose>
    <c:when test="${entry.key == 'document'}">
        <td>
            <a href="${entry.value}" class="btn Terminé" target="_blank">
                <i class="bi bi-file-earmark-pdf" data-toggle="tooltip"
                   data-placement="top" title="Contrat"></i>
            </a>
        </td>
    </c:when>
    <c:otherwise>
        <td><c:out value="${entry.value}" /></td>
    </c:otherwise>
</c:choose>
                </c:forEach>
                <td>
                 <c:if test="${actionsDisponibles['modifier']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=maj&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn En-cours btn-link p-2">
                        <i class="bi bi-pencil-square" data-toggle="tooltip" data-placement="top" title="MAJ"></i>
                    </a>
                      </c:if>
                      <c:if test="${actionsDisponibles['copier']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=copie&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn En-cours btn-link p-2">
                        <i class="bi bi-book-half" data-toggle="tooltip" data-placement="top" title="COPIER"></i>
                    </a>
                     </c:if>
                     <c:if test="${actionsDisponibles['visualiser']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=visualisation&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn Visualisé btn-link btn p-2" data-toggle="tooltip" data-placement="top" title="VISUALISER">
                        <i class="bi bi-eye"></i>
                    </a>
                    </c:if>
                    <c:if test="${actionsDisponibles['supprimer']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=suppression&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn Terminé btn-link btn p-2">
                        <i class="bi bi-trash" data-toggle="tooltip" data-placement="top" title="SUPPRIMER"></i>
                    </a>
                   </c:if>
                    <c:if test="${actionsDisponibles['renommer']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=renommage&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn En-cours btn-link btn p-2">
                        <i class="bi bi-pencil-fill" data-toggle="tooltip" data-placement="top" title="RENOMMER"></i>
                    </a>
                    </c:if>
                    <c:if test="${actionsDisponibles['faire_avenant']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=avenant&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn En-cours btn-link btn p-2">
                        <i class="bi bi-file-earmark-plus" data-toggle="tooltip" data-placement="top" title="AVENANT"></i>
                    </a>
                    </c:if>
                    <c:if test="${actionsDisponibles['annuler']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=annulation&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn btn-orange btn p-2">
                        <i class="bi bi-x" data-toggle="tooltip" data-placement="top" title="ANNULER"></i>
                    </a>
                    </c:if>
                    <c:if test="${actionsDisponibles['terminer']=='True'}">
                    <a href="crudParametreMultiligne?parametre_nom_programme=fin&classe_id=${entite.id}&currentPage=${currentPage}&parametre_nom=${parametre_nom}&type_entite=${type_entite}" class="btn Terminé btn p-2">
                        <i class="bi bi-check" data-toggle="tooltip" data-placement="top" title="TERMINER"></i>
                    </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

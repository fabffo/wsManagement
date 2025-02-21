<!
/////////////////////////////////////////////////////////////////////////////
////    PROJET LOGICIEL FACTURATION COMPTABILITE                          ///
///     PROGRAMME JSP PARAMETRE GESTION SIMPLE                            ///
////    Créé par Fabrice FOUGERY le 29/04/2024                            ///
////    Modifié par ....... .... le ../../....                            ///
/////////////////////////////////////////////////////////////////////////////
-><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gérer</title>
    <link rel="stylesheet" href="styleWSSM.css" />
    <link rel="stylesheet" href="menu-style.css" />
    <script src="menu-script.js" defer></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <meta charset="utf-8" />
</head>
<body>
    <!-- Inclusion de l'entête -->
    <jsp:include page="gestionSimple_EnteteMenu.jsp" />

    <!-- Inclusion des détails -->
    <jsp:include page="gestionSimple_enteteRecherche.jsp" />

    <!-- Inclusion du tableau -->
    <jsp:include page="gestionSimple_lignesAction.jsp" />

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
								href="${nomProgramme_gestion}?currentPage=${currentPage - 1}">Previous</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose></li>

				<c:forEach begin="${noBegin}" end="${maxPage}" var="i">
					<c:choose>
						<c:when test="${currentPage != i}">
							<li class="page-item"><a class="page-link"
								href="${nomProgramme_gestion}?currentPage=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link"
								href="${nomProgramme_gestion}?currentPage=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${currentPage lt noOfPages}">
					<li class="page-item"><a class="page-link"
						href="${nomProgramme_gestion}?currentPage=${currentPage + 1}">Next</a></li>
				</c:if>

			</ul>
		</nav>


    <nav class="navbar navbar-dark flex-md-nowrap p-0 fixed-bottom">
        <h1 class="text-center text-white"></h1>
    </nav>
</body>
</html>

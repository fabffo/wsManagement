<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- Contenu de l'entête -->
<nav class="navbar navbar-dark sticky-top flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">
        <img src="images/logo.png" alt="Logo wavy" />
    </a>
    <h1 class="text-center text-white">WS SYSTEME MANAGEMENT</h1>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
        </li>
    </ul>
</nav>


    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
			  <c:forEach var="menu" items="${menuItems}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="${menu.url}"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${menu.title}
                        </a>

                        <c:if test="${!menu.children.isEmpty()}">
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:forEach var="submenu" items="${menu.children}">
                                    <c:choose>
                                        <c:when test="${!submenu.children.isEmpty()}">
                                            <!-- Sous-menu avec sous-sous-menus -->
                                            <li class="dropdown-submenu">
                                                <a class="dropdown-item dropdown-toggle" href="${submenu.url}">
                                                    ${submenu.title}
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <c:forEach var="subsubmenu" items="${submenu.children}">
                                                        <li><a class="dropdown-item" href="${subsubmenu.url}">${subsubmenu.title}</a></li>
                                                    </c:forEach>
                                                </ul>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Sous-menu sans sous-sous-menus -->
                                            <li><a class="dropdown-item" href="${submenu.url}">${submenu.title}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
			</ul>
		</div>
	</nav>

<div class="container">
    <div class="row">
        <h1 class="text-center">Gestion ${parametre_nom}</h1>
    </div>
</div>

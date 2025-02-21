<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Menu Dynamique</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
     <link rel="stylesheet" href="menu-style.css" />
    <script src="menu-script.js" defer></script>
</head>
<body>

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Menu</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu" aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav mr-auto">

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
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

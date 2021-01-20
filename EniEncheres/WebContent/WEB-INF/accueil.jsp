<%--
  Created by IntelliJ IDEA.
  User: Melanie
  Date: 19/01/2021
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<h3>ENI-Encheres</h3>
<c:if test="${ not empty utilisateurConnecte.pseudo }">
    <p>Bonjour ${utilisateurConnecte.prenom} ${utilisateurConnecte.nom} !</p>
    <a href="deconnexion">Déconnexion</a>
</c:if>
<c:if test="${ empty utilisateurConnecte.pseudo }">
    <a href="connexion">S'inscrire - Se connecter</a>
</c:if>


<h1>Liste des enchères</h1>

</body>
</html>

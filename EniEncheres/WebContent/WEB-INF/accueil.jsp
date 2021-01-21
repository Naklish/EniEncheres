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
<br/>
<h4>${messageOk}</h4>
<h4>${messageSuppr}</h4>
<c:if test="${ not empty utilisateurConnecte.pseudo }">
    <p>Bonjour ${utilisateurConnecte.prenom} ${utilisateurConnecte.nom} !</p>
    <a href="deconnexion">Déconnexion</a>
    <a href="modifierProfil">Modifier mon profil</a>
    <a href="nouvelleVente">Vendre un article</a>
</c:if>
<c:if test="${ empty utilisateurConnecte.pseudo }">
    <a href="connexion">S'inscrire - Se connecter</a>
</c:if>


<h1>Liste des enchères</h1>
<table>
<c:forEach var="article" items="${ listArticles }">
	<tr>
		<ul>
			<li>${ article.nomArticle }</li>
			<li>Prix : ${ article.prixVente }</li>
			<li>Fin de l'enchère : ${ article.dateFin }</li>
			<li>Vendeur : <a href="afficherProfil?noUtilisateur=${ article.vendeur.noUtilisateur }">${ article.vendeur.pseudo }</a></li>
		</ul>
	</tr>
	
</c:forEach>
</table>
</body>
</html>

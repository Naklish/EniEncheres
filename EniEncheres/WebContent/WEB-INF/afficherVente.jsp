<%--
  Created by IntelliJ IDEA.
  User: Melanie
  Date: 21/01/2021
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ENI Encheres</title>
</head>
<body>
<a href="accueil">Accueil</a>
<h1>Détail vente</h1>
<p>${ message }</p>
<ul>
    <h3>${ vente.nomArticle }</h3>
    <li>Description : ${ vente.description }</li>
    <li>Catégorie : ${ vente.noCategorie }</li>
    <li>Meilleure offre : </li>
    <li>Meilleur offre : ${ meilleurEnchere.montantEnchere } par ${ acheteur.pseudo }
    <li>Mise à prix : ${ vente.prixInitial }</li>
    <li>Fin de l'enchère : ${ vente.dateFin }</li>
    <li>Retrait : ${ vente.retrait.adresse }<br/>${ vente.retrait.codePostal } ${ vente.retrait.ville }</li>
    <li>Vendeur : ${ vente.vendeur.pseudo }</li>
    <c:if test="${ not empty utilisateurConnecte.pseudo }">
    	<li>
    		<form action="encherir"	method="get">
    			<label for="enchere">Ma proposition :</label>
    			<input type="number" id="enchere" name="enchere" min=${ vente.prixVente + 1}>
    			<input type="hidden" name="noArticle" value="${ vente.noArticle }">
    			<input type="submit" value="Enchérir">
    		</form>
    	</li>
    </c:if>
</ul>
</body>
</html>
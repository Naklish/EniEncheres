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
<p>${ messageVente }</p>
<ul>
    <h3>${ vente.nomArticle }</h3>
    <li>Description : ${ vente.description }</li>
    <li>Catégorie : ${ vente.categorie.libelle }</li>
    <li>Meilleure offre : ${ meilleurEnchere.montantEnchere } par ${ acheteur.pseudo }
    <li>Mise à prix : ${ vente.prixInitial }</li>
    <li>Fin de l'enchère : ${ vente.dateFin }</li>
    <li>Retrait : ${ vente.retrait.adresse }<br/>${ vente.retrait.codePostal } ${ vente.retrait.ville }</li>
    <li>Vendeur : ${ vente.vendeur.pseudo }</li>
    <c:if test="${ not empty utilisateurConnecte.pseudo && !venteFinie }">
    	<li>
    		<form action="encherir"	method="get">
    			<label for="enchere">Ma proposition :</label>
    			<input type="number" id="enchere" name="enchere" min=${ vente.prixVente + 1}>
    			<input type="hidden" name="noArticle" value="${ vente.noArticle }">
    			<input type="submit" value="Enchérir">
    		</form>
    	</li>
    </c:if>
    <c:if test="${ not empty utilisateurConnecte.pseudo && venteRemporte }">
    	<li>Téléphone : ${ vendeur.telephone }</li>
    	<form action="accueil" method="get">
    		<input type="submit" value="Retour">
    	</form>
    </c:if>
</ul>

    <c:if test="${ vente.vendeur.noUtilisateur == utilisateurConnecte.noUtilisateur }">
        <form action="afficherVente" method="post" id="modifierVente">
            <ul>
                <li>
                    <label for="nomArticle">Article :</label>
                    <input type="text" id="nomArticle" name="nomArticle">
                </li>
                <li>
                    <label for="description">Description :</label>
                    <input type="textarea" id="description" name="description">
                </li>
                <li>

                    <label for="categorie">Categorie :</label>
                    <select id="categorie" name="categorie" form="modifierVente">
                        <c:forEach var="categorie" items="${ listeCategories }">
                            <option value="${ categorie.noCategorie }">${ categorie.libelle }</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <p>Photo de l'article :</p>
                </li>
                <li>
                    <label for="prixInitial">Mise à prix :</label>
                    <input type="number" id="prixInitial" name="prixInitial">
                </li>
                <li>
                    <label for="dateDebut">Début de l'enchère :</label>
                    <input type="date" id="dateDebut" name="dateDebut">
                </li>
                <li>
                    <label for="dateFin">Fin de l'enchère : </label>
                    <input type="date" id="dateFin" name="dateFin" >
                </li>
            </ul>
        </form>
    </c:if>
</body>
</html>
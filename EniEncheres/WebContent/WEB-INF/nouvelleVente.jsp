<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Eni Encheres</title>
</head>
<body>
<h3>Nouvelle vente</h3>
<p>${ message }</p>
<form action="nouvelleVente" method="post" id="nouvelleVente">
    <ul>
        <li>
            <label for="nomArticle">Article :</label>
            <input type="text" id="nomArticle" name="nomArticle" required>
        </li>
        <li>
            <label for="description">Description :</label>
            <input type="textarea" id="description" name="description" required>
        </li>
        <li>
            <label for="categorie">Categorie :</label>
            <select id="categorie" name="categorie" form="nouvelleVente" required>
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
            <input type="number" id="prixInitial" name="prixInitial" required>
        </li>
        <li>
            <label for="dateDebut">Début de l'enchère :</label>
            <input type="date" id="dateDebut" name="dateDebut" required>
        </li>
        <li>
            <label for="dateFin">Fin de l'enchère : </label>
            <input type="date" id="dateFin" name="dateFin" required>
        </li>
    </ul>
    <fieldset>
        <legend>Retrait</legend>
        <label for="adresse">Adresse :</label>
        <input type="text" id="adresse" name="adresse" value="${ utilisateurConnecte.adresse }" required>
        <label for="codePostal">Code postal :</label>
        <input type="text" id="codePostal" name="codePostal" value="${ utilisateurConnecte.codePostal }" required>
        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" value="${ utilisateurConnecte.ville }" required>
    </fieldset>
    <input type="submit" value="Enregistrer">
</form>
</body>
</html>
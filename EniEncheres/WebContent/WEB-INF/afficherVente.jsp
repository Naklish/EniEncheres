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

<div class="row">
    <div class="offset-md-1 col-md-5">
        <h3>${ vente.nomArticle }</h3>
        <ul class="list-group">
            <li class="list-group-item">Description : ${ vente.description }</li>
            <li class="list-group-item">Catégorie : ${ vente.categorie.libelle }</li>
            <li class="list-group-item">Meilleure offre :</li>
            <li class="list-group-item">Meilleur offre : ${ meilleurEnchere.montantEnchere } par ${ acheteur.pseudo }
            <li class="list-group-item">Mise à prix : ${ vente.prixInitial }</li>
            <li class="list-group-item">Fin de l'enchère : ${ vente.dateFin }</li>
            <li class="list-group-item">Retrait
                : ${ vente.retrait.adresse }<br/>${ vente.retrait.codePostal } ${ vente.retrait.ville }</li>
            <li class="list-group-item">Vendeur : ${ vente.vendeur.pseudo }</li>
            <c:if test="${ not empty utilisateurConnecte.pseudo }">
                <li class="list-group-item list-form">
                    <form action="encherir" method="get">
                        <label for="enchere">Ma proposition :</label>
                        <input type="number" id="enchere" name="enchere" min=${ vente.prixVente + 1}>
                        <input type="hidden" name="noArticle" value="${ vente.noArticle }">
                        <input class="btn btn-dark" type="submit" value="Enchérir">
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
    </div>

    <c:if test="${ vente.vendeur.noUtilisateur == utilisateurConnecte.noUtilisateur }">
        <div class="col-md-5 modifArticle" >
            <button class="btn" onclick="afficherFormulaire()">Modifier ma vente</button>
            <div id="hidden_form">
                <form action="afficherVente" method="post" id="modifierVente">
                    <div class="form-group modifVente">
                        <label for="nomArticle">Article :</label>
                        <input type="text" id="nomArticle" name="nomArticle">
                    </div>
                    <div class="form-group">
                        <label for="description">Description :</label>
                        <textarea id="description" name="description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="categorie">Categorie :</label>
                        <select id="categorie" name="categorie" form="modifierVente">
                            <c:forEach var="categorie" items="${ listeCategories }">
                                <option value="${ categorie.noCategorie }">${ categorie.libelle }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <p>Photo de l'article :</p>>
                        <label for="prixInitial">Mise à prix :</label>
                        <input type="number" id="prixInitial" name="prixInitial">
                    </div>
                    <div class="form-group">
                        <label for="dateDebut">Début de l'enchère :</label>
                        <input type="date" id="dateDebut" name="dateDebut">
                    </div>
                    <div class="form-group">
                        <label for="dateFin">Fin de l'enchère : </label>
                        <input type="date" id="dateFin" name="dateFin">
                    </div>
                    <input class="btn btn-dark" type="submit" value="Enregistrer les modifications">
                </form>
            </div>
        </div>
    </c:if>
</div>

<script>
    function afficherFormulaire() {
        var x = document.getElementById("hidden_form");
        if (x.style.display === "block") {
            x.style.display = "none";
        } else {
            x.style.display = "block";
        }
    }
</script>

</body>
</html>
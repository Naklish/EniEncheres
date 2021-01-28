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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/afficherVente.css"/>">
</head>
<body>
<a href="accueil">Accueil</a>
<h1>Détail vente</h1>
<p>${ message }</p>
<p>${ messageVente }</p>

<section class="container">
    <h3 class="text-center text-detail">Détail vente</h3>
    <div class="row">
        <div class="col-sm-2 offset-sm-2 photo-article">
            <img src="img/default-img.png" class="img-fluid"/>
        </div>
        <div class="col-sm-6">
            <ul class="list-group">
                <div class="row">
                    <div class="col-sm-6 offset-sm-1"><h4>${ vente.nomArticle }</h4></div>
                </div>
                <div class="row list-item">
                    <div class="col-sm-4 offset-sm-1">Description :</div>
                    <div class="col-sm-5">${ vente.description }</div>
                </div>
                <div class="row list-item">
                    <div class="col-sm-4 offset-sm-1">Catégorie :</div>
                    <div class="col-sm-5">${ vente.categorie.libelle }</div>
                </div>
                <div class="row list-item">
                    <div class="col-sm-4 offset-sm-1">Meilleure offre :</div>
                    <div class="col-sm-5">${ meilleurEnchere.montantEnchere } par ${ acheteur.pseudo }</div>
                </div>
                <div class="row list-item">
                    <div class="col-sm-4 offset-sm-1">Mise à prix :</div>
                    <div class="col-sm-5">${ vente.prixInitial }</div>
                </div>
                <div class="row list-item">
                    <div class="col-sm-4 offset-sm-1">Fin de l'enchère :</div>
                    <div class="col-sm-5">${ vente.dateFin }</div>
                </div>
                <div class="row list-item">
                    <div class="col-sm-4 offset-sm-1">Retrait :</div>
                    <div class="col-sm-5">${ vente.retrait.adresse }<br/>${ vente.retrait.codePostal } ${ vente.retrait.ville }
                    </div>
                </div>
                <div class="row list-item">
                    <div class="col-sm-4 offset-sm-1">Vendeur :</div>
                    <div class="col-sm-5">${ vente.vendeur.pseudo }</div>
                </div>

                <c:if test="${ not empty utilisateurConnecte.pseudo }">
                    <div class="row list-item">
                        <form id="form-enchere" class="form-inline" action="encherir" method="get">
                            <div class="form-group form-enchere">
                                <label for="enchere" class="col-sm-4 col-form-label">Ma proposition :</label>
                                <input type="number" id="enchere" class="col-sm-2 offset-sm-1 form-control"
                                       name="enchere" min=${ vente.prixVente + 1}>
                                <input type="hidden" name="noArticle" value="${ vente.noArticle }">
                                <input id="btn-encherir"class="btn btn-outline-info" form="form-enchere" type="submit" value="Enchérir">

                            </div>
                        </form>
                    </div>
                </c:if>
                <c:if test="${ not empty utilisateurConnecte.pseudo && venteRemporte }">
                    <li>Téléphone : ${ vendeur.telephone }</li>
                    <form action="accueil" method="get">
                        <input class="btn btn-outline-info" type="submit" value="Retour">
                    </form>
                </c:if>
            </ul>
        </div>
    </div>

    <c:if test="${ vente.vendeur.noUtilisateur == utilisateurConnecte.noUtilisateur }">
        <div class="text-center">
            <button id="btn-modif" class="btn btn-outline-primary" onclick="afficherFormulaire()">Modifier ma vente
            </button>
        </div>


        <div id="hidden_form">
            <h4 class="text-center modif-vente">Modifier vente</h4>
            <form action="afficherVente" method="post" id="modifierVente" accept-charset="UTF-8">
                <input type="hidden" name="noArticle" value="${ vente.noArticle }">
                <div class="row">
                    <div class="col-sm-2 offset-sm-1 photo-article"></div>
                    <div class="col-sm-6">
                        <div class="form-group row">
                            <label for="nomArticle" class="col-sm-3 col-form-label">Article :</label>
                            <input type="text" id="nomArticle" class="col-sm-7 form-control" name="nomArticle"
                                   value="${ vente.nomArticle }"
                                   required>
                        </div>
                        <div class="form-group row">
                            <label for="description" class="col-sm-3 col-form-label">Description :</label>
                            <textarea id="description" class="col-sm-7 form-control" name="description"
                                      required>${ vente.description }</textarea>
                        </div>
                        <div class="form-group row">
                            <label for="categorie" class="col-sm-3 col-form-label">Categorie :</label>
                            <select id="categorie" class="col-sm-7 browser-default custom-select" name="categorie"
                                    form="modifierVente">
                                <c:forEach var="categorie" items="${ listeCategories }">
                                    <option value="${ categorie.noCategorie }">${ categorie.libelle }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <p>Photo de l'article :</p>
                        <div class="form-group row">
                            <label for="prixInitial" class="col-sm-3 col-form-label">Mise à prix :</label>
                            <input type="number" id="prixInitial" class="col-sm-7 form-control" name="prixInitial"
                                   value="${ vente.prixInitial }"
                                   required>
                        </div>
                        <div class="form-group row">
                            <label for="dateDebut" class="col-sm-3 col-form-label">Début de l'enchère :</label>
                            <input type="date" id="dateDebut" class="col-sm-7 form-control" name="dateDebut"
                                   value="${ vente.dateDebut }"
                                   required>
                        </div>
                        <div class="form-group row">
                            <label for="dateFin" class="col-sm-3 col-form-label">Fin de l'enchère : </label>
                            <input type="date" id="dateFin" class="col-sm-7 form-control" name="dateFin"
                                   value="${ vente.dateFin }" required>
                        </div>
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border">Retrait</legend>
                            <div class="form-group row">
                                <label for="adresse" class="col-sm-3 col-form-label">Adresse :</label>
                                <input type="text" id="adresse" class="col-sm-7 form-control" name="adresse"
                                       value="${ vente.retrait.adresse }" required>
                            </div>
                            <div class="form-group row">
                                <label for="codePostal" class="col-sm-3 col-form-label">Code postal :</label>
                                <input type="text" id="codePostal" class="col-sm-7 form-control" name="codePostal"
                                       value="${ vente.retrait.codePostal }"
                                       required>
                            </div>
                            <div class="form-group row">
                                <label for="ville" class="col-sm-3 col-form-label">Ville :</label>
                                <input type="text" id="ville" class="col-sm-7 form-control" name="ville"
                                       value="${ vente.retrait.ville }" required>
                            </div>
                        </fieldset>
                        <div class="btn-group row">
                            <input class="btn btn-outline-info" type="submit" value="Enregistrer">
                            <a id="btn-annuler" class="btn btn-outline-info" href="accueil">Annuler</a>
                        </div>
                    </div>
                </div>
            </form>
            <form id="form-annuler" class="text-center" action="supprimerVente" method="get">
                <input type="hidden" name="noArticle" value="${ vente.noArticle }">
                <input id="btn-annuler-vente" class="btn btn-outline-info" type="submit" value="Annuler la vente">
            </form>
        </div>
    </c:if>
</section>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

<script>
    function afficherFormulaire() {
        var form = document.getElementById("hidden_form");
        var btn = document.getElementById("btn-modif")
        if (form.style.display === "block") {
            form.style.display = "none";
        } else {
            form.style.display = "block";
            btn.style.display = "none";
        }
    }
</script>

</body>
</html>
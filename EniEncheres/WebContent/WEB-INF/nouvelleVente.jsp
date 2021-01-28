<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Eni Encheres</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="<c:url value="/css/nouvelleVente.css"/>" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark linear-gradient">
    <a class="navbar-brand col-sm-2" href="accueil">ENI-Encheres</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <c:if test="${ not empty utilisateurConnecte.pseudo }">
            <ul class="navbar-nav">
                <span class="navbar-text d-none d-md-block">Bonjour ${ utilisateurConnecte.pseudo } !</span>
                <li class="nav-item"><a class="nav-link" href="deconnexion">Déconnexion</a></li>
                <li class="nav-item"><a class="nav-link"
                                        href="afficherProfil?noUtilisateur=${ utilisateurConnecte.noUtilisateur }">Mon
                    profil</a></li>
                <li class="nav-item"><a class="nav-link" href="nouvelleVente">Vendre un article</a></li>
                <c:if test="${ utilisateurConnecte.administrateur == true }">
                 <li class="nav-item"><a class="nav-link" href="listeProfil">Lister les utilisateurs</a></li>
                </c:if>
            </ul>
        </c:if>
        <c:if test="${ empty utilisateurConnecte.pseudo }">

            <a class="nav-link" href="connexion">S'inscrire - Se connecter</a>
        </c:if>
    </div>
</nav>

<section class="container">
    <h3 class="text-center">Nouvelle vente</h3>
    <p>${ message }</p>
    <form action="nouvelleVente" method="post" id="nouvelleVente">
        <div class="row">
            <div class="col-sm-2 offset-sm-1 photo-article"></div>
            <div class="col-sm-6">
                <div class="form-group row">
                    <label for="nomArticle" class="col-sm-3 col-form-label">Article :</label>
                    <input type="text" id="nomArticle" class="col-sm-7 form-control" name="nomArticle" required>
                </div>
                <div class="form-group row">
                    <label for="description" class="col-sm-3 col-form-label">Description :</label>
                    <textarea id="description" class="col-sm-7 form-control" name="description"></textarea>
                </div>
                <div class="form-group row">
                    <label for="categorie" class="col-sm-3 col-form-label">Categorie :</label>
                    <select id="categorie" class="col-sm-7 browser-default custom-select" name="categorie" form="nouvelleVente" required>
                        <c:forEach var="categorie" items="${ listeCategories }">
                            <option value="${ categorie.noCategorie }">${ categorie.libelle }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Photo de l'article :</label>
                </div>
                <div class="form-group row">
                    <label for="prixInitial" class="col-sm-3 col-form-label">Mise à prix :</label>
                    <input type="number" id="prixInitial" class="col-sm-7 form-control" name="prixInitial" required>
                </div>
                <div class="form-group row">
                    <label for="dateDebut" class="col-sm-3 col-form-label">Début de l'enchère :</label>
                    <input type="date" id="dateDebut" class="col-sm-7 form-control" name="dateDebut" required>
                </div>
                <div class="form-group row">
                    <label for="dateFin" class="col-sm-3 col-form-label">Fin de l'enchère : </label>
                    <input type="date" id="dateFin" class="col-sm-7 form-control" name="dateFin" required>
                </div>
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border">Retrait</legend>
                    <div class="form-group row">
                        <label for="adresse" class="col-sm-3 col-form-label">Adresse :</label>
                        <input type="text" id="adresse" class="col-sm-7 form-control" name="adresse"
                               value="${ utilisateurConnecte.adresse }" required>
                    </div>
                    <div class="form-group row">
                        <label for="codePostal" class="col-sm-3 col-form-label">Code postal :</label>
                        <input type="text" id="codePostal" class="col-sm-7 form-control" name="codePostal"
                               value="${ utilisateurConnecte.codePostal }"
                               required>
                    </div>
                    <div class="form-group row">
                        <label for="ville" class="col-sm-3 col-form-label">Ville :</label>
                        <input type="text" id="ville" class="col-sm-7 form-control" name="ville"
                               value="${ utilisateurConnecte.ville }" required>
                    </div>
                </fieldset>
                <div class="boutons">
                    <input class="btn btn-outline-info" type="submit" value="Enregistrer">
                    <a href="accueil" class="btn btn-outline-info">Annuler</a>
                </div>
            </div>
        </div>
    </form>
</section>

<footer>

</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
</body>
</html>
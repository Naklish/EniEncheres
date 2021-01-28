<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Inscription</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/inscription.css"/>">
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

<div class="container">
    <section>
        <h3 class="text-center">Mon profil - Inscription</h3>
        <p>${ message }</p>
        <p>${messageErreur}</p>

        <form action=" inscription" method="post">
            <div class="row">
                <div class="col">
                    <div class="form-group row">
                        <label for="pseudo" class="col-sm-3 col-form-label">Pseudo</label>
                        <div class="col-sm-8">
                            <input type="text" id="pseudo" class="form-control" name="pseudo" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="prenom" class="col-sm-3 col-form-label">Prénom</label>
                        <div class="col-sm-8">
                            <input type="text" id="prenom" class="form-control" name="prenom" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="telephone" class="col-sm-3 col-form-label">Téléphone</label>
                        <div class="col-sm-8">
                            <input type="tel" id="telephone" class="form-control" name="telephone" pattern="[0-9]{10}"
                                   title="0102030405" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="codePostal" class="col-sm-3 col-form-label">Code postal</label>
                        <div class="col-sm-8">
                            <input type="text" id="codePostal" class="form-control" name="codePostal" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="motDePasse" class="col-sm-3 col-form-label">Mot de passe</label>
                        <div class="col-sm-8">
                            <input type="password" id="motDePasse" class="form-control" name="motDePasse" required>
                            <input class="btn btn-outline-info btn-creer" type="submit" value="Créer">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group row">
                        <label for="nom" class="col-sm-3 col-form-label">Nom</label>
                        <div class="col-sm-8">
                            <input type="text" id="nom" class="form-control" name="nom" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="email" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-8">
                            <input type="email" id="email" class="form-control" name="email"
                                   placeholder="monemail@example.com"
                                   required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="adresse" class="col-sm-3 col-form-label">Adresse</label>
                        <div class="col-sm-8">
                            <input type="text" id="adresse" class="form-control" name="adresse" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="ville" class="col-sm-3 col-form-label">Ville</label>
                        <div class="col-sm-8">
                            <input type="text" id="ville" class="form-control" name="ville" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="confirmation" class="col-sm-3 col-form-label">Confirmation</label>
                        <div class="col-sm-8">
                            <input type="password" id="confirmation" class="form-control" name="confirmation" required>
                        </div>
                        <a href="accueil" class="btn btn-outline-info btn-annuler">Annuler</a>
                    </div>
                </div>
            </div>
        </form>
        <p class="err">${ messageMdp }</p>
    </section>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

</body>
</html>
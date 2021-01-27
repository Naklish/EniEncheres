<%--
  Created by IntelliJ IDEA.
  User: Melanie
  Date: 19/01/2021
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Connexion</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link href="<c:url value="/css/connexion.css"/>" rel="stylesheet">
</head>
<body>

<!-- Barre de navigation -->

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
            </ul>
        </c:if>
        <c:if test="${ empty utilisateurConnecte.pseudo }">

            <a class="nav-link" href="connexion">S'inscrire - Se connecter</a>
        </c:if>
    </div>
</nav>


<section id="connexion">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <p class="err">${message}</p>
            <div id="login-column" class="col-md-5">
                <form id="login-form" class="form" action="connexion" method="post">
                    <h3 class="text-center text-info connexion">Connexion</h3>
                    <div class="form-group row">
                        <label for="login" class="col-sm-4 col-form-label">Identifiant</label>
                        <input type="text" id="login" class="col-sm-7 form-control" name="login" required>
                    </div>
                    <div class="form-group row">
                        <label for="motdepasse" class="col-sm-4 col-form-label">Mot de passe</label>
                        <input type="password" id="motdepasse" class="col-sm-7 form-control" name="motdepasse">
                    </div>
                    <div class="form-group row">
                        <div class="col">
                            <input class="btn btn-outline-info connexion" type="submit" value="Connexion" required>
                        </div>
                        <div class="col">
                        <input class="form-check-input" id="souvenir" name="souvenir" type="checkbox" value="souvenir">
                        <label for="souvenir" class="form-check-label">Se souvenir de moi</label>
                            <br/>
                            <a href="#">Mot de passe oublié</a>
                        </div>
                    </div>
                    <div class="col-12">
                        <a class="btn btn-outline-info creer-compte" href="inscription">Créer un compte</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
</body>
</html>

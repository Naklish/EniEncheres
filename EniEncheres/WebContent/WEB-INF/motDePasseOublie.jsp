<%--
  Created by IntelliJ IDEA.
  User: Melanie
  Date: 19/01/2021
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Récupération mot de passe</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link href="<c:url value="/css/motDePasseOublie.css"/>" rel="stylesheet">
</head>
<body>

<!-- Barre de navigation -->

<nav class="navbar navbar-expand-md navbar-dark linear-gradient">
    <a class="navbar-brand col-sm-2" href="accueil"><img src="img/logoEni.png" alt="logo" width="170"></a>
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
 <header class="text-center">
        <h3><strong>Récupération du mot de passe</strong></h3>
</header>

<section id="connexion">
    <div class="container">
        <div id="motDePasse-row" class="row justify-content-center align-items-center">
            <div id="motDepasse-column" class="col-md-5">
                <form id="motDePasse-form" class="form" action="motDePasseOublie" method="post">
                    <div class="form-group row">
                        <label for="email" class="col-sm-4 col-form-label">E-mail :</label>
                        <input type="text" id="email" class="col-sm-7 form-control" name="email" required>
                    </div>
                    <div class="col-12">
                           <input class="btn btn-outline-primary" type="submit" value="Réinitialiser  mot de passe">
                    </div>
                    <div class="col-12">
                    <br>
                    <c:if test="${ not empty message  }">
                    	<p>${ message }</p>
                    	<p><a href="${ url }">Nouveau mot de passe</a></p>
                    </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

</body>
</html>
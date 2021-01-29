<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste Utilisateurs: ENI-Enchères</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="<c:url value="/css/accueil.css"/>" rel="stylesheet">
</head>

<body>

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
        <h3><strong>Section Admin</strong></h3>
    </header>

    <!-- Section qui présente les différentes enchères disponnibles -->
    <section class="container">
        <article class="row">
            <div class="col">
            <div class="titreArticle">
                <h4>Liste des utilisateurs</h4>
                <p class="green">${ messageOk }</p>
            </div>
            <div class="row">
            <ul class="list-group list-group-flush">
                <c:forEach var="utilisateur" items="${ listeUtilisateur }">
                     <div>
                       	<li class="list-group-item">
                            ${ utilisateur.pseudo }
                            <a href="afficherProfil?noUtilisateur=${ utilisateur.noUtilisateur }" >Voir profil</a>
                       	</li>
                     </div>
                </c:forEach>
             </ul>
            </div>
            </div>
        </article>
    </section>
</body>
</html>
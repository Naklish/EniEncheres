<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>ENI Encheres</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/afficherProfil.css"/>">
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

<div class="container-fluid">
    <br/>
    <h1 class="text-center">Profil</h1>


    <div class="page-content page-container" id="page-content">
        <div class="padding">
            <div class="row justify-content-center">
                <div class="col-md-9">
                    <div class="card user-card-full">
                        <div class="row m-l-0 m-r-0">
                            <div class="col-sm-4 bg-c-lite-green user-profile">
                                <div class="card-block text-center text-white">
                                    <div class="m-b-25"><img src="#" class="img-radius" alt="photo-profil"></div>
                                    <h3 class="f-w-600"> ${ profil.prenom } ${ profil.nom }</h3>
                                    <p>${ profil.pseudo }</p>
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <div class="card-block">
                                    <h6 class="m-b-20 p-b-5 b-b-default f-w-600">Informations</h6>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <p class="m-b-10 f-w-600">E-mail</p>
                                            <h6 class="text-muted f-w-400">${ profil.email }</h6>
                                        </div>
                                        <div class="col-sm-6">
                                            <p class="m-b-10 f-w-600">Téléphone</p>
                                            <h6 class="text-muted f-w-400">${ profil.telephone }</h6>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <p class="m-b-10 f-w-600">Adresse</p>
                                            <h6 class="text-muted f-w-400">${ profil.adresse }</h6>
                                        </div>
                                        <div class="col-sm-6">
                                            <p class="m-b-10 f-w-600">Ville/Code postal</p>
                                            <h6 class="text-muted f-w-400">${ profil.ville } ${ profil.codePostal }</h6>
                                        </div>
                                    </div>
                                    <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">Solde</h6>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <h6 class="text-muted f-w-400"><c:if
                                                    test="${ profil.noUtilisateur == utilisateurConnecte.noUtilisateur }">
                                                <p>Crédit : ${ profil.credit }<p></c:if></h6>
                                        </div>

                                    </div>
                                    <c:if test="${ profil.noUtilisateur == utilisateurConnecte.noUtilisateur }">
                                        <a href="modifierProfil" class="btn btn-outline-primary">Modifier</a>
                                    </c:if>
                                    <c:if test="${ utilisateurConnecte.administrateur == true }">
                                    	<form method="post" action="supprimerCompte">
                                    		<input type="hidden" name="utilisateurSuppr" value="${ profil.noUtilisateur }">
                                    		<input class="btn btn-outline-primary" type="submit" value="Supprimer compte">
                                    	</form>
                                    	<c:if test="${ profil.desactivation == false }">
                                    		<form method="get" action="desactiverCompte">
                                    			<input type="hidden" name="utilisateurDesactive" value="${ profil.noUtilisateur }">
                                    			<input class="btn btn-outline-primary" type="submit" value="Désactiver compte">
                                    	</form>
                                    	</c:if>
                                    	<c:if test="${ profil.desactivation == true }">
                                    		<input class="btn btn-outline-primary" type="submit" value="Activer compte">
                                    	</c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Melanie
  Date: 19/01/2021
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Accueil: ENI-Enchères</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="<c:url value="/css/accueil.css"/>" rel="stylesheet">
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
                <c:if test="${ utilisateurConnecte.desactivation == false }">
                	<li class="nav-item"><a class="nav-link" href="nouvelleVente">Vendre un article</a></li>
                </c:if>
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
    <header class="text-center">
        <h3><strong>Liste des enchères</strong></h3>
    </header>

    <h4>${messageOk}</h4>
    <h4>${messageSuppr}</h4>
    <h4>${messageSupprArticle}</h4>

    <!-- recherche d'un article + catégorie + boutton rechercher -->
    <div class="row">
        <div class="offset-sm-1 col-sm-4">
            <form action="accueil" method="post" id="accueil">
                <div classe="form-group">
                    <div class="choixFiltre">
                        <label for="recherche">Filtres :</label>
                        <div class="loupe">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                            </svg>
                        </div>

                        <input type="search" id="recherche" name="recherche"
                               placeholder="Le nom de l'article contient">
                    </div>
                    <br/>
                    <div class="choixCategorie">
                        <label for="categorie">Categorie :</label>
                        <select id="categorie" name="categorie" form="accueil" class="browser-default custom-select"
                                required>
                            <option value="0">Toutes</option>
                            <c:forEach var="categorie" items="${ listeCategories }">
                                <option value="${ categorie.noCategorie }">${ categorie.libelle }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <c:if test="${ not empty utilisateurConnecte.pseudo }">
                        <div class="row">
                            <div class="col">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="choixAchatsVentes" id="achats"
                                           value="value1" checked>
                                    <label class="form-check-label" for="achats">Achats</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="encheresOuvertes"
                                           value="choixAchat" id="encheresOuvertes" onclick='decocher("encheresEnCours", "mesEncheresRemportees")'>
                                    <label class="form-check-label" for="encheresOuvertes">enchères ouvertes</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="encheresEnCours"
                                           value="choixAchat" id="encheresEnCours" onclick='decocher("encheresOuvertes", "mesEncheresRemportees")'>
                                    <label class="form-check-label" for="encheresEnCours">mes enchères en
                                        cours</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="mesEncheresRemportees"
                                           value="choixAchat" id="mesEncheresRemportees" onclick='decocher("encheresOuvertes", "encheresEnCours")'>
                                    <label class="form-check-label" for="mesEncheresRemportees">mes enchères
                                        remportées</label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="choixAchatsVentes" id="ventes"
                                           value="value2">
                                    <label class="form-check-label" for="ventes">Mes ventes</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="ventesEnCours"
                                           value="choixVente" id="ventesEnCours" onclick='decocher("ventesNonDebutee", "ventesTerminees")' disabled>
                                    <label class="form-check-label" for="ventesEnCours">mes ventes en cours</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="ventesNonDebutee"
                                           value="choixVente" id="ventesNonDebutee" onclick='decocher("ventesEnCours", "ventesTerminees")' disabled>
                                    <label class="form-check-label" for="ventesNonDebutee">ventes non débutées</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="ventesTerminees"
                                           value="choixVente" id="ventesTerminees" onclick='decocher("ventesEnCours", "ventesNonDebutee")' disabled>
                                    <label class="form-check-label" for="ventesTerminees">ventes terminées</label>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
        <div class="offset-sm-1 col-sm-5">
            <input class="btn btn-outline-info search-btn" type="submit" form="accueil" value="Rechercher">
        </div>
    </div>

    <!-- Section qui présente les différentes enchères disponnibles -->
    <section>
        <article>
            <div class="titreArticle">
                <h5>Enchères en cours:</h5>
            </div>
            <div class="row liste-encheres">
                <c:forEach var="article" items="${ listArticles }">
                    <div class="col-sm-5 mb-4 ml-4">
                        <div class="card">
                            <div class="row">
                                <div class="col-md-4">
                                    <img src="img/default-img.png" alt="no-image" class="img-fluid"/>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title"><a
                                                href="afficherVente?noArticle=${ article.noArticle }">${ article.nomArticle }</a>
                                        </h5>
                                        <p class="card-text">Prix : ${ article.prixVente } points</p>
                                        <p class="card-text">Fin de l'enchère : ${ article.dateFin }</p>
                                        <p class="card-text"> Vendeur : <a
                                                href="afficherProfil?noUtilisateur=${ article.vendeur.noUtilisateur }">${ article.vendeur.pseudo }</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </article>
    </section>

    <footer>
        <!-- Pied de page (à définir) -->
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

<script>
    // Activer checkboxs partie Achats
    $("input[value='value1']").change(function () {
        $("input[value='choixAchat']").prop('disabled', false);
        $("input[value='choixVente']").prop('checked', false);
        $("input[value='choixVente']").prop('disabled', true);
    });

    // Activer checkboxs partie Mes Ventes
    $("input[value='value2']").change(function () {
        $("input[value='choixVente']").prop('disabled', false);
        $("input[value='choixAchat']").prop('checked', false);
        $("input[value='choixAchat']").prop('disabled', true);
    });

    // Décocher les autres cases lorsqu'une est sélectionnée
    function decocher(a,b) {
        document.getElementById(a).checked=false;
        document.getElementById(b).checked=false;
    }
</script>
</body>
</html>

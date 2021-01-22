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
    <title>Accueil</title>
    <title>Accueil: ENI-Enchères</title>
    <link href="<c:url value="/css/accueil.css"/>" rel="stylesheet">
</head>
<body>
<h4>${messageOk}</h4>
<h4>${messageSuppr}</h4>
<header>
    <div class="eni">
        <h1><a href="accueil">ENI-Encheres</a></h1>
    </div>
    <div class="choixUtilisateur">
        <c:if test="${ not empty utilisateurConnecte.pseudo }">
            <p>Bonjour ${utilisateurConnecte.prenom} ${utilisateurConnecte.nom} !</p>
            <a href="deconnexion">Déconnexion</a>
            <a href="afficherProfil?noUtilisateur=${ utilisateurConnecte.noUtilisateur }">Mon profil</a>
            <a href="nouvelleVente">Vendre un article</a>
        </c:if>
    </div>
    <c:if test="${ empty utilisateurConnecte.pseudo }">

        <div class="choix">
            <p><a href="connexion">S'inscrire - Se connecter</a></p>
        </div>
    </c:if>
    <h2>Accueil</h2>
    <h3><strong>Liste des enchères</strong></h3>
</header>


<nav>
    <!-- recherche d'un article + catégorie + boutton rechercher -->
</nav>

<!-- Section qui présente les différentes enchères disponnibles -->
<section>
    <article>
        <div class="titreArticle">
            <h1>Enchères en cours:</h1>
        </div>
        <table>
            <c:forEach var="article" items="${ listArticles }">
            <tr>
                <ul>
                    <li>
                        <a href="afficherVente?noArticle=${ article.noArticle }">${ article.nomArticle }</a></li>
                    <li>Prix : ${ article.prixVente }</li>
                    <li>Fin de l'enchère : ${ article.dateFin }</li>
                    <li>Vendeur : <a
                            href="afficherProfil?noUtilisateur=${ article.vendeur.noUtilisateur }">${ article.vendeur.pseudo }</a>
                    </li>
                </ul>
            </tr>
    </article>

    </c:forEach>
    </table>

</section>

<footer>
    <!-- Pied de page (à définir) -->
</footer>
</body>
</html>

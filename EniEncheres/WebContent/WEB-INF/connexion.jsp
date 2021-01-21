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
    <link href="<c:url value="/css/connexion.css"/>" rel="stylesheet">
</head>
<body>
    <h1>Page de connexion</h1>
<header>
	<div class="eni">
	<h1><a href="accueil">ENI-Encheres</a></h1>
	</div>
    <h2>Connexion</h2>
</header>

<section>
	<article>
    <p class="err">${message}</p>
    <form action="connexion" method="post">
        <fieldset>
        <label for="login">Identifiant ou adresse e-mail :</label>
        <input type="text" id="login" name="login" required>
        <br/>
        <label for="motdepasse">Mot de passe :</label>
        <input type="password" id="motdepasse" name="motdepasse">
        <br/>
        <input type="submit" value="Connexion" required>
        </fieldset>
    </form>
    </article>
</section>
<a href="inscription">Créer un compte</a>
</body>
</html>

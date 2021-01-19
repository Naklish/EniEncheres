<%--
  Created by IntelliJ IDEA.
  User: Melanie
  Date: 19/01/2021
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
    <h1>Page de connexion</h1>

    <p>${message}</p>
    <form action="connexion" method="post">
        <fieldset>
        <label for="login">Identifiant :</label>
        <input type="text" id="login" name="login">
        <br/>
        <label for="motdepasse">Mot de passe :</label>
        <input type="password" id="motdepasse" name="motdepasse">
        <br/>
        <input type="submit" value="Connexion"/>
        </fieldset>
    </form>

<a href="inscription">Créer un compte</a>
</body>
</html>

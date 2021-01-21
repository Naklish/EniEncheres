<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Eni Encheres</title>
</head>
<body>
<h1>Inscription</h1>
<p>${ message }</p>
<p>${messageErreur}</p>
<form action="inscription" method="post">
    <table>
        <tbody>
        <tr>
            <td>
                <label for="pseudo">Pseudo :</label>
                <input type="text" id="pseudo" name="pseudo" required>
            </td>
            <td>
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
            </td>
        </tr>
        <tr>
            <td>
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" required>
            </td>
            <td>
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" placeholder="monemail@example.com" required>
            </td>
        </tr>
        <tr>
            <td>
                <label for="telephone">Téléphone :</label>
                <input type="tel" id="telephone" name="telephone" pattern="[0-9]{10}"
                       title="0102030405" required>
            </td>
            <td>
                <label for="adresse">Adresse :</label>
                <input type="text" id="adresse" name="adresse" required>
            </td>
        </tr>
        <tr>
            <td>
                <label for="codePostal">Code postal :</label>
                <input type="text" id="codePostal" name="codePostal" required>
            </td>
            <td>
                <label for="ville">Ville :</label>
                <input type="text" id="ville" name="ville" required>
            </td>
        </tr>
        <tr>
            <td>
                <label for="motDePasse">Mot de passe :</label>
                <input type="password" id="motDePasse" name="motDePasse" required>
            </td>
            <td>
                <label for="confirmation">Confirmation:</label>
                <input type="password" id="confirmation" name="confirmation" required>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="Créer">
</form>
<p class="err">${ messageMdp }</p>
</body>
</html>
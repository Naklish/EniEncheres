<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eni Encheres</title>
</head>
<body>
<h3>Mon profil</h3>
<a href="accueil">Accueil</a>
<form action="modifierProfil" method="post">
<p>${ message }</p>
<p>${ messageErreur }</p>
<p>${ messageErrPseudo }</p>
	<p>${ messageErrMail }</p>
<table>
	<tbody>
		<tr>
			<td>
				<label for="pseudo">Pseudo : </label>
				<input type="text" id="pseudo" name="pseudo">
			</td>
			<td>
				<label for="nom">Nom :</label>
				<input type="text" id="nom" name="nom">
			</td>
		</tr>
		<tr>
			<td>
				<label for="prenom">Prénom :</label>
				<input type="text" id="prenom" name="prenom">
			</td>
			<td>
				<label for="email">E-mail :</label>
				<input type="text" id="email" name="email">
			</td>
		</tr>
		<tr>
			<td>
				<label for="telephone">Téléphone :</label>
				<input type="tel" id="telephone" name="telephone">
			</td>
			<td>
				<label for="adresse">Adresse :</label>
				<input type="text" id="adresse" name="adresse">
			</td>
		</tr>
		<tr>
			<td>
				<label for="codePostal">Code postal :</label>
				<input type="text" id="codePostal" name="codePostal">
			</td>
			<td>
				<label for="ville">Ville :</label>
				<input type="text" id="ville" name="ville">
			</td>
		</tr>
		<tr>
			<td>
				<label for="motDePasseActuel">Mot de passe actuel :</label>
				<input type="password" id="motDePasseActuel" name="motDePasseActuel" required>
			</td>				
		</tr>
		<tr>
			<td>
				<label for="nouveauMotDePasse">Nouveau mot de passe :</label>
				<input type="password" id="nouveauMotDePasse" name="nouveauMotDePasse">
			</td>
			<td>
				<label for="confirmation">Confirmation :</label>
				<input type="password" id="confirmation" name="confirmation">
			</td>
		</tr>
		<tr>
			<td>
				<p>Crédit : ${ utilisateurConnecte.credit }</p>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Modifier">
			</td>
		</tr>
	</tbody>
</table>
</form>
	<a href="supprimerCompte">Supprimer mon compte</a>
</body>
</html>
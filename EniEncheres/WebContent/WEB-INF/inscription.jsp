<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eni Encheres</title>
</head>
<body>
<h1>Inscription</h1>
<form action="inscription" method="post">
	<table>
		<tbody>
			<tr>
				<td>
					<label for="pseudo">Pseudo :</label>
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
					<label for="email">Email :</label>
					<input type="text" id="email" name="email">
				</td>
			</tr>
			<tr>
				<td>
					<label for="telephone">Téléphone :</label>
					<input type="text" id="telephone" name="telephone">
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
					<label for="motDePasse">Mot de passe :</label>
					<input type="password" id="motDePasse" name="motDePasse">
				</td>
				<td>
					<label for="confirmation">Confirmation:</label>
					<input type="password" id="confirmation" name="confirmation">
				</td>
			</tr>
		</tbody>
	</table>
	<input type="submit" value="Créer">
</form>
<p>${ message }</p>
</body>
</html>
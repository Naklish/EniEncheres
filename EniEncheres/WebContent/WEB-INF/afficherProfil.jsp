<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ENI Encheres</title>
</head>
<body>
<a href="accueil">Accueil</a>
<h1>Profil</h1>
<ul>
	<li>Pseudo : ${ profil.pseudo }</li>
	<li>Nom : ${ profil.nom }</li>
	<li>Prénom : ${ profil.prenom }</li>
	<li>E-mail : ${ profil.email }</li>
	<li>Téléphone : ${ profil.telephone }</li>
	<li>Adresse : ${ profil.adresse }</li>
	<li>Code postal : ${ profil.codePostal }</li>
	<li>Ville : ${ profil.ville }</li>
</ul>
</body>
</html>
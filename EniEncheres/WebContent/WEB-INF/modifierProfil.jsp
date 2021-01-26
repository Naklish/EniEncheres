<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eni Encheres</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/css/modifierProfil.css"/>">
</head>
<body>
	<div class="container-fluid">

		<!-- Barre de navigation -->

		<nav class="navbar navbar-expand-md navbar-dark linear-gradient">
			<a class="navbar-brand col-sm-2" href="accueil">ENI-Encheres</a>
			<c:if test="${ not empty utilisateurConnecte.pseudo }">
				<ul class="navbar-nav">
					<span class="navbar-text">Bonjour ${ utilisateurConnecte.pseudo }
						!</span>
					<li class="nav-item"><a class="nav-link" href="deconnexion">Déconnexion</a></li>
					<li class="nav-item"><a class="nav-link"
						href="afficherProfil?noUtilisateur=${ utilisateurConnecte.noUtilisateur }">Mon
							profil</a></li>
					<li class="nav-item"><a class="nav-link" href="nouvelleVente">Vendre
							un article</a></li>
				</ul>
			</c:if>
			<c:if test="${ empty utilisateurConnecte.pseudo }">

				<a class="nav-link" href="connexion">S'inscrire - Se connecter</a>
			</c:if>
		</nav>

		<!-- Formulaire de modification du profil -->

		<div class="formulaire">
			<form action="modifierProfil" method="post">
				<p>${ message }</p>
				<p>${ messageErreur }</p>
				<p>${ messageErrPseudo }</p>
				<p>${ messageErrMail }</p>
				<div class="row">
					<div class="col">
						<div class="form-group row">
							<label for="pseudo" class="col-sm-3 col-form-label">Pseudo
								:</label>
							<div class="col-sm-8">
								<input type="text" id="pseudo" class="form-control"
									name="pseudo">
							</div>
						</div>
						<div class="form-group row">
							<label for="prenom" class="col-sm-3 col-form-label">Prénom
								:</label>
							<div class="col-sm-8">
								<input type="text" id="prenom" class="form-control"
									name="prenom">
							</div>
						</div>
						<div class="form-group row">
							<label for="telephone" class="col-sm-3 col-form-label">Téléphone
								:</label>
							<div class="col-sm-8">
								<input type="tel" id="telephone" class="form-control"
									name="telephone" pattern="[0-9]{10}" title="0102030405">
							</div>
						</div>
						<div class="form-group row">
							<label for="codePostal" class="col-sm-3 col-form-label">Code
								postal :</label>
							<div class="col-sm-8">
								<input type="text" id="codePostal" class="form-control"
									name="codePostal">
							</div>
						</div>
						<div class="form-group row">
							<label for="motDePasseActuel" class="col-sm-3 col-form-label">Mot
								de passe actuel :</label>
							<div class="col-sm-8">
								<input type="password" id="motDePasseActuel"
									class="form-control" name="motDePasseActuel" required>
							</div>

						</div>
						<div class="form-group row">
							<label for="nouveauMotDePasse" class="col-sm-3 col-form-label">Nouveau
								mot de passe :</label>
							<div class="col-sm-8">
								<input type="password" id="nouveauMotDePasse"
									class="form-control" name="nouveauMotDePasse">
							</div>

						</div>
					</div>
					<div class="col">
						<div class="form-group row">
							<label for="nom" class="col-sm-3 col-form-label">Nom :</label>
							<div class="col-sm-8">
								<input type="text" id="nom" class="form-control" name="nom">
							</div>
						</div>
						<div class="form-group row">
							<label for="email" class="col-sm-3 col-form-label">Email
								:</label>
							<div class="col-sm-8">
								<input type="email" id="email" class="form-control" name="email"
									placeholder="monemail@example.com">
							</div>
						</div>
						<div class="form-group row">
							<label for="adresse" class="col-sm-3 col-form-label">Adresse
								:</label>
							<div class="col-sm-8">
								<input type="text" id="adresse" class="form-control"
									name="adresse">
							</div>
						</div>
						<div class="form-group row">
							<label for="ville" class="col-sm-3 col-form-label">Ville
								:</label>
							<div class="col-sm-8">
								<input type="text" id="ville" class="form-control" name="ville">
							</div>
						</div>
						<br /> <br />
						<div class="form-group row">
							<label for="confirmation" class="col-sm-3 col-form-label">Confirmez
								:</label>
							<div class="col-sm-8">
								<input type="password" id="confirmation" class="form-control"
									name="confirmation" required>
							</div>


						</div>
					</div>
				</div>
				<input class="btn btn-outline-info btn-creer" type="submit"
					value="Modifier">
			</form>
		</div>
		<br />
		<div class="selection">

			<a href="accueil" class="btn btn-outline-info btn-annuler">Annuler</a>
			<a href="supprimerCompte" class="btn btn-outline-danger">Supprimer
				mon compte</a>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
		crossorigin="anonymous"></script>


</body>
</html>
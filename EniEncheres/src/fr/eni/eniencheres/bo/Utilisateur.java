package fr.eni.eniencheres.bo;

public class Utilisateur {

		private int noUtilisateur;
		private String pseudo;
		private String nom;
		private String prenom;
		private String email;
		private String telephone;
		private String adresse;
		private String codePostal;
		private String ville;
		private String motDePasse;
		private int credit;
		private boolean administrateur;
		
		public Utilisateur() {
			// TODO Auto-generated constructor stub
		}
		
		
		public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
				String adresse, String codePostal, String ville, String motDePasse,
				boolean administrateur) {
			super();
			this.pseudo = pseudo;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.telephone = telephone;
			this.adresse = adresse;
			this.codePostal = codePostal;
			this.ville = ville;
			this.motDePasse = motDePasse;
			this.credit = 0;
			this.administrateur = administrateur;
		}


		public int getNoUtilisateur() {
			return noUtilisateur;
		}

		public void setNoUtilisateur(int noUtilisateur) {
			this.noUtilisateur = noUtilisateur;
		}

		public String getPseudo() {
			return pseudo;
		}

		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public String getCodePostal() {
			return codePostal;
		}

		public void setCodePostal(String codePostal) {
			this.codePostal = codePostal;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public String getMotDePasse() {
			return motDePasse;
		}

		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}

		public int getCredit() {
			return credit;
		}

		public void setCredit(int credit) {
			this.credit = credit;
		}

		public boolean isAdministrateur() {
			return administrateur;
		}

		public void setAdministrateur(boolean administrateur) {
			this.administrateur = administrateur;
		}
		
		
}

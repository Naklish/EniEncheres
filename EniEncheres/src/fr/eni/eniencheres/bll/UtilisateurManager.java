package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

import java.util.List;

public class UtilisateurManager {
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    public String enregistrer(Utilisateur utilisateur) {
        String message = "";
        if(this.utilisateurDAO.selectByEmail(utilisateur.getEmail()) && this.utilisateurDAO.selectByPseudo(utilisateur.getPseudo())) {
            message = "Ces identifiants sont déjà utilisés.";
        } else if(this.utilisateurDAO.selectByPseudo(utilisateur.getPseudo())) {
            message = "Ce pseudo existe déjà.";
        } else if(this.utilisateurDAO.selectByEmail(utilisateur.getEmail())) {
            message = "Cet e-mail est déjà utilisé.";
        } else {
            this.utilisateurDAO.insert(utilisateur);
        } return message;
    }

	public Utilisateur connecter(String identifiant, String motDePasse) {
		Utilisateur utilisateurConnecte = new Utilisateur();
		List<Utilisateur> listeUtilisateurs = this.utilisateurDAO.selectAll();
		for (Utilisateur utilisateur : listeUtilisateurs) {

			if (utilisateur.getPseudo().equals(identifiant) && utilisateur.getMotDePasse().equals(motDePasse)
					|| utilisateur.getEmail().equals(identifiant) && utilisateur.getMotDePasse().equals(motDePasse)) {
				utilisateurConnecte.setNoUtilisateur(utilisateur.getNoUtilisateur());
				utilisateurConnecte.setPseudo(utilisateur.getPseudo());
				utilisateurConnecte.setNom(utilisateur.getNom());
				utilisateurConnecte.setPrenom(utilisateur.getPrenom());
				utilisateurConnecte.setEmail(utilisateur.getEmail());
				utilisateurConnecte.setTelephone(utilisateur.getTelephone());
				utilisateurConnecte.setAdresse(utilisateur.getAdresse());
				utilisateurConnecte.setCodePostal(utilisateur.getCodePostal());
				utilisateurConnecte.setVille(utilisateur.getVille());
				utilisateurConnecte.setMotDePasse(utilisateur.getMotDePasse());
				utilisateurConnecte.setCredit(utilisateur.getCredit());
				utilisateurConnecte.setAdministrateur(utilisateur.isAdministrateur());

			}
		}
		return utilisateurConnecte;
	}
	
	
	public Utilisateur recupererById(int noUtilisateur) {
		return this.utilisateurDAO.selectById(noUtilisateur);
	}
	
	
}

package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurManager() {
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    public String enregistrer(Utilisateur utilisateur) {
        String message = "";
        if(this.utilisateurDAO.selectByEmail(utilisateur.getEmail()) != null && this.utilisateurDAO.selectByPseudo(utilisateur.getPseudo()) != null) {
            message = "Ces identifiants sont déjà utilisés.";
        } else if(this.utilisateurDAO.selectByPseudo(utilisateur.getPseudo()) != null) {
            message = "Ce pseudo existe déjà.";
        } else if(this.utilisateurDAO.selectByEmail(utilisateur.getEmail()) != null) {
            message = "Cet e-mail est déjà utilisé.";
        } else {
            this.utilisateurDAO.insert(utilisateur);
        } return message;
    }

	public Utilisateur connecter(String identifiant, String motDePasse) {
		Utilisateur utilisateurConnecte = null;
		if(this.utilisateurDAO.selectByPseudo(identifiant) != null) {
			utilisateurConnecte = utilisateurDAO.selectByPseudo(identifiant);
			if(!utilisateurConnecte.getMotDePasse().equals(motDePasse)) {
				return null;
			}
		} else if(this.utilisateurDAO.selectByEmail(identifiant) != null) {
			utilisateurConnecte = utilisateurDAO.selectByEmail(identifiant);
			if (!utilisateurConnecte.getMotDePasse().equals(motDePasse)) {
				return null;
			}
		} else {
			return null;
		}
		utilisateurConnecte.setNoUtilisateur(utilisateurConnecte.getNoUtilisateur());
		utilisateurConnecte.setPseudo(utilisateurConnecte.getPseudo());
		utilisateurConnecte.setNom(utilisateurConnecte.getNom());
		utilisateurConnecte.setPrenom(utilisateurConnecte.getPrenom());
		return utilisateurConnecte;
	}
	
	
	public Utilisateur recupererById(int noUtilisateur) {
		return this.utilisateurDAO.selectById(noUtilisateur);
	}
	
	public void modifier(String colonne, String valueColonne, int noUtilisateur) {
		this.utilisateurDAO.update(colonne, valueColonne, noUtilisateur);
	}

	public void supprimer(int noUtilisateur) {
    	this.utilisateurDAO.delete(noUtilisateur);
    }

	public boolean verifPseudoExistant(String nouveauPseudo) {
    	if(this.utilisateurDAO.selectByPseudo(nouveauPseudo) != null) {
    		return true;
		} else {
			return false;
		}
	}

	public boolean verifMailExistant(String nouveauMail) {
		if(this.utilisateurDAO.selectByEmail(nouveauMail) != null) {
			return true;
		} else {
			return false;
		}
	}


	public void majCredit(int credit, int noUtilisateur) {
		this.utilisateurDAO.updateCredit(credit, noUtilisateur);
	}
}

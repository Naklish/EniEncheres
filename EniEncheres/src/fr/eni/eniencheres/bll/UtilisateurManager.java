package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateur;
	
	public UtilisateurManager() {
		this.utilisateur = DAOFactory.getUtilisateurDAO();
	}
}

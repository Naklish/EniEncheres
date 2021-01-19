package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur);
	public List<Utilisateur> selectAll();
}

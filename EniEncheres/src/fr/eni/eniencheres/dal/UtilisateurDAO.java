package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur);

	public List<Utilisateur> selectAll();
	
	public Utilisateur selectByEmail(String email);
	
	public Utilisateur selectByPseudo(String pseudo);

	public Utilisateur selectById(int noUtilisateur);
	
	public void update(String colonne, String valueColonne, int noUtilisateur);

	public void delete(int noUtilisateur);
	
	public void updateCredit(int credit, int noUtilisateur);
	
	public void updateDesactivation(boolean desactivation, int noUtilisateur);
	
	public void updateMDP(String motDePasse, int noUtilisateur);
}

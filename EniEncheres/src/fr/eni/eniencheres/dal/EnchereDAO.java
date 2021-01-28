package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.Utilisateur;

import java.util.List;

public interface EnchereDAO {
	
	public List<Enchere> selectAll();
	
	public void insert(Enchere enchere);
	
	public List<Enchere> selectByArticle(int noArticle);
	
	public Enchere selectByUtilisateurArticle(int noUtilisateur, int noArticle);
	
	public void update(Enchere enchere, Utilisateur utilisateur);
	
	public List<Enchere> selectByUtilisateur(int noUtilisateur);

	public void delete(int noArticle);
}

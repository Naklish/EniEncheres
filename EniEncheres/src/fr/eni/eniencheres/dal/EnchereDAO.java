package fr.eni.eniencheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.Utilisateur;

public interface EnchereDAO {
	
	public List<Enchere> selectAll();
	
	public void insert(Enchere enchere);
	
	public List<Enchere> selectByArticle(int noArticle);
	
	public Enchere selectByUtilisateurArticle(int noUtilisateur, int noArticle);
	
	public void update(Enchere enchere, Utilisateur utilisateur);
}

package fr.eni.eniencheres.dal;

import java.util.List;

import fr.eni.eniencheres.bo.Article;

public interface ArticleDAO {

	
	public List<Article> selectAll();
	
	public void insert(Article article);

	public Article selectById(int noArticle);

	public List<Article> selectByCategorie(int categorie);
	
	public void updatePrixVente(int noArticle, int prixVente);
	
	public List<Article> selectByUtilisateur(int noUtilisateur);
	
	public void deleteByUtilisateur(int noUtilisateur);
	
}

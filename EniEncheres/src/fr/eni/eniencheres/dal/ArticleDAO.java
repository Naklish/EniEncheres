package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Article;

import java.util.List;

public interface ArticleDAO {

	
	public List<Article> selectAll();
	
	public void insert(Article article);

	public Article selectById(int noArticle);

	public List<Article> selectByCategorie(int categorie);
	
	public void updatePrixVente(int noArticle, int prixVente);
	
	public List<Article> selectByUtilisateur(int noUtilisateur);

	public void update(Article article);

	public void delete(int noArticle);
	
}

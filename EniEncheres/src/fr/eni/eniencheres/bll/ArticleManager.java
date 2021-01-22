package fr.eni.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.dal.ArticleDAO;
import fr.eni.eniencheres.dal.DAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;
	
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	//Recupération de la liste d'articles en BDD
	public List<Article> listerArticle(){
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listeArticles = articleDAO.selectAll();
		
		//Pour chaque article on met à jour l'attribut vendeur associé à ce même article
		for(Article article : listeArticles) {
			article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
		}
		
		return listeArticles;
	}
	
	public void mettreEnVente(Article article) {
		this.articleDAO.insert(article);
	}
	
	
	//Recherche d'article en BDD
	public List<Article> rechercherArticle(String recherche) {
		List<Article> listArticles = articleDAO.selectAll();
		List<Article> listArticlesRecherche = new ArrayList<Article>();
		
		// Pour chaque nom d'article qui contient la String recherche on ajoute l'article à une liste
		for(Article article : listArticles) {
			if(article.getNomArticle().toLowerCase().contains(recherche.toLowerCase())) {
				listArticlesRecherche.add(article);
			}
		}
		return listArticlesRecherche;
	}
	
	//Recherche d'article par categorie en BDD
	public List<Article> rechercherArticleByCategorie(String recherche, int categorie){
		List<Article> listArticles = articleDAO.selectByCategorie(categorie);
		List<Article> listArticlesRecherche = null;
		
		if(recherche != null) {
			listArticlesRecherche = new ArrayList<Article>();
			
			// Pour chaque nom d'article qui contient la String recherche on ajoute l'article à une liste
			for(Article article : listArticles) {
				if(article.getNomArticle().toLowerCase().contains(recherche.toLowerCase())) {
					listArticlesRecherche.add(article);
				}
			}
		}else  {
			listArticlesRecherche = articleDAO.selectByCategorie(categorie);
		}
		return listArticlesRecherche;
		
	}
}

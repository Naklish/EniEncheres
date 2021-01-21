package fr.eni.eniencheres.bll;

import java.util.List;

import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.dal.ArticleDAO;
import fr.eni.eniencheres.dal.DAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;
	
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public List<Article> listerArticle(){
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listeArticles = articleDAO.selectAll();
		
		for(Article article : listeArticles) {
			article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
		}
		
		return listeArticles;
	}
	
	public void mettreEnVente(Article article) {
		this.articleDAO.insert(article);
	}
}

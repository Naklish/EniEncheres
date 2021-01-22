package fr.eni.eniencheres.bll;

import java.util.List;

import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.ArticleDAO;
import fr.eni.eniencheres.dal.DAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;


	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public List<Article> listerArticle() {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listeArticles = articleDAO.selectAll();

		for (Article article : listeArticles) {
			article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
		}

		return listeArticles;
	}

	public void mettreEnVente(Article article) {
		this.articleDAO.insert(article);
	}


	public Article recupererById(int noArticle) {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		RetraitManager retraitManager = new RetraitManager();
		Article article = articleDAO.selectById(noArticle);

		article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
		article.setRetrait(retraitManager.recupererById(article.getNoArticle()));

		return article;
	}
}

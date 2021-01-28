package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.dal.ArticleDAO;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.EnchereDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager {
	private ArticleDAO articleDAO;


	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	//Recupération de la liste d'articles en BDD
	public List<Article> listerArticle() {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listeArticles = articleDAO.selectAll();

		//Pour chaque article on met à jour l'attribut vendeur associé à ce même article
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
		CategorieManager categorieManager = new CategorieManager();
		Article article = articleDAO.selectById(noArticle);

		article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
		article.setRetrait(retraitManager.recupererById(article.getNoArticle()));
		article.setCategorie(categorieManager.recupererById(article.getNoCategorie()));

		return article;
	}


	//Recherche d'article en BDD
	public List<Article> rechercherArticle(String recherche) {
		List<Article> listArticles = articleDAO.selectAll();
		List<Article> listArticlesRecherche = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		// Pour chaque nom d'article qui contient la String recherche on ajoute l'article à une liste
		for(Article article : listArticles) {
			if(article.getNomArticle().toLowerCase().contains(recherche.toLowerCase())) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticlesRecherche.add(article);
			}
		}
		return listArticlesRecherche;
	}

	//Recherche d'article par categorie en BDD
	public List<Article> rechercherArticleByCategorie(String recherche, int categorie){
		List<Article> listArticles = articleDAO.selectByCategorie(categorie);
		List<Article> listArticlesRecherche = null;
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		if(recherche != null) {
			listArticlesRecherche = new ArrayList<Article>();

			// Pour chaque nom d'article qui contient la String recherche on ajoute l'article � une liste
			for(Article article : listArticles) {
				if(article.getNomArticle().toLowerCase().contains(recherche.toLowerCase())) {
					article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
					listArticlesRecherche.add(article);
				}
			}
		}else  {
			listArticlesRecherche = articleDAO.selectByCategorie(categorie);
		}
		return listArticlesRecherche;

	}

	public void updatePrixVente(Enchere enchere) {
		articleDAO.updatePrixVente(enchere.getNoArticle(), enchere.getMontantEnchere());
	}

	public List<Article> listerArticleEnCours(String recherche){
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listArticle = this.rechercherArticle(recherche);
		List<Article> listArticleEnCours = new ArrayList<Article>();


		for(Article article : listArticle) {
			if(LocalDate.now().isAfter(article.getDateDebut()) &&  LocalDate.now().isBefore(article.getDateFin())) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticleEnCours.add(article);
			}
		}

		return listArticleEnCours;
	}

	public List<Article> listerArticleEnCoursByCategorie(String recherche, int categorie){
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listArticle = this.rechercherArticleByCategorie(recherche, categorie);
		List<Article> listArticleEnCours = new ArrayList<Article>();

		for(Article article : listArticle) {
			if(LocalDate.now().isAfter(article.getDateDebut()) && LocalDate.now().isBefore(article.getDateFin())) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticleEnCours.add(article);
			}
		}

		return listArticleEnCours;
	}

	public List<Article> listerArticleMesEncheres(String recherche, int noUtilisateur){
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		List<Article> listArticleMesEncheres = new ArrayList<Article>();
		List<Article> listArticleRecherche = this.listerArticleEnCours(recherche);
		List<Enchere> listEnchere = enchereDAO.selectByUtilisateur(noUtilisateur);

		//Pour chaque article on regarde pour quel article l'utilisateur a fait une enchère
		for(Article article : listArticleRecherche) {
			for(Enchere enchere : listEnchere) {
				if(enchere.getNoArticle() == article.getNoArticle()) {
					listArticleMesEncheres.add(article);
				}
			}
		}
		//Pour chaque article on associe le vendeur
		for (Article article : listArticleMesEncheres) {
			article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
		}
		return listArticleMesEncheres;
	}

	public List<Article> listerArticleMesEncheresByCategorie(String recherche, int noUtilisateur, int categorie){
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		List<Article> listArticleMesEncheres = new ArrayList<Article>();
		List<Article> listArticleRecherche = this.listerArticleEnCoursByCategorie(recherche, categorie);
		List<Enchere> listEnchere = enchereDAO.selectByUtilisateur(noUtilisateur);

		//Pour chaque article on regarde pour quel article l'utilisateur a fait une enchère
				for(Article article : listArticleRecherche) {
					for(Enchere enchere : listEnchere) {
						if(enchere.getNoArticle() == article.getNoArticle()) {
							article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
							listArticleMesEncheres.add(article);
						}
					}
				}

		return listArticleMesEncheres;

	}

	public List<Article> listerArticlesFini(String recherche){
		List<Article> listArticle = this.rechercherArticle(recherche);
		List<Article> listArticleEnCours = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article article : listArticle) {
			if(LocalDate.now().isAfter(article.getDateFin())) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticleEnCours.add(article);
			}
		}

		return listArticleEnCours;
	}


	public List<Article> listerArticlesNonDebute(String recherche){
		List<Article> listArticle = this.rechercherArticle(recherche);
		List<Article> listArticleNonDebute = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article article : listArticle) {
			if(LocalDate.now().isBefore(article.getDateDebut())) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticleNonDebute.add(article);
			}
		}

		return listArticleNonDebute;
	}


	public List<Article> listerArticlesNonDebuteByCategorie(String recherche, int categorie){
		List<Article> listArticle = this.rechercherArticleByCategorie(recherche, categorie);
		List<Article> listArticleNonDebute = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article article : listArticle) {
			if(LocalDate.now().isBefore(article.getDateDebut())) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticleNonDebute.add(article);
			}
		}

		return listArticleNonDebute;

	}
	public List<Article> listerArticlesFiniByCategorie(String recherche, int categorie){
		List<Article> listArticle = this.rechercherArticleByCategorie(recherche, categorie);
		List<Article> listArticleFini = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article article : listArticle) {
			if(LocalDate.now().isAfter(article.getDateFin())) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticleFini.add(article);
			}
		}

		return listArticleFini;
	}

	public List<Article> listerMesArticlesRemportes(String recherche, int noUtilisateur) {
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listArticlesFini = this.listerArticlesFini(recherche);
		List<Article> listArticle = new ArrayList<Article>();

		for(Article article : listArticlesFini) {
			Enchere enchere = enchereManager.meilleurEnchereByArticle(article.getNoArticle());
			if(enchere.getNoUtilisateur() == noUtilisateur) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticle.add(article);
			}
		}

		return listArticle;
	}

	public List<Article> listerMesArticlesRemportesByCategorie(String recherche, int noUtilisateur, int categorie){
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Article> listArticlesFini = this.listerArticlesFiniByCategorie(recherche, categorie);
		List<Article> listArticle = new ArrayList<Article>();

		for(Article article : listArticlesFini) {
			Enchere enchere = enchereManager.meilleurEnchereByArticle(article.getNoArticle());
			if(enchere.getNoUtilisateur() == noUtilisateur) {
				article.setVendeur(utilisateurManager.recupererById(article.getNoUtilisateur()));
				listArticle.add(article);
			}
		}

		return listArticle;

	}

	public List<Article> listerMesVentesEnCours(String recherche, int noUtilisateur){
		List<Article> listArticleEnCours = this.listerArticleEnCours(recherche);
		List<Article> listArticleMesVentes = this.articleDAO.selectByUtilisateur(noUtilisateur);
		List<Article> listArticleMesVentesEnCours = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article articleMesVentes : listArticleMesVentes) {
			for(Article articleEnCours : listArticleEnCours) {
				if(articleEnCours.getNoArticle() == articleMesVentes.getNoArticle()) {
					articleMesVentes.setVendeur(utilisateurManager.recupererById(articleMesVentes.getNoUtilisateur()));
					listArticleMesVentesEnCours.add(articleMesVentes);
				}
			}
		}
		return listArticleMesVentesEnCours;
	}

	public List<Article> listerMesVentesEnCoursByCategorie(String recherche, int noUtilisateur, int noCategorie){
		List<Article> listArticleEnCours = this.listerArticleEnCoursByCategorie(recherche, noCategorie);
		List<Article> listArticleMesVentes = this.articleDAO.selectByUtilisateur(noUtilisateur);
		List<Article> listArticleMesVentesEnCours = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article articleEnCours : listArticleEnCours) {
			for(Article articleMesVentes : listArticleMesVentes) {
				if(articleEnCours.getNoArticle() == articleMesVentes.getNoArticle()) {
					articleMesVentes.setVendeur(utilisateurManager.recupererById(articleMesVentes.getNoUtilisateur()));
					listArticleMesVentesEnCours.add(articleMesVentes);
				}
			}
		}
		return listArticleMesVentesEnCours;
	}

	public List<Article> listerMesVentesNonDebutee(String recherche, int noUtilisateur){
		List<Article> listArticleNonDebut = this.listerArticlesNonDebute(recherche);
		List<Article> listArticleMesVentes = this.articleDAO.selectByUtilisateur(noUtilisateur);
		List<Article> listArticleMesVentesNonDebut = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article articleNonDebut : listArticleNonDebut) {
			for(Article articleMesVentes : listArticleMesVentes) {
				if(articleNonDebut.getNoArticle() == articleMesVentes.getNoArticle()) {
					articleMesVentes.setVendeur(utilisateurManager.recupererById(articleMesVentes.getNoUtilisateur()));
					listArticleMesVentesNonDebut.add(articleMesVentes);
				}
			}
		}
		return listArticleMesVentesNonDebut;
	}

	public List<Article> listerMesVentesNonDebuteeByCategorie(String recherche, int noUtilisateur, int categorie){
		List<Article> listArticleNonDebut = this.listerArticlesNonDebuteByCategorie(recherche, categorie);
		List<Article> listArticleMesVentes = this.articleDAO.selectByUtilisateur(noUtilisateur);
		List<Article> listArticleMesVentesNonDebut = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article articleNonDebut : listArticleNonDebut) {
			for(Article articleMesVentes : listArticleMesVentes) {
				if(articleNonDebut.getNoArticle() == articleMesVentes.getNoArticle()) {
					articleMesVentes.setVendeur(utilisateurManager.recupererById(articleMesVentes.getNoUtilisateur()));
					listArticleMesVentesNonDebut.add(articleMesVentes);
				}
			}
		}
		return listArticleMesVentesNonDebut;

	}

	public List<Article> listerMesVentesTerminee(String recherche, int noUtilisateur){
		List<Article> listArticleTermine = this.listerArticlesFini(recherche);
		List<Article> listArticleMesVentes = this.articleDAO.selectByUtilisateur(noUtilisateur);
		List<Article> listArticleMesVentesTerminee = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article articleTermine : listArticleTermine) {

			for(Article articleMesVentes : listArticleMesVentes) {
				System.out.println(articleMesVentes.getNomArticle());
				if(articleTermine.getNoArticle() == articleMesVentes.getNoArticle()) {
					articleMesVentes.setVendeur(utilisateurManager.recupererById(articleMesVentes.getNoUtilisateur()));
					listArticleMesVentesTerminee.add(articleMesVentes);
				}
			}
		}
		return listArticleMesVentesTerminee;
	}

	public List<Article> listerMesVentesTermineeByCategorie(String recherche, int noUtilisateur, int categorie){
		List<Article> listArticleTermine = this.listerArticlesFiniByCategorie(recherche, categorie);
		List<Article> listArticleMesVentes = this.articleDAO.selectByUtilisateur(noUtilisateur);
		List<Article> listArticleMesVentesTerminee = new ArrayList<Article>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		for(Article articleTermine : listArticleTermine) {
			for(Article articleMesVentes : listArticleMesVentes) {
				if(articleTermine.getNoArticle() == articleMesVentes.getNoArticle()) {
					articleMesVentes.setVendeur(utilisateurManager.recupererById(articleMesVentes.getNoUtilisateur()));
					listArticleMesVentesTerminee.add(articleMesVentes);
				}
			}
		}
		return listArticleMesVentesTerminee;

	}

	public void updateVente(Article article) { this.articleDAO.update(article); }

	public void deleteArticle(int noArticle) {
		EnchereManager enchereManager = new EnchereManager();
		RetraitManager retraitManager = new RetraitManager();

		// Si une enchère a été faite sur l'article à supprimer, on la supprime
		if(enchereManager.meilleurEnchereByArticle(noArticle) != null) {
			enchereManager.deleteEnchere(noArticle);
		}

		// On supprime également l'adresse de retrait
			retraitManager.deleteRetrait(noArticle);

		this.articleDAO.delete(noArticle);
	}
}

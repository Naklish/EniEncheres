package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.CategorieManager;
import fr.eni.eniencheres.bll.RetraitManager;
import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/nouvelleVente")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategorieManager categorieManager = new CategorieManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeCategories", categorieManager.listerCategories());
		request.getServletContext().getRequestDispatcher("/WEB-INF/nouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
		ArticleManager articleManager = new ArticleManager();
		String message = null;
		if(!LocalDate.parse(request.getParameter("dateDebut")).isBefore(LocalDate.now())) {
			
			Article article = new Article(request.getParameter("nomArticle"), request.getParameter("description"), 
					LocalDate.parse(request.getParameter("dateDebut")), LocalDate.parse(request.getParameter("dateFin")), Integer.parseInt(request.getParameter("prixInitial")), 
					Integer.parseInt(request.getParameter("prixInitial")), utilisateurConnecte.getNoUtilisateur(),  Integer.parseInt(request.getParameter("categorie")));
			
			articleManager.mettreEnVente(article);
			
			RetraitManager retraitManager = new RetraitManager();
			Retrait retrait = new Retrait(article.getNoArticle(), utilisateurConnecte.getAdresse(), utilisateurConnecte.getCodePostal(), utilisateurConnecte.getVille());
			retraitManager.enregistrerRetrait(retrait);
			
			 message = "Votre article est mis en vente.";
		}else {
			message ="La date de début n'est pas valide.";
		}
		request.setAttribute("message", message);
		request.setAttribute("listeCategories", categorieManager.listerCategories());
		request.getServletContext().getRequestDispatcher("/WEB-INF/nouvelleVente.jsp").forward(request, response);
	}

}

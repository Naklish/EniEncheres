package fr.eni.eniencheres.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.EnchereManager;
import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Encherir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager enchereManager = new EnchereManager();
		ArticleManager articleManager = new ArticleManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Enchere enchere = null;
		//On récupère l'article à enchérir
		Article article = articleManager.recupererById((Integer.parseInt(request.getParameter("noArticle")))); 
		
		//On récupère l'utilisateur en session
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
		
		//On créé l'enchere à effectuer si la vente est en cours
		if(LocalDate.now().isBefore(article.getDateDebut())) {
			request.setAttribute("message", "Enchère impossible, la vente n'a pas débutée");
		}else if(LocalDate.now().isAfter(article.getDateFin())){
			request.setAttribute("message", "Enchère impossible, la vente est terminée");
		}else {
			enchere = new Enchere(utilisateurConnecte.getNoUtilisateur(), article.getNoArticle(), LocalDate.now(), Integer.valueOf(request.getParameter("enchere")));
		}
		
		if(enchere != null) {
			//Si l'utilisateur a assez de crédit, elle est insérée en BDD
			if(enchereManager.encherir(enchere, utilisateurConnecte)) {
				request.setAttribute("message", "Enchère effectuéé.");
			}else {
				request.setAttribute("message", "Enchère impossible, vous n'avez pas assez de crédit;");
			}
		}
		
		
		//On récupère la meilleur enchère de l'article
		Enchere meilleurEnchere = enchereManager.meilleurEnchereByArticle(article.getNoArticle());
		
		 //On récupère l'utilisateur de la meilleur Enchère
        Utilisateur utilisateur = utilisateurManager.recupererById(meilleurEnchere.getNoUtilisateur());
        
        //On récupère l'utilisateur de la session en BDD
        Utilisateur newUtilisateurConnecte = utilisateurManager.recupererById(utilisateurConnecte.getNoUtilisateur());
        
        request.getSession().setAttribute("utilisateurConnecte", newUtilisateurConnecte);
        request.setAttribute("acheteur", utilisateur);
		request.setAttribute("vente", article);
		request.setAttribute("meilleurEnchere", meilleurEnchere);
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/afficherVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

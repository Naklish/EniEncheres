package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/supprimerCompte")
public class SupprimerCompte extends HttpServlet {
	private UtilisateurManager utilisateurManager = new UtilisateurManager();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        utilisateurManager = new UtilisateurManager();
        Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
        
        utilisateurManager.supprimer(utilisateurConnecte.getNoUtilisateur());
        request.getSession().invalidate();
        String messageSuppr = "Votre profil a bien été supprimé.";
        request.setAttribute("messageSuppr", messageSuppr);

        ArticleManager articleManager = new ArticleManager();
        List<Article> listArticles = articleManager.listerArticle();
        request.setAttribute("listArticles", listArticles);

        request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	utilisateurManager.supprimer(Integer.valueOf(request.getParameter("utilisateurSuppr")));
    	
    	List<Utilisateur> listeUtilisateur =  this.utilisateurManager.listerUtilisateurs();
    	
    	request.setAttribute("listeUtilisateur", listeUtilisateur);
    	request.setAttribute("message", "Compte utilisateur supprimer");
    	request.getServletContext().getRequestDispatcher("/WEB-INF/listeProfil.jsp").forward(request, response);
    }
}

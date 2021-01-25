package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.CategorieManager;
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

@WebServlet("/connexion")
public class Connexion extends HttpServlet {
    CategorieManager categorieManager = new CategorieManager();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("listeCategories", categorieManager.listerCategories());
        request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        Utilisateur utilisateurConnecte = utilisateurManager.connecter(request.getParameter("login"), request.getParameter("motdepasse"));

        request.setAttribute("listeCategories", categorieManager.listerCategories());

        ArticleManager articleManager = new ArticleManager();
    	List<Article> listArticles = articleManager.listerArticle();
    	request.setAttribute("listArticles", listArticles);

        if(utilisateurConnecte == null) {
            String message = "Identifiant ou mot de passe incorrect.";
            request.setAttribute("message", message);
            request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,response);

        } else if (utilisateurConnecte.getPseudo() != null){
            request.getSession().setAttribute("utilisateurConnecte", utilisateurConnecte);
            request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request,response);
        }
    }
}


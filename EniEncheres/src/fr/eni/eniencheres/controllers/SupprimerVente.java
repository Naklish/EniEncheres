package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bo.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/supprimerVente")
public class SupprimerVente extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleManager articleManager = new ArticleManager();
        Article article = articleManager.recupererById(Integer.parseInt(request.getParameter("noArticle")));

        // Suppression de l'article concerné
        articleManager.deleteArticle(article.getNoArticle());
        // Création du message de confirmation
        String messageSuppr = "Votre vente a bien été supprimée.";
        request.setAttribute("messageSupprArticle", messageSuppr);

        // Affichage de la liste des enchères lors de la redirection sur la page d'accueil
        List<Article> listArticles = articleManager.listerArticle();
        request.setAttribute("listArticles", listArticles);

        request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.CategorieManager;
import fr.eni.eniencheres.bo.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	CategorieManager categorieManager = new CategorieManager();
	ArticleManager articleManager = new ArticleManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recupération de la liste articles en BDD
        request.setAttribute("listArticles", articleManager.listerArticle());

		request.setAttribute("listeCategories", categorieManager.listerCategories());

        request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		List<Article> listArticles = null;

		request.setAttribute("listeCategories", categorieManager.listerCategories());

        if (categorie == 0) {
            //Récupération d'une liste articles en fonction de la recherche
            listArticles = articleManager.rechercherArticle(request.getParameter("recherche"));

        } else {
            // Récupération d'une liste d'articles en fonction de la recherche et de la categorie
            listArticles = articleManager.rechercherArticleByCategorie(request.getParameter("recherche"), Integer.parseInt(request.getParameter("categorie")));
        }
        request.setAttribute("listArticles", listArticles);
		request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }


}

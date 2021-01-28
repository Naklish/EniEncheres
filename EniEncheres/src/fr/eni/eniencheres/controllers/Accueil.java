package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.CategorieManager;
import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Utilisateur;

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
		String mesEncheresEnCours = request.getParameter("encheresEnCours"); //A revoir
		String encheresOuvertes = request.getParameter("encheresOuvertes");
		String mesEncheresRemportees = request.getParameter("mesEncheresRemportees");
		String mesVentesEncours = request.getParameter("ventesEnCours");
		String mesVentesNonDebutee = request.getParameter("ventesNonDebutee");
		String mesVentesTerminee = request.getParameter("ventesTerminees");
		
		List<Article> listArticles = null;
		
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
		
		request.setAttribute("listeCategories", categorieManager.listerCategories());
		
		if(encheresOuvertes != null){
			if(categorie == 0) {
				listArticles = articleManager.listerArticleEnCours(request.getParameter("recherche"));
			}else {
				listArticles = articleManager.listerArticleEnCoursByCategorie(request.getParameter("recherche"), categorie);
			}
			
		}else if(mesEncheresEnCours != null && utilisateurConnecte != null) {
			
			if (categorie == 0) {
				listArticles = articleManager.listerArticleMesEncheres(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur());
			} else {
				listArticles = articleManager.listerArticleMesEncheresByCategorie(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur(), categorie);
			}
			
		} else if (mesEncheresRemportees != null && utilisateurConnecte != null){
			if(categorie == 0) {
				listArticles = articleManager.listerMesArticlesRemportes(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur());
			}else {
				listArticles = articleManager.listerMesArticlesRemportesByCategorie(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur(), categorie);
			}
			
		} else if (mesVentesEncours != null){
			if(categorie == 0) {
				listArticles = articleManager.listerMesVentesEnCours(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur());
			}else {
				listArticles = articleManager.listerMesVentesEnCoursByCategorie(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur(), categorie);
			}
		}else if(mesVentesNonDebutee != null){
			if(categorie == 0) {
				listArticles = articleManager.listerMesVentesNonDebutee(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur());
			}else {
				listArticles = articleManager.listerMesVentesNonDebuteeByCategorie(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur(), categorie);
			}
		}else if (mesVentesTerminee != null) {
			if(categorie == 0) {
				listArticles = articleManager.listerMesVentesTerminee(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur());
			}else {
				listArticles = articleManager.listerMesVentesTermineeByCategorie(request.getParameter("recherche"), utilisateurConnecte.getNoUtilisateur(), categorie);
			}
		}else {
			// 0 -> Toutes les catégories
	        if (categorie == 0) {
	        	
	            //Récupération d'une liste articles en fonction de la recherche
	            listArticles = articleManager.rechercherArticle(request.getParameter("recherche"));

	        } else {
	            // Récupération d'une liste d'articles en fonction de la recherche et de la categorie
	            listArticles = articleManager.rechercherArticleByCategorie(request.getParameter("recherche"), Integer.parseInt(request.getParameter("categorie")));
	        }
		}
		
        
        
        request.setAttribute("listArticles", listArticles);
		request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }


}


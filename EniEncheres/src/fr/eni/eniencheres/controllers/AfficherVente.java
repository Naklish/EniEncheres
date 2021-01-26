package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.EnchereManager;
import fr.eni.eniencheres.bll.RetraitManager;
import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/afficherVente")
public class AfficherVente extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("utilisateurConnecte") != null) {
            ArticleManager articleManager = new ArticleManager();
            EnchereManager enchereManager = new EnchereManager();
            UtilisateurManager utilisateurManager = new UtilisateurManager();
            boolean venteRemporte = false;
            boolean venteFinie = false;
            
            Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
            
            //On récupère l'article dans la base de donnéé en fonction de l'id
            Article article = articleManager.recupererById(Integer.parseInt(request.getParameter("noArticle")));
            
            
  

            //On récupère la meilleure Enchère de l'article
            Enchere enchere = enchereManager.meilleurEnchereByArticle(article.getNoArticle());

            //On récupère l'utilisateur de la meilleure Enchère
            Utilisateur utilisateur = utilisateurManager.recupererById(enchere.getNoUtilisateur());

            
            //On récupère le vendeur de l'article
            Utilisateur vendeur = utilisateurManager.recupererById(article.getNoUtilisateur());
            
            if(article.getDateFin().isBefore(LocalDate.now())) {
            	venteFinie = true;
            }
            if(utilisateurConnecte.getNoUtilisateur() == utilisateur.getNoUtilisateur()) {
            	 if(article.getDateFin().isBefore(LocalDate.now())) {
                 	request.setAttribute("messageVente", "Vous avez remporté la vente");
                 	venteRemporte = true;
                 }
            }
           
            
            request.setAttribute("vendeur", vendeur);
            request.setAttribute("acheteur", utilisateur);
            request.setAttribute("meilleurEnchere", enchere);
            request.setAttribute("vente", article);
            request.setAttribute("venteRemporte", venteRemporte);
            request.setAttribute("venteFinie", venteFinie);
            
            request.getServletContext().getRequestDispatcher("/WEB-INF/afficherVente.jsp").forward(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

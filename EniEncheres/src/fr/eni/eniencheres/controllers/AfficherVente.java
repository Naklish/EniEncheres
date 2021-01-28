package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.*;
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

@WebServlet("/afficherVente")
public class AfficherVente extends HttpServlet {
    CategorieManager categorieManager = new CategorieManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listeCategories", categorieManager.listerCategories());
        if (request.getSession().getAttribute("utilisateurConnecte") != null) {
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


            if (article.getDateFin().isBefore(LocalDate.now())) {
                venteFinie = true;
            }
            if (utilisateurConnecte.getNoUtilisateur() == utilisateur.getNoUtilisateur()) {
                if (article.getDateFin().isBefore(LocalDate.now())) {
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
            request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleManager articleManager = new ArticleManager();
        RetraitManager retraitManager = new RetraitManager();

        Article article = articleManager.recupererById(Integer.parseInt(request.getParameter("noArticle")));
        Retrait retrait = retraitManager.recupererById(article.getNoArticle());
        request.setAttribute("listeCategories", categorieManager.listerCategories());

        // Modification de l'article
        article.setNomArticle(request.getParameter("nomArticle"));
        article.setDescription(request.getParameter("description"));
        article.setDateDebut(LocalDate.parse(request.getParameter("dateDebut")));
        article.setDateFin(LocalDate.parse(request.getParameter("dateFin")));
        article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
        article.setPrixVente(Integer.parseInt(request.getParameter("prixInitial")));
        article.setNoCategorie(Integer.parseInt(request.getParameter("categorie")));

        // Modification de l'adresse de retrait
        retrait.setAdresse(request.getParameter("adresse"));
        retrait.setCodePostal(request.getParameter("codePostal"));
        retrait.setVille(request.getParameter("ville"));

        // On appelle les méthodes update pour mettre à jour l'article et son adresse de retrait
        articleManager.updateVente(article);
        retraitManager.updateRetrait(retrait);

        request.setAttribute("message", "L'article a bien été modifié");
        request.setAttribute("retrait", retrait);
        request.setAttribute("vente", article);

        request.getServletContext().getRequestDispatcher("/WEB-INF/afficherVente.jsp").forward(request, response);
    }
}

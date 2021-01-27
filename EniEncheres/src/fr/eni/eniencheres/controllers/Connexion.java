package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.CategorieManager;
import fr.eni.eniencheres.bll.CookieIDManager;
import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.CookieID;
import fr.eni.eniencheres.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {
    CategorieManager categorieManager = new CategorieManager();
    CookieIDManager cookieIdManager = new CookieIDManager();
    UtilisateurManager utilisateurManager = new UtilisateurManager();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Cookie[] cookies = request.getCookies();
    	CookieID cookieId = null;
    	Utilisateur utilisateur = null;
    	
    	//S'il y a des cookies on tente de récupérer le cookie qui permet de se souvenir de l'utilisateur
    	if(cookies != null) {
    		for(Cookie cookieT : cookies) {
    			if(cookieIdManager.recupererCookie(cookieT.getValue()) != null) {
    				cookieId = cookieIdManager.recupererCookie(cookieT.getValue());
    			}
    		}
    	}
    	//S'il y a le cookieID on récupère les données de l'utilisateur pour qu'il puisse se connecter automatiquement
    	if(cookieId != null) {
    		utilisateur = utilisateurManager.recupererById(cookieId.getNoUtilisateur());
    		request.setAttribute("utilisateurSouvenir", utilisateur);
    	}

        request.setAttribute("listeCategories", categorieManager.listerCategories());
        request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        Utilisateur utilisateurConnecte = utilisateurManager.connecter(request.getParameter("login"), request.getParameter("motdepasse"));
        String souvenir = request.getParameter("souvenir");
        
        request.setAttribute("listeCategories", categorieManager.listerCategories());
        
        ArticleManager articleManager = new ArticleManager();
        List<Article> listArticles = articleManager.listerArticle();
        request.setAttribute("listArticles", listArticles);
        
        //Récupération des cookies
        Cookie[] cookies = request.getCookies();
        Cookie cookieId = null;
        //Recupération du cookie permettant d'indentifier automatiquement l'utilisateur 
    	if(cookies != null) {
    		for(Cookie cookieT : cookies) {
    			if(cookieIdManager.recupererCookie(cookieT.getValue()) != null) {
    				cookieId = cookieT;
    			}
    		}
    	}
        
    	//Si l'utilisateur n'a pas de cookie permettant de l'identifier on lui en créé un s'il le désir et s'il est effectivement connnecté
        Cookie cookie = null;
        if(cookieId == null && souvenir != null && utilisateurConnecte.getPseudo() != null) {
        	String uuid = UUID.randomUUID().toString();
        	cookie = new Cookie("cookieID", uuid);
        	cookie.setMaxAge(86400);
        	cookieIdManager.enregistrerCookie(cookie.getValue(), utilisateurConnecte.getNoUtilisateur());
        	response.addCookie(cookie);
        
        	//S'il ne désire plus que l'on se souvienne de lui, on lui supprime son cookie
        }else if(cookieId != null && souvenir == null) {
        	cookieId.setMaxAge(0);
        	response.addCookie(cookieId);
        	cookieIdManager.effacerCookie(utilisateurConnecte.getNoUtilisateur());
        }
        

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


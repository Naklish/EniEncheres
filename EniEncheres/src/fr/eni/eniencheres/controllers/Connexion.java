package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateurManager utilisateurManager = new UtilisateurManager();
       
        Utilisateur utilisateurConnecte = utilisateurManager.connecter(request.getParameter("login"), request.getParameter("motdepasse"));

        if(utilisateurConnecte.getPseudo() != null) {
            request.getSession().setAttribute("utilisateurConnecte", utilisateurConnecte);
            System.out.println(utilisateurConnecte.toString());
            request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request,response);

        } else {
            String message = "Vous n'êtes pas enregistré.";
            request.setAttribute("message", message);
            request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,response);
        }
    }
}


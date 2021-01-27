package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/modifierProfil")
public class ModifierProfil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

        if (request.getParameter("motDePasseActuel") != "" && request.getParameter("motDePasseActuel").equals(utilisateurConnecte.getMotDePasse())) {

            if (request.getParameter("pseudo") != "") {
                if (!utilisateurManager.verifPseudoExistant(request.getParameter("pseudo"))) {
                    utilisateurManager.modifier("pseudo", request.getParameter("pseudo"), utilisateurConnecte.getNoUtilisateur());
                    request.setAttribute("message", "Compte utilisateur modifié");
                } else {
                    System.out.println("Ce pseudo existe déjà");
                    request.setAttribute("messageErrPseudo", "Ce pseudo existe déjà.");
                }
            }
            if (request.getParameter("nom") != "") {
                utilisateurManager.modifier("nom", request.getParameter("nom"), utilisateurConnecte.getNoUtilisateur());
                request.setAttribute("message", "Compte utilisateur modifié");
            }
            if (request.getParameter("prenom") != "") {
                utilisateurManager.modifier("prenom", request.getParameter("prenom"), utilisateurConnecte.getNoUtilisateur());
                request.setAttribute("message", "Compte utilisateur modifié");
            }
            if (request.getParameter("email") != "") {
                if (!utilisateurManager.verifMailExistant(request.getParameter("email"))) {
                    utilisateurManager.modifier("email", request.getParameter("email"), utilisateurConnecte.getNoUtilisateur());
                    request.setAttribute("message", "Compte utilisateur modifié");
                } else {
                    request.setAttribute("messageErrMail", "Cet e-mail est déjà utilisé.");
                }
            }
            if (request.getParameter("telephone") != "") {
                utilisateurManager.modifier("telephone", request.getParameter("telephone"), utilisateurConnecte.getNoUtilisateur());
                request.setAttribute("message", "Compte utilisateur modifié");
            }
            if (request.getParameter("adresse") != "") {
                utilisateurManager.modifier("rue", request.getParameter("adresse"), utilisateurConnecte.getNoUtilisateur());
                request.setAttribute("message", "Compte utilisateur modifié");
            }
            if (request.getParameter("codePostal") != "") {
                utilisateurManager.modifier("code_postal", request.getParameter("codePostal"), utilisateurConnecte.getNoUtilisateur());
                request.setAttribute("message", "Compte utilisateur modifié");
            }
            if (request.getParameter("ville") != "") {
                utilisateurManager.modifier("ville", request.getParameter("ville"), utilisateurConnecte.getNoUtilisateur());
                request.setAttribute("message", "Compte utilisateur modifié");
            }
            if (request.getParameter("nouveauMotDePasse") != "" && request.getParameter("confirmation").equals(request.getParameter("nouveauMotDePasse"))) {
                utilisateurManager.modifier("mot_de_passe", request.getParameter("nouveauMotDePasse"), utilisateurConnecte.getNoUtilisateur());
                request.setAttribute("message", "Compte utilisateur modifié");
            } else {
                request.setAttribute("messageErreurConfirmation", "La confirmation n'est pas identique au nouveau mot de passe.");
            }

        } else {
            request.setAttribute("messageErreur", "Votre mot de passe ne correspond pas");
        }

        request.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
    }

}

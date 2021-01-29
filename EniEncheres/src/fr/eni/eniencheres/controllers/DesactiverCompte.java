package fr.eni.eniencheres.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class DesactiverCompte
 */
@WebServlet("/desactiverCompte")
public class DesactiverCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurManager utilisateurManager = new UtilisateurManager(); 
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		utilisateurManager.desactiverCompte(Integer.valueOf(request.getParameter("utilisateurDesactive")));
		Utilisateur profil = utilisateurManager.recupererById(Integer.valueOf(request.getParameter("utilisateurDesactive")));
		
		request.setAttribute("message", "Le compte a été désactivé");
		request.setAttribute("profil", profil);
		request.getServletContext().getRequestDispatcher("/WEB-INF/afficherProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		utilisateurManager.reactiverCompte(Integer.valueOf(request.getParameter("utilisateurReactive")));
		Utilisateur profil = utilisateurManager.recupererById(Integer.valueOf(request.getParameter("utilisateurReactive")));
		
		request.setAttribute("message", "Le compte a été réactivé");
		
		request.setAttribute("profil", profil);
		request.getServletContext().getRequestDispatcher("/WEB-INF/afficherProfil.jsp").forward(request, response);
	}

}

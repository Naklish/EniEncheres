package fr.eni.eniencheres.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ListeProfil
 */
@WebServlet("/listeProfil")
public class ListeProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurManager utilisateurManager = new UtilisateurManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> listeUtilisateur =  this.utilisateurManager.listerUtilisateurs();
		
		Utilisateur utilisateurConnecte = (Utilisateur) request.getAttribute("utilisateurConnecte");
		
		request.setAttribute("utilisateurConnecte", utilisateurConnecte);
		request.setAttribute("listeUtilisateur", listeUtilisateur);
		request.getServletContext().getRequestDispatcher("/WEB-INF/listeProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

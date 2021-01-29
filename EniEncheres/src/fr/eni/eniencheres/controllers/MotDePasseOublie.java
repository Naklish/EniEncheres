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
 * Servlet implementation class MotDePasseOublie
 */
@WebServlet("/motDePasseOublie")
public class MotDePasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/motDePasseOublie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email  = request.getParameter("email");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = utilisateurManager.recupererByEmail(email);
		
		String url = "http://localhost:8080/EniEncheres_Web_exploded/nouveauMotDePasse?ID=" + utilisateur.getNoUtilisateur() +"\"";
		
		request.setAttribute("message", "Veuillez cliquez sur ce lien :");
		request.setAttribute("url", url);
		request.getServletContext().getRequestDispatcher("/WEB-INF/motDePasseOublie.jsp").forward(request, response);
	}

}

package fr.eni.eniencheres.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		//Utilisateur utilisateur = new Utilisateur("Flooz", "Tonton", "toto", "mon@email.com", "0666666666", "maRue", "35656", "MaVille", "Password", false);
		//utilisateurDAO.insert(utilisateur);
		request.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("moDePasse") == request.getParameter("confirmation")) {
			
		}else {
			String message = "La confirmation ne correspond pas au mot de passe";
			request.setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		}
	}

}

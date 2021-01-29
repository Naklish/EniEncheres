package fr.eni.eniencheres.controllers;

import fr.eni.eniencheres.bll.UtilisateurManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class NouveauMotDePasse
 */
@WebServlet("/nouveauMotDePasse")
public class NouveauMotDePasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouveauMotDePasse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ID", Integer.valueOf(request.getParameter("ID")));
		request.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		if(request.getParameter("nouveauMotDePasse").equals(request.getParameter("confirmation"))) {
			utilisateurManager.majMDP(request.getParameter("nouveauMotDePasse"), Integer.valueOf(request.getParameter("ID")));
			request.setAttribute("messageOk",	"Mot de passe réinitialisé");
		}
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
	}

}

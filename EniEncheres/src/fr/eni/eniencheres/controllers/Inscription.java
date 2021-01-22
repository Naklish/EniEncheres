package fr.eni.eniencheres.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniencheres.bll.ArticleManager;
import fr.eni.eniencheres.bll.UtilisateurManager;
import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
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
        ArticleManager articleManager = new ArticleManager();
        List<Article> listArticles = articleManager.listerArticle();
        request.setAttribute("listArticles", listArticles);
        request.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("motDePasse").equals(request.getParameter("confirmation"))) {
            Utilisateur utilisateur = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("email"),
                    request.getParameter("telephone"), request.getParameter("adresse"), request.getParameter("codePostal"), request.getParameter("ville"), request.getParameter("motDePasse"), false);
            UtilisateurManager utilisateurManager = new UtilisateurManager();
            String messageOk = "Votre compte a bien été créé !\nConnectez vous :";
            String messageErreur = utilisateurManager.enregistrer(utilisateur);
            if (messageErreur == "") {
                request.setAttribute("messageOk", messageOk);
                request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
            } else if (messageErreur != "") {
                request.setAttribute("messageErreur", messageErreur);
                request.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
            }
        } else {
            String messageMdp = "La confirmation ne correspond pas au mot de passe";
            request.setAttribute("messageMdp", messageMdp);
            request.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
        }
    }
}
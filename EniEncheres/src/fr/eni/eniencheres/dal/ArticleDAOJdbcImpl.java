package fr.eni.eniencheres.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Article;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String SELECT = "SELECT * FROM ARTICLES_VENDUS";
	
	@Override
	public List<Article> selectAll() {
		List<Article> listArticles = new ArrayList<Article>();
		Article article = null;
		Connection cnx = null;
		Statement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT);
			
			if(rs == null) {
				throw new SQLException("Erreur d'éxecution");
			}
			
			while(rs.next()) {
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				listArticles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listArticles;
	}

}

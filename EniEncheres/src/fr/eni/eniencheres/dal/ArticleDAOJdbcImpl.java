package fr.eni.eniencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Article;
import fr.eni.eniencheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {
    private static final String SELECT = "SELECT * FROM ARTICLES_VENDUS";
    private static final String INSERT = "INSERT INTO ARTICLES_VENDUS "
            + "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)"
            + "VALUES (?,?,?,?,?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";


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

            if (rs == null) {
                throw new SQLException("Erreur d'�xecution");
            }

            while (rs.next()) {
                article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
                listArticles.add(article);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (cnx != null) {
                try {
                    cnx.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return listArticles;
    }


    @Override
    public void insert(Article article) {
        Connection cnx = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, article.getNomArticle());
            pstmt.setString(2, article.getDescription());
            pstmt.setDate(3, Date.valueOf(article.getDateDebut()));
            pstmt.setDate(4, Date.valueOf(article.getDateFin()));
            pstmt.setInt(5, article.getPrixInitial());
            pstmt.setInt(6, article.getPrixVente());
            pstmt.setInt(7, article.getNoUtilisateur());
            pstmt.setInt(8, article.getNoCategorie());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                article.setNoArticle(rs.getInt(1));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (cnx != null) {
                try {
                    cnx.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public Article selectById(int noArticle) {
        Article article = new Article();
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_BY_ID);

            pstmt.setInt(1, noArticle);
            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {
                throw new SQLException("Erreur d'exécution");
            }

            while (rs.next()) {
                article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (cnx != null) {
                try {
                    cnx.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return article;
    }

}

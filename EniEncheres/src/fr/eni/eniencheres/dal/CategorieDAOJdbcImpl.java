package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOJdbcImpl implements CategorieDAO {
    private static final String SELECT_ALL = "SELECT * FROM CATEGORIES";
    private static final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";


    @Override
    public List<Categorie> selectAll() {
        List<Categorie> categories = new ArrayList<>();
        Categorie categorie;
        Connection cnx = null;
        Statement stmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            stmt = cnx.createStatement();

            ResultSet rs = stmt.executeQuery(SELECT_ALL);

            if (rs == null) {
                throw new SQLException("Erreur d'exécution");
            }

            while(rs.next()) {
                categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
                categories.add(categorie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(cnx != null) {
                try {
                    cnx.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return categories;
    }


    @Override
    public Categorie selectById(int noCategorie) {
        Categorie categorie = null;
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1, noCategorie);

            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {
                throw new SQLException("Erreur d'exécution");
            }

            while (rs.next()) {
                categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        return categorie;
    }
}

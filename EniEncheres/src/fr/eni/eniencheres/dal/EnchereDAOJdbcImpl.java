package fr.eni.eniencheres.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String SELECT = "SELECT * FROM ENCHERES";
	
	@Override
	public List<Enchere> selectAll() {
		List<Enchere> listEncheres = new ArrayList<Enchere>();
		Enchere enchere = null;
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
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), 
						rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"));
				listEncheres.add(enchere);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
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
		return listEncheres;
	}

}

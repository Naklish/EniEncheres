package fr.eni.eniencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String SELECT = "SELECT * FROM ENCHERES";
	private static final String INSERT ="INSERT INTO ENCHERES VALUES (?,?,?,?)";
	private static final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?";
	private static final String SELECT_BY_UTILISATEUR_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ? AND no_utilisateur = ?";
	private static final String UPDATE = "UPDATE ENCHERES SET montant_enchere = ?, date_enchere = ? WHERE no_utilisateur = ? AND no_article = ?";
	private static final String SELECT_BY_UTILISATEUR = "SELECT * FROM ENCHERES WHERE no_utilisateur = ?";
	private static final String DELETE_BY_UTILISATEUR ="DELETE FROM ENCHERES WHERE no_utilisateur = ?";
	private static final String DELETE_BY_ARTICLE = "DELETE FROM ENCHERES WHERE no_article = ?";
	
	
	@Override
	public void deleteByArticle(int noArticle) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_BY_UTILISATEUR);
			
			pstmt.setInt(1, noArticle);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void deleteByUtilisateur(int noUtilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE_BY_UTILISATEUR);
			
			pstmt.setInt(1, noUtilisateur);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
				throw new SQLException("Erreur d'�xecution");
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

	@Override
	public void insert(Enchere enchere) {
		Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT);

			pstmt.setInt(1, enchere.getNoUtilisateur());
			pstmt.setInt(2, enchere.getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Enchere selectByUtilisateurArticle(int noUtilisateur, int noArticle){

		Enchere enchere = null;
		Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR_ARTICLE);

			pstmt.setInt(1, noArticle);
			pstmt.setInt(2, noUtilisateur);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enchere;
	}
	@Override
	public List<Enchere> selectByArticle(int noArticle) {
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE);

			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"));
				listEnchere.add(enchere);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listEnchere;
	}

	@Override
	public void update(Enchere enchere, Utilisateur utilisateur) {
		Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
        cnx = ConnectionProvider.getConnection();
        pstmt = cnx.prepareStatement(UPDATE);

        pstmt.setInt(1, enchere.getMontantEnchere());
        pstmt.setDate(2, Date.valueOf(enchere.getDateEnchere()));
        pstmt.setInt(3, utilisateur.getNoUtilisateur());
        pstmt.setInt(4, enchere.getNoArticle());

        pstmt.executeUpdate();

        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Enchere> selectByUtilisateur(int noUtilisateur) {
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		Connection cnx = null;
        PreparedStatement pstmt = null;
		
        try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR);
			
			pstmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"));
				listEnchere.add(enchere);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listEnchere;
	}

	
}

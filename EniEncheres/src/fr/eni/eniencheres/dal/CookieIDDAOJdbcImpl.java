package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.CookieID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CookieIDDAOJdbcImpl implements CookieIDDAO {
	
	private static final String SELECT = "SELECT * FROM COOKIES_ID WHERE UUID = ?";
	private static final String INSERT = "INSERT INTO COOKIES_ID (UUID, no_utlisateur) VALUES (?,?)";
	private static final String DELETE = "DELETE FROM COOKIES_ID WHERE no_utlisateur = ?";
	
	@Override
	public CookieID select(String uuid) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		CookieID cookieId = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT);
			
			pstmt.setString(1, uuid);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cookieId = new CookieID(rs.getString("UUID"), rs.getInt("no_utlisateur"));
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
		return cookieId;
	}

	@Override
	public void insert(String uuid, int noUtilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT);
			
			pstmt.setString(1, uuid);
			pstmt.setInt(2, noUtilisateur);
			
			pstmt.executeUpdate();
			
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
	public void delete(int noUtilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE);
			
			pstmt.setInt(1, noUtilisateur);
			
			pstmt.executeUpdate();
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
}

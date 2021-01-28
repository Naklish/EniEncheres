package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	private static final String INSERT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article = ?";
	private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
	private static final String DELETE = "DELETE FROM RETRAITS WHERE no_article = ?";

	@Override
	public void insert(Retrait retrait) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT);
			
			pstmt.setInt(1, retrait.getNoArticle());
			pstmt.setString(2, retrait.getAdresse());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
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
	}

	@Override
	public Retrait selectById(int noArticle) {
		Retrait retrait = new Retrait();
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
				retrait = new Retrait(rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
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
		return retrait;
	}

	@Override
	public void update(Retrait retrait) {
		Connection cnx = null;
		PreparedStatement pstmt = null;


		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE);

			pstmt.setString(1, retrait.getAdresse());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, retrait.getNoArticle());

			pstmt.executeUpdate();
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
	}


	@Override
	public void delete(int noArticle) {
		Connection cnx = null;
		PreparedStatement pstmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(DELETE);

			pstmt.setInt(1, noArticle);

			pstmt.executeUpdate();

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
	}

}

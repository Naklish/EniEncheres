package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

    private static final String INSERT = "INSERT INTO UTILISATEURS "
            + "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM UTILISATEURS";
    private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
    private static final String SELECT_MAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";
    private static final String SELECT_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
    private static final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
    private static final String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?";


    @Override
    public void insert(Utilisateur utilisateur) {
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, utilisateur.getPseudo());
            pstmt.setString(2, utilisateur.getNom());
            pstmt.setString(3, utilisateur.getPrenom());
            pstmt.setString(4, utilisateur.getEmail());
            pstmt.setString(5, utilisateur.getTelephone());
            pstmt.setString(6, utilisateur.getAdresse());
            pstmt.setString(7, utilisateur.getCodePostal());
            pstmt.setString(8, utilisateur.getVille());
            pstmt.setString(9, utilisateur.getMotDePasse());
            pstmt.setInt(10, utilisateur.getCredit());
            pstmt.setBoolean(11, utilisateur.isAdministrateur());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    utilisateur.setNoUtilisateur(rs.getInt(1));
                }
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
    public List<Utilisateur> selectAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Utilisateur utilisateur = null;
        Connection cnx = null;
        Statement stmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            stmt = cnx.createStatement();

            ResultSet rs = stmt.executeQuery(SELECT);
            if (rs == null) {
                throw new SQLException("Erreur d'exécution");
            }
            while (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
                        rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
                        rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

                utilisateurs.add(utilisateur);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        return utilisateurs;
    }

    @Override
    public Utilisateur selectById(int noUtilisateur) {
        Utilisateur utilisateur = new Utilisateur();
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_BY_ID);

            pstmt.setInt(1, noUtilisateur);
            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {
                throw new SQLException("Erreur d'exécution");
            }

            while (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
                        rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
                        rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
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
        return utilisateur;
    }

    @Override
    public Utilisateur selectByEmail(String email) {
        Utilisateur utilisateur = null;
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_MAIL);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {

                throw new SQLException("Rien n'a été trouvé en BDD");
            }
            while (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
                        rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
                        rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
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
        return utilisateur;
    }

    @Override
    public Utilisateur selectByPseudo(String pseudo) {
        Utilisateur utilisateur = null;
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_PSEUDO);
            pstmt.setString(1, pseudo);

            ResultSet rs = pstmt.executeQuery();

            if (rs == null) {
                throw new SQLException("Rien n'a été trouvé en BDD");
            }
            while (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
                        rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
                        rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
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
        return utilisateur;
    }

    @Override
    public void update(String colonne, String valueColonne, int noUtilisateur) {
        String update = "UPDATE UTILISATEURS SET " + colonne + " = ? WHERE no_utilisateur = ?";
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnectionProvider.getConnection();
            pstmt = cnx.prepareStatement(update);

            pstmt.setString(1, valueColonne);
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
	public void updateCredit(int credit, int noUtilisateur) {
		Connection cnx = null;
        PreparedStatement pstmt = null;
        
        try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_CREDIT);
			
			pstmt.setInt(1, credit);
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
}


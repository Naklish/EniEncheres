package fr.eni.eniencheres.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() { return new CategorieDAOJdbcImpl(); };
	
	public static CookieIDDAO getCookieIDDAO() {
		return new CookieIDDAOJdbcImpl();
	}
}

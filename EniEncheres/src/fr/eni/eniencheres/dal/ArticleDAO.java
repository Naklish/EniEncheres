package fr.eni.eniencheres.dal;

import java.util.List;

import fr.eni.eniencheres.bo.Article;

public interface ArticleDAO {

	
	public List<Article> selectAll();
}

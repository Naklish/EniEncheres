package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Retrait;

public interface RetraitDAO {
	
	public void insert(Retrait retrait);

	public Retrait selectById(int noArticle);

	public void update(Retrait retrait);

	public void delete(int noArticle);
}

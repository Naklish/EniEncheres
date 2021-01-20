package fr.eni.eniencheres.dal;

import java.util.List;

import fr.eni.eniencheres.bo.Enchere;

public interface EnchereDAO {
	
	public List<Enchere> selectAll();
}

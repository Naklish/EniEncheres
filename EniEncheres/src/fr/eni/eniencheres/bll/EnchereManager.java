package fr.eni.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.EnchereDAO;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	
	
	private void EnchereManger() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public List<Enchere> listerEnchere(){
		List<Enchere> listEncheres = enchereDAO.selectAll();
		
		return listEncheres;
		
	}
}

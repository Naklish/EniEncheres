package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.RetraitDAO;

public class RetraitManager {
	private RetraitDAO retraitDAO;
	
	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void enregistrerRetrait(Retrait retrait) {
		this.retraitDAO.insert(retrait);
	}

	public Retrait recupererById(int noArticle) {
		return this.retraitDAO.selectById(noArticle);
	}

	public void updateRetrait(Retrait retrait) { this.retraitDAO.update(retrait); }

	public void deleteRetrait(int noArticle) { this.retraitDAO.delete(noArticle); }
}

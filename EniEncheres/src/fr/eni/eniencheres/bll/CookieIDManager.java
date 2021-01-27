package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.CookieID;
import fr.eni.eniencheres.dal.CookieIDDAO;
import fr.eni.eniencheres.dal.DAOFactory;

public class CookieIDManager {
	 private CookieIDDAO cookieIdDAO;
	 
	 public CookieIDManager() {
		 this.cookieIdDAO = DAOFactory.getCookieIDDAO();
	 }
	 
	 public void enregistrerCookie(String uuid, int noUtilisateur) {
		 this.cookieIdDAO.insert(uuid, noUtilisateur);
	 }
	 
	 public CookieID recupererCookie(String uuid) {
		return this.cookieIdDAO.select(uuid);
	 }
	 
	 public void effacerCookie(int noUtilisateur) {
		 this.cookieIdDAO.delete(noUtilisateur);
	 }
}

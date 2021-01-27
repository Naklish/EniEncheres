package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.CookieID;

public interface CookieIDDAO {
	
	
	public CookieID select(String uuid);
	public void insert(String uuid, int noUtilisateur);
	public void delete(int noUtilisateur);
}

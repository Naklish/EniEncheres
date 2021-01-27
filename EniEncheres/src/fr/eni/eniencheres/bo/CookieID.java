package fr.eni.eniencheres.bo;

public class CookieID {
	private int noCookie;
	private String uuid;
	private int noUtilisateur;
	
	
	public CookieID() {
		super();
	}


	public CookieID(int noCookie, String uuid, int noUtilisateur) {
		super();
		this.noCookie = noCookie;
		this.uuid = uuid;
		this.noUtilisateur = noUtilisateur;
	}

	
	public CookieID(String uuid, int noUtilisateur) {
		super();
		this.uuid = uuid;
		this.noUtilisateur = noUtilisateur;
	}


	public int getNoCookie() {
		return noCookie;
	}


	public void setNoCookie(int noCookie) {
		this.noCookie = noCookie;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	
}

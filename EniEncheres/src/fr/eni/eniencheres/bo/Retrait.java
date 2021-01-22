package fr.eni.eniencheres.bo;

public class Retrait {
	private int noArticle;
	private String adresse;
	private String codePostal;
	private String ville;
	private Utilisateur vendeur;
	
	public Retrait() {
		super();
	}
	public Retrait(int noArticle, String adresse, String codePostal, String ville) {
		super();
		this.noArticle = noArticle;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() { return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}

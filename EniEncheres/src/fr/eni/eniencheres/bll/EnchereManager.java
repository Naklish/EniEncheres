package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.EnchereDAO;

import java.util.List;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public List<Enchere> listerEnchere(){
		List<Enchere> listEncheres = enchereDAO.selectAll();
		
		return listEncheres;
		
	}
	
	public boolean encherir(Enchere enchere, Utilisateur utilisateur) {

		ArticleManager articleManager = new ArticleManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		//Recherche de la dernière meilleure enchère
		Enchere meilleurEnchere = this.meilleurEnchereByArticle(enchere.getNoArticle());
		
		//Recherche de l'utilisateur de la meilleure enchère
		Utilisateur utilisateurMeilleurEnchere = utilisateurManager.recupererById(meilleurEnchere.getNoUtilisateur());
		
		System.out.println(utilisateurMeilleurEnchere.getPseudo());
		
		//Recherche pour savoir si l'utilisateur a déjà enchéri
		Enchere vielleEnchere = this.enchereDAO.selectByUtilisateurArticle(utilisateur.getNoUtilisateur(), enchere.getNoArticle());
		boolean ok = false;
		
		System.out.println(meilleurEnchere.getNoArticle() + " " + meilleurEnchere.getNoUtilisateur());
		System.out.println(utilisateur.getCredit());
		//Si l'utilisateur a assez de credit
		if(utilisateur.getCredit() - enchere.getMontantEnchere() >= 0) {
			
				
				//On recrédite l'utilisateur de la dernière meilleure enchère 
				utilisateurManager.majCredit(meilleurEnchere.getMontantEnchere() + utilisateurMeilleurEnchere.getCredit(), utilisateurMeilleurEnchere.getNoUtilisateur());
				System.out.println(utilisateurMeilleurEnchere.getNoUtilisateur());
			
			
			//S'il a déjà enchéri
			if(vielleEnchere == null) {
				this.enchereDAO.insert(enchere);
			}else {
				this.enchereDAO.update(enchere, utilisateur);
			}
			
			utilisateurManager.majCredit(utilisateur.getCredit() - enchere.getMontantEnchere(), utilisateur.getNoUtilisateur());
			articleManager.updatePrixVente(enchere);
			ok = true;
		}
		return ok;
	}
	
	
	public Enchere meilleurEnchereByArticle (int noArticle){
		List<Enchere> listEnchere = this.enchereDAO.selectByArticle(noArticle);
		Enchere enchere = new Enchere();
		int meilleurEnchere = 0;
		int index = 0;
		
		if(!listEnchere.isEmpty()) {
			
			//On parcours la liste pour détérminer quelle est la meilleure enchère
			for(int i = 0 ;  i < listEnchere.size() ; i++) {
				if(listEnchere.get(i).getMontantEnchere() > meilleurEnchere) {
					meilleurEnchere = listEnchere.get(i).getMontantEnchere();
					index = i;
				}
			}
			
			// Enregistrement de la meilleur enchère
			enchere.setNoArticle(listEnchere.get(index).getNoArticle());
			enchere.setNoUtilisateur(listEnchere.get(index).getNoUtilisateur());
			enchere.setMontantEnchere(listEnchere.get(index).getMontantEnchere());
			enchere.setDateEnchere(listEnchere.get(index).getDateEnchere());
			
			
	}
		return enchere;
	}

	public void deleteEnchere(int noArticle) { this.enchereDAO.delete(noArticle); }
	
}

	
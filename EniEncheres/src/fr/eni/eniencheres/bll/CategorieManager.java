package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.dal.CategorieDAO;
import fr.eni.eniencheres.dal.DAOFactory;

import java.util.List;

public class CategorieManager {
    private CategorieDAO categorieDAO;

    public CategorieManager() { this.categorieDAO = DAOFactory.getCategorieDAO(); }

    public List<Categorie> listerCategories() {
        List<Categorie> categories = categorieDAO.selectAll();

        return categories;
    }

    public Categorie recupererById(int noCategorie) {
        return this.categorieDAO.selectById(noCategorie);
    }

}

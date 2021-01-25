package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Categorie;

import java.util.List;

public interface CategorieDAO {

    public List<Categorie> selectAll();

    public Categorie selectById(int noCategorie);

}
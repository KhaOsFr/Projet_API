package metier;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe métier regroupant le niveau des responsables et les disciplines
 *
 * @author Simon Robert
 * @version 1.0
 *
 */

public class NiveauResponsableDiscipline {
    /**
     * niveau de compétence du responsable
     */
    protected int niveau;
    /**
     * nom du responsable
     */
    protected String nom;
    /**
     * discipline concernée
     */
    protected Discipline discipline;

    /**
     *
     * @param niveau niveau de la compétence
     * @param nom nom du responsable pour comprendre d'où provient le niveau
     * @param discipline discipline concernée
     */
    public NiveauResponsableDiscipline(int niveau, String nom,Discipline discipline) {
        this.niveau = niveau;
        this.nom = nom;
        this.discipline = discipline;
    }

    /**
     * getter du nom du responsable
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter du nom du responsable
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter du niveau de compétence du responsable
     * @return niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * setter du niveau de compétence du responsable
     * @param niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * getter de la discipline concernée
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * setter de la discipline concernée
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}

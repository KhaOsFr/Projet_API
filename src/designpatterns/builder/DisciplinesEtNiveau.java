package designpatterns.builder;

/**
 * Classe métier pour regrouper les disciplines et les niveaux
 *
 * @author Simon Robert
 * @version 1.2
 *
 */

public class DisciplinesEtNiveau {
    /**
     * objet discipline
     */
    protected Discipline discipline;
    /**
     * niveau de la compétence
     */
    protected int niveau;

    /**
     *
     * @param discipline discipline concernée
     * @param niveau niveau de compétence pour la discipline
     */
    public DisciplinesEtNiveau(Discipline discipline, int niveau) {
        this.discipline = discipline;
        this.niveau = niveau;
    }

    /**
     * getter de la discipline
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * setter de la discipline
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    /**
     * getter du niveau de compétence
     * @return niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * setter du niveau de compétence
     * @param niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}

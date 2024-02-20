package metier;

/**
 * Classe métier de gestion des compétences
 *
 * @author Simon Robert
 * @version 1.0
 *
 */

public class Competence {

    /**
     * identifiant unique de compétence
     */
    protected int id_comp;
    /**
     * niveau de la compétence (1 : peu compétent, 2 : moyennement compétent, 3 : très compétent)
     */
    protected int niveau;
    /**
     * discipline concernée par la compétence
     */
    protected Discipline discipline;


    /**
     *
     * @param id_comp identifiant compétence
     * @param niveau niveau de la compétence
     * @param discipline discipline concernée par la compétence
     */
    public Competence(int id_comp, int niveau, Discipline discipline) {
        this.id_comp = id_comp;
        this.niveau = niveau;
        this.discipline = discipline;
    }

    /**
     *
     * @param niveau niveau de la compétence
     * @param discipline discipline concernée par la compétence
     */
    public Competence(int niveau, Discipline discipline) {
        this.niveau = niveau;
        this.discipline = discipline;
    }

    /**
     * getter de l'identifiant de la compétence
     * @return id_comp
     */
    public int getId_comp() {
        return id_comp;
    }

    /**
     * setter de l'identifiant de la compétence
     * @param id_comp
     */
    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    /**
     * getter du niveau de la compétence
     * @return niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * setter du niveau de la compétence
     * @param niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * getter de la discipline concernée par la compétence
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * setter de la discipline concernée par la compétence
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}

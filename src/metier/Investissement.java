package metier;

/**
 * Classe métier de gestion des investissements
 *
 * @author Simon Robert
 * @version 1.0
 *
 */

public class Investissement {

    /**
     * identifiant unique de l'investissement
     */
    protected int id_invest;
    /**
     * quantité de temps en journée / homme
     */
    protected int quantiteJH;
    /**
     * discipline concernée par l'investissement
     */
    protected Discipline discipline;

    /**
     *
     * @param id_invest identifiant de l'investissment
     * @param quantiteJH quantité (journée / homme)
     * @param discipline discipline concernée par l'investissement
     */
    public Investissement(int id_invest, int quantiteJH, Discipline discipline) {
        this.id_invest = id_invest;
        this.quantiteJH = quantiteJH;
        this.discipline = discipline;
    }

    /**
     *
     * @param quantiteJH quantité (journée / homme)
     * @param discipline discipline concernée par l'investissement
     */
    public Investissement(int quantiteJH, Discipline discipline) {
        this.quantiteJH = quantiteJH;
        this.discipline = discipline;
    }

    /**
     * getter de l'identifiant de l'investissement
     * @return identifiant
     */
    public int getId_invest() {
        return id_invest;
    }

    /**
     * setter de l'identifiant de l'investissement
     * @param id_invest
     */
    public void setId_invest(int id_invest) {
        this.id_invest = id_invest;
    }

    /**
     * getter de la quantité en temps de l'investissement (journée / homme)
     * @return
     */
    public int getQuantiteJH() {
        return quantiteJH;
    }

    /**
     * setter de la quantité en temps de l'investissement (journée / homme)
     * @param quantiteJH
     */
    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }

    /**
     * getter de la discipline concernée par l'investissement
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * setter de la discipline concernée par l'investissement
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}

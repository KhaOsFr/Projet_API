package metier;

/**
 * Classe métier de gestion des disciplines
 *
 * @author Simon Robert
 * @version 1.0
 *
 */

public class Discipline {

    /**
     * identifiant unique de discipline
     */
    protected int id_discipline;
    /**
     * nom de la discipline
     */
    protected String nom;
    /**
     * description de la discipline
     */
    protected String description;

    /**
     *
     * @param id_discipline identifiant de la discipline
     * @param nom nom de la discipline
     * @param description description de la discipline
     */
    public Discipline(int id_discipline, String nom, String description) {
        this.id_discipline = id_discipline;
        this.nom = nom;
        this.description = description;
    }

    /**
     *
     * @param nom nom de la discipline
     * @param description description de la discipline
     */
    public Discipline(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * getter de l'identifiant de la discipline
     * @return id_discipline
     */
    public int getId_discipline() {
        return id_discipline;
    }

    /**
     * setter de l'identifiant de la discipline
     * @param id_discipline
     */
    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    /**
     * getter du nom de la discipline
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter du nom de la discipline
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter de la description de la discipline
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter de la description de la discipline
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

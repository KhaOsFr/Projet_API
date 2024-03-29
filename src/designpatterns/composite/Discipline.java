package designpatterns.composite;

import java.util.Objects;

/**
 * Classe métier de gestion des disciplines
 *
 * @author Simon Robert
 * @version 1.2
 *
 */

public class Discipline {

    /**
     * Variable statique qui permet d'incrémenter un identifiant unique à chaque nouvelle discipline
     */
    protected static int id = 0;
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
     * @param nom nom de la discipline
     * @param description description de la discipline
     */
    public Discipline(String nom, String description) {
        id_discipline = id++;
        this.nom = nom;
        this.description = description;
    }

    /**
     * Constructeur par défaut si on ne rentre pas de paramètre à la création de l'objet
     */
    public Discipline() {
        id_discipline = id++;
    }

    /**
     * Redéfinition de la méthode equals pour pouvoir comparer des disciplines directement par leur identifiant
     * @param o
     * @return true si les identifiants sont les mêmes, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return id_discipline == that.id_discipline;
    }

    /**
     * Redéfinition de la méthode hashCode afin de permettre au equals de ne comparer que les identifiants des disciplines
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_discipline);
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

package metier;

public class Discipline {
    protected int id_discipline;
    protected String nom;
    protected String description;

    public Discipline(int id_discipline, String nom, String description) {
        this.id_discipline = id_discipline;
        this.nom = nom;
        this.description = description;
    }

    public int getId_discipline() {
        return id_discipline;
    }

    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

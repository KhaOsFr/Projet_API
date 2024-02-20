package metier;

public class Competence {
    protected int id_comp;
    protected int niveau;
    protected Discipline discipline;

    public Competence(int id_comp, int niveau, Discipline discipline) {
        this.id_comp = id_comp;
        this.niveau = niveau;
        this.discipline = discipline;
    }

    public Competence(int niveau, Discipline discipline) {
        this.niveau = niveau;
        this.discipline = discipline;
    }

    public int getId_comp() {
        return id_comp;
    }

    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}

package metier;

public class DisciplinesEtNiveau {
    protected Discipline discipline;
    protected int niveau;

    public DisciplinesEtNiveau(Discipline discipline, int niveau) {
        this.discipline = discipline;
        this.niveau = niveau;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}

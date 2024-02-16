package metier;

public class Investissement {
    protected int id_invest;
    protected int quantiteJH;
    protected Discipline discipline;

    public Investissement(int id_invest, int quantiteJH, Discipline discipline) {
        this.id_invest = id_invest;
        this.quantiteJH = quantiteJH;
        this.discipline = discipline;
    }

    public int getId_invest() {
        return id_invest;
    }

    public void setId_invest(int id_invest) {
        this.id_invest = id_invest;
    }

    public int getQuantiteJH() {
        return quantiteJH;
    }

    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}

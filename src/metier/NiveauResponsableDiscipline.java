package metier;

import java.util.ArrayList;
import java.util.List;

public class NiveauResponsableDiscipline {
    protected int niveau;
    protected List<Discipline> liste_discipline = new ArrayList<>();

    public NiveauResponsableDiscipline(int niveau, List<Discipline> liste_discipline) {
        this.niveau = niveau;
        this.liste_discipline = liste_discipline;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public List<Discipline> getListe_discipline() {
        return liste_discipline;
    }

    public void setListe_discipline(List<Discipline> liste_discipline) {
        this.liste_discipline = liste_discipline;
    }
}

package mvc_2.model;

import metier.Discipline;
import metier.Investissement;
import metier.NiveauResponsableDiscipline;

import java.util.List;

public interface DAOSpecialProjet {

    public List<Investissement> listeDisciplinesEtInvestissement();

    public void addDiscipline(Discipline d, int qte);

    public void modifDiscipline(Discipline d, int qte);

    public void suppDiscipline(Discipline d);

    public List<NiveauResponsableDiscipline> niveauxResponsableDisciplines();

    public int investissementTotal();
}

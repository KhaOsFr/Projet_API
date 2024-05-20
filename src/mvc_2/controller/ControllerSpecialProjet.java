package mvc_2.controller;

import metier.Discipline;
import metier.Investissement;
import metier.NiveauResponsableDiscipline;

import java.util.List;

public interface ControllerSpecialProjet {

    public List<Investissement> listeDisciplinesEtInvestissement();

    public void addDiscipline(Discipline d, int qte);

    public void modifDiscipline(Discipline d, int qte);

    public void suppDiscipline(Discipline d);

    public List<NiveauResponsableDiscipline> niveauxResponsableDisciplines();

    public int investissementTotal();
}

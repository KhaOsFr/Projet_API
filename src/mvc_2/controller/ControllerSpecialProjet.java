package mvc_2.controller;

import metier.*;

import java.util.List;

public interface ControllerSpecialProjet {

    public List<Investissement> listeDisciplinesEtInvestissement(Projet p);

    public boolean addDiscipline(Discipline d, int qte, Projet p);

    public boolean modifDiscipline(Discipline d, int qte, Projet p);

    public boolean suppDiscipline(Discipline d, Projet p);

    public List<Competence> niveauxResponsableDisciplines(Projet p);

    public int investissementTotal(Projet p);
}

package mvc_2.controller;

import metier.Discipline;
import metier.DisciplinesEtNiveau;
import metier.Projet;

import java.util.List;

public interface ControllerSpecialEmploye {

    public List<DisciplinesEtNiveau> listeDisciplinesEtNiveau();

    public void addDiscipline(Discipline d, int niveau);

    public void modifDiscipline(Discipline d, int niveau);

    public void suppDiscipline(Discipline d);

    public List<Projet> listeProjets();
}

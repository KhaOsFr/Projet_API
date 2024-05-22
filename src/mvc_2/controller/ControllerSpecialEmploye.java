package mvc_2.controller;

import metier.*;

import java.util.List;

public interface ControllerSpecialEmploye {

    public List<Competence> listeDisciplinesEtNiveau(Employe emp);

    public boolean addDiscipline(Discipline d, int niveau, Employe emp);

    public boolean modifDiscipline(Discipline d, int niveau, Employe emp);

    public boolean suppDiscipline(Discipline d, Employe emp);

    public List<Projet> listeProjets(Employe emp);
}

package mvc_2.model;

import metier.*;

import java.util.List;

public interface DAOSpecialEmploye {

    public List<Competence> listeDisciplinesEtNiveau(Employe emp);

    public boolean addDiscipline(Discipline d, int niveau, Employe emp);

    public boolean modifDiscipline(Discipline d, int niveau, Employe emp);

    public boolean suppDiscipline(Discipline d, Employe emp);

    public List<Projet> listeProjets(Employe emp);
}

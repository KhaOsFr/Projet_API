package mvc_2.controller;

import metier.*;
import mvc_2.model.DAO;
import mvc_2.model.DAOSpecialEmploye;
import mvc_2.view.AbstractView;

import java.util.List;

public class EmployeController extends Controller<Employe> implements ControllerSpecialEmploye {

    public EmployeController(DAO<Employe> model, AbstractView<Employe> view) {
        super(model, view);
    }

    @Override
    public List<Competence> listeDisciplinesEtNiveau(Employe emp) {
        return ((DAOSpecialEmploye) model).listeDisciplinesEtNiveau(emp);
    }

    @Override
    public boolean addDiscipline(Discipline d, int niveau, Employe emp) {
        return ((DAOSpecialEmploye) model).addDiscipline(d, niveau, emp);
    }

    @Override
    public boolean modifDiscipline(Discipline d, int niveau, Employe emp) {
        return ((DAOSpecialEmploye) model).modifDiscipline(d, niveau, emp);
    }

    @Override
    public boolean suppDiscipline(Discipline d, Employe emp) {
        return ((DAOSpecialEmploye) model).suppDiscipline(d, emp);
    }

    @Override
    public List<Projet> listeProjets(Employe emp) {
        return ((DAOSpecialEmploye) model).listeProjets(emp);
    }
}

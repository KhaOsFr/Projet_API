package mvc_2.controller;

import metier.Discipline;
import metier.DisciplinesEtNiveau;
import metier.Employe;
import metier.Projet;
import mvc_2.model.DAO;
import mvc_2.model.DAOSpecialEmploye;
import mvc_2.view.AbstractView;

import java.util.List;

public class EmployeController extends Controller<Employe> implements ControllerSpecialEmploye {

    public EmployeController(DAO<Employe> model, AbstractView<Employe> view) {
        super(model, view);
    }

    @Override
    public List<DisciplinesEtNiveau> listeDisciplinesEtNiveau() {
        return ((DAOSpecialEmploye) model).listeDisciplinesEtNiveau();
    }

    @Override
    public void addDiscipline(Discipline d, int niveau) {
        ((DAOSpecialEmploye) model).addDiscipline(d, niveau);
    }

    @Override
    public void modifDiscipline(Discipline d, int niveau) {
        ((DAOSpecialEmploye) model).modifDiscipline(d, niveau);
    }

    @Override
    public void suppDiscipline(Discipline d) {
        ((DAOSpecialEmploye) model).suppDiscipline(d);
    }

    @Override
    public List<Projet> listeProjets() {
        return ((DAOSpecialEmploye) model).listeProjets();
    }
}

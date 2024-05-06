package mvc.controller;

import metier.Employe;
import mvc.model.DAOEmploye;
import mvc.view.EmployeAbstractView;

import java.util.List;

public class EmployeController {
    private DAOEmploye model;
    private EmployeAbstractView view;

    public EmployeController(DAOEmploye model, EmployeAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Employe> getAll() {
        return model.getEmployes();
    }

    public Employe addEmploye(Employe employe) {
        return model.addEmploye(employe);
    }

    public boolean removeEmploye(Employe emp) {
        return model.removeEmploye(emp);
    }

    public Employe update(Employe employe) {
        return model.updateEmploye(employe);

    }

    public Employe search(int idEmpl) {
        return model.readEmploye(idEmpl);
    }

}
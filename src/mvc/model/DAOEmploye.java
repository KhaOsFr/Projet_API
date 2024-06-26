package mvc.model;

import metier.Employe;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOEmploye extends Subject {

    public abstract Employe addEmploye(Employe employe);

    public abstract boolean removeEmploye(Employe employe);

    public abstract Employe updateEmploye(Employe employe);

    public abstract Employe readEmploye(int idEmploye);

    public abstract List<Employe> getEmployes();
}

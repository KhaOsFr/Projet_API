package mvc_2.controller;

import metier.Discipline;
import mvc_2.model.DAO;
import mvc_2.view.AbstractView;

public class DisciplineController extends Controller<Discipline> {

    public DisciplineController(DAO<Discipline> model, AbstractView<Discipline> view) {
        super(model, view);
    }
}

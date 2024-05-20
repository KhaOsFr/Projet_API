package mvc_2.controller;

import mvc_2.model.DAO;
import mvc_2.view.AbstractView;

import java.util.List;

public class Controller<T> {
    protected DAO<T> model;
    protected AbstractView<T> view;

    public Controller(DAO<T> model, AbstractView<T> view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<T> getAll() {
        List<T> l = model.getAll();
        return l;
    }

    public T add(T elt) {
        T nelt = model.add(elt);
        return nelt;
    }

    public boolean remove(T elt) {
        return model.remove(elt);
    }

    public T update(T elt) {
        return model.update(elt);
    }

    public T search(int rech) {
        return model.read(rech);
    }

}

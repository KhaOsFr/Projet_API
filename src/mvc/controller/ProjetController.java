package mvc.controller;

import metier.Projet;
import mvc.model.DAOProjet;
import mvc.view.ProjetAbstractView;

import java.util.List;

public class ProjetController {
    private DAOProjet model;
    private ProjetAbstractView view;


    public ProjetController(DAOProjet model, ProjetAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Projet> getAll(){
        return model.getProjets();
    }

    public boolean removeProjet(Projet p) {
        return  model.removeProjet(p);
    }

    public Projet update(Projet p) {
        return model.updateProjet(p);
    }

    public Projet search(int idProjet) {
        return  model.readProjet(idProjet);
    }

    public Projet addProjet(Projet p) {
        return model.addProjet(p);
    }
}


package mvc_2.controller;

import metier.Discipline;
import metier.Investissement;
import metier.NiveauResponsableDiscipline;
import metier.Projet;
import mvc_2.model.DAO;
import mvc_2.model.DAOSpecialProjet;
import mvc_2.view.AbstractView;

import java.util.List;

public class ProjetController extends Controller<Projet> implements ControllerSpecialProjet {

    public ProjetController(DAO<Projet> model, AbstractView<Projet> view) {
        super(model, view);
    }

    @Override
    public List<Investissement> listeDisciplinesEtInvestissement(Projet p) {
        return ((DAOSpecialProjet) model).listeDisciplinesEtInvestissement(p);
    }

    @Override
    public boolean addDiscipline(Discipline d, int qte, Projet p) {
        return ((DAOSpecialProjet) model).addDiscipline(d, qte, p);
    }

    @Override
    public boolean modifDiscipline(Discipline d, int qte, Projet p) {
        return ((DAOSpecialProjet) model).modifDiscipline(d, qte, p);
    }

    @Override
    public boolean suppDiscipline(Discipline d, Projet p) {
        return ((DAOSpecialProjet) model).suppDiscipline(d, p);
    }

    @Override
    public List<NiveauResponsableDiscipline> niveauxResponsableDisciplines(Projet p) {
        return ((DAOSpecialProjet) model).niveauxResponsableDisciplines(p);
    }

    @Override
    public int investissementTotal() {
        return ((DAOSpecialProjet) model).investissementTotal();
    }
}

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
    public List<Investissement> listeDisciplinesEtInvestissement() {
        return ((DAOSpecialProjet) model).listeDisciplinesEtInvestissement();
    }

    @Override
    public void addDiscipline(Discipline d, int qte) {
        ((DAOSpecialProjet) model).addDiscipline(d, qte);
    }

    @Override
    public void modifDiscipline(Discipline d, int qte) {
        ((DAOSpecialProjet) model).modifDiscipline(d, qte);
    }

    @Override
    public void suppDiscipline(Discipline d) {
        ((DAOSpecialProjet) model).suppDiscipline(d);
    }

    @Override
    public List<NiveauResponsableDiscipline> niveauxResponsableDisciplines() {
        return ((DAOSpecialProjet) model).niveauxResponsableDisciplines();
    }

    @Override
    public int investissementTotal() {
        return ((DAOSpecialProjet) model).investissementTotal();
    }
}

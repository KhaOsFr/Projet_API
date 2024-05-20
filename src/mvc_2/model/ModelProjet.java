package mvc_2.model;

import metier.Discipline;
import metier.Investissement;
import metier.NiveauResponsableDiscipline;
import metier.Projet;
import myconnections.DBConnection;

import java.sql.Connection;
import java.util.List;

public class ModelProjet extends DAO<Projet> implements DAOSpecialProjet {

    protected Connection dbConnect;

    public ModelProjet() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Projet add(Projet elt) {
        return null;
    }

    @Override
    public boolean remove(Projet elt) {
        return false;
    }

    @Override
    public Projet update(Projet elt) {
        return null;
    }

    @Override
    public Projet read(int rech) {
        return null;
    }

    @Override
    public List<Projet> getAll() {
        return List.of();
    }

    @Override
    public List<Investissement> listeDisciplinesEtInvestissement() {
        return List.of();
    }

    @Override
    public void addDiscipline(Discipline d, int qte) {

    }

    @Override
    public void modifDiscipline(Discipline d, int qte) {

    }

    @Override
    public void suppDiscipline(Discipline d) {

    }

    @Override
    public List<NiveauResponsableDiscipline> niveauxResponsableDisciplines() {
        return List.of();
    }

    @Override
    public int investissementTotal() {
        return 0;
    }
}

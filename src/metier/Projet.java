package metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projet {
    protected int id_projet;
    protected String nom;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected BigDecimal cout;
    protected Employe responsable;
    protected List<Investissement> listInvestissement = new ArrayList<>();

    public Projet(int id_projet, String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe responsable) {
        this.id_projet = id_projet;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.responsable = responsable;
    }

    public List<Investissement> listeDisciplinesEtInvestissement() {
        return listInvestissement; //On a accès à la quantité et la discipline depuis les investissements
    }

    public void addDiscipline(Discipline d, int qte) {
        listInvestissement.add(new Investissement(qte, d));
    }

    public void modifDiscipline(Discipline d, int qte) {
        for (Investissement i : listInvestissement) {
            if (i.getDiscipline() == d) i.setQuantiteJH(qte);
        }
    }

    public void suppDiscipline(Discipline d) {
        for (Investissement i : listInvestissement) {
            if (i.getDiscipline() == d) listInvestissement.remove(i);
        }
    }

    /*TODO public NiveauResponsableDiscipline niveauxResponsableDisciplines() {
        return;
    }*/

    public int getId_projet() {
        return id_projet;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public Employe getResponsable() {
        return responsable;
    }

    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    public List<Investissement> getListInvestissement() {
        return listInvestissement;
    }

    public void setListInvestissement(List<Investissement> listInvestissement) {
        this.listInvestissement = listInvestissement;
    }
}

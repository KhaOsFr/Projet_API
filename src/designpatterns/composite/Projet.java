package designpatterns.composite;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe métier de gestion des projets
 *
 * @author Simon Robert
 * @version 1.2
 */

public class Projet extends Element {

    /**
     * date de début du projet
     */
    protected LocalDate dateDebut;
    /**
     * date de fin du projet
     */
    protected LocalDate dateFin;
    /**
     * cout du projet
     */
    protected BigDecimal cout;
    /**
     * employé responsable du projet
     */
    protected Employe responsable;
    /**
     * liste des investissements du projet
     */
    protected List<Investissement> listInvestissement = new ArrayList<>();

    /**
     * @param nom         nom du projet
     * @param dateDebut   date de début du projet
     * @param dateFin     date de fin du projet
     * @param cout        coût du projet
     * @param responsable employé responsable du projet
     */
    public Projet(String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe responsable) {
        super(nom);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.responsable = responsable;
    }

    /**
     * renvoie la liste des disciplines faisant l'objet d'investissement
     *
     * @return liste des investissement
     */
    public List<Investissement> listeDisciplinesEtInvestissement() {
        return listInvestissement; //On a accès à la quantité et la discipline depuis les investissements
    }

    /**
     * méthode d'ajout d'une discipline
     *
     * @param d   discipline à ajouter
     * @param qte quantité de temps de l'investissement
     */
    public void addDiscipline(Discipline d, int qte) {
        listInvestissement.add(new Investissement(qte, d));
    }

    /**
     * méthode de modification d'une discipline
     *
     * @param d   discipline à modifier
     * @param qte nouvelle valeur de la quantité de temps de l'investissement
     */
    public void modifDiscipline(Discipline d, int qte) {
        boolean trouve = false;
        for (Investissement i : listInvestissement) {
            if (i.getDiscipline().equals(d)) {
                i.setQuantiteJH(qte);
                trouve = true;
            }
        }
        System.out.println(trouve ? "Discipline modifiée" : "Discipline non connue, modification impossible");
    }

    /**
     * méthode de suppression d'une discipline
     *
     * @param d discipline à supprimer
     */
    public void suppDiscipline(Discipline d) {
        boolean trouve = false;
        for (Investissement i : listInvestissement) {
            if (i.getDiscipline().equals(d)) {
                listInvestissement.remove(i);
                trouve = true;
            }
        }
        System.out.println(trouve ? "Discipline modifiée" : "Discipline non connue, suppression impossible");
    }

    /**
     * renvoie pour chaque discipline faisant l'objet d'un investissement, le niveau de compétence du responsable
     *
     * @return liste des discipline et niveau
     */
    public List<NiveauResponsableDiscipline> niveauxResponsableDisciplines() {
        List<NiveauResponsableDiscipline> liste_respDisc = new ArrayList<>();
        for (Competence c : responsable.list_competences) {
            for (Investissement i : listInvestissement) {
                if (c.getDiscipline().equals(i.getDiscipline())) {
                    liste_respDisc.add(new NiveauResponsableDiscipline(c.getNiveau(), responsable.getNom(), c.getDiscipline()));
                }
            }
        } //La double boucle permet de vérifier que la discipline fait bien l'objet d'un investisssement
        return liste_respDisc;
    }

    /**
     * méthode qui retourne la valeur totale du temps des investissements
     *
     * @return total du temps (journée / homme)
     */
    public int investissementTotal() {
        int total = 0;
        for (Investissement i : listInvestissement) {
            total += i.getQuantiteJH();
        }
        return total;
    }

    @Override
    public BigDecimal coutTotal() {
        return null;
    }

    /**
     * getter de la date de début du projet
     *
     * @return date de début
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * setter de la date de début du projet
     *
     * @param dateDebut
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter de la date de fin du projet
     *
     * @return date de fin
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * setter de la date de fin du projet
     *
     * @param dateFin
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * getter du cout du projet
     *
     * @return cout du projet
     */
    public BigDecimal getCout() {
        return cout;
    }

    /**
     * setter du cout du projet
     *
     * @param cout
     */
    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    /**
     * getter de l'employé responsable du projet
     *
     * @return responsable
     */
    public Employe getResponsable() {
        return responsable;
    }

    /**
     * setter de l'employé responsable du projet
     *
     * @param responsable
     */
    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    /**
     * getter de la liste des investissements du projet
     *
     * @return liste des investissements
     */
    public List<Investissement> getListInvestissement() {
        return listInvestissement;
    }

    /**
     * setter de la liste des investissements du projet
     *
     * @param listInvestissement
     */
    public void setListInvestissement(List<Investissement> listInvestissement) {
        this.listInvestissement = listInvestissement;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + getId() +
                ", nom=" + getNom() +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cout=" + cout +
                ", responsable=" + responsable +
                ", listInvestissement=" + listInvestissement +
                '}';
    }
}

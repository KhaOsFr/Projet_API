package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employe {
    protected int id_emplye;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected String mail;
    protected List<Projet> list_projets = new ArrayList<>();
    protected List<Competence> list_competences = new ArrayList<>();
    protected List<DisciplinesEtNiveau> list_disc_niveau = new ArrayList<>();

    public Employe(int id_emplye, String matricule, String nom, String prenom, String tel, String mail) {
        this.id_emplye = id_emplye;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
    }

    public List<DisciplinesEtNiveau> listeDisciplinesEtNiveau() {
        for (Competence c : list_competences) {
            list_disc_niveau.add(new DisciplinesEtNiveau(c.getDiscipline(), c.getNiveau()));
        }
        return list_disc_niveau;
    }

    public void addDiscipline(Discipline d, int niveau) {
        list_competences.add(new Competence(niveau, d));
    }

    public void modifDiscipline(Discipline d, int niveau) {
        for (Competence c : list_competences) {
            if (c.getDiscipline() == d) c.setNiveau(niveau);
        }
    }

    public void suppDiscipline(Discipline d) {
        for (Competence c : list_competences) {
            if (c.getDiscipline() == d) list_competences.remove(c);
        }
    }

    public int getId_emplye() {
        return id_emplye;
    }

    public void setId_emplye(int id_emplye) {
        this.id_emplye = id_emplye;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Projet> getList_projets() {
        return list_projets;
    }

    public void setList_projets(List<Projet> list_projets) {
        this.list_projets = list_projets;
    }

    public List<Competence> getList_competences() {
        return list_competences;
    }

    public void setList_competences(List<Competence> list_competences) {
        this.list_competences = list_competences;
    }
}

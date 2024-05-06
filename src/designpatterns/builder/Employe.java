package designpatterns.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe métier de gestion des employés
 *
 * @author Simon Robert
 * @version 1.2
 *
 */

public class Employe {

    /**
     * Variable statique qui permet d'incrémenter un identifiant unique à chaque nouvel employé
     */
    protected static int id = 0;
    /**
     * identifiant unique de l'employé
     */
    protected int id_emplye;
    /**
     * matricule de l'employé
     */
    protected String matricule;
    /**
     * nom de l'employé
     */
    protected String nom;
    /**
     * prénom de l'employé
     */
    protected String prenom;
    /**
     * numéro de téléphone de l'employé
     */
    protected String tel;
    /**
     * adresse mail de l'employé
     */
    protected String mail;
    /**
     * liste des projets enregistrés
     */
    protected List<Projet> list_projets = new ArrayList<>();
    /**
     * liste des compétences enregistrées
     */
    protected List<Competence> list_competences = new ArrayList<>();
    /**
     * liste des disciplines et de leur niveau de compétence
     */
    protected List<DisciplinesEtNiveau> list_disc_niveau = new ArrayList<>();

    /**
     *
     * @param matricule matricule de l'employé
     * @param nom nom de l'employé
     * @param prenom prénom de l'employé
     * @param tel numéro de téléphone de l'employé
     * @param mail adresse mail de l'employé
     */
    public Employe(String matricule, String nom, String prenom, String tel, String mail) {
        id_emplye = id++;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
    }

    /**
     *
     * @param id_emplye identifiant unique de l'employé
     * @param matricule matricule de l'employé
     * @param nom nom de l'employé
     * @param prenom prénom de l'employé
     * @param tel numéro de téléphone de l'employé
     * @param mail adresse mail de l'employé
     */
    public Employe(int id_emplye, String matricule, String nom, String prenom, String tel, String mail) {
        this.id_emplye = id_emplye;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
    }

    /**
     *
     * @param matricule matricule de l'employé
     * @param nom nom de l'employé
     * @param prenom prénom de l'employé
     */
    public Employe(String matricule, String nom, String prenom) {
        id_emplye = id++;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Constructeur par défaut si on ne rentre pas de paramètre à la création de l'objet
     */
    public Employe() {
        id_emplye = id++;
    }

    public Employe(EmployeBuilder eb) {
        this.id_emplye = id++;
        this.matricule = eb.matricule;
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.tel = eb.tel;
        this.mail = eb.mail;
    }

    /**
     * méthode retournant a liste des disciplines et leur niveau de compétence
     * @return list_disc_niveau
     */
    public List<DisciplinesEtNiveau> listeDisciplinesEtNiveau() {
        for (Competence c : list_competences) {
            list_disc_niveau.add(new DisciplinesEtNiveau(c.getDiscipline(), c.getNiveau()));
        }
        return list_disc_niveau;
    }

    /**
     * méthode d'ajout d'une discipline
     * @param d discipline à ajouter
     * @param niveau niveau de compétence
     */
    public void addDiscipline(Discipline d, int niveau) {
        list_competences.add(new Competence(niveau, d));
    }

    /**
     * méthode de modification d'une discipline
     * @param d discipline à modifier
     * @param niveau nouvelle valeur du niveau
     */
    public void modifDiscipline(Discipline d, int niveau) {
        boolean trouve = false;
        for (Competence c : list_competences) {
            if (c.getDiscipline().equals(d)) {
                c.setNiveau(niveau);
                trouve = true; //Permet d'avertir l'utilisateur si la discipline envoyée en paramètre n'existe pas dans la liste
            }
        }
        System.out.println(trouve ? "Discipline modifiée" : "Discipline non connue, modification impossible");
    }

    /**
     * méthode de suppression d'une discipline
     * @param d discipline à supprimer
     */
    public void suppDiscipline(Discipline d) {
        boolean trouve = false;
        for (Competence c : list_competences) {
            if (c.getDiscipline().equals(d)) {
                list_competences.remove(c);
                trouve = true;
            }
        }
        System.out.println(trouve ? "Discipline supprimée" : "Discipline non connue, suppression impossible");
    }

    /**
     * getter de l'identifiant de l'employé
     * @return id_emplye
     */
    public int getId_emplye() {
        return id_emplye;
    }

    /**
     * getter du matricule de l'employé
     * @return matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * getter du nom de l'employé
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter du prénom de l'employé
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * getter du numéro de téléphone de l'employé
     * @return telephone
     */
    public String getTel() {
        return tel;
    }

    /**
     * getter de l'adresse mail de l'employé
     * @return adresse mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * getter de la liste des projets de l'employé
     * @return liste des projets
     */
    public List<Projet> getList_projets() {
        return list_projets;
    }


    /**
     * getter de la liste des compétences de l'employé
     * @return liste des compétences
     */
    public List<Competence> getList_competences() {
        return list_competences;
    }

    @Override
    public String toString() {
        return "Employé " +
                "n°" + id_emplye +
                "| Matricule : " + matricule +
                "| Nom : " + nom +
                "| Prénom : " + prenom +
                "| Téléphone : " + tel +
                "| Mail : " + mail;
    }

    public static class EmployeBuilder {
        protected int id_emplye;
        protected String matricule;
        protected String nom;
        protected String prenom;
        protected String tel;
        protected String mail;

        public EmployeBuilder setId_emplye(int id_emplye) {
            this.id_emplye = id_emplye;
            return this;
        }

        public EmployeBuilder setMatricule(String matricule) {
            this.matricule = matricule;
            return this;
        }

        public EmployeBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public EmployeBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public EmployeBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public EmployeBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Employe Build() throws Exception {
            if(matricule == null || nom == null || prenom == null) throw new Exception("Informations manquantes");
            return new Employe(this);
        }
    }
}

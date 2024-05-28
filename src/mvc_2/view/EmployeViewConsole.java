package mvc_2.view;

import metier.Competence;
import metier.Discipline;
import metier.Employe;
import metier.Projet;
import mvc_2.Gestion;
import mvc_2.controller.ControllerSpecialEmploye;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EmployeViewConsole extends AbstractView<Employe> {

    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(controller.getAll());
        System.out.println();
        List options = Arrays.asList("Ajouter", "Retirer", "Rechercher", "Modifier", "Fin");
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    private void ajouter() {
        System.out.print("\nMatricule de l'employé : ");
        String matricule = sc.nextLine();
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Téléphone : ");
        String telephone = sc.nextLine();
        System.out.print("Mail : ");
        String mail = sc.nextLine();
        Employe emp = controller.add(new Employe(0, matricule, nom, prenom, telephone, mail));
        if (emp != null) affMsg("\ncréation de : " + emp + "");
        else affMsg("\nErreur de création");
    }

    private void retirer() {
        int nl = choixElt(la) - 1;
        Employe e = la.get(nl);
        boolean ok = controller.remove(e);
        if (ok) affMsg("\nEmployé effacé");
        else affMsg("\nEmployé non effacé");
    }

    private void rechercher() {
        System.out.print("\nIdentifiant de l'employé : ");
        int idEmploye = sc.nextInt();
        Employe emp = controller.search(idEmploye);
        if (emp == null) affMsg("\nEmployé inconnu");
        else {
            affMsg(emp.toString());
            special(emp);
        }
    }

    private void modifier() {
        int nl = choixElt(la);

        Employe emp = la.get(nl - 1);
        String matricule = modifyIfNotBlank("\nMatricule de l'employé", emp.getMatricule());
        String nom = modifyIfNotBlank("Nom", emp.getNom());
        String prenom = modifyIfNotBlank("Prénom", emp.getPrenom());
        String tel = modifyIfNotBlank("Téléphone", emp.getTel());
        String mail = modifyIfNotBlank("Mail", emp.getMail());
        Employe empmaj = controller.update(new Employe(emp.getId_emplye(), matricule, nom, prenom, tel, mail));
        if (empmaj == null) affMsg("\nMise à jour infrucueuse");
        else affMsg("\nMise à jour effectuée : " + empmaj + "");
    }

    private void special(Employe emp) {
        do {
            System.out.println();
            int ch = choixListe(Arrays.asList("Ajouter discipline", "Modifier discipline", "Supprimer discipline", "Lister disciplines", "Lister projets", "Menu principal"));

            switch (ch) {
                case 1:
                    ajouterDisc(emp);
                    break;
                case 2:
                    modifierDisc(emp);
                    break;
                case 3:
                    supprimerDisc(emp);
                    break;
                case 4:
                    listerDisc(emp);
                    break;
                case 5:
                    listerProjets(emp);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\nChoix invalide recommencez");
            }
        } while (true);

    }

    private void ajouterDisc(Employe emp) {
        List<Discipline> ld = Gestion.dm.getAll();
        System.out.println("\nAjout d'une discipline :");
        int ch = choixListe(ld);
        System.out.print("Niveau : ");
        int niv = sc.nextInt();
        boolean ok = ((ControllerSpecialEmploye) controller).addDiscipline(ld.get(ch - 1), niv, emp);
        if (ok) affMsg("\nDiscipline ajoutée");
        else affMsg("\nErreur lors de l'ajout de la discipline");
    }

    private void modifierDisc(Employe emp) {
        List<Competence> lc = ((ControllerSpecialEmploye) controller).listeDisciplinesEtNiveau(emp);
        if (lc.isEmpty()) {
            affMsg("\nAucune discipline");
            return;
        }
        System.out.println("\nModification d'une discipline :");
        int ch = choixListe(lc);
        System.out.print("Niveau : ");
        int niv = sc.nextInt();
        boolean ok = ((ControllerSpecialEmploye) controller).modifDiscipline(lc.get(ch - 1).getDiscipline(), niv, emp);
        if (ok) affMsg("\nMise à jour effectuée");
        else affMsg("\nMise à jour infructueuse");
    }

    private void supprimerDisc(Employe emp) {
        List<Competence> lc = ((ControllerSpecialEmploye) controller).listeDisciplinesEtNiveau(emp);
        if (lc.isEmpty()) {
            affMsg("\nAucune discipline");
            return;
        }
        System.out.println("\nSuppression d'une discipline :");
        int ch = choixListe(lc);
        boolean ok = ((ControllerSpecialEmploye) controller).suppDiscipline(lc.get(ch - 1).getDiscipline(), emp);
        if (ok) affMsg("\nDiscipline supprimée");
        else affMsg("\nDiscipline non supprimée");
    }

    private void listerDisc(Employe emp) {
        List<Competence> lc = ((ControllerSpecialEmploye) controller).listeDisciplinesEtNiveau(emp);
        if(lc.isEmpty()) affMsg("\nAucune discipline pour cet employé");
        else {
            affMsg("\nDisciplines de l'employé :");
            affList(lc);
        }
    }

    private void listerProjets(Employe emp) {
        System.out.println("\nProjets de l'employé :");
        List<Projet> lp = ((ControllerSpecialEmploye) controller).listeProjets(emp);
        if(lp.isEmpty()) affMsg("\nAucun projet pour cet employé");
        else affList(lp);
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}

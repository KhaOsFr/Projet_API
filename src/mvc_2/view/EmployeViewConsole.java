package mvc_2.view;

import metier.Competence;
import metier.Discipline;
import metier.Employe;
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
        System.out.print("Matricule de l'employé : ");
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
        if (emp != null) affMsg("création de :" + emp);
        else affMsg("Erreur de création");
    }

    private void retirer() {
        int nl = choixElt(la) - 1;
        Employe e = la.get(nl);
        boolean ok = controller.remove(e);
        if (ok) affMsg("Employé effacé");
        else affMsg("Employé non effacé");
    }

    private void rechercher() {
        System.out.print("Identifiant de l'employé : ");
        int idEmploye = sc.nextInt();
        Employe emp = controller.search(idEmploye);
        if (emp == null) affMsg("Employé inconnu");
        else {
            affMsg(emp.toString());
            special(emp);
        }
    }

    private void modifier() {
        int nl = choixElt(la);

        Employe emp = la.get(nl - 1);
        String matricule = modifyIfNotBlank("Matricule de l'employé : ", emp.getMatricule());
        String nom = modifyIfNotBlank("Nom : ", emp.getNom());
        String prenom = modifyIfNotBlank("Prénom : ", emp.getPrenom());
        String tel = modifyIfNotBlank("Téléphone : ", emp.getTel());
        String mail = modifyIfNotBlank("Mail : ", emp.getMail());
        Employe empmaj = controller.update(new Employe(emp.getId_emplye(), matricule, nom, prenom, tel, mail));
        if (empmaj == null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : " + empmaj);
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
                    //listerProjets(emp);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide recommencez");
            }
        } while (true);

    }

    public void ajouterDisc(Employe emp) {
        List<Discipline> ld = Gestion.dm.getAll();
        System.out.println("Ajout d'une discipline");
        int ch = choixListe(ld);
        System.out.print("Niveau : ");
        int niv = sc.nextInt();
        boolean ok = ((ControllerSpecialEmploye) controller).addDiscipline(ld.get(ch - 1), niv, emp);
        if (ok) affMsg("Discipline ajoutée");
        else affMsg("Erreur lors de l'ajout de la discipline");
    }

    public void modifierDisc(Employe emp) {
        List<Competence> ld = ((ControllerSpecialEmploye) controller).listeDisciplinesEtNiveau(emp);
        System.out.println("Modification d'une discipline");
        int ch = choixListe(ld);
        System.out.print("Niveau : ");
        int niv = sc.nextInt();
        boolean ok = ((ControllerSpecialEmploye) controller).modifDiscipline(ld.get(ch - 1).getDiscipline(), niv, emp);
        if (ok) affMsg("Mise à jour effectuée");
        else affMsg("Mise à jour infructueuse");
    }

    public void supprimerDisc(Employe emp) {
        List<Competence> ld = ((ControllerSpecialEmploye) controller).listeDisciplinesEtNiveau(emp);
        System.out.println("Suppression d'une discipline");
        int ch = choixListe(ld);
        boolean ok = ((ControllerSpecialEmploye) controller).suppDiscipline(ld.get(ch - 1).getDiscipline(), emp);
        if (ok) affMsg("Discipline supprimée");
        else affMsg("Discipline non supprimée");
    }

    public void listerDisc(Employe emp) {
        System.out.println("Disciplines de l'employé :");
        List<Competence> lc = ((ControllerSpecialEmploye) controller).listeDisciplinesEtNiveau(emp);
        if(lc.isEmpty()) affMsg("Aucune discipline pour cet employé");
        else affList(lc);
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}

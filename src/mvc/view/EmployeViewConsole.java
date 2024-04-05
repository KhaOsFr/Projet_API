package mvc.view;

import metier.Employe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EmployeViewConsole extends EmployeAbstractView {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu() {
        update(employeController.getAll());
        do {
            int ch = choixListe(Arrays.asList("Ajout", "Retrait", "Rechercher", "Modifier", "Fin"));

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


    private void modifier() {
        int nl = choixElt(lp);

        Employe emp = lp.get(nl - 1);
        String matricule = modifyIfNotBlank("Matricule de l'employé : ", emp.getMatricule());
        String nom = modifyIfNotBlank("Nom : ", emp.getNom());
        String prenom = modifyIfNotBlank("Prénom : ", emp.getPrenom());
        String tel = modifyIfNotBlank("Téléphone : ", emp.getTel());
        String mail = modifyIfNotBlank("Mail : ", emp.getMail());
        Employe empmaj = employeController.update(new Employe(emp.getId_emplye(), matricule, nom, prenom, tel, mail));
        if (empmaj == null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : " + empmaj);
    }

    private void rechercher() {
        System.out.println("idEmploye : ");
        int idEmploye = sc.nextInt();
        employeController.search(idEmploye);
    }

    private void retirer() {

        int nl = choixElt(lp);
        Employe emp = lp.get(nl - 1);
        boolean ok = employeController.removeEmploye(emp);
        if (ok) affMsg("Employé effacé");
        else affMsg("Employé non effacé");
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
        Employe emp = employeController.addEmploye(new Employe(0, matricule, nom, prenom, telephone, mail));
        if (emp != null) affMsg("création de :" + emp);
        else affMsg("erreur de création");
    }

    @Override
    public Employe selectionner() {
        update(employeController.getAll());
        int nl = choixListe(lp);
        Employe emp = lp.get(nl - 1);
        return emp;
    }
}


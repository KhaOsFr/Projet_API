package mvc_2.view;

import metier.Employe;

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
        System.out.println(emp);
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

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}

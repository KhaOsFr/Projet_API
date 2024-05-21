package mvc_2.view;

import metier.Discipline;
import metier.Employe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class DisciplineViewConsole extends AbstractView<Discipline> {

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
        System.out.print("Nom de la discipline : ");
        String nom = sc.nextLine();
        System.out.print("Description : ");
        String desc = sc.nextLine();
        Discipline disc = controller.add(new Discipline(0, nom, desc));
        if (disc != null) affMsg("création de :" + disc);
        else affMsg("Erreur de création");
    }

    private void retirer() {
        int nl = choixElt(la) - 1;
        Discipline d = la.get(nl);
        boolean ok = controller.remove(d);
        if (ok) affMsg("Discipline effacée");
        else affMsg("Discipline non effacée");
    }

    private void rechercher() {
        System.out.print("Identifiant de la discipline : ");
        int idDisc = sc.nextInt();
        Discipline disc = controller.search(idDisc);
        System.out.println(disc);
    }

    private void modifier() {
        int nl = choixElt(la);
        Discipline disc = la.get(nl - 1);
        String nom = modifyIfNotBlank("Nom : ", disc.getNom());
        String desc = modifyIfNotBlank("Description : ", disc.getDescription());
        Discipline discmaj = controller.update(new Discipline(disc.getId_discipline(), nom, desc));
        if (discmaj == null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : " + discmaj);
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}

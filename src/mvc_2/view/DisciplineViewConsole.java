package mvc_2.view;

import metier.Discipline;

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
        System.out.print("\nNom de la discipline : ");
        String nom = sc.nextLine();
        System.out.print("Description : ");
        String desc = sc.nextLine();
        Discipline disc = controller.add(new Discipline(0, nom, desc));
        if (disc != null) affMsg("\nCréation de :" + disc);
        else affMsg("\nErreur de création");
    }

    private void retirer() {
        int nl = choixElt(la) - 1;
        Discipline d = la.get(nl);
        boolean ok = controller.remove(d);
        if (ok) affMsg("\nDiscipline effacée");
        else affMsg("\nDiscipline non effacée");
    }

    private void rechercher() {
        System.out.print("\nIdentifiant de la discipline : ");
        int idDisc = sc.nextInt();
        Discipline disc = controller.search(idDisc);
        System.out.println(disc);
    }

    private void modifier() {
        int nl = choixElt(la);
        Discipline disc = la.get(nl - 1);
        String nom = modifyIfNotBlank("Nom", disc.getNom());
        String desc = modifyIfNotBlank("Description", disc.getDescription());
        Discipline discmaj = controller.update(new Discipline(disc.getId_discipline(), nom, desc));
        if (discmaj == null) affMsg("\nMise à jour infrucueuse");
        else affMsg("\nMise à jour effectuée : " + discmaj);
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}

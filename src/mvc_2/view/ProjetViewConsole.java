package mvc_2.view;

import metier.Employe;
import metier.Projet;
import mvc_2.Gestion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class ProjetViewConsole extends AbstractView<Projet> {

    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(controller.getAll());
        System.out.println();
        List options = Arrays.asList("Ajouter", "Retirer", "Rechercher","Modifier","Fin");
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

    @Override
    public void affList(List la) {
        affListe(la);
    }


    private void ajouter() {
        Projet p;
        do {
            try {
                System.out.print("Nom du projet : ");
                String nom = sc.nextLine();
                System.out.print("Date de début : ");
                LocalDate dated = lecDate();
                System.out.print("Date de fin : ");
                LocalDate datef = lecDate();
                System.out.print("Coût : ");
                BigDecimal cout = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
                List<Employe> le = Gestion.em.getAll();
                System.out.println("Responsable :");
                int ch = choixListe(le);
                System.out.println(le.get(ch - 1));
                p = new Projet(nom, dated, datef, cout, le.get(ch - 1));
                break;
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        } while (true);
        controller.add(p);
        System.out.println("Projet créé : " + p);
    }

    private void retirer() {
        int nl = choixElt(la) - 1;
        Projet p = la.get(nl);
        boolean ok = controller.remove(p);
        if (ok) affMsg("Projet effacé");
        else affMsg("Projet non effacé");
    }

    private void rechercher() {
        System.out.print("Identifiant du projet : ");
        int idProjet = sc.nextInt();
        Projet p = controller.search(idProjet);
        System.out.println(p);
    }

    private void modifier() {
        int nl = choixElt(la);
        Projet p = la.get(nl - 1);
        String nom = modifyIfNotBlank("Nom : ", p.getNom());
        String date = modifyIfNotBlank("Date de début ",p.getDateDebut()+"");
        LocalDate dated = !date.equals("null")?LocalDate.parse(date):null;
        date = modifyIfNotBlank("Date de début ",p.getDateFin()+"");
        LocalDate datef = !date.equals("null")?LocalDate.parse(date):null;
        BigDecimal cout = new BigDecimal(modifyIfNotBlank("montant",p.getCout().toString())).setScale(2, RoundingMode.HALF_UP);
        Projet projmaj = controller.update(new Projet(p.getId_projet(), nom, dated, datef, cout, p.getResponsable()));
        if (projmaj == null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : " + projmaj);
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }
}

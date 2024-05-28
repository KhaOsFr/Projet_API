package mvc_2.view;

import metier.*;
import mvc_2.Gestion;
import mvc_2.controller.ControllerSpecialProjet;

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

    @Override
    public void affList(List la) {
        affListe(la);
    }


    private void ajouter() {
        Projet p;
        do {
            try {
                System.out.print("\nNom du projet : ");
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
                System.out.println("\nProjet créé : " + p);
                controller.add(p);
                break;
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        } while (true);
    }

    private void retirer() {
        int nl = choixElt(la) - 1;
        Projet p = la.get(nl);
        boolean ok = controller.remove(p);
        if (ok) affMsg("\nProjet effacé");
        else affMsg("\nProjet non effacé");
    }

    private void rechercher() {
        System.out.print("\nIdentifiant du projet : ");
        int idProjet = sc.nextInt();
        Projet p = controller.search(idProjet);
        if (p == null) affMsg("\nProjet non existant");
        else {
            affMsg(p.toString());
            special(p);
        }
    }

    private void modifier() {
        int nl = choixElt(la);
        Projet p = la.get(nl - 1);
        String nom = modifyIfNotBlank("Nom", p.getNom());
        String date = modifyIfNotBlank("Date de début", p.getDateDebut() + "");
        LocalDate dated = !date.equals("null") ? LocalDate.parse(date) : null;
        date = modifyIfNotBlank("Date de début", p.getDateFin() + "");
        LocalDate datef = !date.equals("null") ? LocalDate.parse(date) : null;
        BigDecimal cout = new BigDecimal(modifyIfNotBlank("montant", p.getCout().toString())).setScale(2, RoundingMode.HALF_UP);
        Projet projmaj = controller.update(new Projet(p.getId_projet(), nom, dated, datef, cout, p.getResponsable()));
        if (projmaj == null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : " + projmaj);
    }

    private void special(Projet p) {
        do {
            System.out.println();
            int ch = choixListe(Arrays.asList("Ajouter discipline", "Modifier discipline", "Supprimer discipline", "Lister disciplines", "Investissement total", "Compétences du responsable", "Menu principal"));

            switch (ch) {
                case 1:
                    ajouterDisc(p);
                    break;
                case 2:
                    modifierDisc(p);
                    break;
                case 3:
                    supprimerDisc(p);
                    break;
                case 4:
                    listerDisc(p);
                    break;
                case 5:
                    investTotal(p);
                    break;
                case 6:
                    niveauxRespDisc(p);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("\nChoix invalide recommencez");
            }
        } while (true);
    }

    private void ajouterDisc(Projet p) {
        List<Discipline> ld = Gestion.dm.getAll();
        System.out.println("\nAjout d'une discipline");
        int ch = choixListe(ld);
        System.out.print("\nQuantité JH : ");
        int qte = sc.nextInt();
        boolean ok = ((ControllerSpecialProjet) controller).addDiscipline(ld.get(ch - 1), qte, p);
        if (ok) affMsg("\nDiscipline ajoutée");
        else affMsg("\nErreur lors de l'ajout de la discipline");
    }

    private void modifierDisc(Projet p) {
        List<Investissement> li = ((ControllerSpecialProjet) controller).listeDisciplinesEtInvestissement(p);
        if (li.isEmpty()) {
            affMsg("\nAucune discipline");
            return;
        }
        System.out.println("\nModification d'une discipline");
        int ch = choixListe(li);
        System.out.print("\nQuantité JH : ");
        int qte = sc.nextInt();
        boolean ok = ((ControllerSpecialProjet) controller).modifDiscipline(li.get(ch - 1).getDiscipline(), qte, p);
        if (ok) affMsg("\nMise à jour effectuée");
        else affMsg("\nMise à jour infructueuse");
    }

    private void supprimerDisc(Projet p) {
        List<Investissement> li = ((ControllerSpecialProjet) controller).listeDisciplinesEtInvestissement(p);
        if (li.isEmpty()) {
            affMsg("\nAucune discipline");
            return;
        }
        System.out.println("\nSuppression d'une discipline");
        int ch = choixListe(li);
        boolean ok = ((ControllerSpecialProjet) controller).suppDiscipline(li.get(ch - 1).getDiscipline(), p);
        if (ok) affMsg("\nDiscipline supprimée");
        else affMsg("\nDiscipline non supprimée");
    }

    private void listerDisc(Projet p) {
        System.out.println("\nDisciplines du projet :");
        List<Investissement> li = ((ControllerSpecialProjet) controller).listeDisciplinesEtInvestissement(p);
        if (li.isEmpty()) affMsg("\nAucune discipline pour ce projet");
        else affList(li);
    }

    private void investTotal(Projet p) {
        int total = ((ControllerSpecialProjet) controller).investissementTotal(p);
        if (total == 0) affMsg("\nAucun investissement pour ce projet");
        else
            affMsg("\nVoici le total des investissement pour le projet '" + p.getNom() + "' : " + total + " journées/hommes");
    }

    private void niveauxRespDisc(Projet p) {
        List<Investissement> li = ((ControllerSpecialProjet) controller).listeDisciplinesEtInvestissement(p);
        List<Competence> lc = ((ControllerSpecialProjet) controller).niveauxResponsableDisciplines(p);
        if(lc.isEmpty()) {
            affMsg("\nAucune compétences");
            return;
        }
        System.out.println("\nVoici les compétences du responsable pour les disciplines du projet :");
        for (Investissement i : li) { //Double boucle qui compare les disciplines en commun et les affiche
            for (Competence c : lc) {
                if (i.getDiscipline().getId_discipline() == c.getDiscipline().getId_discipline()) {
                    System.out.println(c);
                }
            }
        }
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }
}

package mvc_2;

import metier.*;
import mvc_2.controller.*;
import mvc_2.model.*;
import mvc_2.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;


public class Gestion {
    public static DAO<Employe> em;
    public static AbstractView<Employe> ev;
    public static Controller<Employe> ec;

    public void gestion () {
        em = new ModelEmploye();
        ev = new EmployeViewConsole();
        ec = new EmployeController(em, ev);
        em.addObserver(ev);

        List<String> loptions = Arrays.asList("Employés", "Projet", "Discipline", "Investissement", "Compétence", "Fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    ev.menu();
                    break;
                case 2:
                    //lv.menu();
                    break;
                case 3:
                    //ov.menu();
                    break;
                case 4:
                    //ev.menu();
                    break;
                case 5:
                    //rv.menu();
                    break;
                case 6:
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        Gestion g = new Gestion();
        g.gestion();
    }
}
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

    public static DAO<Projet> pm;
    public static AbstractView<Projet> pv;
    public static Controller<Projet> pc;

    public void gestion () {
        em = new ModelEmploye();
        ev = new EmployeViewConsole();
        ec = new EmployeController(em, ev);
        em.addObserver(ev);

        pm = new ModelProjet();
        pv = new ProjetViewConsole();
        pc = new ProjetController(pm, pv);
        pm.addObserver(pv);

        List<String> loptions = Arrays.asList("Employés", "Projet", "Discipline", "Investissement", "Compétence", "Fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    ev.menu();
                    break;
                case 2:
                    pv.menu();
                    break;
                case 3:
                    //dv.menu();
                    break;
                case 4:
                    //iv.menu();
                    break;
                case 5:
                    //cv.menu();
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
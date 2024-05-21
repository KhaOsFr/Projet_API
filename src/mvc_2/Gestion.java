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

    public static DAO<Discipline> dm;
    public static AbstractView<Discipline> dv;
    public static Controller<Discipline> dc;

    public void gestion() {
        em = new ModelEmploye();
        ev = new EmployeViewConsole();
        ec = new EmployeController(em, ev);
        em.addObserver(ev);

        pm = new ModelProjet();
        pv = new ProjetViewConsole();
        pc = new ProjetController(pm, pv);
        pm.addObserver(pv);

        dm = new ModelDiscipline();
        dv = new DisciplineViewConsole();
        dc = new DisciplineController(dm, dv);
        dm.addObserver(dv);

        List<String> loptions = Arrays.asList("Employés", "Projet", "Discipline", "Fin");
        do {
            System.out.println();
            int ch = Utilitaire.choixListe(loptions);
            System.out.println();
            switch (ch) {
                case 1:
                    ev.menu();
                    break;
                case 2:
                    pv.menu();
                    break;
                case 3:
                    dv.menu();
                    break;
                case 4:
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        Gestion g = new Gestion();
        g.gestion();
    }
}
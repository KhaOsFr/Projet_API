package mvc;

import mvc.controller.EmployeController;
import mvc.model.*;
import mvc.view.EmployeAbstractView;
import mvc.view.EmployeViewConsole;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionProjet {
    private DAOEmploye em;
    private EmployeController ec;
    private EmployeAbstractView ev;

    public void gestion() {
        em = new ModelEmployeDB();
        ev = new EmployeViewConsole();
        ec = new EmployeController(em, ev);
        em.addObserver(ev);
        List<String> loptions = Arrays.asList("Employés", "Fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    ev.menu();
                    break;
                case 2:
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestionProjet gp = new GestionProjet();
        gp.gestion();
    }
}

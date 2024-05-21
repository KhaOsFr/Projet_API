package utilitaires.comparators;

import metier.Employe;

import java.util.Comparator;

public class EmployeComparator implements Comparator<Employe> {

    @Override
    public int compare(Employe o1, Employe o2) {
        if (o1.getNom().compareTo(o2.getNom()) != 0) return o1.getNom().compareTo(o2.getNom());
        return o1.getPrenom().compareTo(o2.getPrenom());
    }
}

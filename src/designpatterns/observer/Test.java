package designpatterns.observer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Employe e1 = new Employe("AB12", "Dupond", "Jean", "078974564", "j.d@condorcet.be");
        Employe e2 = new Employe("CD34", "Montant", "Yves", "078934665", "y.m@condorcet.be");
        Projet p1 = new Projet("Cours JAVA", LocalDate.of(2024, 6, 30), LocalDate.of(2024, 8, 30), new BigDecimal(900), e1);
        Projet p2 = new Projet("Cours C++", LocalDate.of(2024, 6, 30), LocalDate.of(2024, 8, 30), new BigDecimal(800), e2);
        p1.addObserver(e1);
        p1.addObserver(e2);
        p2.addObserver(e2);

        p1.setDateFin(LocalDate.of(2024, 8, 20));
        p2.setDateFin(LocalDate.of(2024, 8, 15));
    }
}

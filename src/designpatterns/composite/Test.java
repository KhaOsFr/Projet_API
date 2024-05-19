package designpatterns.composite;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Employe e1 = new Employe("TEST", "Test", "Test");
        Projet p1 = new Projet("Test", LocalDate.of(2024, 6, 15), LocalDate.of(2024, 8, 15), new BigDecimal(5000), e1);
        System.out.println(p1);

        Employe e2 = new Employe("TEST2", "Test2", "Test2");
        Projet p2 = new Projet("Test2", LocalDate.of(2024, 6, 30), LocalDate.of(2024, 8, 30), new BigDecimal(4900), e2);
        System.out.println(p2);

        Employe e3 = new Employe("TEST3", "Test3", "Test3");
        Projet p3 = new Projet("Test3", LocalDate.of(2024, 6, 20), LocalDate.of(2024, 8, 20), new BigDecimal(4700), e3);
        System.out.println(p3);

        p1.getElts().add(p2);
        System.out.println(p1);

        p1.getElts().add(p3);
        System.out.println(p1);
    }
}

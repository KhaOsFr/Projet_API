package designpatterns.composite;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Employe e1 = new Employe("TEST", "Test", "Test");
        LocalDate dated = LocalDate.of(2024, 6, 15);
        LocalDate datef = LocalDate.of(2024, 8, 15);
        BigDecimal cout = new BigDecimal(5000);
        Projet p1 = new Projet("Test", dated, datef, cout, e1);
        System.out.println(p1);

        Employe e2 = new Employe("TEST2", "Test2", "Test2");
        LocalDate datede = LocalDate.of(2024, 6, 30);
        LocalDate datefi = LocalDate.of(2024, 8, 30);
        BigDecimal cout2 = new BigDecimal(5000);
        Projet p2 = new Projet("Test2", datede, datefi, cout2, e2);
        System.out.println(p2);
    }
}

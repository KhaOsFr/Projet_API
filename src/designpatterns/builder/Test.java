package designpatterns.builder;

public class Test {
    public static void main(String[] args) {
        try {
            Employe e1 = new Employe.EmployeBuilder()
                    .setMatricule("ABC45")
                    .setNom("Dupont")
                    .setPrenom("Jean")
                    .Build();
            System.out.println(e1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Employe e2 = new Employe.EmployeBuilder()
                    .setMatricule("ABC98")
                    .setNom("Durand")
                    .setPrenom("Bernard")
                    .setMail("dur.ber@condorcet.be")
                    .setTel("066546589")
                    .Build();
            System.out.println(e2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Employe e3 = new Employe.EmployeBuilder()
                    .setMatricule("ABC98")
                    .setPrenom("Alain")
                    .Build();
            System.out.println(e3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

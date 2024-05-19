package designpatterns.composite;

import java.math.BigDecimal;

public abstract class Element {
    private int id;
    private static int idd=0;
    private String nom;

    public Element(String nom) {
        this.id = idd++;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public abstract BigDecimal coutTotal();
}

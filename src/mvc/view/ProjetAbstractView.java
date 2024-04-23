package mvc.view;

import metier.Projet;
import mvc.observer.Observer;

import java.util.List;

public abstract class ProjetAbstractView implements Observer {
    protected List<Projet> lp;

    protected ProjetController projetController;

    protected EmployeAbstractView ev;

    public void setController(ProjetController projetController){
        this.projetController=projetController;
    };

    public void setEmployeView(EmployeAbstractView ev){
        this.ev=ev;
    }

    public abstract void affMsg(String msg);

    public abstract void menu();

    public abstract void affList(List l);
    @Override
    public void update(List lp) {
        this.lp = lp;
        affList(lp);
    }
}

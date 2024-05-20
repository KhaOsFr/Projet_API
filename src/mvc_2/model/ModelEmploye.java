package mvc_2.model;

import metier.Discipline;
import metier.DisciplinesEtNiveau;
import metier.Employe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metier.Projet;
import myconnections.DBConnection;

public class ModelEmploye extends DAO<Employe> implements DAOSpecialEmploye {

    protected Connection dbConnect;

    public ModelEmploye() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Employe add(Employe elt) {
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,telephone,mail) values(?,?,?,?,?)";
        String query2 = "select id_empl from APIPRODUIT where matricule= ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, elt.getMatricule());
            pstm1.setString(2, elt.getNom());
            pstm1.setString(3, elt.getPrenom());
            pstm1.setString(4, elt.getTel());
            pstm1.setString(5, elt.getMail());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, elt.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idempl = rs.getInt(1);
                    elt.setId_emplye(idempl);
                    notifyObservers();
                    return elt;
                } else {
                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean remove(Employe elt) {
        String query = "delete from APIEMPLOYE where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, elt.getId_emplye());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public Employe update(Employe elt) {
        String query = "update APIEMPLOYE set matricule =?,nom=?,prenom=?,telephone=?,mail=? where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, elt.getMatricule());
            pstm.setString(2, elt.getNom());
            pstm.setString(3, elt.getPrenom());
            pstm.setString(4, elt.getTel());
            pstm.setString(5, elt.getMail());
            pstm.setInt(6, elt.getId_emplye());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return read(elt.getId_emplye());
            else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Employe read(int rech) {
        String query = "select * from APIEMPLOYE where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, rech);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(rech, matricule, nom, prenom, tel, mail);
                return emp;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Employe> getAll() {
        List<Employe> le = new ArrayList<>();
        String query = "select * from APIEMPLOYE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idEmploye = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(idEmploye, matricule, nom, prenom, tel, mail);
                le.add(emp);
            }
            return le;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<DisciplinesEtNiveau> listeDisciplinesEtNiveau() {
        return List.of();
    }

    @Override
    public void addDiscipline(Discipline d, int niveau) {

    }

    @Override
    public void modifDiscipline(Discipline d, int niveau) {

    }

    @Override
    public void suppDiscipline(Discipline d) {

    }

    @Override
    public List<Projet> listeProjets() {
        return List.of();
    }
}

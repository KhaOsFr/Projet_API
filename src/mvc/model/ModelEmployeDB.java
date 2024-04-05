package mvc.model;


import metier.Employe;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ModelEmployeDB extends DAOEmploye {

    protected Connection dbConnect;

    public ModelEmployeDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Employe addEmploye(Employe emp) {
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,telephone,mail) values(?,?,?,?,?)";
        String query2 = "select id_empl from APIPRODUIT where matricule= ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, emp.getMatricule());
            pstm1.setString(2, emp.getNom());
            pstm1.setString(3, emp.getPrenom());
            pstm1.setString(4, emp.getTel());
            pstm1.setString(5, emp.getMail());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, emp.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idempl = rs.getInt(1);
                    emp.setId_emplye(idempl);
                    notifyObservers();
                    return emp;
                } else {

                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;

        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeEmploye(Employe emp) {
        String query = "delete from APIEMPLOYE where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, emp.getId_emplye());
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
    public Employe updateEmploye(Employe emp) {
        String query = "update APIEMPLOYE set matricule =?,nom=?,prenom=?,telephone=?,mail=? where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, emp.getMatricule());
            pstm.setString(2, emp.getNom());
            pstm.setString(3, emp.getPrenom());
            pstm.setString(4, emp.getTel());
            pstm.setString(5, emp.getMail());
            pstm.setInt(6, emp.getId_emplye());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readEmploye(emp.getId_emplye());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Employe readEmploye(int idEmp) {
        String query = "select * from APIEMPLOYE where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idEmp);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(idEmp, matricule, nom, prenom, tel, mail);
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
    public List<Employe> getEmployes() {
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
    public List getNotification() {
        return getEmployes();
    }
}

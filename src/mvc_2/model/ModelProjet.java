package mvc_2.model;

import metier.*;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelProjet extends DAO<Projet> implements DAOSpecialProjet {

    protected Connection dbConnect;

    public ModelProjet() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Projet add(Projet elt) {
        String query1 = "insert into APIPROJET(nom, datedebut, datefin, cout, id_empl) values(?, ?, ?, ?, ?)";
        String query2 = "select max(id_projet) from APIPROJET where id_empl = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, elt.getNom());
            pstm1.setDate(2, java.sql.Date.valueOf(elt.getDateDebut()));
            pstm1.setDate(3, java.sql.Date.valueOf(elt.getDateFin()));
            pstm1.setBigDecimal(4, elt.getCout());
            pstm1.setInt(5, elt.getResponsable().getId_emplye());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, elt.getResponsable().getId_emplye());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_projet = rs.getInt(1);
                    elt.setId_projet(id_projet);
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
    public boolean remove(Projet elt) {
        String query = "delete from APIPROJET where id_projet = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, elt.getId_projet());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("Erreur sql :" + e);
            return false;
        }
    }

    @Override
    public Projet update(Projet elt) {
        String query = "update APIPROJET set nom=?,datedebut=?,datefin=?,cout=? where id_projet=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, elt.getNom());
            pstm.setDate(2,elt.getDateDebut()!=null?Date.valueOf(elt.getDateDebut()):null);
            pstm.setDate(3,elt.getDateFin()!=null?Date.valueOf(elt.getDateFin()):null);
            pstm.setBigDecimal(4, elt.getCout());
            pstm.setInt(5,elt.getId_projet());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return read(elt.getId_projet());
            else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Projet read(int rech) {
        String query = "select * from APIPROJET where id_projet = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, rech);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                LocalDate dated = rs.getDate(3).toLocalDate();
                LocalDate datef = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                return new Projet(rech, nom, dated, datef, cout);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Projet> getAll() {
        List<Projet> lp = new ArrayList<>();
        String query1 = "select * from APIPROJET";
        String query2 = "select * from APIEMPLOYE where id_empl = ?";
        try (Statement stm = dbConnect.createStatement();
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

            ResultSet rs = stm.executeQuery(query1);
            while (rs.next()) {
                int idProjet = rs.getInt(1);
                String nom = rs.getString(2);
                LocalDate dated = rs.getDate(3).toLocalDate();
                LocalDate datef = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                int id_empl = rs.getInt(6);

                pstm2.setInt(1, id_empl);
                ResultSet rsEmp = pstm2.executeQuery();
                Employe emp = null;
                if (rsEmp.next()) {
                    int id_employe = rsEmp.getInt(1);
                    String mat = rsEmp.getString(2);
                    String nomp = rsEmp.getString(3);
                    String prenom = rsEmp.getString(4);
                    String tel = rsEmp.getString(5);
                    String mail = rsEmp.getString(6);
                    emp = new Employe(id_employe, mat, nomp, prenom, tel, mail);
                }
                Projet p = new Projet(idProjet, nom, dated, datef, cout, emp);
                lp.add(p);
            }
            return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Investissement> listeDisciplinesEtInvestissement(Projet p) {
        return List.of();
    }

    @Override
    public boolean addDiscipline(Discipline d, int qte, Projet p) {
        return true;
    }

    @Override
    public boolean modifDiscipline(Discipline d, int qte, Projet p) {
        return true;
    }

    @Override
    public boolean suppDiscipline(Discipline d, Projet p) {
        return true;
    }

    @Override
    public List<NiveauResponsableDiscipline> niveauxResponsableDisciplines(Projet p) {
        return List.of();
    }

    @Override
    public int investissementTotal() {
        return 0;
    }
}

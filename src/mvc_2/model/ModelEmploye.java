package mvc_2.model;

import metier.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import myconnections.DBConnection;

public class ModelEmploye extends DAO<Employe> implements DAOSpecialEmploye {

    protected Connection dbConnect;

    public ModelEmploye() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Employe add(Employe elt) {
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,telephone,mail) values(?,?,?,?,?)";
        String query2 = "select id_empl from APIEMPLOYE where matricule= ?";
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
                return new Employe(rech, matricule, nom, prenom, tel, mail);
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
    public boolean addDiscipline(Discipline d, int niveau, Employe emp) {
        String query = "insert into  APICOMPETENCE(niveau,id_disc,id_empl) values(?,?,?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, niveau);
            pstm.setInt(2, d.getId_discipline());
            pstm.setInt(3, emp.getId_emplye());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean modifDiscipline(Discipline d, int niveau, Employe emp) {
        String query = "update  APICOMPETENCE set niveau = ? where id_disc = ? AND id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, niveau);
            pstm.setInt(2, d.getId_discipline());
            pstm.setInt(3, emp.getId_emplye());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean suppDiscipline(Discipline d, Employe emp) {
        String query = "delete from APICOMPETENCE where  id_disc = ? and id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, d.getId_discipline());
            pstm.setInt(2, emp.getId_emplye());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public List<Competence> listeDisciplinesEtNiveau(Employe emp) {
        String query1 = "select * from APICOMPETENCE where id_empl = ?";
        String query2 = "select * from APIDISCIPLINE where id_disc = ?";
        List<Competence> lc = new ArrayList<>();
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setInt(1, emp.getId_emplye());
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {
                int id_comp = rs1.getInt(1);
                int niveau = rs1.getInt(2);
                int id_disc = rs1.getInt(3);
                pstm2.setInt(1, id_disc);
                ResultSet rs2 = pstm2.executeQuery();
                Discipline disc = null;
                if (rs2.next()) {
                    String nom = rs2.getString(2);
                    String desc = rs2.getString(3);
                    disc = new Discipline(id_disc, nom, desc);
                }
                Competence comp = new Competence(id_comp, niveau, disc, emp);
                lc.add(comp);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return lc;
    }

    @Override
    public List<Projet> listeProjets(Employe emp) {
        String query = "select * from  APIPROJET where  id_empl = ?";
        List<Projet> lp = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, emp.getId_emplye());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id_proj = rs.getInt(1);
                String nom = rs.getString(2);
                LocalDate dated = rs.getDate(3).toLocalDate();
                LocalDate datef = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                Projet p = new Projet(id_proj, nom, dated, datef, cout, emp);
                lp.add(p);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return lp;
    }
}

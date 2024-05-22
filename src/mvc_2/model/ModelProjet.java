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
            pstm.setDate(2, elt.getDateDebut() != null ? Date.valueOf(elt.getDateDebut()) : null);
            pstm.setDate(3, elt.getDateFin() != null ? Date.valueOf(elt.getDateFin()) : null);
            pstm.setBigDecimal(4, elt.getCout());
            pstm.setInt(5, elt.getId_projet());
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
        String query1 = "select * from APIPROJET where id_projet = ?";
        String query2 = "select * from APIEMPLOYE where id_empl = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);) {
            pstm1.setInt(1, rech);
            ResultSet rs = pstm1.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                LocalDate dated = rs.getDate(3).toLocalDate();
                LocalDate datef = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                int id_emp = rs.getInt(6);

                pstm2.setInt(1, id_emp);
                ResultSet rs2 = pstm2.executeQuery();
                Employe emp = null;
                if (rs2.next()) {
                    String mat = rs2.getString(2);
                    String nomp = rs2.getString(3);
                    String prenom = rs2.getString(4);
                    String tel = rs2.getString(5);
                    String mail = rs2.getString(6);
                    emp = new Employe(id_emp, mat, nomp, prenom, tel, mail);
                }
                return new Projet(rech, nom, dated, datef, cout, emp);
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
    public boolean addDiscipline(Discipline d, int qte, Projet p) {
        String query = "insert into  APIINVESTISSEMENT(quantitejh,id_disc,id_proj) values(?,?,?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, qte);
            pstm.setInt(2, d.getId_discipline());
            pstm.setInt(3, p.getId_projet());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean modifDiscipline(Discipline d, int qte, Projet p) {
        String query = "update  APIINVESTISSEMENT set quantitejh = ? where id_disc = ? and id_proj = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, qte);
            pstm.setInt(2, d.getId_discipline());
            pstm.setInt(3, p.getId_projet());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean suppDiscipline(Discipline d, Projet p) {
        String query = "delete from APIINVESTISSEMENT where  id_disc = ? and id_proj = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, d.getId_discipline());
            pstm.setInt(2, p.getId_projet());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public int investissementTotal(Projet p) {
        String query = "{ ? = call apiproj_invest_total(?) }"; //Utilisation de la fonction embarquée
        try (CallableStatement cs = dbConnect.prepareCall(query)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setInt(2, p.getId_projet());
            cs.executeQuery();
            return cs.getInt(1);
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return -1;
        }
    }

    @Override
    public List<Investissement> listeDisciplinesEtInvestissement(Projet p) {
        String query1 = "select * from APIINVESTISSEMENT where id_proj = ?";
        String query2 = "select * from APIDISCIPLINE where id_disc = ?";
        List<Investissement> li = new ArrayList<>();
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setInt(1, p.getId_projet());
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {
                int id_invest = rs1.getInt(1);
                int qte = rs1.getInt(2);
                int id_disc = rs1.getInt(3);
                pstm2.setInt(1, id_disc);
                ResultSet rs2 = pstm2.executeQuery();
                Discipline disc = null;
                if (rs2.next()) {
                    String nom = rs2.getString(2);
                    String desc = rs2.getString(3);
                    disc = new Discipline(id_disc, nom, desc);
                }
                Investissement inv = new Investissement(id_invest, qte, disc, p);
                li.add(inv);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return li;
    }

    @Override
    public List<Competence> niveauxResponsableDisciplines(Projet p) {
        String query1 = "select c.id_comp, c.niveau, c.id_disc, c.id_empl from APICOMPETENCE c join APIPROJET p on c.id_empl = p.id_empl where p.id_projet = ? and c.id_empl = ?";
        String query2 = "select * from APIDISCIPLINE where id_disc = ?";
        List<Competence> lc = new ArrayList<>();
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setInt(1, p.getId_projet());
            pstm1.setInt(2, p.getResponsable().getId_emplye());
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
                Competence comp = new Competence(id_comp, niveau, disc, p.getResponsable());
                lc.add(comp);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return lc;
    }
}

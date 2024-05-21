package mvc_2.model;

import metier.Discipline;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelDiscipline extends DAO<Discipline> {

    protected Connection dbConnect;

    public ModelDiscipline() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("Erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Discipline add(Discipline elt) {
        String query1 = "insert into APIDISCIPLINE(nom,description) values(?,?)";
        String query2 = "select id_disc from APIDISCIPLINE where nom = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, elt.getNom());
            pstm1.setString(2, elt.getDescription());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, elt.getNom());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int iddisc = rs.getInt(1);
                    elt.setId_discipline(iddisc);
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
    public boolean remove(Discipline elt) {
        String query = "delete from APIDISCIPLINE where id_disc = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, elt.getId_discipline());
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
    public Discipline update(Discipline elt) {
        String query = "update APIDISCIPLINE set nom=?,description=? where id_disc = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, elt.getNom());
            pstm.setString(2, elt.getDescription());
            pstm.setInt(3, elt.getId_discipline());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return read(elt.getId_discipline());
            else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Discipline read(int rech) {
        String query = "select * from APIDISCIPLINE where id_disc = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, rech);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                String desc = rs.getString(3);
                return new Discipline(rech, nom, desc);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Discipline> getAll() {
        List<Discipline> ld = new ArrayList<>();
        String query = "select * from APIDISCIPLINE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idDisc = rs.getInt(1);
                String nom = rs.getString(2);
                String desc = rs.getString(3);
                Discipline disc = new Discipline(idDisc, nom, desc);
                ld.add(disc);
            }
            return ld;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }
}

package mvc.model;

import metier.Projet;
import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModelProjet extends DAOProjet {

    protected Connection dbConnect;

    public ModelProjet() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Projet addProjet(Projet projet) {
        String query1 = "insert into APIProjet(nom,datedebut,datefin,cout,id_empl) values(?,CURRENT_DATE,null,?,?)";
        String query2 = "select max(id_projet) from APIProjet where id_empl = ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1, projet.getNom());
            pstm1.setBigDecimal(2, projet.getCout());
            pstm1.setInt(3,projet.getResponsable().getId_emplye());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setInt(1, projet.getResponsable().getId_emplye());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idproj= rs.getInt(1);
                    projet.setId_projet(idproj);
                    notifyObservers();
                    return projet;
                }
                else {

                    System.err.println("record introuvable");
                    return null;
                }
            }
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeProjet(Projet projet) {
        return false;
    }

    @Override
    public Projet updateProjet(Projet projet) {
        return null;
    }

    @Override
    public Projet readProjet(int idProj) {
        return null;
    }

    @Override
    public List<Projet> getProjets() {
        return List.of();
    }

    @Override
    public List getNotification() {
        return List.of();
    }
}

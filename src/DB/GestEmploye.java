package DB;

import metier.Employe;
import myconnections.DBConnection;

import java.sql.*;
import java.util.PropertyResourceBundle;

import java.util.Scanner;

public class GestEmploye {
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("Connexion établie");
        do {
            System.out.println("1. Ajout\n2. Recherche\n3. Modification\n4. Suppression\n5. Tous\n6. Fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    //recherche();
                    break;
                case 3:
                    //modification();
                    break;
                case 4:
                    //suppression();
                    break;
                case 5:
                    //tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajout() {

        System.out.print("Matricule de l'employé : ");
        String matricule = sc.nextLine();
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Téléphone :");
        String telephone = sc.nextLine();
        System.out.print("Mail : ");
        String mail = sc.nextLine();
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,telephone,mail) values(?,?,?,?,?)";
        String query2 = "select id_empl from APIEMPLOYE where matricule =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, matricule);
            pstm1.setString(2, nom);
            pstm1.setString(2, prenom);
            pstm1.setString(4, telephone);
            pstm1.setString(5, mail);
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne(s) insérée(s)");
            if (n == 1) {
                pstm2.setString(1, matricule);
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idclient = rs.getInt(1);
                    System.out.println("idclient = " + idclient);
                } else System.out.println("Record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("Erreur sql :" + e);
        }
    }

    public void recherche() {

        System.out.print("Id de l'employé recherché : ");
        int idrech = sc.nextInt();
        String query = "select * from APIEMPLOYE where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idrech);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(idrech, matricule, nom, prenom, tel, mail);
                System.out.println(emp);
            } else System.out.println("Record introuvable");
        } catch (SQLException e) {
            System.out.println("Erreur sql :" + e);
        }

    }

    public void modification() {
        System.out.print("Id de l'employé recherché : ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau téléphone ");
        String ntel = sc.nextLine();
        String query = "update APIEMPLOYE set telephone=? where id_empl = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, ntel);
            pstm.setInt(2, idrech);
            int n = pstm.executeUpdate();
            if (n != 0) System.out.println(n + "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void suppression() {
        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        String query = "delete from APICLIENT where idclient = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idrech);
            int n = pstm.executeUpdate();
            if (n != 0) System.out.println(n + "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }

    }


    public static void main(String[] args) {
        GestEmploye g = new GestEmploye();
        g.gestion();
    }
}

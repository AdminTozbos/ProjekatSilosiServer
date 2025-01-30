/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Miloš
 */
public class Konekcija {
    private static Konekcija konekcija;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Konekcija() {
        String url="jdbc:mysql://localhost:3306/silosi";
        try {
            connection=DriverManager.getConnection(url, "root", "miki");
            System.out.println("Baza je povezana");
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Konekcija getInstance(){
        if(konekcija==null){
            konekcija=new Konekcija();
        }
        return konekcija;
    }
}

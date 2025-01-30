/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RukovodilacKooperacije;

/**
 *
 * @author Miloš
 */
public class DBBroker {
    private static DBBroker dbb;

    private DBBroker() {
        Konekcija k=Konekcija.getInstance();
    }
    public static DBBroker getInstance(){
        if(dbb==null){
            dbb=new DBBroker();
        }
        return dbb;
    }

    public RukovodilacKooperacije login(List<String> parametri) {
        //List<RukovodilacKooperacije>ruk=new ArrayList<>();
       
        String query="SELECT * FROM rukovodilac JOIN sifarnik ON id=idrukovodilac WHERE username=? AND password=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, parametri.get(0));
            ps.setString(2, parametri.get(1));
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                int id=rs.getInt("id");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String brtel=rs.getString("brtel");
                RukovodilacKooperacije r=new RukovodilacKooperacije(id, ime, prezime, brtel);
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

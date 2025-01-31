/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RadnoIskustvo;
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

    public boolean dodajRukovodioca(RukovodilacKooperacije ruk) {
        String query="INSERT INTO rukovodilac (ime,prezime,brtel) VALUES (?,?,?)";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, ruk.getIme());
            ps.setString(2, ruk.getPrezime());
            ps.setString(3, ruk.getBrojTelefona());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<RukovodilacKooperacije> vratiSveRukovodioce() {
        List<RukovodilacKooperacije>ruk=new ArrayList<>();
        String query="SELECT *FROM rukovodilac";
        try {
            
            Statement s=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(query);
            while (rs.next()) {                
                int id=rs.getInt("id");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String brtel=rs.getString("brtel");
                RukovodilacKooperacije r=new RukovodilacKooperacije(id, ime, prezime, brtel);
                ruk.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ruk;
    }

    public boolean obrisiRukovodioca(RukovodilacKooperacije ruko) {
        String query="DELETE FROM rukovodilac WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, ruko.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniRukovodioca(RukovodilacKooperacije ruk2) {
        String query="UPDATE rukovodilac SET ime=?,prezime=?,brtel=? WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, ruk2.getIme());
            ps.setString(2, ruk2.getPrezime());
            ps.setString(3, ruk2.getBrojTelefona());
            ps.setInt(4, ruk2.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    public List<RadnoIskustvo> vratiSvaIskustva() {
        List<RadnoIskustvo>iskustva=new ArrayList<>();
        String query="SELECT *FROM iskustvo";
        try {
            
            Statement s=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(query);
            while (rs.next()) {                
                int id=rs.getInt("id");
                String isk=rs.getString("radnomesto");
               RadnoIskustvo ri=new RadnoIskustvo(id, isk);
               iskustva.add(ri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return iskustva;
    }

    public Object dodajIskustvo(RadnoIskustvo ri) {
        String query="INSERT INTO iskustvo (radnomesto) VALUES (?)";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, ri.getRadnoMesto());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiIskustvo(RadnoIskustvo ri2) {
        String query="DELETE FROM iskustvo WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, ri2.getIdRadnoIskustvo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniIskustvo(RadnoIskustvo ri3) {
       String query="UPDATE iskustvo SET radnomesto=? WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, ri3.getRadnoMesto());
            ps.setInt(2, ri3.getIdRadnoIskustvo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
}

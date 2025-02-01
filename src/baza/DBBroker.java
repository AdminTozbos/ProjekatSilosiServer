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
import model.Mesec;
import model.PoljoprivrednaKultura;
import model.PoljoprivrednoGazdinstvo;
import model.PoljoprivrednoPreduzece;
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

    public List<PoljoprivrednaKultura> vratiSveKulture() {
        List<PoljoprivrednaKultura>kulture=new ArrayList<>();
        String query="SELECT *FROM kultura";
        try {
            
            Statement s=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(query);
            while (rs.next()) {                
                int id=rs.getInt("id");
                String isk=rs.getString("naziv");
                Double cena=rs.getDouble("cena");
                Mesec mesec=Mesec.valueOf(rs.getString("mesec"));
                PoljoprivrednaKultura pk=new PoljoprivrednaKultura(id, isk, cena, mesec);
               
                kulture.add(pk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kulture;
    }

    public boolean dodajKulturu(PoljoprivrednaKultura pk) {
        String query="INSERT INTO kultura (naziv,cena,mesec) VALUES (?,?,?)";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, pk.getNazivKulture());
            ps.setDouble(2, pk.getCena());
            ps.setString(3, String.valueOf(pk.getMesecZetve()));
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiKulturu(PoljoprivrednaKultura pk2) {
        String query="DELETE FROM kultura WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, pk2.getIdKultura());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniKulturu(PoljoprivrednaKultura pk3) {
        String query="UPDATE kultura SET naziv=?,cena=?,mesec=? WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, pk3.getNazivKulture());
            ps.setDouble(2, pk3.getCena());
            ps.setString(3, String.valueOf(pk3.getMesecZetve()));
            ps.setInt(4, pk3.getIdKultura());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<PoljoprivrednoPreduzece> vratiSvaPreduzeca() {
       List<PoljoprivrednoPreduzece>preduzeca=new ArrayList<>();
        String query="SELECT *FROM preduzece";
        try {
            
            Statement s=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(query);
            while (rs.next()) {                
                int id=rs.getInt("id");
                String naziv=rs.getString("naziv");
                String mesto=rs.getString("mesto");
                String zastupnik=rs.getString("zastupnik");
                String pib=rs.getString("pib");
                String email=rs.getString("email");
                PoljoprivrednoPreduzece pp=new PoljoprivrednoPreduzece(zastupnik, pib, email, id, naziv, mesto);
               
                preduzeca.add(pp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preduzeca;
    }

    public boolean dodajPreduzece(PoljoprivrednoPreduzece pp) {
        String query="INSERT INTO preduzece (naziv,mesto,pib,email,zastupnik) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, pp.getNazivKooperanta());
            ps.setString(2, pp.getMesto());
            ps.setString(3, pp.getPib());
            ps.setString(4, pp.getEmail());
            ps.setString(5, pp.getPravniZastupnik());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniPreduzece(PoljoprivrednoPreduzece pp2) {
        String query="UPDATE preduzece SET naziv=?,mesto=?,pib=?,email=?,zastupnik=? WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, pp2.getNazivKooperanta());
            ps.setString(2, pp2.getMesto());
            ps.setString(3, pp2.getPib());
            ps.setString(4, pp2.getEmail());
            ps.setString(5, pp2.getPravniZastupnik());
            ps.setInt(6, pp2.getIdKooperant());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiPreduzece(PoljoprivrednoPreduzece pp3) {
         String query="DELETE FROM preduzece WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, pp3.getIdKooperant());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public List<PoljoprivrednoGazdinstvo> vratiSvaGazdinstva() {
       List<PoljoprivrednoGazdinstvo>gazdinstva=new ArrayList<>();
        String query="SELECT *FROM gazdinstvo";
        try {
            
            Statement s=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(query);
            while (rs.next()) {                
                int id=rs.getInt("id");
                String naziv=rs.getString("naziv");
                String mesto=rs.getString("mesto");
                String vlasnik=rs.getString("vlasnik");
                String brtel=rs.getString("brtel");
                
                PoljoprivrednoGazdinstvo pp=new PoljoprivrednoGazdinstvo(vlasnik, brtel, id, naziv, mesto);
                
                gazdinstva.add(pp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gazdinstva;
    }

    public boolean dodajGazdinstvo(PoljoprivrednoGazdinstvo pp) {
        String query="INSERT INTO gazdinstvo (naziv,mesto,vlasnik,brtel) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, pp.getNazivKooperanta());
            ps.setString(2, pp.getMesto());
            ps.setString(3, pp.getVlasnikGaz());
            ps.setString(4, pp.getBrojTelefona());
           

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniGazdinstvo(PoljoprivrednoGazdinstvo pp2) {
        String query="UPDATE gazdinstvo SET naziv=?,mesto=?,vlasnik=?,brtel=? WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, pp2.getNazivKooperanta());
            ps.setString(2, pp2.getMesto());
            ps.setString(3, pp2.getVlasnikGaz());
            ps.setString(4, pp2.getBrojTelefona());
            ps.setInt(5, pp2.getIdKooperant());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiGazdinstvo(PoljoprivrednoGazdinstvo pp3) {
         String query="DELETE FROM gazdinstvo WHERE id=?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, pp3.getIdKooperant());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

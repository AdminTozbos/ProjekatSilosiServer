/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import baza.GenericDBBroker;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PoljoprivrednaKultura;
import model.PoljoprivrednoGazdinstvo;
import model.PoljoprivrednoPreduzece;
import model.Potvrda;
import model.PrRi;
import model.RadnoIskustvo;
import model.RukovodilacKooperacije;
import model.StavkaPotvrde;

/**
 *
 * @author Miloš
 */
public class Controller {
    private static Controller controller;
    private GenericDBBroker<RadnoIskustvo>dbbISK;
    private GenericDBBroker<PoljoprivrednaKultura>dbbKUL;
    private GenericDBBroker<RukovodilacKooperacije>dbbRUK;
    private GenericDBBroker<PoljoprivrednoPreduzece>dbbPRED;
    private GenericDBBroker<PoljoprivrednoGazdinstvo>dbbGAZ;
    private GenericDBBroker<StavkaPotvrde>dbbSTA;
    private GenericDBBroker<Potvrda>dbbPOT;
    private GenericDBBroker<PrRi>dbbPRI;




    private DBBroker dbb;

    private Controller() {
        dbb=DBBroker.getInstance();
        dbbISK=GenericDBBroker.getInstance();
        dbbKUL=GenericDBBroker.getInstance();
        dbbRUK=GenericDBBroker.getInstance();
        dbbPRED=GenericDBBroker.getInstance();
        dbbGAZ=GenericDBBroker.getInstance();
        dbbSTA=GenericDBBroker.getInstance();
        dbbPOT=GenericDBBroker.getInstance();
        dbbPRI=GenericDBBroker.getInstance();
    }
    public static Controller getInstance(){
        if(controller==null){
            controller=new Controller();
        }
        return controller;
    }

    public RukovodilacKooperacije login(List<String> parametri) {
        return dbb.login(parametri);
    }

    public boolean dodajRukovodioca(RukovodilacKooperacije ruk) {
        try {
            //return dbb.dodajRukovodioca(ruk);
            return dbbRUK.insert(ruk);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<RukovodilacKooperacije> vratiSveRukovodioce() {
        try {
            //return dbb.vratiSveRukovodioce();
            return dbbRUK.getAll(new RukovodilacKooperacije());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean obrisiRukovodioca(RukovodilacKooperacije ruko) {
        try {
            //return dbb.obrisiRukovodioca(ruko);
            return dbbRUK.delete(ruko);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniRukovodioca(RukovodilacKooperacije ruk2) {
        try {
            //return dbb.izmeniRukovodioca(ruk2);
            return dbbRUK.update(ruk2);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<RadnoIskustvo> vratiSvaIskustva() {
        try {
            //return dbb.vratiSvaIskustva();
            return dbbISK.getAll(new RadnoIskustvo());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean dodajIskustvo(RadnoIskustvo ri) {
        try {
            //return dbb.dodajIskustvo(ri);
            return dbbISK.insert(ri);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiIskustvo(RadnoIskustvo ri2) {
        try {
            //return dbb.obrisiIskustvo(ri2);
            return dbbISK.delete(ri2);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniIskustvo(RadnoIskustvo ri3) {
        try {
            //return dbb.izmeniIskustvo(ri3);
            return dbbISK.update(ri3);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<PoljoprivrednaKultura> vratiSveKulture() {
        try {
            //return dbb.vratiSveKulture();
            return dbbKUL.getAll(new PoljoprivrednaKultura());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean dodajKulturu(PoljoprivrednaKultura pk) {
        try {
            //return dbb.dodajKulturu(pk);
            return dbbKUL.insert(pk);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiKulturu(PoljoprivrednaKultura pk2) {
        try {
            //return dbb.obrisiKulturu(pk2);
            return dbbKUL.delete(pk2);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniKulturu(PoljoprivrednaKultura pk3) {
        try {
            //return dbb.izmeniKulturu(pk3);
            return dbbKUL.update(pk3);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<PoljoprivrednoPreduzece> vratiSvaPreduzeca() {
        try {
            //return dbb.vratiSvaPreduzeca();
            return dbbPRED.getAll(new PoljoprivrednoPreduzece());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean dodajPreduzece(PoljoprivrednoPreduzece pp) {
        try {
            //return dbb.dodajPreduzece(pp);
            return dbbPRED.insert(pp);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniPreduzece(PoljoprivrednoPreduzece pp2) {
        try {
            //return dbb.izmeniPreduzece(pp2);
            return dbbPRED.update(pp2);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiPreduzece(PoljoprivrednoPreduzece pp3) {
        try {
            //return dbb.obrisiPreduzece(pp3);
            return dbbPRED.delete(pp3);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public List<PoljoprivrednoGazdinstvo> vratiSvaGazdinstva() {
        try {
            //return dbb.vratiSvaGazdinstva();
            return dbbGAZ.getAll(new PoljoprivrednoGazdinstvo());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean dodajGazdinstvo(PoljoprivrednoGazdinstvo pp) {
        try {
            //return dbb.dodajGazdinstvo(pp);
            return dbbGAZ.insert(pp);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniGazdinstvo(PoljoprivrednoGazdinstvo pp2) {
        try {
            //return dbb.izmeniGazdinstvo(pp2);
            return dbbGAZ.update(pp2);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiGazdinstvo(PoljoprivrednoGazdinstvo pp3) {
        try {
            //return dbb.obrisiGazdinstvo(pp3);
            return dbbGAZ.delete(pp3);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Potvrda> vratiSvePotvrde() {
        try {
            //return dbb.vratiSvePotvrde();
            return dbbPOT.getAll(new Potvrda());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean dodajPotvrdu(Potvrda p) {
        try {
            //return dbb.dodajPotvrdu(p);
            return dbbPOT.insert(p);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean dodajStavku(StavkaPotvrde st) {
        try {
            //return dbb.dodajStavku(st);
            return dbbSTA.insert(st);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    

    public boolean obrisiPotvrdu(Potvrda st2) {
        try {
            //return dbb.obrisiPotvrdu(st2);
            boolean uspehPot=dbbPOT.delete(st2);
            boolean uspehSta=dbbSTA.delete(new StavkaPotvrde(st2.getIdPotvrda(), 0, 0, 0, 0, 0));
            if(uspehPot==true&&uspehSta==true)return true;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<StavkaPotvrde> vratiSveStavke(Potvrda st3) {
        try {
            //return dbb.vratiSveStavke(st3);
            return dbbSTA.getAll(new StavkaPotvrde(st3.getIdPotvrda(), 0, 0, 0, 0, 0));
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean izmeniPotvrdu(Potvrda st4) {
        try {
            //return dbb.izmeniPotvrdu(st4);
            boolean uspehBris=dbbPOT.delete(st4);
            boolean uspehStaBris=dbbSTA.delete(new StavkaPotvrde(st4.getIdPotvrda(), 0, 0, 0, 0, 0));
            boolean uspehDod=dbbPOT.insert(st4);
            if(uspehBris==true&&uspehDod==true)return true;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object vratiSvePrri() {
        try {
           
            return dbbPRI.getAll(new PrRi());
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean dodajPrri(PrRi prri) {
        try {
            return dbbPRI.insert(prri);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean obrisiPrri(PrRi prri2) {
        try {
            return dbbPRI.delete(prri2);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniPrri(PrRi prri3) {
        try {
            return dbbPRI.update(prri3);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

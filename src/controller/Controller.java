/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.List;
import model.PoljoprivrednaKultura;
import model.PoljoprivrednoGazdinstvo;
import model.PoljoprivrednoPreduzece;
import model.RadnoIskustvo;
import model.RukovodilacKooperacije;

/**
 *
 * @author Miloš
 */
public class Controller {
    private static Controller controller;
    private DBBroker dbb;

    private Controller() {
        dbb=DBBroker.getInstance();
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
        return dbb.dodajRukovodioca(ruk);
    }

    public List<RukovodilacKooperacije> vratiSveRukovodioce() {
        return dbb.vratiSveRukovodioce();
    }

    public boolean obrisiRukovodioca(RukovodilacKooperacije ruko) {
        return dbb.obrisiRukovodioca(ruko);
    }

    public boolean izmeniRukovodioca(RukovodilacKooperacije ruk2) {
        return dbb.izmeniRukovodioca(ruk2);
    }

    public List<RadnoIskustvo> vratiSvaIskustva() {
        return dbb.vratiSvaIskustva();
    }

    public Object dodajIskustvo(RadnoIskustvo ri) {
        return dbb.dodajIskustvo(ri);
    }

    public boolean obrisiIskustvo(RadnoIskustvo ri2) {
        return dbb.obrisiIskustvo(ri2);
    }

    public boolean izmeniIskustvo(RadnoIskustvo ri3) {
        return dbb.izmeniIskustvo(ri3);
    }

    public List<PoljoprivrednaKultura> vratiSveKulture() {
        return dbb.vratiSveKulture();
    }

    public boolean dodajKulturu(PoljoprivrednaKultura pk) {
        return dbb.dodajKulturu(pk);
    }

    public boolean obrisiKulturu(PoljoprivrednaKultura pk2) {
        return dbb.obrisiKulturu(pk2);
    }

    public boolean izmeniKulturu(PoljoprivrednaKultura pk3) {
        return dbb.izmeniKulturu(pk3);
    }

    public List<PoljoprivrednoPreduzece> vratiSvaPreduzeca() {
        return dbb.vratiSvaPreduzeca();
    }

    public boolean dodajPreduzece(PoljoprivrednoPreduzece pp) {
        return dbb.dodajPreduzece(pp);
    }

    public boolean izmeniPreduzece(PoljoprivrednoPreduzece pp2) {
        return dbb.izmeniPreduzece(pp2);
    }

    public boolean obrisiPreduzece(PoljoprivrednoPreduzece pp3) {
        return dbb.obrisiPreduzece(pp3);
    }
    public List<PoljoprivrednoGazdinstvo> vratiSvaGazdinstva() {
        return dbb.vratiSvaGazdinstva();
    }

    public boolean dodajGazdinstvo(PoljoprivrednoGazdinstvo pp) {
        return dbb.dodajGazdinstvo(pp);
    }

    public boolean izmeniGazdinstvo(PoljoprivrednoGazdinstvo pp2) {
        return dbb.izmeniGazdinstvo(pp2);
    }

    public boolean obrisiGazdinstvo(PoljoprivrednoGazdinstvo pp3) {
        return dbb.obrisiGazdinstvo(pp3);
    }
}

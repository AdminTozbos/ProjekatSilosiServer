/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.List;
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
}

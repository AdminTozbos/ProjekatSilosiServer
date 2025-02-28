/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KlijentskiZahtev;
import model.Operacije;
import model.PoljoprivrednaKultura;
import model.PoljoprivrednoGazdinstvo;
import model.PoljoprivrednoPreduzece;
import model.Potvrda;
import model.PrRi;
import model.RadnoIskustvo;
import model.RukovodilacKooperacije;
import model.ServerskiOdgovor;
import model.StavkaPotvrde;

/**
 *
 * @author Miloš
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {   
            controller.Controller.getInstance();
            KlijentskiZahtev kz=primiOdgovor();
            ServerskiOdgovor so=new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    List<String>parametri=(List<String>) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().login(parametri));
                    break;
                case Operacije.DODAJRUK:
                    RukovodilacKooperacije ruk=(RukovodilacKooperacije) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajRukovodioca(ruk));
                    break;
                case Operacije.VRATILISTURUK:
                    List<RukovodilacKooperacije> ruklist=Controller.getInstance().vratiSveRukovodioce();
                    so.setOdgovor(ruklist);
                    break;
                case Operacije.OBRISIRUK:
                    RukovodilacKooperacije ruko=(RukovodilacKooperacije) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().obrisiRukovodioca(ruko));
                    break;
                case Operacije.IZMENIRUK:
                    RukovodilacKooperacije ruk2=(RukovodilacKooperacije) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().izmeniRukovodioca(ruk2));
                    break;
                 case Operacije.VRATIISK:
                    
                    so.setOdgovor(Controller.getInstance().vratiSvaIskustva());
                    break;
                 case Operacije.DODAJISK:
                    RadnoIskustvo ri=(RadnoIskustvo) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajIskustvo(ri));
                    break;
                case Operacije.OBRISIISK:
                    RadnoIskustvo ri2=(RadnoIskustvo) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().obrisiIskustvo(ri2));
                    break;
                case Operacije.IZMENIISK:
                    RadnoIskustvo ri3=(RadnoIskustvo) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().izmeniIskustvo(ri3));
                    break;
                case Operacije.VRATIKUL:
                    
                    so.setOdgovor(Controller.getInstance().vratiSveKulture());
                    break;
                 case Operacije.DODAJKUL:
                     PoljoprivrednaKultura pk=(PoljoprivrednaKultura) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajKulturu(pk));
                    break;
                case Operacije.OBRISIKUL:
                     PoljoprivrednaKultura pk2=(PoljoprivrednaKultura) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().obrisiKulturu(pk2));
                    break;
                case Operacije.IZMENIKUL:
                    PoljoprivrednaKultura pk3=(PoljoprivrednaKultura) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().izmeniKulturu(pk3));
                    break;
                case Operacije.VRATIPRED:
                    
                    so.setOdgovor(Controller.getInstance().vratiSvaPreduzeca());
                    break;
                case Operacije.DODAJPRED:
                    PoljoprivrednoPreduzece pp=(PoljoprivrednoPreduzece) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajPreduzece(pp));
                    break;
                case Operacije.IZMENIPRED:
                    PoljoprivrednoPreduzece pp2=(PoljoprivrednoPreduzece) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().izmeniPreduzece(pp2));
                    break;
                case Operacije.OBRISIPRED:
                    PoljoprivrednoPreduzece pp3=(PoljoprivrednoPreduzece) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().obrisiPreduzece(pp3));
                    break;
                case Operacije.VRATIGAZ:
                    
                    so.setOdgovor(Controller.getInstance().vratiSvaGazdinstva());
                    break;
                case Operacije.DODAJGAZ:
                    PoljoprivrednoGazdinstvo pg=(PoljoprivrednoGazdinstvo) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajGazdinstvo(pg));
                    break;
                case Operacije.IZMENIGAZ:
                    PoljoprivrednoGazdinstvo pg2=(PoljoprivrednoGazdinstvo) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().izmeniGazdinstvo(pg2));
                    break;
                case Operacije.OBRISIGAZ:
                    PoljoprivrednoGazdinstvo pg3=(PoljoprivrednoGazdinstvo) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().obrisiGazdinstvo(pg3));
                    break;
                case Operacije.VRATIPOT:
                    
                    so.setOdgovor(Controller.getInstance().vratiSvePotvrde());
                    break;
                case Operacije.DODAJPOT:
                    Potvrda p=(Potvrda) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajPotvrdu(p));
                    break;
                case Operacije.DODAJSTA:
                    StavkaPotvrde st=(StavkaPotvrde) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajStavku(st));
                    break;
                case Operacije.OBRISIPOT:
                    Potvrda st2=(Potvrda) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().obrisiPotvrdu(st2));
                    break;
                case Operacije.VRATISTA:
                    Potvrda st3= (Potvrda) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().vratiSveStavke(st3));
                    break;
                case Operacije.IZMENIPOT:
                    Potvrda st4= (Potvrda) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().izmeniPotvrdu(st4));
                    break;
                case Operacije.VRATIPRRI:
                    
                    so.setOdgovor(Controller.getInstance().vratiSvePrri());
                    break;
                case Operacije.DODAJPRRI:
                    PrRi prri=(PrRi) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().dodajPrri(prri));
                    break;
                case Operacije.OBRISIPRRI:
                    PrRi prri2=(PrRi) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().obrisiPrri(prri2));
                    break;
                case Operacije.IZMENIPRRI:
                    PrRi prri3=(PrRi) kz.getZahtev();
                    so.setOdgovor(Controller.getInstance().izmeniPrri(prri3));
                    break;
                default:
                    System.out.println("Greska");
            }
            posaljiOdgovor(so);
        }
    }
    public KlijentskiZahtev primiOdgovor(){
        try {
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void posaljiOdgovor(ServerskiOdgovor so){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

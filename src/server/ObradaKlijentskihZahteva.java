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
import model.RadnoIskustvo;
import model.RukovodilacKooperacije;
import model.ServerskiOdgovor;

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

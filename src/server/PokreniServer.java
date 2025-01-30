/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miloš
 */
public class PokreniServer extends Thread{

    @Override
    public void run() {
        try {
            ServerSocket ss=new ServerSocket(9000);
            while (true) {                
                Socket s=ss.accept();
                System.out.println("Klijent je povezan");
                ObradaKlijentskihZahteva nit=new ObradaKlijentskihZahteva(s);
                nit.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

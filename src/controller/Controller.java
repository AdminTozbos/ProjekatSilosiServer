/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;

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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

/**
 *
 * @author Miloš
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DomainObject;

public class GenericDBBroker<T extends DomainObject<T>> {
    private Connection connection;
    private static GenericDBBroker dbb;
    private GenericDBBroker() {
        
        this.connection = Konekcija.getInstance().getConnection();
    }
    public static GenericDBBroker getInstance(){
        if(dbb==null){
            dbb=new GenericDBBroker();
        }
        return dbb;
    }
    public boolean insert(T object) throws SQLException {
        String query = object.getInsertQuery();
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            object.fillInsertStatement(stmt);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public boolean update(T object) throws SQLException {
        String query = object.getUpdateQuery();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            object.fillUpdateStatement(stmt);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public boolean delete(T object) throws SQLException {
        String query = object.getDeleteQuery();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            object.fillDeleteStatement(stmt);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    /**
     * Vraća sve objekte za koje je definisan upit u metodi getSelectQuery().
     * Za kreiranje pojedinačnih objekata koristi se metoda createFromResultSet() nad prosleđenim
     * "prototipom" objekta.
     */
    public List<T> getAll(T prototype) throws SQLException {
        String query = prototype.getSelectQuery();
        List<T> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                T obj = prototype.createFromResultSet(rs);
                list.add(obj);
            }
        }
        return list;
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alumno
 */
public class ConnectionFactoryImplJDBC implements ConnectionFactory{
  
    private Connection conexion;
    
    @Override
    public Connection getConnection()throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = null;
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "root");
        
        return conexion;
    
    }
}

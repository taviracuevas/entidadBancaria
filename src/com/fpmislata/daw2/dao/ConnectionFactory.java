/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author alumno
 */
public interface ConnectionFactory {

   Connection getConnection()throws ClassNotFoundException, SQLException; 
        

    }

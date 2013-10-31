/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fpmislata.daw2.entidadBancaria.EntidadBancaria;
import com.fpmislata.daw2.entidadBancaria.EntidadBancaria;
import com.fpmislata.daw2.entidadBancaria.TipoEntidadBancaria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class EntidadBancariaDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactoryImplJDBC();

    public EntidadBancaria read(int idEntidad) throws SQLException, ClassNotFoundException {

        Connection conexion = connectionFactory.getConnection();
        EntidadBancaria entidadBancaria;

        String selectSQL = "Select idEntidad , codigo , nombre ,cif , tipoEntidadBancaria from entidad_bancaria WHERE idEntidad = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(selectSQL);
        preparedStatement.setInt(1, idEntidad);
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            String codigo = resulSet.getString("codigo");
            String nombre = resulSet.getString("nombre");
            String cif = resulSet.getString("cif");
            String tipo = resulSet.getString("tipoEntidadBancaria");
            entidadBancaria = new EntidadBancaria(idEntidad, codigo, nombre, cif, TipoEntidadBancaria.valueOf(tipo), null);

            if (resulSet.next() == true) {
                conexion.close();
                throw new RuntimeException("Existe mas de una entidad bancaria con codigo: " + codigo);
            }

        } else {
            entidadBancaria = null;
        }
        conexion.close();
        return entidadBancaria;

    }

    public void insert(EntidadBancaria entidad) throws SQLException, ClassNotFoundException {
        Connection conexion = connectionFactory.getConnection();

        String insertTableSQL = "INSERT INTO entidad_bancaria"
                + "(idEntidad,codigo,nombre,cif,tipoEntidadBancaria) VALUES "
                + "(?,?,?,?,?)";

        PreparedStatement preparedStatement = conexion.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, entidad.getIdEntidad());
        preparedStatement.setString(2, entidad.getCodigoEntidad());
        preparedStatement.setString(3, entidad.getNombre());
        preparedStatement.setString(4, entidad.getCif());
        preparedStatement.setString(5, entidad.getTipoEntidadBancaria().name());

        preparedStatement.executeUpdate();
        conexion.close();
    }

    public void update(EntidadBancaria entidad) throws SQLException, ClassNotFoundException {

        Connection conexion = connectionFactory.getConnection();

        String updateSQL = "UPDATE entidad_bancaria SET codigo = ?, nombre = ?, cif = ?, tipoEntidadBancaria = ? WHERE idEntidad = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(updateSQL);
        preparedStatement.setString(1, entidad.getCodigoEntidad());
        preparedStatement.setString(2, entidad.getNombre());
        preparedStatement.setString(3, entidad.getCif());
        preparedStatement.setString(4, entidad.getTipoEntidadBancaria().name());
        preparedStatement.setInt(5, entidad.getIdEntidad());
        preparedStatement.executeUpdate();
        conexion.close();

    }

    public void delete(int idEntidad) throws SQLException, ClassNotFoundException {
        int filasBorradas;
        Connection conexion = connectionFactory.getConnection();
            String deleteSQL = "DELETE FROM entidad_bancaria WHERE idEntidad = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idEntidad);
            filasBorradas = preparedStatement.executeUpdate();
        
        if (filasBorradas > 1) {
            throw new RuntimeException("Se han borrado mas de una fila ");

        }
        //preparedStatement3.executeUpdate();

    }

    public List<EntidadBancaria> findAll() throws SQLException, ClassNotFoundException {
        Connection conexion = connectionFactory.getConnection();

        List<EntidadBancaria> listaEntidades = new ArrayList<EntidadBancaria>();
        String selectSQL = "Select * from entidad_bancaria";

        PreparedStatement preparedStatement = conexion.prepareStatement(selectSQL);
        ResultSet resulSet = preparedStatement.executeQuery();

        while (resulSet.next()) {
            EntidadBancaria entidad = new EntidadBancaria(resulSet.getInt("idEntidad"), resulSet.getString("codigo"), resulSet.getString("nombre"), resulSet.getString("cif"), TipoEntidadBancaria.valueOf(resulSet.getString("tipoEntidadBancaria")), null);
            listaEntidades.add(entidad);
        }
        conexion.close();
        return listaEntidades;

    }

    public List<EntidadBancaria> findByCodigo(String codigo) throws SQLException, ClassNotFoundException {
        Connection conexion = connectionFactory.getConnection();

        List<EntidadBancaria> listaEntidades = new ArrayList<EntidadBancaria>();
        String selectSQL = "Select * from entidad_bancaria where codigo = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(selectSQL);
        preparedStatement.setString(1, codigo);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            EntidadBancaria entidad = new EntidadBancaria(rs.getInt("idEntidad"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("cif"), TipoEntidadBancaria.valueOf(rs.getString("tipoEntidadBancaria")), null);
            listaEntidades.add(entidad);
        }
        conexion.close();
        return listaEntidades;

    }
}

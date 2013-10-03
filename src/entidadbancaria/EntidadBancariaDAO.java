/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadbancaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidadbancaria.EntidadBancaria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class EntidadBancariaDAO {

    private Connection conexion;

    public EntidadBancariaDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conexion = null;
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "root");

    }

    public EntidadBancaria read(int idEntidad) throws SQLException {
        EntidadBancaria entidad;

        String selectSQL = "Select idEntidad , codigo , nombre ,cif , tipoEntidadBancaria from entidad_bancaria WHERE idEntidad = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(selectSQL);
        preparedStatement.setInt(1, idEntidad);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            String codigo = rs.getString("codigo");
            String nombre = rs.getString("nombre");
            String cif = rs.getString("cif");
            String tipo = rs.getString("tipoEntidadBancaria");
            entidad = new EntidadBancaria(idEntidad, codigo, nombre, cif, TipoEntidadBancaria.valueOf(tipo), null);

            if (rs.next() == true) {
                throw new RuntimeException("Existe mas de una entidad bancaria con codigo: " + codigo);
            }

        } else {
            entidad = null;
        }

        return entidad;

    }
    
    public void insert(EntidadBancaria entidad) throws SQLException{
        String insertTableSQL = "INSERT INTO entidad_bancaria"
                                + "(idEntidad,codigo,nombre,cif,tipoEntidadBancaria) VALUES "
                                + "(?,?,?,?,?)";
        
        PreparedStatement preparedStatement = conexion.prepareStatement(insertTableSQL);
        preparedStatement.setInt(1, entidad.getIdEntidad());
        preparedStatement.setString(2, entidad.getCodigoEntidad());
        preparedStatement.setString(3, entidad.getNombre());
        preparedStatement.setString(4, entidad.getCif());
        preparedStatement.setString(5, entidad.getTipoEntidadBancaria().toString());
        
        preparedStatement.executeUpdate();
    }
    public void update(EntidadBancaria entidad) throws SQLException{
        
         String updateSQL = "UPDATE entidad_bancaria SET codigo = ?, nombre = ?, cif = ?, tipoEntidadBancaria = ? WHERE idEntidad = ?";
        PreparedStatement preparedStatement2 = conexion.prepareStatement(updateSQL);
        preparedStatement2.setString(1, entidad.getCodigoEntidad());
        preparedStatement2.setString(2, entidad.getNombre());
        preparedStatement2.setString(3, entidad.getCif());
        preparedStatement2.setString(4, entidad.getTipoEntidadBancaria().toString());
         preparedStatement2.setInt(5, entidad.getIdEntidad());
        preparedStatement2.executeUpdate();
        
    }
    public void delete (int idEntidad) throws SQLException{
        String deleteSQL = "DELETE FROM entidad_bancaria WHERE idEntidad = ?";
        PreparedStatement preparedStatement3 = conexion.prepareStatement(deleteSQL);

        preparedStatement3.setInt(1, idEntidad);
        int filasBorradas = preparedStatement3.executeUpdate();
        
        if(filasBorradas>1){
            throw new RuntimeException("Se han borrado mas de una fila ");
                
        }
        //preparedStatement3.executeUpdate();
    }
    
    public List<EntidadBancaria> findAll() throws SQLException{
        
        List<EntidadBancaria> listaEntidades = new ArrayList<EntidadBancaria>();
        String selectSQL = "Select * from entidad_bancaria";
        
        PreparedStatement preparedStatement = conexion.prepareStatement(selectSQL);
        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
            EntidadBancaria entidad = new EntidadBancaria(rs.getInt("idEntidad"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("cif"), TipoEntidadBancaria.valueOf(rs.getString("tipoEntidadBancaria")), null);
            listaEntidades.add(entidad);
        }
        return listaEntidades;
        
    }
     public List<EntidadBancaria> findByCodigo(String codigo) throws SQLException{
        
        List<EntidadBancaria> listaEntidades = new ArrayList<EntidadBancaria>();
        String selectSQL = "Select * from entidad_bancaria where codigo = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(selectSQL);
        preparedStatement.setString(1, codigo);
        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
            EntidadBancaria entidad = new EntidadBancaria(rs.getInt("idEntidad"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("cif"), TipoEntidadBancaria.valueOf(rs.getString("tipoEntidadBancaria")), null);
            listaEntidades.add(entidad);
        }
        return listaEntidades;
        
    }
}

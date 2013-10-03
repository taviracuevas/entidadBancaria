/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios;
import entidadbancaria.EntidadBancaria;
import entidadbancaria.EntidadBancariaDAO;
import entidadbancaria.TipoEntidadBancaria;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alumno
 */
public class PruebasSQL {
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        EntidadBancariaDAO entidadDao = new EntidadBancariaDAO();
        
        EntidadBancaria entidad = entidadDao.read(1);
        
        //System.out.println(entidad.getNombre());
        
        EntidadBancaria entidad2 = entidadDao.read(2);
        
        //System.out.println(entidad.getNombre());
        
        EntidadBancaria entidad3 = new EntidadBancaria(5, "555", "Prueba", "84445", TipoEntidadBancaria.BANCO, null);
        entidad3.setNombre("Prueba2");
        //entidadDao.insert(entidad3);
        
        //entidadDao.delete(5);
        
        List<EntidadBancaria> listaEntidades = entidadDao.findByCodigo("3");
        
        for (EntidadBancaria e: listaEntidades){
            System.out.println(e.getNombre());
        }
    }
}

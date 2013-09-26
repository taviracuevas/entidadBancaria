/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadbancaria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class EntidadBancaria {

    /**
     * @param args the command line arguments
     */
    
    private int idEntidad;
    private String codigoEntidad;
    private String nombre;
    private String cif;
    private TipoEntidadBancaria tipoEntidadBancaria;
    private List<SucursalBancaria> sucursales;

    public EntidadBancaria() {
        this.sucursales = new ArrayList<SucursalBancaria>();
    
    }

    public EntidadBancaria(int idEntidad, String codigoEntidad, String nombre, String cif, TipoEntidadBancaria tipoEntidadBancaria, List<SucursalBancaria> sucursales) {
        this.idEntidad = idEntidad;
        this.codigoEntidad = codigoEntidad;
        this.nombre = nombre;
        this.cif = cif;
        this.tipoEntidadBancaria = tipoEntidadBancaria;
        this.sucursales = new ArrayList<SucursalBancaria>();
    }

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public TipoEntidadBancaria getTipoEntidadBancaria() {
        return tipoEntidadBancaria;
    }

    public void setTipoEntidadBancaria(TipoEntidadBancaria tipoEntidadBancaria) {
        this.tipoEntidadBancaria = tipoEntidadBancaria;
    }

    public List<SucursalBancaria> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<SucursalBancaria> sucursales) {
        this.sucursales = sucursales;
    }
    
        
}

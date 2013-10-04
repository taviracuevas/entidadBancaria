/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.ejercicios;

import com.fpmislata.daw2.entidadBancaria.CuentaBancaria;
import com.fpmislata.daw2.entidadBancaria.EntidadBancaria;
import com.fpmislata.daw2.entidadBancaria.MovimientoBancario;
import com.fpmislata.daw2.entidadBancaria.SucursalBancaria;
import com.fpmislata.daw2.entidadBancaria.TipoEntidadBancaria;
import com.fpmislata.daw2.entidadBancaria.TipoMovimientoBancario;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) {
        SucursalBancaria sucursal = new SucursalBancaria();
        SucursalBancaria sucursal2 = new SucursalBancaria();
        EntidadBancaria entidad = new EntidadBancaria();
        EntidadBancaria entidad2 = new EntidadBancaria();
        MovimientoBancario movimiento = new MovimientoBancario();
        MovimientoBancario movimiento2 = new MovimientoBancario();
        CuentaBancaria cuenta = new CuentaBancaria();
        CuentaBancaria cuenta2 = new CuentaBancaria();

        sucursal.setCodigoSucursal("123");
        sucursal.setEntidadBancaria(entidad);
        sucursal.setIdSucursalBancaria(1);
        sucursal.setNombre("Sucursal1");

        sucursal2.setCodigoSucursal("456");
        sucursal2.setEntidadBancaria(entidad);
        sucursal2.setIdSucursalBancaria(2);
        sucursal2.setNombre("Sucursal2");


        entidad.setCif("12345");
        entidad.setCodigoEntidad("12345");
        entidad.setIdEntidad(1);
        entidad.setNombre("Bankia");
        entidad.setTipoEntidadBancaria(TipoEntidadBancaria.BANCO);


        entidad2.setCif("78910");
        entidad2.setCodigoEntidad("78910");
        entidad2.setIdEntidad(2);
        entidad2.setNombre("Caja de ahorros");
        entidad2.setTipoEntidadBancaria(TipoEntidadBancaria.CAJADEAHORRO);

        entidad.getSucursales().add(sucursal);
        entidad.getSucursales().add(sucursal2);

        cuenta.setCif("48406012M");
        cuenta.setDc("001");
        cuenta.setIdBancaria(1);
        cuenta.setNumeroDeCuenta("123-5455-115");
        cuenta.setSaldo(new BigDecimal("0"));
        cuenta.setSucursalBancaria(sucursal);

        cuenta2.setCif("48406013M");
        cuenta2.setDc("002");
        cuenta2.setIdBancaria(1);
        cuenta2.setNumeroDeCuenta("155-5547-998");
        cuenta2.setSaldo(new BigDecimal("0"));
        cuenta2.setSucursalBancaria(sucursal);

        sucursal.getCuentasBancarias().add(cuenta);
        sucursal.getCuentasBancarias().add(cuenta2);

        movimiento.setConcepto("Compra tele");
        movimiento.setFecha(new GregorianCalendar(2013, 3, 2).getTime());
        movimiento.setIdMovimientoBancario(1);
        movimiento.setImporte(new BigDecimal("500"));
        movimiento.setSaldoTotal(new BigDecimal("500"));
        movimiento.setTipoMovimientoBancario(TipoMovimientoBancario.DEBE);

        movimiento2.setConcepto("Nomina");
        movimiento2.setFecha(new GregorianCalendar(2013, 2, 2).getTime());
        movimiento2.setIdMovimientoBancario(2);
        movimiento2.setImporte(new BigDecimal("1000"));
        movimiento2.setSaldoTotal(new BigDecimal("2500.50"));
        movimiento2.setTipoMovimientoBancario(TipoMovimientoBancario.HABER);

        cuenta.getMovimientos().add(movimiento);
        cuenta.getMovimientos().add(movimiento2);

        System.out.println("Entidad: " + entidad.getNombre());

        for (SucursalBancaria s : entidad.getSucursales()) {
            System.out.println("Sucursal: " + s.getNombre());

            for (CuentaBancaria c : s.getCuentasBancarias()) {
                System.out.println("Cuenta: " + c.getNumeroDeCuenta() + " Saldo: " + c.getSaldo());

                for (MovimientoBancario m : c.getMovimientos()) {
                    System.out.println("Tipo Momiviento " + m.getTipoMovimientoBancario() + " Importe: " + m.getImporte() + " Fecha: " + m.getFecha().toString());

                }
            }

        }
        System.out.println("--------");
        mostrarDatosCuenta(cuenta);

        
    }

    public static void mostrarDatosCuenta(CuentaBancaria cuenta) {
        String codigoCuentaCliente;
        codigoCuentaCliente = cuenta.getSucursalBancaria().getEntidadBancaria().getCodigoEntidad() + " " + cuenta.getSucursalBancaria().getCodigoSucursal() + " " + cuenta.getDc() + " " + cuenta.getNumeroDeCuenta() + " Saldo " + cuenta.getSaldo() ;
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        for (MovimientoBancario m : cuenta.getMovimientos()) {
            codigoCuentaCliente = codigoCuentaCliente + "\n" + "Tipo Momiviento " + m.getTipoMovimientoBancario() + " Importe: " + m.getImporte() + " Fecha: " + df1.format(m.getFecha()).toString();
        }
        System.out.println(codigoCuentaCliente);
    }
}

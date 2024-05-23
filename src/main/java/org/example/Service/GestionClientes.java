package org.example.Service;


import org.example.Common.IdNoValidoExcepcion;
import org.example.DAO.DaoClientesImplementacion;
import org.example.DAO.IClientes;
import org.example.Domain.Cliente;
import org.example.Domain.Vuelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GestionClientes implements IGestionClientes {

    public final IClientes daoClientes;

    public GestionClientes() {
        this.daoClientes = new DaoClientesImplementacion();
    }
    public boolean comprobarUsuario(String dni, String contrasena){
        return daoClientes.comprobarUsuario(dni, contrasena);
    }

    @Override
    public List<Cliente> consultarClientes() {
        return daoClientes.consultarClientes();
    }

    @Override
    public boolean crearCliente(String nombre, String apellidos, String dni, String contrasena) throws IdNoValidoExcepcion {
        return daoClientes.crearCliente(nombre, apellidos, dni, contrasena);
    }

    @Override
    public boolean añadirvuelo(Vuelo comprarvuelos,Cliente cliente) {
        return daoClientes.añadirvuelo(comprarvuelos, cliente);
    }

    @Override
    public List<Vuelo> verVueloscomprados(Cliente cliente) {
        return daoClientes.verVueloscomprados(cliente);
    }

    @Override
    public void eliminarVuelo(String idVueloEliminar,Cliente cliente) {
        daoClientes.eliminarVuelo(idVueloEliminar, cliente);
    }

    @Override
    public Cliente cogerCliente(String dni, String contrasena) {
        return daoClientes.cogerClientes(dni,contrasena);
    }

    @Override
    public boolean añadirDiaHoracompra(LocalDate dia, Cliente cliente, LocalTime horaCompra) {
        return daoClientes.añadirDiacompra(dia,cliente, horaCompra);
    }

    @Override
    public List<LocalDate> getDiacompra(Cliente cliente) {
        return daoClientes.getDiacompra(cliente);
    }

    @Override
    public List<LocalTime> getHoracompra(Cliente cliente) {
        return daoClientes.getHoracompra(cliente);
    }

}

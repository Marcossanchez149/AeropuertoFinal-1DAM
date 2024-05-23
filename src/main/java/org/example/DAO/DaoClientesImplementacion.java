package org.example.DAO;

import org.example.Common.IdNoValidoExcepcion;
import org.example.Domain.Cliente;
import org.example.Domain.Vuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DaoClientesImplementacion implements IClientes {
    private final Clientes clientes;

    public DaoClientesImplementacion(Clientes personas) {
        this.clientes = personas;
    }

    public DaoClientesImplementacion() {
        this.clientes = DAOFicheros.leerFicheroBin("Clientes.bin");
    }
    public void actualizarClientes(){
         DAOFicheros.escribirFicheroBin(clientes, "Clientes.bin");
    }

    @Override
    public boolean comprobarUsuario(String dni, String contrasena) {
        return clientes.comprobarUsuario(dni, contrasena);
    }

    @Override
    public List<Cliente> consultarClientes() {
        return clientes.consultarClientes();
    }

    @Override
    public boolean crearCliente(String nombre, String apellidos, String dni, String contrasena) throws IdNoValidoExcepcion {
        boolean creado = false;
        if(clientes.crearCliente(nombre, apellidos, dni, contrasena)){
            actualizarClientes();
            creado = true;
        }

        return creado;
    }

    @Override
    public boolean añadirvuelo(Vuelo comprarvuelos,Cliente cliente) {
        boolean añadido = false;
        if (clientes.añadirvuelo(comprarvuelos,cliente)){
            actualizarClientes();
            añadido = true;
        }
        return añadido;
    }

    @Override
    public List<Vuelo> verVueloscomprados(Cliente cliente) {
        return clientes.verVueloscomprados(cliente);
    }

    @Override
    public void eliminarVuelo(String idVueloEliminar,Cliente cliente) {
        clientes.eliminarVuelo(idVueloEliminar, cliente);
    }

    @Override
    public Cliente cogerClientes(String dni, String contrasena) {
        return clientes.getClientes().stream().filter(persona -> persona.getDni().equalsIgnoreCase(dni) && persona.getContraseña().equals(contrasena)).findAny().orElseThrow(null);
    }

    @Override
    public boolean añadirDiacompra(LocalDate dia, Cliente cliente, LocalTime horaCompra) {
        return clientes.añadirDiacompra(dia,cliente,  horaCompra);
    }

    @Override
    public List<LocalDate> getDiacompra(Cliente cliente) {
        return clientes.getClientes().stream().filter(cliente1 -> cliente1.equals(cliente)).findAny().map(cliente1 -> cliente1.getDiasCompra()).orElse(null);
    }

    @Override
    public List<LocalTime> getHoracompra(Cliente cliente) {
        return clientes.getClientes().stream().filter(cliente1 -> cliente1.equals(cliente)).findAny().map(cliente1 -> cliente1.getHoracompra()).orElse(null);
    }
}

package org.example.Service;

import org.example.Common.IdNoValidoExcepcion;
import org.example.Domain.Cliente;
import org.example.Domain.Vuelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IGestionClientes {

    boolean comprobarUsuario(String dni, String contrasena);


    List<Cliente>  consultarClientes();

    boolean crearCliente(String nombre, String apellidos, String dni, String contrasena) throws IdNoValidoExcepcion;

    boolean añadirvuelo(Vuelo comprarvuelos,Cliente cliente);

    List<Vuelo> verVueloscomprados(Cliente cliente);

    void eliminarVuelo(String idVueloEliminar,Cliente cliente);

    Cliente cogerCliente(String dni, String contrasena);

    boolean añadirDiaHoracompra(LocalDate dia, Cliente cliente, LocalTime horaCompra);

    List<LocalDate> getDiacompra(Cliente cliente);

    List<LocalTime> getHoracompra(Cliente cliente);
}

package org.example.DAO;

import org.example.Common.IdNoValidoExcepcion;
import org.example.Domain.Cliente;
import org.example.Domain.Vuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface IClientes {

    boolean comprobarUsuario(String dni, String contrasena);

    List<Cliente> consultarClientes();

    boolean crearCliente(String nombre, String apellidos, String dni, String contrasena) throws IdNoValidoExcepcion;

    boolean añadirvuelo(Vuelo comprarvuelos,Cliente cliente);

    List<Vuelo> verVueloscomprados(Cliente cliente);

    void eliminarVuelo(String idVueloEliminar,Cliente cliente);

    Cliente cogerClientes(String dni, String contrasena);

    boolean añadirDiacompra(LocalDate dia, Cliente cliente, LocalTime horaCompra);

    List<LocalDate> getDiacompra(Cliente cliente);

    List<LocalTime> getHoracompra(Cliente cliente);
}

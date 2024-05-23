package org.example.DAO;

import lombok.Data;
import net.datafaker.Faker;
import org.example.Common.Comprobacion;
import org.example.Common.IdNoValidoExcepcion;
import org.example.Domain.Cliente;
import org.example.Domain.Vuelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Clientes implements  Serializable {
    private List<Cliente> clientes;

    public Clientes() {

        clientes = new ArrayList<>();
        clientes= (List<Cliente>) DAOFicheros.leerFicheroBin("Clientes.bin");
        /*Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            clientes.add(new Cliente(faker.name().firstName(), faker.name().lastName(), faker.idNumber().valid(), faker.internet().password()));
        }*/
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes1 = (Clientes) o;
        return Objects.equals(clientes, clientes1.clientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientes);
    }


    public boolean comprobarUsuario(String dni, String contrasena) {
        return clientes.stream().anyMatch(cliente -> cliente.getDni().equals(dni) && cliente.getContraseña().equals(contrasena));
    }


    public List<Cliente> consultarClientes() {
        return clientes;
    }


    public boolean crearCliente(String nombre, String apellidos, String dni, String contrasena) throws IdNoValidoExcepcion {
        Comprobacion.comprobarId(dni);
        return clientes.add(new Cliente(nombre, apellidos, dni, contrasena));
    }

    public boolean añadirvuelo(Vuelo comprarvuelos,Cliente cliente) {
        return clientes.stream().filter(cliente1 -> cliente1.getDni().equalsIgnoreCase(cliente.getDni())).anyMatch(cliente1 -> cliente1.getVuelosComprados().add(comprarvuelos));
    }

    public List<Vuelo> verVueloscomprados(Cliente cliente) {
        return clientes.stream().filter(cliente1 -> cliente1.getDni().equalsIgnoreCase(cliente.getDni())).findAny().map(Cliente::getVuelosComprados).orElse(null);

    }

    public void eliminarVuelo(String idVueloEliminar, Cliente cliente) {
        cliente.getVuelosComprados().removeIf(vuelo -> vuelo.getId().equalsIgnoreCase(idVueloEliminar));
        clientes.stream().filter(cliente1 -> cliente1.getDni().equalsIgnoreCase(cliente.getDni())).findAny().ifPresent(cliente1 -> cliente1.setVuelosComprados(cliente.getVuelosComprados()));

    }

    public boolean añadirDiacompra(LocalDate dia, Cliente cliente, LocalTime horaCompra) {
        return clientes.stream().filter(cliente1 -> cliente1.equals(cliente)).anyMatch(cliente1 -> cliente1.getDiasCompra().add(dia) && cliente1.getHoracompra().add(horaCompra));
    }

}

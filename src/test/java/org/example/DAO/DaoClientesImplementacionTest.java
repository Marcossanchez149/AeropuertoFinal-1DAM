package org.example.DAO;

import org.example.Common.IdNoValidoExcepcion;
import org.example.Domain.Vuelo;
import org.junit.jupiter.api.Test;

import org.example.Domain.Cliente;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.Random.class)
@ExtendWith(MockitoExtension.class)
class DaoClientesImplementacionTest {
    @InjectMocks
    DaoClientesImplementacion daoClientesImplementacion;
    @Mock
    Clientes clientemock;

    @BeforeAll
    public static void inicio() {
        System.out.println("Comenzando los tests de mi prueba");
    }

    @BeforeEach
    void inicioEspecifico() {
        System.out.println("Iniciando test ...");
    }

    @AfterEach
    void finEspecifico() {
        System.out.println("Test finalizado");
    }

    @AfterAll
    public static void fin() {
        System.out.println("Finalizando los test de mi prueba");
    }


    @Order(1)
    @ParameterizedTest
    @MethodSource("value")
    void comprobarUsuario(Cliente cliente) {
        //given

        //when
        when(clientemock.comprobarUsuario("12345678A", "1234")).thenReturn(true);
        boolean resultado = daoClientesImplementacion.comprobarUsuario(cliente.getDni(), cliente.getContraseña());

        //then
        assertTrue(resultado);

    }

    public static Stream<Cliente> value() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Pepe", "Perez", "12345678A", "1234"));
        clientes.add(new Cliente("Bartolo", "Perez", "12345678A", "1234"));
        clientes.add(new Cliente("Felipe", "Perez", "12345678A", "1234"));
        return clientes.stream();
    }
@Nested
class clientes{
    @Order(2)
    @Test
    void consultarClientes() {
        //given
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Pepe", "Perez", "1234567843A", "1234"));
        clientes.add(new Cliente("Bartolo", "Luis", "15618589B", "5678"));
        clientes.add(new Cliente("Felipe", "Panama", "5984861C", "9101"));
        //when
        when(clientemock.consultarClientes()).thenReturn(clientes);
        List<Cliente> resultado = daoClientesImplementacion.consultarClientes();
        //then
        assert (resultado).equals(clientes);

    }

    @Order(3)
    @Test
    void crearCliente() {
        //given
        String nombre = "Pepe";
        String apellidos = "Perez";
        String dni = "12345678A";
        String contrasena = "1234";
        //when
        try {
            when(clientemock.crearCliente(nombre, apellidos, dni, contrasena)).thenReturn(true);
            boolean resultado = daoClientesImplementacion.crearCliente(nombre, apellidos, dni, contrasena);


            //then

            assertTrue(resultado);
        } catch (IdNoValidoExcepcion e) {
            throw new RuntimeException(e);
        }


    }

}


    @Order(4)
    @Test
    void añadirvuelo() {
        //given
        Vuelo vuelo = new Vuelo("AB1", "Madrid", "Roma", "2h", "18:00", "20:00", "15/06/2024");
        Cliente cliente = new Cliente("Pepe", "Perez", "12345678A", "1234");
        //when

        when(clientemock.añadirvuelo(vuelo, cliente)).thenReturn(true);
        boolean resultado = daoClientesImplementacion.añadirvuelo(vuelo, cliente);

        //then
        assertTrue(resultado);

    }

    @Order(5)
    @Test
    void verVueloscomprados() {
        //given
        Cliente cliente = new Cliente("Pepe", "Perez", "12345678A", "1234");
        Vuelo vuelo1 = new Vuelo("AB1", "Madrid", "Roma", "2h", "18:00", "20:00", "15/06/2024");
        Vuelo vuelo2 = new Vuelo("AB2", "Barcelona", "Paris", "3h", "12:00", "15:00", "18/06/2024");

        //when
        when(clientemock.verVueloscomprados(cliente)).thenReturn(List.of(vuelo1, vuelo2));
        List<Vuelo> resultado = daoClientesImplementacion.verVueloscomprados(cliente);

        //then
        assertEquals(resultado, List.of(vuelo1, vuelo2));
    }


    @Order(6)
    @Test
    void añadirDiacompra() {
        //given
        Cliente cliente = new Cliente("Pepe", "Perez", "12345678A", "1234");
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        //when
        when(clientemock.añadirDiacompra(localDate, cliente, localTime)).thenReturn(true);
        boolean resultado = daoClientesImplementacion.añadirDiacompra(localDate, cliente, localTime);

        //then
        assertTrue(resultado);
    }



}
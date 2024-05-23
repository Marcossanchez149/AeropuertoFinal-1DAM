package org.example.Service;


import org.example.Common.IdVueloError;
import org.example.DAO.DaoVuelos;
import org.example.Domain.Vuelo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.Random.class)
@ExtendWith(MockitoExtension.class)
public class GestionVuelosTest {
    @InjectMocks
    GestionVuelos gestionVuelos;
    @Mock
    DaoVuelos daoVuelos;

    @BeforeAll
    static void inicio() {
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

    @Order (4)
    @Test
    public void testComprobarvuelos() {
        //given
        List<Vuelo> vuelos = new ArrayList<>();
        vuelos.add(new Vuelo("AB1", "Madrid", "Roma", "2h", "18:00", "20:00", "15/06/2024"));
        vuelos.add(new Vuelo("AB2", "Madrid", "Paris", "3h", "11:00", "14:00", "20/06/2024"));
        vuelos.add(new Vuelo("AB3", "Madrid", "Londres", "4h", "10:00", "14:00", "29/07/2024"));

        //When
        when(daoVuelos.comprobarvuelos()).thenReturn(vuelos);

        List<Vuelo> resultados = gestionVuelos.comprobarvuelos();

        //then
        assert (resultados).equals(vuelos);

    }

    @Order (3)
    @Test
    public void testComprobarvuelosFecha() {
        //given
        List<Vuelo> vuelos2 = new ArrayList<>();
        Vuelo vuelo2 = new Vuelo("AB2", "Madrid", "Paris", "3h", "11:00", "14:00", "20/06/2024");
        vuelos2.add(vuelo2);
        Vuelo vuelo3 = new Vuelo("AB3", "Madrid", "Londres", "4h", "10:00", "14:00", "20/06/2024");
        vuelos2.add(vuelo3);
        String fecha = "20/06/2024";

        //When
        when(daoVuelos.comprobarvuelosFecha(fecha)).thenReturn(vuelos2);
        List<Vuelo> resultados = gestionVuelos.comprobarvuelosFecha("20/06/2024");

        //then
        assert (resultados).equals(vuelos2);
        assertThat(resultados).contains(vuelo2);


    }
    @Order (1)
    @Nested
    class porCiudades {
        @ParameterizedTest
        @MethodSource("value")
        public void testComprobarvuelosSalidaCiudad(Vuelo vuelo) {
            //given
            List<Vuelo> vuelos = new ArrayList<>();
            vuelos.add(vuelo);
            //when
            when(daoVuelos.comprobarvuelosSalidaCiudad("Madrid")).thenReturn(vuelos);
            List<Vuelo> resultados = (gestionVuelos.comprobarvuelosSalidaCiudad("Madrid"));

            //then
            assert (resultados).equals(vuelos);

        }

        public static Stream<Vuelo> value() {
            List<Vuelo> vuelos = new ArrayList<>();
            Vuelo vuelo1 = new Vuelo("AB1", "Madrid", "Roma", "2h", "18:00", "20:00", "15/06/2024");
            Vuelo vuelo2 = new Vuelo("AB2", "Madrid", "Londres", "3h", "18:00", "20:00", "20/06/2024");
            vuelos.add(vuelo1);
            vuelos.add(vuelo2);
            return vuelos.stream();
        }


        @Test
        public void testComprobarvuelosLlegadaCiudad() {
            List<Vuelo> vuelos = new ArrayList<>();
            Vuelo vuelo3 = new Vuelo("AB3", "Cordoba", "Londres", "4h", "10:00", "14:00", "20/06/2024");
            vuelos.add(vuelo3);

            //when
            when(daoVuelos.comprobarvuelosLlegadaCiudad("Londres")).thenReturn(vuelos);
            List<Vuelo> resultados = gestionVuelos.comprobarvuelosLlegadaCiudad("Londres");

            //then
            assert (resultados).equals(vuelos);

        }

    }

    @Order (2)
    @Test
    public void testComprarvuelos() {
        List<Vuelo> vuelos = new ArrayList<>();
        Vuelo vuelo1 = new Vuelo("AB1", "Madrid", "Roma", "2h", "18:00", "20:00", "15/06/2024");
        vuelos.add(vuelo1);
        Vuelo vuelo2 = new Vuelo("AB2", "Barcelona", "Paris", "3h", "11:00", "14:00", "20/06/2024");
        vuelos.add(vuelo2);
        Vuelo vuelo3 = new Vuelo("AB3", "Cordoba", "Londres", "4h", "10:00", "14:00", "20/06/2024");
        vuelos.add(vuelo3);

        //when
        try {
            when(daoVuelos.comprarvuelos("AB1")).thenReturn(vuelos.getFirst());
            Vuelo resultados = gestionVuelos.comprarvuelos("AB1");


            //then
            assert (resultados).equals(vuelo1);

        } catch (IdVueloError e) {
            System.out.println(e.getMessage());
        }


    }

    @Order (6)
    @Test
    public void testAñadirVuelo() {
        //Given
        Vuelo vuelo1 = new Vuelo("AB1", "Madrid", "Roma", "2h", "18:00", "20:00", "15/06/2024");
        try {
            //When
            when(daoVuelos.añadirVuelo(vuelo1)).thenReturn(true);
            Boolean respuesta = gestionVuelos.añadirVuelo(vuelo1);

            //Then
            assertThat(respuesta).isTrue();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IdVueloError e) {
            throw new RuntimeException(e);
        }

    }
    @Order (5)
    @Test
    public void testEliminarVuelo() {
        //Given
        Vuelo vuelo1 = new Vuelo("AB1", "Madrid", "Roma", "2h", "18:00", "20:00", "15/06/2024");

        try {
            //when
            when(daoVuelos.eliminarVuelo("AB1")).thenReturn(true);
            Boolean respuesta = gestionVuelos.eliminarVuelo("AB1");

            //Then
            assertTrue(respuesta);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
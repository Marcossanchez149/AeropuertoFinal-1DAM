package org.example.Service;

import org.example.Common.IdVueloError;
import org.example.Domain.Vuelo;

import java.io.FileNotFoundException;
import java.util.List;

public interface IGestionVuelos {
    List<Vuelo> comprobarvuelos();
    List<Vuelo> comprobarvuelosFecha(String fecha);


    List<Vuelo> comprobarvuelosSalidaCiudad(String ciudad);

    List<Vuelo> comprobarvuelosLlegadaCiudad(String ciudadLlegada);

    Vuelo comprarvuelos(String idVuelo) throws IdVueloError;

    boolean añadirVuelo(Vuelo vuelo) throws FileNotFoundException, IdVueloError;

    boolean eliminarVuelo(String idVueloEliminar) throws FileNotFoundException;

    void modificarVuelCiudadsalida(String id, String ciudadSalida) throws FileNotFoundException;

    void modificarVueloCiudadllegada(String id, String ciudadLlegada) throws FileNotFoundException;

    void modificarVueloHorasalida(String id, String horaSalida) throws FileNotFoundException;

    void modificarVueloHorallegada(String id, String horaLlegada) throws FileNotFoundException;

    void modificarVuelofecha(String id, String fecha) throws FileNotFoundException;

    List<Vuelo> ordenarVuelosFecha();
}

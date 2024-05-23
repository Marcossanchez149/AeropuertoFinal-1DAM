package org.example.DAO;

import org.example.Common.ComparadorVuelosNombreLlegadaSalida;
import org.example.Common.Comprobacion;
import org.example.Common.IdVueloError;
import org.example.Domain.Vuelo;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class DaoVuelosimplementacion implements DaoVuelos {
    private final Vuelos vuelos;
    public DaoVuelosimplementacion() {
        this.vuelos = new Vuelos();
    }

    public List<Vuelo> comprobarvuelos() {
       return vuelos.getVuelos();
    }

    @Override
    public List<Vuelo> comprobarvuelosFecha(String fecha) {
        return vuelos.getVuelosFecha(fecha);
    }

    @Override
    public List<Vuelo> comprobarvuelosSalidaCiudad(String ciudad) {
        return vuelos.getVuelosSalidaCiudad(ciudad);
    }

    @Override
    public List<Vuelo> comprobarvuelosLlegadaCiudad(String ciudadLlegada) {
        return vuelos.getVuelosLlegadaCiudad(ciudadLlegada);
    }

    @Override
    public Vuelo comprarvuelos(String idVuelo) throws IdVueloError {
        Comprobacion.comprobarIDVuelo(idVuelo,vuelos.getIdVuelos());
        return vuelos.comprarVuelo(idVuelo);
    }

    @Override
    public boolean añadirVuelo(Vuelo vuelo) throws FileNotFoundException, IdVueloError {
        Comprobacion.comprobarIdVueloUsado(vuelo.getId(),vuelos.getIdVuelos());
        return vuelos.añadirVuelo(vuelo);
    }

    @Override
    public boolean eliminarVuelo(String idVueloEliminar) throws FileNotFoundException {
        return vuelos.eliminarVuelo(idVueloEliminar);
    }

    @Override
    public void modificarVuelCiudadsalida(String id, String ciudadSalida) throws FileNotFoundException {
        vuelos.modificarVueloCiudadSalida(id,ciudadSalida);
        vuelos.actualizarVuelos();
    }

    @Override
    public void modificarVueloCiudadllegada(String id, String ciudadLlegada) throws FileNotFoundException {
        vuelos.modificarVueloCiudadLlegada(id,ciudadLlegada);
        vuelos.actualizarVuelos();
    }

    @Override
    public void modificarVueloHorasalida(String id, String horaSalida) throws FileNotFoundException {
        vuelos.modificarVueloHoraSalida(id,horaSalida);
        vuelos.actualizarVuelos();
    }

    @Override
    public void modificarVueloHorallegada(String id, String horaLlegada) throws FileNotFoundException {
        vuelos.modificarVueloHoraLlegada(id,horaLlegada);
        vuelos.actualizarVuelos();
    }

    @Override
    public void modificarVuelofecha(String id, String fecha) throws FileNotFoundException {
        vuelos.modificarVueloFecha(id,fecha);
        vuelos.actualizarVuelos();
    }

    @Override
    public List<Vuelo> ordenarVuelosFecha() {
        List<Vuelo> vuelosOrdenados = vuelos.getVuelos();
        vuelosOrdenados.sort(new ComparadorVuelosNombreLlegadaSalida());
        return vuelosOrdenados;
    }
}

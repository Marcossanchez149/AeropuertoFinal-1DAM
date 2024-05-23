package org.example.Service;

import org.example.Common.IdVueloError;
import org.example.DAO.DaoVuelos;
import org.example.DAO.DaoVuelosimplementacion;
import org.example.Domain.Vuelo;

import java.io.FileNotFoundException;
import java.util.List;


public class GestionVuelos implements IGestionVuelos{
    private final DaoVuelos daoVuelos;

    public GestionVuelos() {
        this.daoVuelos =  new DaoVuelosimplementacion();
    }
    public GestionVuelos(DaoVuelos daoVuelos) {
        this.daoVuelos =  daoVuelos;
    }
    public List<Vuelo> comprobarvuelos() {
        return daoVuelos.comprobarvuelos();
    }

    @Override
    public List<Vuelo> comprobarvuelosFecha(String fecha) {
        return daoVuelos.comprobarvuelosFecha(fecha);
    }

    @Override
    public List<Vuelo> comprobarvuelosSalidaCiudad(String ciudad) {
        return daoVuelos.comprobarvuelosSalidaCiudad(ciudad);
    }

    @Override
    public List<Vuelo> comprobarvuelosLlegadaCiudad(String ciudadLlegada) {
        return daoVuelos.comprobarvuelosLlegadaCiudad(ciudadLlegada);
    }

    @Override
    public Vuelo comprarvuelos(String idVuelo) throws IdVueloError {
       return daoVuelos.comprarvuelos(idVuelo);
    }

    @Override
    public boolean añadirVuelo(Vuelo vuelo) throws FileNotFoundException, IdVueloError {
       return daoVuelos.añadirVuelo(vuelo);
    }

    @Override
    public boolean eliminarVuelo(String idVueloEliminar) throws FileNotFoundException {
        return daoVuelos.eliminarVuelo(idVueloEliminar);
    }

    @Override
    public void modificarVuelCiudadsalida(String id, String ciudadSalida) throws FileNotFoundException {
        daoVuelos.modificarVuelCiudadsalida(id,ciudadSalida);
    }

    @Override
    public void modificarVueloCiudadllegada(String id, String ciudadLlegada) throws FileNotFoundException {
        daoVuelos.modificarVueloCiudadllegada(id,ciudadLlegada);
    }

    @Override
    public void modificarVueloHorasalida(String id, String horaSalida) throws FileNotFoundException {
        daoVuelos.modificarVueloHorasalida(id,horaSalida);

    }

    @Override
    public void modificarVueloHorallegada(String id, String horaLlegada) throws FileNotFoundException {
        daoVuelos.modificarVueloHorallegada(id,horaLlegada);
    }

    @Override
    public void modificarVuelofecha(String id, String fecha) throws FileNotFoundException {
        daoVuelos.modificarVuelofecha(id,fecha);
    }

    @Override
    public List<Vuelo> ordenarVuelosFecha() {
        return daoVuelos.ordenarVuelosFecha();
    }
}

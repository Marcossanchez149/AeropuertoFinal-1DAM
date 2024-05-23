package org.example.DAO;

import net.datafaker.Faker;
import org.example.Domain.Vuelo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Vuelos {

    private List<Vuelo> vuelos;

    private static int numId;

    public Vuelos() {
        vuelos = new ArrayList<>();
        vuelos = DAOFicheros.leerFichero("Vuelos");
      /*Faker faker = new Faker();
        Random random = new Random();
        do {
            numId++;
            String ciudadSalida = faker.address().cityName();
            String ciudadLlegada;
            do {
                 ciudadLlegada=faker.address().cityName();
            }while (ciudadLlegada.equalsIgnoreCase(ciudadSalida));
            vuelos.add( new Vuelo("AB"+numId,ciudadSalida,ciudadLlegada,random.nextInt(1,12)+"H",random.nextInt(1,23)+":"+random.nextInt(1,59),random.nextInt(1,23)+":"+random.nextInt(1,59),(random.nextInt(1,30)+"/"+random.nextInt(1,12)+"/2024")));
        }while (numId!=50);*/
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }
    public List<String> getIdVuelos(){
        List<String> idVuelos = new ArrayList<>();
        vuelos.forEach(vuelo -> idVuelos.add(vuelo.getId()));
        return idVuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }


    @Override
    public String toString() {
        return "Vuelos{" +
                "vuelos=" + vuelos +
                '}';
    }

    public List<Vuelo> getVuelosFecha(String fecha) {
        List<Vuelo> vuelosFecha = new ArrayList<>();
        vuelos.stream().filter(vuelo -> vuelo.getFecha().equalsIgnoreCase(fecha)).forEach(vuelosFecha::add);
        return vuelosFecha;
    }

    public List<Vuelo> getVuelosSalidaCiudad(String ciudad) {
        List<Vuelo> vuelosSalidaCiudad = new ArrayList<>();
        vuelos.stream().filter(vuelo -> vuelo.getCiudadSalida().equalsIgnoreCase(ciudad)).forEach(vuelosSalidaCiudad::add);
        return vuelosSalidaCiudad;
    }

    public List<Vuelo> getVuelosLlegadaCiudad(String ciudadLlegada) {
        List<Vuelo> vuelosLlegadaCiudad = new ArrayList<>();
        vuelos.stream().filter(vuelo -> vuelo.getCiudadLlegada().equalsIgnoreCase(ciudadLlegada)).forEach(vuelosLlegadaCiudad::add);
        return vuelosLlegadaCiudad;
    }

    public Vuelo comprarVuelo(String idVuelo) {
        return vuelos.stream().filter(vuelo -> vuelo.getId().equalsIgnoreCase(idVuelo)).findFirst().orElse(null);
    }
    public void actualizarVuelos() throws FileNotFoundException {
         DAOFicheros.escribirFichero(vuelos,"Vuelos");
    }

    public boolean añadirVuelo(Vuelo vuelo) throws FileNotFoundException {
        boolean añadido = false;
        if (vuelos.stream().noneMatch(vuelos -> vuelos.getId().equalsIgnoreCase(vuelo.getId()))) {
            vuelos.add(vuelo);
            actualizarVuelos();
            añadido = true;
        }
        return añadido;

    }

    public boolean eliminarVuelo(String idVueloEliminar) throws FileNotFoundException {
        boolean eliminado = false;
        if (vuelos.stream().anyMatch(vuelo -> vuelo.getId().equalsIgnoreCase(idVueloEliminar))) {
            vuelos.removeIf(vuelo -> vuelo.getId().equalsIgnoreCase(idVueloEliminar));
            eliminado = true;
            actualizarVuelos();
        }

        return eliminado;
    }

    public void modificarVueloCiudadSalida(String id, String ciudadSalida) {
        vuelos.stream().filter(vuelo -> vuelo.getId().equalsIgnoreCase(id)).forEach(vuelo -> vuelo.setCiudadSalida(ciudadSalida));
    }

    public void modificarVueloCiudadLlegada(String id, String ciudadLlegada) {
        vuelos.stream().filter(vuelo -> vuelo.getId().equalsIgnoreCase(id)).forEach(vuelo -> vuelo.setCiudadLlegada(ciudadLlegada));
    }

    public void modificarVueloHoraSalida(String id, String horaSalida) {
        vuelos.stream().filter(vuelo -> vuelo.getId().equalsIgnoreCase(id)).forEach(vuelo -> vuelo.setHoraSalida(horaSalida));
    }

    public void modificarVueloHoraLlegada(String id, String horaLlegada) {
        vuelos.stream().filter(vuelo -> vuelo.getId().equalsIgnoreCase(id)).forEach(vuelo -> vuelo.setHoraLlegada(horaLlegada));
    }

    public void modificarVueloFecha(String id, String fecha) {
        vuelos.stream().filter(vuelo -> vuelo.getId().equalsIgnoreCase(id)).forEach(vuelo -> vuelo.setFecha(fecha));
    }

}


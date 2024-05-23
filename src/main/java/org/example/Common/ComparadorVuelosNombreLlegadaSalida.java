package org.example.Common;

import org.example.Domain.Vuelo;

import java.util.Comparator;

public class ComparadorVuelosNombreLlegadaSalida implements Comparator<Vuelo> {
    @Override
    public int compare(Vuelo o1, Vuelo o2) {

        int resultado = o1.getCiudadSalida().compareTo(o2.getCiudadSalida());
        if (resultado == 0) {
            resultado = o1.getCiudadLlegada().compareTo(o2.getCiudadLlegada());
        }
        return resultado;
    }
}

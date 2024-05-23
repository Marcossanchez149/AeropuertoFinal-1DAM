package org.example.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


public class Vuelo implements Serializable {

    private String id;
    private String ciudadSalida;
    private String ciudadLlegada;
    private String duracion;
    private String fecha;
    private String horaSalida;
    private String horaLlegada;

    public Vuelo(String id, String ciudadSalida, String ciudadLlegada, String duracion, String horaSalida, String horaLlegada,String fecha){
        this.id = id;
        this.ciudadSalida = ciudadSalida;
        this.ciudadLlegada = ciudadLlegada;
        this.duracion = duracion;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.fecha=fecha;
    }
    public Vuelo(String lista) {
        String[] listaAct = lista.split("'");
        this.id = listaAct[0];
        this.ciudadSalida = listaAct[1];
        this.ciudadLlegada = listaAct[2];
        this.duracion = (listaAct[3]) ;
        this.fecha = listaAct[4];
        this.horaSalida = listaAct[5];
        this.horaLlegada = listaAct[6];

    }

    public Vuelo(Vuelo vuelo) {
        this.id = vuelo.id;
        this.ciudadSalida = vuelo.ciudadSalida;
        this.ciudadLlegada = vuelo.ciudadLlegada;
        this.duracion = vuelo.duracion;
        this.fecha = vuelo.fecha;
        this.horaSalida = vuelo.horaSalida;
        this.horaLlegada = vuelo.horaLlegada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCiudadSalida() {
        return ciudadSalida;
    }

    public void setCiudadSalida(String ciudadSalida) {
        this.ciudadSalida = ciudadSalida;
    }

    public String getCiudadLlegada() {
        return ciudadLlegada;
    }

    public void setCiudadLlegada(String ciudadLlegada) {
        this.ciudadLlegada = ciudadLlegada;
    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }



    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    //To string para crear el fichero



    public String mostrarVueloBonito() {
        return "Vuelo{" +
                "id='" + id + '\'' +
                ", ciudadSalida='" + ciudadSalida + '\'' +
                ", ciudadLlegada='" + ciudadLlegada + '\'' +
                ", duracion='" + duracion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", horaLlegada='" + horaLlegada + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return
                id + '\'' +
                        ciudadSalida + '\'' +
                        ciudadLlegada + '\'' +
                        duracion + '\'' +
                        fecha + '\'' +
                        horaSalida + '\'' +
                        horaLlegada + '\''
                ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(id, vuelo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ciudadSalida, ciudadLlegada, duracion, fecha, horaSalida, horaLlegada);
    }
}

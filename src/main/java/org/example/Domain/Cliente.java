package org.example.Domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cliente implements Serializable {

    private String nombre;
    private String apellidos;
    private String dni;
    private String contraseña;
    private List<Vuelo> vuelosComprados;
    private List<LocalDate> diasCompra;
    private List<LocalTime> horacompra;



    public Cliente( String nombre, String apellidos, String dni, String contraseña) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.contraseña = contraseña;
        this.vuelosComprados = new ArrayList<>();
        this.diasCompra = new ArrayList<>();
        this.horacompra = new ArrayList<>();
    }
}




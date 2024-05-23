package org.example.Domain;

import lombok.Data;

@Data
public class Trabajador {
    private String id;
    private String nombre;
    private int edad;
    private String contraseña;

    public Trabajador(String id, String nombre, int edad, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.contraseña = contraseña;
    }


}

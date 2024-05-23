package org.example.Service;

public interface IGestionTrabajadores {
    boolean comprobarTrabajador(String idAdmin, String contraseñaAdmin);

    boolean crearTrabajador(String id, String nombre,int edad,  String contrasena);
}

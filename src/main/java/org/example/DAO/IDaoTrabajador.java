package org.example.DAO;

public interface IDaoTrabajador {
    boolean comprobarTrabajador(String idAdmin, String contraseñaAdmin);

    boolean crearTrabajador(String id, String nombre,int edad,  String contrasena);
}

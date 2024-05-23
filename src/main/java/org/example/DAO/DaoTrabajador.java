package org.example.DAO;

import org.example.Domain.Trabajador;

public class DaoTrabajador implements IDaoTrabajador{
    private final Trabajadores trabajadores;

    public DaoTrabajador() {
        this.trabajadores = new Trabajadores();
    }

    @Override
    public boolean comprobarTrabajador(String idAdmin, String contraseñaAdmin) {
        return trabajadores.getTrabajadores().stream().anyMatch(t -> t.getId().equalsIgnoreCase(idAdmin) && t.getContraseña().equals(contraseñaAdmin));
    }

    @Override
    public boolean crearTrabajador(String id, String nombre,int edad,  String contrasena) {
        return trabajadores.addTrabajador(new Trabajador(id,nombre, edad, contrasena));
    }
}

package org.example.Service;

import org.example.DAO.DaoTrabajador;
import org.example.DAO.IDaoTrabajador;

public class GestionTrabajadores implements  IGestionTrabajadores{
    public final IDaoTrabajador daoTrabajador;

    public GestionTrabajadores() {
        this.daoTrabajador = new DaoTrabajador();
    }


    @Override
    public boolean comprobarTrabajador(String idAdmin, String contraseñaAdmin) {
        return daoTrabajador.comprobarTrabajador(idAdmin, contraseñaAdmin);
    }

    @Override
    public boolean crearTrabajador(String id, String nombre,int edad,  String contrasena) {
        return daoTrabajador.crearTrabajador(id,nombre,edad, contrasena);
    }
}

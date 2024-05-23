package org.example.DAO;

import lombok.Data;
import org.example.Domain.Trabajador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Trabajadores {
    private LeerTrabajadores leerTrabajadores;

    private List<Trabajador> trabajdores ;


    public Trabajadores() {
        this.leerTrabajadores = new LeerTrabajadores() ;
        this.trabajdores = leerTrabajadores.cargarTrabajadores();
    }

    public Trabajadores(List<Trabajador> trabajadores) {
        this.trabajdores = trabajadores;
    }

    public boolean actualizarTrabajadores(){
        return leerTrabajadores.saveTrabajadores(trabajdores);
    }


    public List<Trabajador> getTrabajadores() {
        return trabajdores;
    }

    public boolean addTrabajador(Trabajador trabajador) {
        trabajdores.add(trabajador);
        return actualizarTrabajadores();
    }
}

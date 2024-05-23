package org.example.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.example.Common.Configuracion;
import org.example.Domain.Trabajador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class LeerTrabajadores {
    public List<Trabajador> cargarTrabajadores() {
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<Trabajador>>() {}.getType();
        log.info("cargando clientes");
        List<Trabajador> trabajadores = null;
        try {
            trabajadores = gson.fromJson(
                    new FileReader(new Configuracion().loadPathProperties()),
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return trabajadores;
    }


    public boolean saveTrabajadores(List<Trabajador> trabajadores) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fw = new FileWriter(new Configuracion().loadPathProperties())) {
            gson.toJson(trabajadores, fw);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}

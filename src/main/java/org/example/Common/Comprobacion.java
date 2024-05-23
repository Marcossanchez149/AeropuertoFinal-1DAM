package org.example.Common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comprobacion {

    public static void comprobarId(String id) throws IdNoValidoExcepcion {
        if (id.length() < 5 || id.length() > 15) {
            throw new IdNoValidoExcepcion();
        }
    }

    public static void comprobarIDVuelo(String idIntroducdo, List<String> listaID) throws IdVueloError{
        listaID.stream().filter(id -> id.equals(idIntroducdo)).findFirst().orElseThrow(IdVueloError::new);
    }

    public static void comprobarIdVueloUsado(String id, List<String> idVuelos) throws IdVueloError {
      if ( idVuelos.stream().anyMatch(idVuelo -> idVuelo.equals(id))) {
          throw new IdVueloError();
      }
    }

    public static boolean comprobarContraseña(String contraseña){
        Pattern pat = Pattern.compile("");
        Matcher mat = pat.matcher(contraseña);
        return mat.matches();


    }
}

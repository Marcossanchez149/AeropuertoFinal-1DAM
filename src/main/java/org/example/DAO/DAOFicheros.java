package org.example.DAO;

import org.example.Domain.Vuelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DAOFicheros implements Serializable{
    public  final  static String ficheroVuelos = "Vuelos";
    public  final  static String ficheroClientes = "Clientes.bin";

    public static void crearFichero() throws IOException {
        File fichero1 = new File(ficheroVuelos);
        File fichero2 = new File(ficheroClientes);
        if (!fichero1.exists()){
            fichero1.createNewFile();
        } else if (!fichero2.exists()){
            fichero2.createNewFile();
        }
    }

    public  static void escribirFichero(List<Vuelo> vuelos,String nombrefichero) throws FileNotFoundException {
        String cadena = null;
        PrintWriter pw = new PrintWriter(nombrefichero );
        //? si no está vacía
        for (int i = 0; i < vuelos.size(); i++) {
            pw.println(vuelos.get(i));
        }
        pw.close();
    }

    public static List<Vuelo> leerFichero(String nombrefichero){
        ArrayList<Vuelo> auxiliar = null;
        try (Scanner sc = new Scanner(new File(nombrefichero))) {
            auxiliar = new ArrayList<>();
            while (sc.hasNextLine()) {
                String cadena = sc.nextLine();
                auxiliar.add(new Vuelo(cadena));

            };
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(DAOFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);

        }
        return auxiliar;
    }

    public static Clientes leerFicheroBin (String nombrefichero){
        Clientes personas = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombrefichero))) {
            personas = (Clientes) ois.readObject();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(DAOFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);

        } catch (IOException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DAOFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);

        }
        return personas;
    }


    public static void escribirFicheroBin(Clientes p, String nombrefichero) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombrefichero))) {
            oos.writeObject(p);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(DAOFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DAOFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
    }


}

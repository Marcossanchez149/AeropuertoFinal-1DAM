package org.example.UI;

import org.example.Common.Comprobacion;
import org.example.Common.Constantes;
import org.example.Common.IdNoValidoExcepcion;
import org.example.Common.IdVueloError;
import org.example.Domain.Cliente;
import org.example.Domain.Vuelo;
import org.example.Service.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ContactoUsuario {
    private final IGestionVuelos gestionVuelos;
    private final IGestionClientes gestionPersonas;

    private final IGestionTrabajadores gestionTrabajadores;

    public ContactoUsuario() {
        this.gestionVuelos = new GestionVuelos();
        this.gestionPersonas = new GestionClientes();
        this.gestionTrabajadores = new GestionTrabajadores();

    }

    public void tipoUsuario() {
        Scanner leer = new Scanner(System.in);
        System.out.println(Constantes.INICIOAPP);
        int opcion;
        do {
            System.out.println(Constantes.MENUELEGIRUSUARIO);
            opcion = leer.nextInt();
            if (opcion == 1) {
                comprobarUsuario();
            } else if (opcion == 2) {
                comprobarAdministrador();
            } else {
                System.out.println("Opción no válida");
            }
        } while (opcion != 1 && opcion != 2);
    }

    public void comprobarAdministrador(){
        boolean comprobar = false;
        do {
            Scanner leer = new Scanner(System.in);
            System.out.println(Constantes.COMPROBARADMIN);
            System.out.println("Introduce tu id de administrador");
            String idAdmin = leer.nextLine();
            System.out.println("Introduce tu contraseña");
            String contraseñaAdmin = leer.nextLine();

            if (gestionTrabajadores.comprobarTrabajador(idAdmin, contraseñaAdmin)) {
                System.out.println(Constantes.BIENVENIDOADMIN);
                menuAdmin();
                comprobar = true;
            } else {
                System.out.println(Constantes.DATOSINCORRECTOS);
                String respuesta = leer.nextLine();
                if (respuesta.equalsIgnoreCase("si")) {
                    crearTrabajador();
                    comprobar = true;
                }
            }
        }while (!comprobar);

    }

    private void crearTrabajador() {
        Scanner leer = new Scanner(System.in);
        boolean crear = false;
        do {
            System.out.println(Constantes.CREARADMIN);
            System.out.println(Constantes.INTRODUCEID);
            String id = leer.nextLine();
            System.out.println(Constantes.INTRODUCENOMBRE);
            String nombre = leer.nextLine();
            System.out.println(Constantes.INTRODUCEEDAD);
            int edad = leer.nextInt();
            String salto_linea = leer.nextLine();
            System.out.println(Constantes.INTRODUCECONTRASENA);
            String contrasena = leer.nextLine();
            if (gestionTrabajadores.crearTrabajador( id, nombre, edad,  contrasena)) {
                System.out.println(Constantes.ADMINCREADO);
                menuAdmin();
                crear = true;
            } else {
                System.out.println(Constantes.ADMINNOVALIDO);
            }
        } while (!crear);
    }

    public void comprobarUsuario() {
        Scanner leer = new Scanner(System.in);
        boolean comprobar = false;
        do {
            System.out.println(gestionPersonas.consultarClientes());
            System.out.println(Constantes.INICIARSESION1);
            String dni = leer.nextLine();
            System.out.println(Constantes.INICIARSESION2);
            String contrasena = leer.nextLine();
            if (gestionPersonas.comprobarUsuario(dni, contrasena)) {
                Cliente cliente = gestionPersonas.cogerCliente(dni,contrasena);
                System.out.println(Constantes.BIENVENIDO);
                menuUsuario(cliente);
                comprobar = true;
            } else {
                System.out.println(Constantes.DATOSINCORRECTOS);
                String respuesta = leer.nextLine();
                if (respuesta.equalsIgnoreCase("si")) {
                    crearUsuario();
                    comprobar = true;
                }
            }
        } while (!comprobar);
    }

    public void crearUsuario() {
        Scanner leer = new Scanner(System.in);
        boolean crear = false;
        do {
            System.out.println(Constantes.CREARUSUARIO);
            System.out.println(Constantes.INTRODUCEDNI);
            String dni = leer.nextLine();
            System.out.println(Constantes.INTRODUCENOMBRE);
            String nombre = leer.nextLine();
            System.out.println(Constantes.INTRODUCEAPELLIDOS);
            String apellidos = leer.nextLine();
            System.out.println(Constantes.INTRODUCECONTRASENA);
            String contrasena = leer.nextLine();
            if(Comprobacion.comprobarContraseña(contrasena)){
                try {
                    if (gestionPersonas.crearCliente(nombre, apellidos, dni, contrasena)) {
                        Cliente cliente = new Cliente(nombre, apellidos, dni, contrasena);
                        System.out.println(Constantes.USUARIOCREADO);
                        menuUsuario(cliente);
                        crear = true;
                    } else {
                        System.out.println(Constantes.USUARIONOVALIDO);
                    }
                } catch (IdNoValidoExcepcion e) {
                    System.out.println(e.getMessage());
                }
            }else{
                System.out.println(Constantes.CONTRASENANOVALIDA);
            }
        } while (!crear);
    }

    public void menuUsuario(Cliente cliente) {
        Scanner leer = new Scanner(System.in);
        int opcion;
        do {
            System.out.println(Constantes.MENUCLIENTE);
            opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    misVuelos(cliente);
                    break;
                case 2:
                    //ver vuelos disponibles
                    List<Vuelo> vuelos = (gestionVuelos.comprobarvuelos());
                    vuelos.stream().forEach(vuelo -> System.out.println(vuelo.mostrarVueloBonito()));
                    break;
                case 3:
                    //Ver Vuelos Disponibles en una fecha
                    String fecha = pedirFecha();
                    List<Vuelo> vuelosFecha = (gestionVuelos.comprobarvuelosFecha(fecha));
                    if (vuelosFecha != null) {
                        vuelosFecha.stream().forEach(vuelo -> System.out.println(vuelo.mostrarVueloBonito()));
                    } else {
                        System.out.println(Constantes.NOHAYUELOS);
                    }
                    break;
                case 4:
                    //Ver los vuelos de salida de un país
                    String ciudad = pedirCiudadSalida();
                    List<Vuelo> vuelosCiudad = (gestionVuelos.comprobarvuelosSalidaCiudad(ciudad));
                    vuelosCiudad.stream().forEach(vuelo -> System.out.println(vuelo.mostrarVueloBonito()));
                    break;
                case 5:
                    //Ver los vuelos de llegada a un país
                    String ciudadLlegada = pedirCiudadLlegada();
                    List<Vuelo> vuelosCiudadLlegada = (gestionVuelos.comprobarvuelosLlegadaCiudad(ciudadLlegada));
                    vuelosCiudadLlegada.stream().forEach(vuelo -> System.out.println(vuelo.mostrarVueloBonito()));
                    break;

                    case 6:
                        //Ordenar los vuelos por ciudad de salida
                        List<Vuelo> vuelosOrdenados = (gestionVuelos.ordenarVuelosFecha());
                        vuelosOrdenados.stream().forEach(vuelo -> System.out.println(vuelo.mostrarVueloBonito()));
                        break;

                case 7:
                    //Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 7);


    }

    public void misVuelos(Cliente cliente) {
        Scanner leer = new Scanner(System.in);
        int respuesta;
        do {
            System.out.println(Constantes.MISVUELOS);
            respuesta = leer.nextInt();
            switch (respuesta) {
                case 1:
                    //Comprar vuelo
                    String saltoLinea = leer.nextLine();
                    System.out.println(Constantes.COMPRARVUELO);
                    String idVuelo = leer.nextLine();
                    try {
                        if (gestionPersonas.añadirvuelo(gestionVuelos.comprarvuelos(idVuelo), cliente)) {
                            System.out.println(Constantes.VUELOCOMPRADO);
                            LocalDate dia = LocalDate.now();
                            System.out.println("Comprado el día "+dia);
                            LocalTime horaCompra = LocalTime.now();
                            System.out.println("Comprado a las "+horaCompra);
                            gestionPersonas.añadirDiaHoracompra(dia,cliente, horaCompra);

                        } else {
                            System.out.println(Constantes.VUELOCOMPRADOERROR);
                        }
                    } catch (IdVueloError e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    //Eliminar vuelo
                    String saltoLinea2 = leer.nextLine();
                    System.out.println(Constantes.ELIMINARVUELO);
                    String idVueloEliminar = leer.nextLine();
                   gestionPersonas.eliminarVuelo(idVueloEliminar,cliente);
                    System.out.println(Constantes.VUELOELIMINADO);
                    break;
                case 3:
                    //Ver mis vuelos
                    System.out.println(Constantes.VERMISSVUELOS);
                    List<Vuelo> vuelos = gestionPersonas.verVueloscomprados(cliente);
                    vuelos.forEach(vuelo -> System.out.println(vuelo.mostrarVueloBonito()));
                    break;
                case 4:
                    //ver facturas
                    List<LocalDate> diacompra = gestionPersonas.getDiacompra(cliente);
                    List<LocalTime> horacompra = gestionPersonas.getHoracompra(cliente);
                    for (int i = 0; i < diacompra.size(); i++) {
                        for (int j = 0; j < 1; j++) {
                            System.out.println("Dia compra :"+diacompra.get(i)+", Hora compra: "+horacompra.get(i));
                        }
                    }
                    break;
                case 5:
                    //Salir
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        } while (respuesta != 4);
    }

    private String pedirCiudadLlegada() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce la ciudad de llegada que deseas buscar");
        return leer.nextLine();
    }

    public static String pedirFecha() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce la fecha que deseas buscar en formato dd/mm/yyyy");
        return leer.nextLine();
    }

    public static String pedirCiudadSalida() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce la ciudad de salida que deseas buscar");
        return leer.nextLine();
    }

    public void menuAdmin() {
        Scanner leer = new Scanner(System.in);
        int opcion;
        do {
            System.out.println(Constantes.MENUADMINISTRADOR);
            opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    //Añadir vuelo
                    System.out.println(Constantes.CREARNUEVOVUELO);
                    Vuelo vuelo = new Vuelo(nuevovuelo());
                    try {
                        if (gestionVuelos.añadirVuelo(vuelo)) {
                            System.out.println("Vuelo añadido");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (IdVueloError e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    //Eliminar vuelo
                    String saltoLinea = leer.nextLine();
                    System.out.println(Constantes.ELIMINARVUELO);
                    String idVueloEliminar = leer.nextLine();
                    try {
                        if (gestionVuelos.eliminarVuelo(idVueloEliminar)) {
                            System.out.println("Vuelo eliminado");
                        } else {
                            System.out.println("Error al eliminar el vuelo");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    //Modificar vuelo
                    try {
                        menuModificar();
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    //Ver vuelos
                    List<Vuelo> vuelos = (gestionVuelos.comprobarvuelos());
                    vuelos.stream().forEach(vuelo1 -> System.out.println(vuelo1.mostrarVueloBonito()));
                    break;
                case 5:
                    //Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 5);

    }

    private void menuModificar() throws FileNotFoundException {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce el id del vuelo que deseas modificar");
        String id = leer.nextLine();
        int opcion;
        do {
            System.out.println(Constantes.MODIFICARVUELO);
            opcion = leer.nextInt();
            String saltoLinea = leer.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce la nueva ciudad de salida ");
                    String ciudadSalida = leer.nextLine();
                    gestionVuelos.modificarVuelCiudadsalida(id, ciudadSalida);
                    break;
                case 2:
                    System.out.println("Introduce la nueva ciudad de llegada ");
                    String ciudadLlegada = leer.nextLine();
                    gestionVuelos.modificarVueloCiudadllegada(id, ciudadLlegada);
                    break;
                case 3:
                    System.out.println("Introduce la nueva hora de salida ");
                    String horaSalida = leer.nextLine();
                    gestionVuelos.modificarVueloHorasalida(id, horaSalida);
                    break;
                case 4:
                    System.out.println("Introduce la nueva hora de llegada ");
                    String horaLlegada = leer.nextLine();
                    gestionVuelos.modificarVueloHorallegada(id, horaLlegada);
                    break;
                case 5:
                    System.out.println("Introduce la nueva fecha ");
                    String fecha = leer.nextLine();
                    gestionVuelos.modificarVuelofecha(id, fecha);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");

            }
        } while (opcion != 6);
    }

    private Vuelo nuevovuelo() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce el id del vuelo");
        String id = leer.nextLine();
        System.out.println("Introduce la ciudad de salida");
        String ciudadSalida = leer.nextLine();
        System.out.println("Introduce la ciudad de llegada");
        String ciudadLlegada = leer.nextLine();
        System.out.println("Introduce la duración del vuelo");
        String duracion = leer.nextLine();
        System.out.println("Introduce la hora de salida");
        String fechaSalida = leer.nextLine();
        System.out.println("Introduce la hora de llegada");
        String fechaLlegada = leer.nextLine();
        System.out.println("Introduce la fecha del vuelo en formato dd/mm/yyyy");
        String fecha = leer.nextLine();
        return new Vuelo(id, ciudadSalida, ciudadLlegada, duracion, fechaSalida, fechaLlegada, fecha);
    }


}

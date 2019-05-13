package Controlador;

import GestorArchivos.ManejoArchivos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdministradorCanciones {

    private ArrayList<Cancion> canciones;
    private int cantidadCanciones;

    public AdministradorCanciones(String ubicacionCanciones) throws IOException {
        canciones = new ArrayList<Cancion>();
        recuperarCanciones(ubicacionCanciones);
        iniciar();
    }

    public int getCantidadCanciones() {
        return cantidadCanciones;
    }

    public void setCantidadCanciones(int cantidadCanciones) {
        this.cantidadCanciones = cantidadCanciones;
    }

    /*
    Se asegura que el administrador de canciones inicie con las canciones del archivo de texto ya creadas.
    @param direccion relativa del texto con la lista de canciones (String).
    @return void.
     */
    public void recuperarCanciones(String ubicacionCanciones) throws IOException {
        cantidadCanciones = 0;
        ManejoArchivos man = new ManejoArchivos(ubicacionCanciones);
        for (int i = 0; i < man.getDatos().size(); i++) {
            String[] datosCancion = man.getDatos().get(i);
            canciones.add(new Cancion((i + 1), datosCancion[0], datosCancion[1], datosCancion[2], datosCancion[3], man.getLetras()[i]));
            cantidadCanciones++;
        }
    }

    /*
    Agrega una canción con todos sus respectivos datos.
    @param codigo de la canción (int), nombre de la canción (String), nombre del principal intérprete (String), año de lanzamiento (String), letra de la canción (String).
    return void.
     */
    public void agregarCancion(int codigo, String nombre, String nombreInterprete, String album, String anoLanzamiento, String letra) {
        canciones.add(new Cancion(codigo, nombre, nombreInterprete, album, anoLanzamiento, letra));
    }

    /*
    Muestra todas las canciones que se encuentran actualmente en el administrador.
    @param void.
    @return void.
     */
    public void mostrarCanciones() {
        for (int i = 0; i < canciones.size(); i++) {
            System.out.println("Canción " + canciones.get(i).getCodigo() + ": " + canciones.get(i).getNombre());
            System.out.println("Principal interprete: " + canciones.get(i).getNombreInterprete());
            System.out.println("Album: " + canciones.get(i).getAlbum());
            System.out.println("Año de lanzamiento: " + canciones.get(i).getAnoLanzamiento());
            System.out.println();
        }
    }

    /*
    Muestra la letra de una canción seleccionada previamente.
    @param código de la canción de la que queremos ver su letra (int).
    @return void.
     */
    public void mostrarLetra(int codigoCancion) {
        try {
            System.out.println("La letra de la canción " + canciones.get(codigoCancion - 1).getNombre() + " es:");
            System.out.println();
            System.out.println(canciones.get(codigoCancion - 1).getLetra());
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("Introduzca una opción correcta por favor");
        }
    }

    /*
    Elimina una canción seleccionada previamente.
    @param código de la canción que queremos eliminar.
    @return void.
     */
    public void quitarCancion(int codigo) {
        try {
            canciones.remove(codigo);
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("Ingrese una canción existente por favor");
        }
    }

    /*
    Muestra el menú de opciones para poder usar el administrador de canciones.
    @param void.
    @return void.
     */
    public void mostrarMenu() {
        System.out.println("Bienvenido nuestro administrador de canciones.");
        System.out.println("1.- Añadir canción");
        System.out.println("2.- Quitar canción");
        System.out.println("3.- Mostrar canciones");
        System.out.println("4.- Mostrar la letra de una canción");
        System.out.println("5.- Salir");
    }

    /*
    Pide un número por consola.
    @param void.
    @return número ingresado por consola (int).
     */
    public int ingresarNumero() {
        int eleccion;
        Scanner leer = new Scanner(System.in);
        try {
            eleccion = leer.nextInt();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Ingrese un número entero por favor");
            eleccion = -1;
        }
        return eleccion;
    }

    /*
    Hace una pregunta al usuario y recibe un valor por consola.
    @param pregunta que se mostrará al usuario (String).
    @return void dato ingresado por el usuario (String).
     */
    public String pregunta(String oracion) {
        Scanner leer = new Scanner(System.in);
        System.out.println(oracion);
        return leer.nextLine();
    }

    /*
    Ejecuta la funcionalidad previamente seleccionada del administrador del administrador de canciones.
    @param elección del usuario que indica que funcionalidad ejecutar (int).
    @return valor que indica si el programa se sigue ejecutando o no (boolean).
     */
    public boolean ejecutarFuncionalidad(int eleccion) {
        boolean indicador = true;
        switch (eleccion) {
            case 1:
                String nombreCancion = pregunta("¿Cúal es el nombre de la canción que quieres agregar?");
                String principalInterprete = pregunta("¿Quién es el principal intérprete de esa canción?");
                String album = pregunta("¿A qué album pertenece?");
                String anoLanzamiento = pregunta("¿En que año de lanzó?");
                String letra = pregunta("¿Cual es su letra?");
                agregarCancion(cantidadCanciones + 1, nombreCancion, principalInterprete, album, anoLanzamiento, letra);
                cantidadCanciones++;
                break;
            case 2:
                System.out.println("Ingrese el código de la canción que desea eliminar");
                int cancionSeleccionada = ingresarNumero();
                quitarCancion(cancionSeleccionada - 1);
                break;
            case 3:
                mostrarCanciones();
                break;
            case 4:
                System.out.println("Ingrese el código de la canción a la que pertenece la letra");
                int letraSeleccionada = ingresarNumero();
                mostrarLetra(letraSeleccionada);
                break;
            case 5:
                System.out.println("Usted ha salido del sistema");
                indicador = false;
                break;
            default:
                System.out.println("Usted ha ingresado una alternativa incorrecta, por favor inténtelo de nuevo.");
                break;
        }
        return indicador;
    }

    /*
    Se encarga ejecutar de manera correcta los métodos principales que contienen a los demás métodos.
    @param void.
    @return void.
     */
    public void iniciar() {
        boolean indicador = true;
        while (indicador) {
            mostrarMenu();
            int eleccion = ingresarNumero();
            indicador = ejecutarFuncionalidad(eleccion);
        }
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }
}

package GestorArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ManejoArchivos {

    private ArrayList<String> lineas;
    private ArrayList<String[]> datos;
    private String[] letras;

    public ManejoArchivos() {

    }

    public ManejoArchivos(String ubicacionArchivo) throws IOException {
        datos = new ArrayList<String[]>();
        lineas = new ArrayList<String>();
        letras = new String[4];
        if (almacenarLineas(ubicacionArchivo)) {;
            asignarDatos();
            almacenarLetras();
        }
    }

    /*
    Almacena cada linea del texto en una variable ArrayList<String>.
    @param direccion relativa del archivo de texto que contiene la lista de canciones (String).
    @return valor que indica si el archivo ingresado existe o no (boolean).
     */
    public boolean almacenarLineas(String ubicacionArchivo) throws FileNotFoundException, IOException {
        String[] texto_a_ignorar = {""};
        File file = new File(ubicacionArchivo);
        String cadena;
        if (file.exists()) {
            FileReader fr = new FileReader(ubicacionArchivo);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            while ((cadena = br.readLine()) != null) {
                for (String texto : texto_a_ignorar) {
                    if (cadena.contains(texto)) {
                        cadena = cadena.replaceAll(texto, "");
                    }
                }
                if (!cadena.isEmpty()) {
                    lineas.add(cadena);
                }
            }
            fr.close();
            br.close();
            return true;
        } else {
            return false;
        }
    }

    /*
    Asigna a un atributo los datos ya depurados de cada línea de texto,
    param void.
    return void.
     */
    public void asignarDatos() {
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            datos.add(depurarLineas(contador));
            contador = contador + 4;
        }
    }

    /*
    Elimina los carácteres que no se necesitan en cada conjunto de líneas del archivo de texto.
    @param posición de cada línea en el texto (int).
    @return conjunto de líneas limpias (String[]).
     */
    public String[] depurarLineas(int posicionLinea) {
        String[] lineasDepuradas = new String[4];
        for (int j = 0; j < 4; j++) {
            try {
                lineasDepuradas[j] = lineas.get(posicionLinea).substring(establecerCaracteresInnecesarios(j));
                posicionLinea++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
                System.out.println("No es una posición válida la que se introdujo");
                String[] nada = new String[]{"nada", "nada", "nada", "nada"};
                return nada;
            }
        }
        return lineasDepuradas;
    }

    /*
    Establece los carácteres innecesarios al principio de cada línea no nula del texto que contiene la lista de canciones.
    @param valor que indica la posición que ocupa la línea de texto dentro del archivo.
    @return carácteres innecesarios para la línea de texto en cuestión.
     */
    public int establecerCaracteresInnecesarios(int posicionLinea) {
        int caracteres_a_eliminar = 0;
        switch (posicionLinea) {
            case 0:
                caracteres_a_eliminar = 9;
                break;
            case 1:
                caracteres_a_eliminar = 9;
                break;
            case 2:
                caracteres_a_eliminar = 8;
                break;
            case 3:
                caracteres_a_eliminar = 6;
                break;
            default:
                caracteres_a_eliminar = -1;
                break;
        }
        return caracteres_a_eliminar;
    }

    /*
    Almacena en un atributo todas las letras de canciones.
    @param void.
    @return void.
     */
    public void almacenarLetras() throws IOException {
        String[] archivosLetras = new String[4];
        archivosLetras[0] = "dreams.txt";
        archivosLetras[1] = "maquavelico.txt";
        archivosLetras[2] = "put_you_on_the_game.txt";
        archivosLetras[3] = "space_bound.txt";
        for (int i = 0; i < archivosLetras.length; i++) {
            almacenarLetra("src\\main\\java\\Archivos\\" + archivosLetras[i], i);
        }
    }

    /*
    Almacena la letra de una canción en la partición de un atributo.
    @param dirección relativa del archivo de texto que contiene la letra de la canción (String), posición del archivo de letra respecto a los demás archivos del mismo tipo (int).
    @return valor que indica la existencia del archivo pertenciente a la dirección ingresada (boolean).
     */
    public boolean almacenarLetra(String ubicacionLetra, int numeroLetra) throws FileNotFoundException, IOException {
        File file = new File(ubicacionLetra);
        if (file.exists()) {
            Path archivo = Paths.get(ubicacionLetra);
            String texto;
            texto = new String(Files.readAllBytes(archivo));
            try {
                letras[numeroLetra] = texto;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
                System.out.println("No es una posición válida la que se introdujo");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String[]> getLineas() {
        return datos;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }

    public ArrayList<String[]> getDatos() {
        return this.datos;
    }

    public void setDatos(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    public String[] getLetras() {
        return this.letras;
    }

    public void setLetras(String[] letras) {
        this.letras = letras;
    }
}

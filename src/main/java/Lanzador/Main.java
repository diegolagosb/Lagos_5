package Lanzador;

import Controlador.AdministradorCanciones;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AdministradorCanciones adm = new AdministradorCanciones("src\\main\\java\\Archivos\\info canciones.txt");
    }
}

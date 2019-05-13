
import GestorArchivos.ManejoArchivos;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ALEX
 */
public class testManejoArchivos {

    private ManejoArchivos man;
    private ManejoArchivos man2;

    @Before
    public void setUp() throws IOException {
        man = new ManejoArchivos();
        man2 = new ManejoArchivos("src\\main\\java\\Archivos\\info canciones.txt");
    }

    /*
   Se trata de almacenar las lineas de un texto que no existe.
     */
    @Test
    public void testAlmacenarLineas() throws IOException {
        boolean resultadoConseguido = man.almacenarLineas("nada.txt");
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado, resultadoConseguido);
    }

    /*
   Se trata de establecer carácteres innecesarios de una línea que no tiene sentido en el contexto del problema o que no existe en los datos ya almacenados.
     */
    @Test
    public void testEstablecerCaracteresInnecesarios() {
        int resultadoConseguido = man2.establecerCaracteresInnecesarios(-1);
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado, resultadoConseguido);
    }

    /*
   Se trata de almacenar la letra de un archivo que no existe en una posición válida.
     */
    @Test
    public void testAlmacenarLetra() throws IOException {
        boolean resultadoConseguido = man.almacenarLetra("no importa.txt", 0);
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado, resultadoConseguido);
    }

    /*
   Se trata de almacenar la letra de un archivo que si existe en una posición inválida para un arreglo, por lo cual también debería retornar false, ya que no se cumple la función del método.
     */
    @Test
    public void testAlmacenarLetra2() throws IOException {
        boolean resultadoConseguido = man2.almacenarLetra("src\\main\\java\\Archivos\\dreams.txt", -1);
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado, resultadoConseguido);
    }

    /*
   Se trata de depurar la línea situada en una posición no válida para cualquier arreglo.
     */
    @Test
    public void testDepurarLineas() {
        String[] resultadoConseguido = man2.depurarLineas(-1);
        String[] resultadoEsperado = new String[]{"nada", "nada", "nada", "nada"};
        assertEquals(resultadoEsperado, resultadoConseguido);
    }
}

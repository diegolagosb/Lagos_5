package Controlador;

public class Cancion {

    private int codigo;
    private String nombre;
    private String nombreInterprete;
    private String album;
    private String anoLanzamiento;
    private String letra;

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreInterprete() {
        return this.nombreInterprete;
    }

    public void setNombreInterprete(String nombreInterprete) {
        this.nombreInterprete = nombreInterprete;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLetra() {
        return this.letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Cancion(int codigo, String nombre, String nombreInterprete, String album, String anoLanzamiento, String letra) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreInterprete = nombreInterprete;
        this.album = album;
        this.anoLanzamiento = anoLanzamiento;
        this.letra = letra;
    }

    public String getAnoLanzamiento() {
        return this.anoLanzamiento;
    }

    public void setAnoLanzamiento(String anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }
}

package modelo;
import java.util.ArrayList;

public class Ciudad {

    private String codigo;
    private String nombre;
    private boolean habilitada;
    private Pais pais;
    private Aerolinea aerolinea;
    private ArrayList<Ruta> rutasSalida;
    private ArrayList<Ruta> rutasLlegada;

    public Ciudad() {
    }

    public Ciudad(String codigo, String nombre, boolean habilitada, Pais pais, Aerolinea aerolinea,
                  ArrayList<Ruta> rutasSalida, ArrayList<Ruta> rutasLlegada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.habilitada = habilitada;
        this.pais = pais;
        this.aerolinea = aerolinea;
        this.rutasSalida = rutasSalida;
        this.rutasLlegada = rutasLlegada;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public ArrayList<Ruta> getRutasSalida() {
        return rutasSalida;
    }

    public ArrayList<Ruta> getRutasLlegada() {
        return rutasLlegada;
    }

    public void habilitar() {
        //Falta por implementar 
    }

    public void agregarRutaSalida(Ruta ruta) {
        //Falta por implementar 
    }

    public void agregarRutaLlegada(Ruta ruta) {
        //Falta por implementar 
    }

    public static boolean esCodigoValido(String codigo) {
        //Falta por implementar 
        return false;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}

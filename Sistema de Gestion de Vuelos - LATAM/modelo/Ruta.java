package modelo;
import java.util.ArrayList;

public class Ruta {

    private int duracionEstimada;
    private int topeTarifario;
    private ArrayList<Vuelo> vuelos;
    private Ciudad ciudadSalida;
    private Ciudad ciudadLlegada;

    public Ruta() {
    }

    public Ruta(int duracionEstimada, int topeTarifario, ArrayList<Vuelo> vuelos, Ciudad ciudadSalida,
                Ciudad ciudadLlegada) {
        this.duracionEstimada = duracionEstimada;
        this.topeTarifario = topeTarifario;
        this.vuelos = vuelos;
        this.ciudadSalida = ciudadSalida;
        this.ciudadLlegada = ciudadLlegada;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public int getTopeTarifario() {
        return topeTarifario;
    }

    public void setTopeTarifario(int topeTarifario) {
        this.topeTarifario = topeTarifario;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public Ciudad getCiudadSalida() {
        return ciudadSalida;
    }

    public Ciudad getCiudadLlegada() {
        return ciudadLlegada;
    }

    public boolean esValida() {
        //Falta por implementar 
        return false;
    }

    public boolean precioBaseValido(int precioBase) {
        //Falta por implementar 
        return false;
    }

    public void agregarVuelo(Vuelo vuelo) {
        //Falta por implementar 
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
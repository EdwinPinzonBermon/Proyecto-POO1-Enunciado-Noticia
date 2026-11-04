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
        if (ciudadSalida == null || ciudadLlegada == null) {
            return false;
        }
        if (ciudadSalida.getCodigo().equals(ciudadLlegada.getCodigo())) {
            return false;
        }
        if (!ciudadSalida.isHabilitada() || !ciudadLlegada.isHabilitada()) {
            return false;
        }
        return duracionEstimada > 0 && topeTarifario > 0;
    }

    public boolean precioBaseValido(int precioBase) {
        return precioBase > 0 && precioBase <= topeTarifario;
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
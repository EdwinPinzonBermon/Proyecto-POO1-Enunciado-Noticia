package modelo;
import java.util.ArrayList;

public class Pasajero extends Persona {

    private ArrayList<Pasaje> pasajes;

    public Pasajero() {
    }

    public Pasajero(String id, String nombre, ArrayList<Pasaje> pasajes) {
        super(id, nombre);
        this.pasajes = pasajes;
    }

    public ArrayList<Pasaje> getPasajes() {
        return pasajes;
    }

    public void agregarPasaje(Pasaje pasaje) {
        pasajes.add(pasaje);
    }

    public boolean tienePasajeNoCancelado(Vuelo vuelo) {
        for (Pasaje pasaje : pasajes) {
            if (pasaje.getVuelo() == vuelo && !pasaje.estaCancelado()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
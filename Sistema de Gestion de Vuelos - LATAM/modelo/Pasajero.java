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
        //Falta por implementar 
    }

    public boolean tienePasajeNoCancelado(Vuelo vuelo) {
        //Falta por implementar 
        return false;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
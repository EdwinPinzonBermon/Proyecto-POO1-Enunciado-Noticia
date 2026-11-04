package modelo;
import java.util.ArrayList;

public class Tripulante extends Persona {

    private Rol rol;
    private Aerolinea aerolinea;
    private ArrayList<Vuelo> vuelos;

    public Tripulante() {
    }

    public Tripulante(String id, String nombre, Rol rol, Aerolinea aerolinea, ArrayList<Vuelo> vuelos) {
        super(id, nombre);
        this.rol = rol;
        this.aerolinea = aerolinea;
        this.vuelos = vuelos;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public boolean estaLibre() {
        return !tieneVueloActivo();
    }

    public boolean tieneVueloActivo() {
        for (int i = 0; i < vuelos.size(); i++) {
            if (vuelos.get(i).estaActivo()) {
                return true;
            }
        }
        return false;
    }

    public boolean asignarAVuelo(Vuelo vuelo) {
        if (vuelo == null || tieneVueloActivo()) {
            return false;
        }
        vuelos.add(vuelo);
        return true;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
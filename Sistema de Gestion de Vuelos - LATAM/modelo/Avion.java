package modelo;
import java.util.ArrayList;

public class Avion {

    private String matricula;
    private String modelo;
    private String color;
    private int capacidad;
    private int asientosPrioritarios;
    private ArrayList<Vuelo> vuelos;
    private Aerolinea aerolinea;

    public Avion() {
    }

    public Avion(String matricula, String modelo, String color, int capacidad, int asientosPrioritarios,
                 ArrayList<Vuelo> vuelos, Aerolinea aerolinea) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.capacidad = capacidad;
        this.asientosPrioritarios = asientosPrioritarios;
        this.vuelos = vuelos;
        this.aerolinea = aerolinea;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getAsientosPrioritarios() {
        return asientosPrioritarios;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
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

    public boolean cubreCapacidad(int capacidadVuelo) {
        return capacidad >= capacidadVuelo;
    }

    public boolean cubreCapacidadYPrioritarios(int capacidadVuelo, int prioritariosVuelo) {
        return cubreCapacidad(capacidadVuelo) && asientosPrioritarios >= prioritariosVuelo;
    }

    public boolean asignarAVuelo(Vuelo vuelo) {
        if (vuelo == null || !estaLibre()) {
            return false;
        }
        vuelos.add(vuelo);
        return true;
    }

    public static boolean esMatriculaValida(String matricula) {
        if (matricula == null) {
            return false;
        }
        return matricula.matches("HK-[0-9]{4}");
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}

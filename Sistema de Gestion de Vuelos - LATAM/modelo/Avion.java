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
        //Falta por implementar 
        return false;
    }

    public boolean tieneVueloActivo() {
        //Falta por implementar 
        return false;
    }

    public boolean cubreCapacidad(int capacidadVuelo) {
        //Falta por implementar 
        return false;
    }

    public boolean cubreCapacidadYPrioritarios(int capacidadVuelo, int prioritariosVuelo) {
        //Falta por implementar 
        return false;
    }

    public boolean asignarAVuelo(Vuelo vuelo) {
        //Falta por implementar 
        return false;
    }

    public static boolean esMatriculaValida(String matricula) {
        //Falta por implementar 
        return false;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}

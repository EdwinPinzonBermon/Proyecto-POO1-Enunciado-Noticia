package modelo;
import java.util.ArrayList;

public class Aerolinea {

    private String nombre;
    private ArrayList<Avion> aviones;
    private ArrayList<Tripulante> tripulantes;
    private ArrayList<Pais> paises;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<Ruta> rutas;
    private ArrayList<Vuelo> vuelos;
    private ArrayList<Pasajero> pasajeros;

    public Aerolinea() {
    }

    public Aerolinea(String nombre, ArrayList<Avion> aviones, ArrayList<Tripulante> tripulantes,
                     ArrayList<Pais> paises, ArrayList<Ciudad> ciudades, ArrayList<Ruta> rutas,
                     ArrayList<Vuelo> vuelos, ArrayList<Pasajero> pasajeros) {
        this.nombre = nombre;
        this.aviones = aviones;
        this.tripulantes = tripulantes;
        this.paises = paises;
        this.ciudades = ciudades;
        this.rutas = rutas;
        this.vuelos = vuelos;
        this.pasajeros = pasajeros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(ArrayList<Avion> aviones) {
        this.aviones = aviones;
    }

    public ArrayList<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(ArrayList<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
    }

    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        this.rutas = rutas;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public boolean registrarPais(Pais pais) {
        //Falta por implementar 
        return false;
    }

    public boolean registrarCiudad(Ciudad ciudad) {
        //Falta por implementar 
        return false;
    }

    public boolean habilitarCiudad(String codigo) {
        //Falta por implementar 
        return false;
    }

    public boolean registrarRuta(Ciudad salida, Ciudad llegada, int duracion, int tope) {
        //Falta por implementar 
        return false;
    }

    public boolean registrarAvion(Avion avion) {
        //Falta por implementar 
        return false;
    }

    public boolean registrarTripulante(Tripulante tripulante, String codigoVuelo) {
        //Falta por implementar 
        return false;
    }

    public boolean asignarTripulanteAVuelo(String idTripulante, String codigoVuelo) {
        //Falta por implementar 
        return false;
    }

    public boolean registrarVuelo(Vuelo vuelo) {
        //Falta por implementar 
        return false;
    }

    public boolean registrarPasajero(Pasajero pasajero) {
        //Falta por implementar 
        return false;
    }

    public boolean registrarPasaje(String codigoVuelo, Pasajero pasajero, int numeroAsiento, ClasePasaje clase, int precioBase) {
        //Falta por implementar 
        return false;
    }

    public ArrayList<Avion> listarAviones() {
        //Falta por implementar 
        return null;
    }

    public ArrayList<Tripulante> listarTripulantes() {
        //Falta por implementar 
        return null;
    }

    public Vuelo buscarVuelo(String codigo) {
        //Falta por implementar 
        return null;
    }

    public Avion buscarAvion(String matricula) {
        //Falta por implementar 
        return null;
    }

    public Tripulante buscarTripulante(String id) {
        //Falta por implementar 
        return null;
    }

    public Pasajero buscarPasajero(String id) {
        //Falta por implementar 
        return null;
    }

    public Pasaje buscarPasaje(String codigoVuelo, int numeroAsiento) {
        //Falta por implementar 
        return null;
    }

    public Ciudad buscarCiudad(String codigo) {
        //Falta por implementar 
        return null;
    }

    public Ruta buscarRuta(Ciudad salida, Ciudad llegada) {
        //Falta por implementar 
        return null;
    }

    public ArrayList<Pasaje> listarPasajesDePasajero(String id) {
        //Falta por implementar 
        return null;
    }

    public ArrayList<Pasaje> listarPasajesDeVuelo(String codigoVuelo) {
        //Falta por implementar 
        return null;
    }

    public ArrayList<Tripulante> listarTripulacionDeVuelo(String codigoVuelo) {
        //Falta por implementar 
        return null;
    }

    public boolean asignarAvionAVuelo(String codigoVuelo, String matricula) {
        //Falta por implementar 
        return false;
    }

    public boolean actualizarEstadoVuelo(String codigoVuelo) {
        //Falta por implementar 
        return false;
    }

    public boolean actualizarEstadoEmbarque(String codigoVuelo) {
        //Falta por implementar 
        return false;
    }

    public boolean cancelarPasaje(String codigoVuelo, int numeroAsiento) {
        //Falta por implementar 
        return false;
    }

    public ArrayList<Vuelo> filtrarVuelosPorSalida(Ciudad ciudad) {
        //Falta por implementar 
        return null;
    }

    public ArrayList<Vuelo> filtrarVuelosPorLlegada(Ciudad ciudad) {
        //Falta por implementar 
        return null;
    }

    public ArrayList<Vuelo> filtrarVuelosPorAvion(Avion avion) {
        //Falta por implementar 
        return null;
    }

    public int consultarAsientosLibres(String codigoVuelo) {
        //Falta por implementar 
        return 0;
    }

    public int calcularOcupacionVuelo(String codigoVuelo) {
        //Falta por implementar 
        return 0;
    }

    public int calcularIngresoVuelo(String codigoVuelo) {
        //Falta por implementar 
        return 0;
    }

    public ArrayList<Vuelo> vuelosMayorOcupacion() {
        //Falta por implementar 
        return null;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }

}
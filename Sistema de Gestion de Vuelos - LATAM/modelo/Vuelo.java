package modelo;

import java.util.ArrayList;

public class Vuelo {

    private String codigo;
    private Fecha fechaSalida;
    private Hora horaSalida;
    private int capacidad;
    private EstadoVuelo estadoVuelo;
    private EstadoEmbarque estadoEmbarque;
    private Avion avion;
    private Ruta ruta;
    private ArrayList<Tripulante> tripulantes;
    private ArrayList<Pasaje> pasajes;

    public Vuelo() {
    }

    public Vuelo(String codigo, Fecha fechaSalida, Hora horaSalida, int capacidad, Avion avion, Ruta ruta,
                 ArrayList<Tripulante> tripulantes, ArrayList<Pasaje> pasajes) {
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.capacidad = capacidad;
        this.avion = avion;
        this.ruta = ruta;
        this.tripulantes = tripulantes;
        this.pasajes = pasajes;
        this.estadoVuelo = EstadoVuelo.DISPONIBLE;
        this.estadoEmbarque = EstadoEmbarque.SIN_EMBARQUE;
    }

    public String getCodigo() {
        return codigo;
    }

    public Fecha getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Fecha fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Hora getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Hora horaSalida) {
        this.horaSalida = horaSalida;
    }

    public FechaHora getFechaHoraSalida() {
        //Falta por implementar 
        return null;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public EstadoVuelo getEstadoVuelo() {
        return estadoVuelo;
    }

    public EstadoEmbarque getEstadoEmbarque() {
        return estadoEmbarque;
    }

    public Avion getAvion() {
        return avion;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public ArrayList<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public ArrayList<Pasaje> getPasajes() {
        return pasajes;
    }

    public boolean salidaNoEsAnteriorA(FechaHora momento) {
        //Falta por implementar 
        return false;
    }

    public int calcularDuracion() {
        //Falta por implementar 
        return 0;
    }

    public FechaHora calcularFechaHoraLlegada() {
        //Falta por implementar 
        return null;
    }

    public boolean estaActivo() {
        //Falta por implementar 
        return false;
    }

    public boolean admiteVentas() {
        //Falta por implementar 
        return false;
    }

    public int contarTripulantesPorRol(Rol rol) {
        //Falta por implementar 
        return 0;
    }

    public boolean tripulacionCompleta() {
        //Falta por implementar 
        return false;
    }

    public boolean puedeDespegar() {
        //Falta por implementar 
        return false;
    }

    public boolean avanzarEstadoVuelo() {
        //Falta por implementar 
        return false;
    }

    public boolean avanzarEstadoEmbarque() {
        //Falta por implementar 
        return false;
    }

    public boolean asignarAvion(Avion avion) {
        //Falta por implementar 
        return false;
    }

    public boolean agregarTripulante(Tripulante tripulante) {
        //Falta por implementar 
        return false;
    }

    public boolean agregarPasaje(Pasaje pasaje) {
        //Falta por implementar 
        return false;
    }

    public boolean asientoOcupado(int numeroAsiento) {
        //Falta por implementar 
        return false;
    }

    public Pasaje buscarPasajePorAsiento(int numeroAsiento) {
        //Falta por implementar 
        return null;
    }

    public int contarPasajesNoCancelados() {
        //Falta por implementar 
        return 0;
    }

    public int contarPasajesPrioritarios() {
        //Falta por implementar 
        return 0;
    }

    public int asientosLibres() {
        //Falta por implementar 
        return 0;
    }

    public int calcularOcupacion() {
        //Falta por implementar 
        return 0;
    }

    public int calcularIngresoConfirmado() {
        //Falta por implementar 
        return 0;
    }

    private boolean haDespegado() {
        //Falta por implementar 
        return false;
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
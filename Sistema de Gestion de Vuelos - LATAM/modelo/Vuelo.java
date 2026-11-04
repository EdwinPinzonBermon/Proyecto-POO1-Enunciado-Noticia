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
        return !getFechaHoraSalida().esAnteriorA(momento);
    }

    public int calcularDuracion() {
        return ruta.getDuracionEstimada();
    }

    public FechaHora calcularFechaHoraLlegada() {
        return getFechaHoraSalida().sumarMinutos(calcularDuracion());
    }

    public boolean estaActivo() {
        return estadoVuelo != EstadoVuelo.EN_EL_DESTINO;
    }

    public boolean admiteVentas() {
        return estadoVuelo == EstadoVuelo.DISPONIBLE || estadoVuelo == EstadoVuelo.ABORDANDO;
    }

    public int contarTripulantesPorRol(Rol rol) {
        int cantidad = 0;
        for (int i = 0; i < tripulantes.size(); i++) {
            if (tripulantes.get(i).getRol() == rol) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public boolean tripulacionCompleta() {
        return contarTripulantesPorRol(Rol.CAPITAN) == 1
            && contarTripulantesPorRol(Rol.COPILOTO) == 1
            && contarTripulantesPorRol(Rol.AZAFATA) >= 1;
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
        if (avion == null || haDespegado()) {
            return false;
        }
        if (!avion.estaLibre()) {
            return false;
        }
        if (!avion.cubreCapacidadYPrioritarios(capacidad, contarPasajesPrioritarios())) {
            return false;
        }
        avion.asignarAVuelo(this);
        if (this.avion != null) {
            this.avion.getVuelos().remove(this);
        }
        this.avion = avion;
        return true;
    }

    public boolean agregarTripulante(Tripulante tripulante) {
        if (tripulante == null || haDespegado() || tripulantes.contains(tripulante)) {
            return false;
        }
        if (tripulante.getRol() == Rol.CAPITAN && contarTripulantesPorRol(Rol.CAPITAN) == 1) {
            return false;
        }
        if (tripulante.getRol() == Rol.COPILOTO && contarTripulantesPorRol(Rol.COPILOTO) == 1) {
            return false;
        }
        if (!tripulante.asignarAVuelo(this)) {
            return false;
        }
        tripulantes.add(tripulante);
        return true;
    }

    public boolean agregarPasaje(Pasaje pasaje) {
        if (pasaje == null || !admiteVentas()) {
            return false;
        }
        if (pasaje.getNumeroAsiento() < 1 || pasaje.getNumeroAsiento() > capacidad) {
            return false;
        }
        if (asientoOcupado(pasaje.getNumeroAsiento())) {
            return false;
        }
        if (pasaje.getPasajero().tienePasajeNoCancelado(this)) {
            return false;
        }
        if (!ruta.precioBaseValido(pasaje.getPrecioBase())) {
            return false;
        }
        if (pasaje.getClase() == ClasePasaje.PRIORITARIA) {
            if (avion == null || contarPasajesPrioritarios() >= avion.getAsientosPrioritarios()) {
                return false;
            }
        }
        pasajes.add(pasaje);
        return true;
    }

    public boolean asientoOcupado(int numeroAsiento) {
        return buscarPasajePorAsiento(numeroAsiento) != null;
    }

    public Pasaje buscarPasajePorAsiento(int numeroAsiento) {
        for (int i = 0; i < pasajes.size(); i++) {
            Pasaje pasaje = pasajes.get(i);
            if (pasaje.getNumeroAsiento() == numeroAsiento && !pasaje.estaCancelado()) {
                return pasaje;
            }
        }
        return null;
    }

    public int contarPasajesNoCancelados() {
        int cantidad = 0;
        for (int i = 0; i < pasajes.size(); i++) {
            if (!pasajes.get(i).estaCancelado()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int contarPasajesPrioritarios() {
        int cantidad = 0;
        for (int i = 0; i < pasajes.size(); i++) {
            Pasaje pasaje = pasajes.get(i);
            if (!pasaje.estaCancelado() && pasaje.getClase() == ClasePasaje.PRIORITARIA) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int asientosLibres() {
        return capacidad - contarPasajesNoCancelados();
    }

    public int calcularOcupacion() {
        if (capacidad <= 0) {
            return 0;
        }
        return contarPasajesNoCancelados() * 100 / capacidad;
    }

    public int calcularIngresoConfirmado() {
        int ingreso = 0;
        for (int i = 0; i < pasajes.size(); i++) {
            Pasaje pasaje = pasajes.get(i);
            if (!pasaje.estaCancelado()) {
                ingreso += pasaje.calcularPrecioFinal();
            }
        }
        return ingreso;
    }

    private boolean haDespegado() {
        return estadoVuelo.ordinal() >= EstadoVuelo.DESPEGUE.ordinal();
    }

    public static boolean esCodigoValido(String codigo) {
        if (codigo == null) {
            return false;
        }
        return codigo.matches("VUE-[0-9]{3}");
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
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
        if (pais == null || pais.getCodigo() == null || pais.getCodigo().trim().equals("")) {
            return false;
        }
        if (pais.getNombre() == null || pais.getNombre().trim().equals("")) {
            return false;
        }
        for (Pais p : paises) {
            if (p.getCodigo().equalsIgnoreCase(pais.getCodigo())) {
                return false;
            }
        }
        paises.add(pais);
        return true;
    }

    public boolean registrarCiudad(Ciudad ciudad) {
        if (ciudad == null || !Ciudad.esCodigoValido(ciudad.getCodigo())) {
            return false;
        }
        if (ciudad.getNombre() == null || ciudad.getNombre().trim().equals("")) {
            return false;
        }
        if (ciudad.getPais() == null || buscarCiudad(ciudad.getCodigo()) != null) {
            return false;
        }
        if (!ciudad.getPais().agregarCiudad(ciudad)) {
            return false;
        }
        ciudades.add(ciudad);
        return true;
    }

    public boolean habilitarCiudad(String codigo) {
        Ciudad ciudad = buscarCiudad(codigo);
        if (ciudad == null || ciudad.isHabilitada()) {
            return false;
        }
        ciudad.habilitar();
        return true;
    }

    public boolean registrarRuta(Ciudad salida, Ciudad llegada, int duracion, int tope) {
        if (salida == null || llegada == null || buscarRuta(salida, llegada) != null) {
            return false;
        }
        Ruta ruta = new Ruta(duracion, tope, new ArrayList<Vuelo>(), salida, llegada);
        if (!ruta.esValida()) {
            return false;
        }
        rutas.add(ruta);
        salida.agregarRutaSalida(ruta);
        llegada.agregarRutaLlegada(ruta);
        return true;
    }

    public boolean registrarAvion(Avion avion) {
        if (avion == null || !Avion.esMatriculaValida(avion.getMatricula())) {
            return false;
        }
        if (buscarAvion(avion.getMatricula()) != null) {
            return false;
        }
        if (avion.getCapacidad() <= 0) {
            return false;
        }
        if (avion.getAsientosPrioritarios() < 0 || avion.getAsientosPrioritarios() > avion.getCapacidad()) {
            return false;
        }
        aviones.add(avion);
        return true;
    }

    public boolean registrarTripulante(Tripulante tripulante, String codigoVuelo) {
        if (tripulante == null || !Persona.esIdValido(tripulante.getId())) {
            return false;
        }
        if (tripulante.getNombre() == null || tripulante.getNombre().trim().equals("")) {
            return false;
        }
        if (tripulante.getRol() == null || buscarTripulante(tripulante.getId()) != null) {
            return false;
        }
        if (codigoVuelo != null) {
            Vuelo vuelo = buscarVuelo(codigoVuelo);
            if (vuelo == null || !vuelo.agregarTripulante(tripulante)) {
                return false;
            }
        }
        tripulantes.add(tripulante);
        return true;
    }

    public boolean asignarTripulanteAVuelo(String idTripulante, String codigoVuelo) {
        Tripulante tripulante = buscarTripulante(idTripulante);
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (tripulante == null || vuelo == null) {
            return false;
        }
        return vuelo.agregarTripulante(tripulante);
    }

    public boolean registrarVuelo(Vuelo vuelo) {
        if (vuelo == null || !Vuelo.esCodigoValido(vuelo.getCodigo())) {
            return false;
        }
        if (buscarVuelo(vuelo.getCodigo()) != null || vuelo.getCapacidad() <= 0) {
            return false;
        }
        if (vuelo.getFechaSalida() == null || vuelo.getHoraSalida() == null) {
            return false;
        }
        if (!vuelo.getFechaSalida().esValida() || !vuelo.getHoraSalida().esValida()) {
            return false;
        }
        if (!vuelo.salidaNoEsAnteriorA(FechaHora.ahora())) {
            return false;
        }
        Ruta ruta = vuelo.getRuta();
        if (ruta == null || buscarRuta(ruta.getCiudadSalida(), ruta.getCiudadLlegada()) != ruta) {
            return false;
        }
        Avion avion = vuelo.getAvion();
        if (avion != null) {
            if (buscarAvion(avion.getMatricula()) != avion || !avion.cubreCapacidad(vuelo.getCapacidad())) {
                return false;
            }
            if (!avion.asignarAVuelo(vuelo)) {
                return false;
            }
        }
        vuelos.add(vuelo);
        ruta.agregarVuelo(vuelo);
        return true;
    }

    public boolean registrarPasajero(Pasajero pasajero) {
        if (pasajero == null || !Persona.esIdValido(pasajero.getId())) {
            return false;
        }
        if (pasajero.getNombre() == null || pasajero.getNombre().trim().equals("")) {
            return false;
        }
        if (buscarPasajero(pasajero.getId()) != null) {
            return false;
        }
        pasajeros.add(pasajero);
        return true;
    }

    public boolean registrarPasaje(String codigoVuelo, Pasajero pasajero, int numeroAsiento, ClasePasaje clase, int precioBase) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null || pasajero == null || clase == null) {
            return false;
        }
        Pasajero registrado = buscarPasajero(pasajero.getId());
        if (registrado == null) {
            return false;
        }
        Pasaje pasaje;
        if (clase == ClasePasaje.PRIORITARIA) {
            pasaje = new PasajePrioritario(numeroAsiento, precioBase, vuelo, registrado);
        } else {
            pasaje = new PasajeEconomico(numeroAsiento, precioBase, vuelo, registrado);
        }
        if (!vuelo.agregarPasaje(pasaje)) {
            return false;
        }
        registrado.agregarPasaje(pasaje);
        return true;
    }

    public ArrayList<Avion> listarAviones() {
        return aviones;
    }

    public ArrayList<Tripulante> listarTripulantes() {
        return tripulantes;
    }

    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getCodigo().equalsIgnoreCase(codigo)) {
                return vuelo;
            }
        }
        return null;
    }

    public Avion buscarAvion(String matricula) {
        for (Avion avion : aviones) {
            if (avion.getMatricula().equalsIgnoreCase(matricula)) {
                return avion;
            }
        }
        return null;
    }

    public Tripulante buscarTripulante(String id) {
        for (Tripulante tripulante : tripulantes) {
            if (tripulante.getId().equals(id)) {
                return tripulante;
            }
        }
        return null;
    }

    public Pasajero buscarPasajero(String id) {
        for (Pasajero pasajero : pasajeros) {
            if (pasajero.getId().equals(id)) {
                return pasajero;
            }
        }
        return null;
    }

    public Pasaje buscarPasaje(String codigoVuelo, int numeroAsiento) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return null;
        }
        return vuelo.buscarPasajePorAsiento(numeroAsiento);
    }

    public Ciudad buscarCiudad(String codigo) {
        for (Ciudad ciudad : ciudades) {
            if (ciudad.getCodigo().equalsIgnoreCase(codigo)) {
                return ciudad;
            }
        }
        return null;
    }

    public Ruta buscarRuta(Ciudad salida, Ciudad llegada) {
        for (Ruta ruta : rutas) {
            if (ruta.getCiudadSalida().getCodigo().equals(salida.getCodigo())
                && ruta.getCiudadLlegada().getCodigo().equals(llegada.getCodigo())) {
                return ruta;
            }
        }
        return null;
    }

    public ArrayList<Pasaje> listarPasajesDePasajero(String id) {
        Pasajero pasajero = buscarPasajero(id);
        if (pasajero == null) {
            return new ArrayList<Pasaje>();
        }
        return pasajero.getPasajes();
    }

    public ArrayList<Pasaje> listarPasajesDeVuelo(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return new ArrayList<Pasaje>();
        }
        return vuelo.getPasajes();
    }

    public ArrayList<Tripulante> listarTripulacionDeVuelo(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return new ArrayList<Tripulante>();
        }
        return vuelo.getTripulantes();
    }

    public boolean asignarAvionAVuelo(String codigoVuelo, String matricula) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        Avion avion = buscarAvion(matricula);
        if (vuelo == null || avion == null) {
            return false;
        }
        return vuelo.asignarAvion(avion);
    }

    public boolean actualizarEstadoVuelo(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return false;
        }
        return vuelo.avanzarEstadoVuelo();
    }

    public boolean actualizarEstadoEmbarque(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return false;
        }
        return vuelo.avanzarEstadoEmbarque();
    }

    public boolean cancelarPasaje(String codigoVuelo, int numeroAsiento) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return false;
        }
        if (vuelo.getEstadoVuelo() != EstadoVuelo.DISPONIBLE && vuelo.getEstadoVuelo() != EstadoVuelo.ABORDANDO) {
            return false;
        }
        Pasaje pasaje = vuelo.buscarPasajePorAsiento(numeroAsiento);
        if (pasaje == null) {
            return false;
        }
        return pasaje.cancelar();
    }

    public ArrayList<Vuelo> filtrarVuelosPorSalida(Ciudad ciudad) {
        ArrayList<Vuelo> resultado = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getRuta().getCiudadSalida().getCodigo().equalsIgnoreCase(ciudad.getCodigo())) {
                resultado.add(vuelo);
            }
        }
        return resultado;
    }

    public ArrayList<Vuelo> filtrarVuelosPorLlegada(Ciudad ciudad) {
        ArrayList<Vuelo> resultado = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getRuta().getCiudadLlegada().getCodigo().equalsIgnoreCase(ciudad.getCodigo())) {
                resultado.add(vuelo);
            }
        }
        return resultado;
    }

    public ArrayList<Vuelo> filtrarVuelosPorAvion(Avion avion) {
        ArrayList<Vuelo> resultado = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getAvion() != null && vuelo.getAvion().getMatricula().equalsIgnoreCase(avion.getMatricula())) {
                resultado.add(vuelo);
            }
        }
        return resultado;
    }

    public int consultarAsientosLibres(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return 0;
        }
        return vuelo.asientosLibres();
    }

    public int calcularOcupacionVuelo(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return 0;
        }
        return vuelo.calcularOcupacion();
    }

    public int calcularIngresoVuelo(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            return 0;
        }
        return vuelo.calcularIngresoConfirmado();
    }

    public ArrayList<Vuelo> vuelosMayorOcupacion() {
        ArrayList<Vuelo> resultado = new ArrayList<>();
        if (vuelos.isEmpty()) {
            return resultado;
        }
        int maxOcupacion = -1;
        for (Vuelo vuelo : vuelos) {
            int ocupacion = vuelo.calcularOcupacion();
            if (ocupacion > maxOcupacion) {
                maxOcupacion = ocupacion;
                resultado.clear();
                resultado.add(vuelo);
            } else if (ocupacion == maxOcupacion) {
                resultado.add(vuelo);
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "Aerolinea: " + nombre;
    }
}
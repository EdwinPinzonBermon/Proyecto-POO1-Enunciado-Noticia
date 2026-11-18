import vista.*;
import modelo.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

// Esta clase carga información de ejemplo para probar las funciones del sistema.
// Los datos son ficticios y se pueden eliminar para iniciar el programa vacío.
public class DatosPrueba
{
    private JFPrincipal vista;
    private Aerolinea modelo;

    public DatosPrueba(JFPrincipal vista, Aerolinea modelo){
        this.vista = vista;
        this.modelo = modelo;
    }

    public void cargar(){
        // -------------------- Países, ciudades y rutas --------------------
        Pais colombia = new Pais("CO", "Colombia", new ArrayList<Ciudad>());
        modelo.registrarPais(colombia);
        agregarCiudad("BOG", "Bogotá", colombia);
        agregarCiudad("MDE", "Medellín", colombia);
        agregarCiudad("PEI", "Pereira", colombia);
        agregarCiudad("ARM", "Armenia", colombia);

        agregarRuta("BOG", "PEI", 50, 228000);
        agregarRuta("MDE", "PEI", 45, 228000);
        agregarRuta("BOG", "ARM", 55, 234000);
        agregarRuta("PEI", "BOG", 50, 228000);

        // -------------------- Aviones --------------------
        agregarAvion("HK-1001", "Airbus A320", "Blanco", 180, 12);
        agregarAvion("HK-1002", "Airbus A320", "Azul", 180, 12);
        agregarAvion("HK-1003", "Boeing 787", "Rojo", 250, 20);
        agregarAvion("HK-1004", "Airbus A319", "Blanco", 140, 10);
        agregarAvion("HK-1005", "Airbus A320neo", "Gris", 186, 16);
        agregarAvion("HK-1006", "Airbus A321", "Azul", 220, 18);
        agregarAvion("HK-1007", "Boeing 787", "Blanco", 240, 20);
        agregarAvion("HK-1008", "Airbus A319", "Rojo", 140, 10);
        agregarAvion("HK-1009", "Airbus A320", "Negro", 180, 12);
        agregarAvion("HK-1010", "Airbus A321", "Blanco", 220, 18);

        // -------------------- Vuelos --------------------
        // Todos tienen fechas futuras respecto a noviembre de 2026.
        agregarVuelo("VUE-001", "BOG", "PEI", new Fecha(20, 12, 2026), new Hora(8, 30), 150, "HK-1001");
        agregarVuelo("VUE-002", "MDE", "PEI", new Fecha(20, 12, 2026), new Hora(10, 15), 120, "HK-1002");
        agregarVuelo("VUE-003", "PEI", "BOG", new Fecha(21, 12, 2026), new Hora(14, 0), 130, null);
        agregarVuelo("VUE-004", "BOG", "ARM", new Fecha(22, 12, 2026), new Hora(6, 45), 200, "HK-1003");
        agregarVuelo("VUE-005", "BOG", "PEI", new Fecha(23, 12, 2026), new Hora(9, 0), 160, "HK-1005");
        agregarVuelo("VUE-006", "MDE", "PEI", new Fecha(24, 12, 2026), new Hora(11, 20), 130, "HK-1006");
        agregarVuelo("VUE-007", "PEI", "BOG", new Fecha(25, 12, 2026), new Hora(15, 30), 120, "HK-1007");
        agregarVuelo("VUE-008", "BOG", "ARM", new Fecha(26, 12, 2026), new Hora(7, 10), 12, "HK-1008");

        // -------------------- Tripulantes --------------------
        // Los primeros vuelos quedan con tripulación asignada para probar consultas,
        // disponibilidad, asignaciones y las reglas necesarias antes del despegue.
        agregarTripulante("10000001", "Carlos Andrés Rojas", Rol.CAPITAN, "VUE-001");
        agregarTripulante("10000002", "María Fernanda López", Rol.COPILOTO, "VUE-001");
        agregarTripulante("10000003", "Laura Camila Pérez", Rol.AZAFATA, "VUE-001");
        agregarTripulante("10000004", "Jorge Iván Ramírez", Rol.AYUDANTE, "VUE-001");

        agregarTripulante("10000005", "Andrés Felipe Gómez", Rol.CAPITAN, "VUE-002");
        agregarTripulante("10000006", "Natalia Andrea Díaz", Rol.COPILOTO, "VUE-002");
        agregarTripulante("10000007", "Paula Andrea Ruiz", Rol.AZAFATA, "VUE-002");
        agregarTripulante("10000008", "Daniel Esteban Castro", Rol.AZAFATA, "VUE-002");

        agregarTripulante("10000009", "Santiago David Torres", Rol.CAPITAN, "VUE-003");
        agregarTripulante("10000010", "Juliana Marcela Ortiz", Rol.COPILOTO, "VUE-003");
        agregarTripulante("10000011", "Valentina Mora Sánchez", Rol.AZAFATA, "VUE-003");

        // VUE-004 queda sin azafata: sirve para comprobar que no puede despegar.
        agregarTripulante("10000012", "Felipe Santiago Vargas", Rol.CAPITAN, "VUE-004");
        agregarTripulante("10000013", "Daniela Carolina Silva", Rol.COPILOTO, "VUE-004");

        agregarTripulante("10000014", "Mateo Alejandro Herrera", Rol.CAPITAN, "VUE-005");
        agregarTripulante("10000015", "Isabella Rodríguez León", Rol.COPILOTO, "VUE-005");
        agregarTripulante("10000016", "Sofía Valentina Muñoz", Rol.AZAFATA, "VUE-005");

        agregarTripulante("10000017", "Samuel Nicolás Peña", Rol.CAPITAN, "VUE-006");
        agregarTripulante("10000018", "Mariana Cárdenas Gil", Rol.COPILOTO, "VUE-006");
        agregarTripulante("10000019", "Gabriela Suárez Ríos", Rol.AZAFATA, "VUE-006");

        agregarTripulante("10000020", "Tomás Eduardo Rincón", Rol.CAPITAN, "VUE-007");
        agregarTripulante("10000021", "Camila Alejandra Pardo", Rol.COPILOTO, "VUE-007");
        agregarTripulante("10000022", "Lucía Fernanda Reyes", Rol.AZAFATA, "VUE-007");

        agregarTripulante("10000023", "Nicolás Méndez", Rol.CAPITAN, "VUE-008");
        agregarTripulante("10000024", "Sara Martínez", Rol.COPILOTO, "VUE-008");
        agregarTripulante("10000025", "Emilia Navarro", Rol.AZAFATA, "VUE-008");

        // Estos tripulantes quedan libres para probar el registro y la asignación.
        agregarTripulante("10000026", "Miguel Ángel Castro", Rol.AYUDANTE, null);
        agregarTripulante("10000027", "Antonia Beltrán", Rol.AZAFATA, null);
        agregarTripulante("10000028", "Esteban Ospina", Rol.COPILOTO, null);

        // -------------------- Pasajeros y pasajes --------------------
        agregarPasajero("20000001", "Ana María González");
        agregarPasajero("20000002", "Luis Fernando Martínez");
        agregarPasajero("20000003", "Camila Torres Rodríguez");
        agregarPasajero("20000004", "David Alejandro Pérez");
        agregarPasajero("20000005", "Valeria Gómez Rojas");
        agregarPasajero("20000006", "Juan Sebastián Ramírez");
        agregarPasajero("20000007", "Isabella Castro Díaz");
        agregarPasajero("20000008", "Mateo López Herrera");
        agregarPasajero("20000009", "Mariana Silva Ortiz");
        agregarPasajero("20000010", "Samuel Rincón Peña");
        agregarPasajero("20000011", "Luciana Vargas Mora");
        agregarPasajero("20000012", "Nicolás Cárdenas Ruiz");
        agregarPasajero("20000013", "Gabriela Muñoz León");
        agregarPasajero("20000014", "Tomás Suárez Gil");
        agregarPasajero("20000015", "Paula Andrea Méndez");
        agregarPasajero("20000016", "Emilio Navarro Ríos");
        agregarPasajero("20000017", "Sara Beltrán Ospina");
        agregarPasajero("20000018", "Martín Pardo Reyes");
        agregarPasajero("20000019", "Daniela Pardo Méndez");

        // VUE-001: varios pasajes económicos y prioritarios; uno queda cancelado.
        agregarPasaje("VUE-001", "20000001", 1, ClasePasaje.ECONOMICA, 180000);
        agregarPasaje("VUE-001", "20000002", 2, ClasePasaje.ECONOMICA, 190000);
        agregarPasaje("VUE-001", "20000003", 3, ClasePasaje.PRIORITARIA, 200000);
        agregarPasaje("VUE-001", "20000004", 4, ClasePasaje.ECONOMICA, 210000);
        agregarPasaje("VUE-001", "20000005", 5, ClasePasaje.PRIORITARIA, 220000);
        agregarPasaje("VUE-001", "20000006", 6, ClasePasaje.ECONOMICA, 175000);
        agregarPasaje("VUE-001", "20000007", 7, ClasePasaje.ECONOMICA, 185000);
        agregarPasaje("VUE-001", "20000008", 8, ClasePasaje.ECONOMICA, 195000);
        agregarPasaje("VUE-001", "20000009", 9, ClasePasaje.ECONOMICA, 205000);
        agregarPasaje("VUE-001", "20000010", 10, ClasePasaje.ECONOMICA, 215000);
        agregarPasaje("VUE-001", "20000011", 11, ClasePasaje.ECONOMICA, 180000);
        agregarPasaje("VUE-001", "20000012", 12, ClasePasaje.ECONOMICA, 190000);
        agregarPasaje("VUE-001", "20000019", 13, ClasePasaje.ECONOMICA, 195000);

        // VUE-002 ya está abordando; se pueden seguir registrando pasajes.
        agregarPasaje("VUE-002", "20000013", 1, ClasePasaje.ECONOMICA, 170000);
        agregarPasaje("VUE-002", "20000014", 2, ClasePasaje.PRIORITARIA, 200000);
        agregarPasaje("VUE-002", "20000015", 3, ClasePasaje.ECONOMICA, 185000);
        agregarPasaje("VUE-002", "20000016", 4, ClasePasaje.ECONOMICA, 195000);

        agregarPasaje("VUE-003", "20000017", 1, ClasePasaje.ECONOMICA, 180000);
        agregarPasaje("VUE-003", "20000018", 2, ClasePasaje.ECONOMICA, 190000);
        agregarPasaje("VUE-005", "20000001", 1, ClasePasaje.ECONOMICA, 200000);
        agregarPasaje("VUE-005", "20000002", 2, ClasePasaje.PRIORITARIA, 210000);
        agregarPasaje("VUE-006", "20000003", 1, ClasePasaje.ECONOMICA, 190000);
        agregarPasaje("VUE-006", "20000004", 2, ClasePasaje.ECONOMICA, 200000);
        agregarPasaje("VUE-008", "20000005", 1, ClasePasaje.ECONOMICA, 180000);

        // Se cancela un pasaje de prueba para comprobar que el asiento se libera
        // y que el ingreso confirmado deja de contarlo.
        modelo.cancelarPasaje("VUE-001", 12);

        // VUE-001 y VUE-008 quedan con la misma ocupación máxima (8 %).
        // Esto permite comprobar que el ranking reporte todos los vuelos empatados.

        // -------------------- Estados de ejemplo --------------------
        // VUE-002 queda en ABORDANDO y con maletas DURANTE_EMBARQUE.
        modelo.actualizarEstadoVuelo("VUE-002");
        modelo.actualizarEstadoEmbarque("VUE-002");

        // VUE-005 queda EN_EL_AIRE y con maletas A_BORDO.
        modelo.actualizarEstadoVuelo("VUE-005");
        modelo.actualizarEstadoEmbarque("VUE-005");
        modelo.actualizarEstadoEmbarque("VUE-005");
        modelo.actualizarEstadoVuelo("VUE-005");
        modelo.actualizarEstadoVuelo("VUE-005");

        // VUE-006 queda en ATERRIZAJE y las maletas durante desembarque.
        modelo.actualizarEstadoVuelo("VUE-006");
        modelo.actualizarEstadoEmbarque("VUE-006");
        modelo.actualizarEstadoEmbarque("VUE-006");
        modelo.actualizarEstadoVuelo("VUE-006");
        modelo.actualizarEstadoVuelo("VUE-006");
        modelo.actualizarEstadoVuelo("VUE-006");
        modelo.actualizarEstadoEmbarque("VUE-006");

        // VUE-007 llega al destino y libera su avión y su tripulación.
        modelo.actualizarEstadoVuelo("VUE-007");
        modelo.actualizarEstadoEmbarque("VUE-007");
        modelo.actualizarEstadoEmbarque("VUE-007");
        modelo.actualizarEstadoVuelo("VUE-007");
        modelo.actualizarEstadoVuelo("VUE-007");
        modelo.actualizarEstadoVuelo("VUE-007");
        modelo.actualizarEstadoVuelo("VUE-007");
        modelo.actualizarEstadoEmbarque("VUE-007");

        // -------------------- Actualizar la interfaz --------------------
        refrescarTablaAviones();
        refrescarTablaVuelos();
        refrescarAvionesLibres();
        refrescarCombosAdicionales();
        refrescarTablaPasajes();
    }

    private void agregarCiudad(String iata, String nombre, Pais pais){
        Ciudad ciudad = new Ciudad(iata, nombre, true, pais, modelo,
                                   new ArrayList<Ruta>(), new ArrayList<Ruta>());
        if(modelo.registrarCiudad(ciudad)){
            JPRutasYCiudades panel = vista.getPanelRutasYCiudades();
            panel.getModeloTablaCiudad().addRow(new Object[]{iata, nombre, pais.getNombre(), pais.getCodigo(), "Habilitada"});
            panel.getCbCiudadesSalida().addItem(ciudad);
            panel.getCbCiudadesLlegada().addItem(ciudad);
            vista.getPanelAvionesYVuelos().getCbOrigen().addItem(ciudad);
            vista.getPanelAvionesYVuelos().getCbDestino().addItem(ciudad);
        }
    }

    private void agregarRuta(String iataSalida, String iataLlegada, int duracion, int tope){
        Ciudad salida = modelo.buscarCiudad(iataSalida);
        Ciudad llegada = modelo.buscarCiudad(iataLlegada);
        if(modelo.registrarRuta(salida, llegada, duracion, tope)){
            Ruta ruta = modelo.buscarRuta(salida, llegada);
            vista.getPanelRutasYCiudades().getModeloTablaRuta().addRow(
                new Object[]{salida.getNombre(), llegada.getNombre(), duracion, tope});
            vista.getPanelAvionesYVuelos().getCbRuta().addItem(ruta);
        }
    }

    private void agregarAvion(String matricula, String modeloAvion, String color, int capacidad, int prioritarios){
        Avion avion = new Avion(matricula, modeloAvion, color, capacidad, prioritarios,
                                new ArrayList<Vuelo>(), modelo);
        if(modelo.registrarAvion(avion)){
            vista.getPanelAvionesYVuelos().getCbAvion().addItem(avion);
        }
    }

    private void agregarVuelo(String codigo, String iataSalida, String iataLlegada, Fecha fecha, Hora hora, int capacidad, String matricula){
        Ruta ruta = modelo.buscarRuta(modelo.buscarCiudad(iataSalida), modelo.buscarCiudad(iataLlegada));
        Avion avion = null;
        if(matricula != null){
            avion = modelo.buscarAvion(matricula);
        }
        Vuelo vuelo = new Vuelo(codigo, fecha, hora, capacidad, avion, ruta,
                                new ArrayList<Tripulante>(), new ArrayList<Pasaje>());
        modelo.registrarVuelo(vuelo);
    }

    private void agregarTripulante(String id, String nombre, Rol rol, String codigoVuelo){
        Tripulante tripulante = new Tripulante(id, nombre, rol, modelo, new ArrayList<Vuelo>());
        if(modelo.registrarTripulante(tripulante, codigoVuelo)){
            // El combo de asignación solo muestra tripulantes que están libres.
            if(tripulante.estaLibre()){
                vista.getPanelTripulacion().getCbTripulante().addItem(tripulante);
            }
            String estado = "En operación";
            if(tripulante.estaLibre()){
                estado = "Libre";
            }
            String vueloActual = "-";
            for(int indice = 0; indice < tripulante.getVuelos().size(); indice++){
                Vuelo vuelo = tripulante.getVuelos().get(indice);
                if(vuelo.estaActivo()){
                    vueloActual = vuelo.getCodigo();
                }
            }
            vista.getPanelTripulacion().getModeloTablaTripulantes().addRow(
                new Object[]{tripulante.getId(), tripulante.getNombre(), tripulante.getRol(), estado, vueloActual});
        }
    }

    private void agregarPasajero(String id, String nombre){
        Pasajero pasajero = new Pasajero(id, nombre, new ArrayList<Pasaje>());
        modelo.registrarPasajero(pasajero);
    }

    private void agregarPasaje(String codigoVuelo, String idPasajero, int asiento, ClasePasaje clase, int precio){
        Pasajero pasajero = modelo.buscarPasajero(idPasajero);
        modelo.registrarPasaje(codigoVuelo, pasajero, asiento, clase, precio);
    }

    private void refrescarTablaAviones(){
        DefaultTableModel tabla = vista.getPanelAvionesYVuelos().getModeloAviones();
        tabla.setRowCount(0);
        for(int indice = 0; indice < modelo.getAviones().size(); indice++){
            Avion avion = modelo.getAviones().get(indice);
            String estado = "Libre";
            if(!avion.estaLibre()){
                estado = "En operación";
            }
            tabla.addRow(new Object[]{avion.getMatricula(), avion.getModelo(), avion.getColor(),
                    avion.getCapacidad(), avion.getAsientosPrioritarios(), estado});
        }
    }

    private void refrescarTablaVuelos(){
        DefaultTableModel tabla = vista.getPanelAvionesYVuelos().getModeloVuelos();
        tabla.setRowCount(0);
        for(int indice = 0; indice < modelo.getVuelos().size(); indice++){
            Vuelo vuelo = modelo.getVuelos().get(indice);
            String matricula = "Sin asignar";
            if(vuelo.getAvion() != null){
                matricula = vuelo.getAvion().getMatricula();
            }
            tabla.addRow(new Object[]{vuelo.getCodigo(), vuelo.getRuta().toString(),
                    vuelo.getFechaHoraSalida().toString(), vuelo.calcularFechaHoraLlegada().toString(),
                    matricula, vuelo.getCapacidad(), vuelo.calcularOcupacion() + "%",
                    vuelo.getEstadoVuelo(), vuelo.getEstadoEmbarque()});
        }
    }

    private void refrescarAvionesLibres(){
        JComboBox combo = vista.getPanelAvionesYVuelos().getCbAvionLibreOp();
        combo.removeAllItems();
        combo.addItem("Sin avión");
        JComboBox comboAsignar = vista.getPanelAvionesYVuelos().getCbAvionLibre();
        comboAsignar.removeAllItems();
        comboAsignar.addItem("Seleccione avión");
        for(int indice = 0; indice < modelo.getAviones().size(); indice++){
            Avion avion = modelo.getAviones().get(indice);
            if(avion.estaLibre()){
                combo.addItem(avion);
                comboAsignar.addItem(avion);
            }
        }
    }

    private void refrescarCombosAdicionales(){
        JPTripulacion panelTripulacion = vista.getPanelTripulacion();
        panelTripulacion.getCbVueloRegistro().removeAllItems();
        panelTripulacion.getCbVueloAsignar().removeAllItems();
        for(int indice = 0; indice < modelo.getVuelos().size(); indice++){
            Vuelo vuelo = modelo.getVuelos().get(indice);
            panelTripulacion.getCbVueloRegistro().addItem(vuelo);
            panelTripulacion.getCbVueloAsignar().addItem(vuelo);
        }
        panelTripulacion.getCbTripulante().removeAllItems();
        DefaultTableModel tablaTripulantes = panelTripulacion.getModeloTablaTripulantes();
        tablaTripulantes.setRowCount(0);
        for(int indice = 0; indice < modelo.getTripulantes().size(); indice++){
            Tripulante tripulante = modelo.getTripulantes().get(indice);
            if(tripulante.estaLibre()){
                panelTripulacion.getCbTripulante().addItem(tripulante);
            }
            String estado = "En operación";
            if(tripulante.estaLibre()){
                estado = "Libre";
            }
            String codigoVuelo = "-";
            for(int indiceVuelo = 0; indiceVuelo < tripulante.getVuelos().size(); indiceVuelo++){
                Vuelo vuelo = tripulante.getVuelos().get(indiceVuelo);
                if(vuelo.estaActivo()){
                    codigoVuelo = vuelo.getCodigo();
                }
            }
            tablaTripulantes.addRow(new Object[]{tripulante.getId(), tripulante.getNombre(),
                tripulante.getRol(), estado, codigoVuelo});
        }
    }

    private void refrescarTablaPasajes(){
        DefaultTableModel tabla = vista.getPanelPasajesYAsientos().getModeloPasajes();
        tabla.setRowCount(0);
        for(int indiceVuelo = 0; indiceVuelo < modelo.getVuelos().size(); indiceVuelo++){
            Vuelo vuelo = modelo.getVuelos().get(indiceVuelo);
            for(int indicePasaje = 0; indicePasaje < vuelo.getPasajes().size(); indicePasaje++){
                Pasaje pasaje = vuelo.getPasajes().get(indicePasaje);
                tabla.addRow(new Object[]{pasaje.getNumeroAsiento(), pasaje.getPasajero().getId(),
                    pasaje.getPasajero().getNombre(), pasaje.getClase(), pasaje.getPrecioBase(),
                    pasaje.calcularPrecioFinal(), pasaje.getEstado()});
            }
        }
    }
}

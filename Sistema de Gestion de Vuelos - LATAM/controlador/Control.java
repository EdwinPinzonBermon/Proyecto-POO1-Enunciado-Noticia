package controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import vista.*;
import modelo.*;
import java.util.ArrayList;

// Controlador principal: recibe las acciones de la vista y trabaja con el modelo.
// La vista solo muestra componentes; las validaciones y operaciones se hacen aquí.
public class Control implements ActionListener {
    private JFPrincipal vista;
    private Aerolinea modelo;
    private JPOpciones panelOpciones;

    public Control(JFPrincipal vista, Aerolinea modelo) {
        this.vista = vista;
        this.modelo = modelo;
        panelOpciones = vista.getPanelOpciones();

        // Paneles del programa
        panelOpciones.getBtnPanelControl().addActionListener(this);
        panelOpciones.getBtnRutasYCiudades().addActionListener(this);
        panelOpciones.getBtnAvionesYVuelos().addActionListener(this);
        panelOpciones.getBtnTripulacion().addActionListener(this);
        panelOpciones.getBtnPasajesYAsientos().addActionListener(this);
        panelOpciones.getBtnOcupacionEIngresos().addActionListener(this);

        // Acciones de rutas y ciudades
        vista.getPanelRutasYCiudades().getBtnRegistrarCiudad().addActionListener(this);
        vista.getPanelRutasYCiudades().getBtnRegistrarRuta().addActionListener(this);

        // Acciones de aviones y vuelos
        JPAvionesYVuelos pv = vista.getPanelAvionesYVuelos();
        pv.getBtnRegistrarAvion().addActionListener(this);
        pv.getBtnCrearVuelo().addActionListener(this);
        pv.getBtnFiltrar().addActionListener(this);
        pv.getBtnTodos().addActionListener(this);
        pv.getBtnAsignar().addActionListener(this);
        pv.getBtnSiguienteFase().addActionListener(this);
        pv.getBtnAvanzarEmbarque().addActionListener(this);
        pv.getBtnVerTripulacion().addActionListener(this);
        pv.getCbMostrar().addActionListener(this);
        pv.getBtnBuscarAvion().addActionListener(this);
        pv.getBtnBuscarVuelo().addActionListener(this);

        // Acciones de tripulación pasajes y metricas
        vista.getPanelTripulacion().getBtnRegistrarTripulante().addActionListener(this);
        vista.getPanelTripulacion().getBtnAsignarTripulante().addActionListener(this);
        vista.getPanelPasajesYAsientos().getCbVuelo().addActionListener(this);
        vista.getPanelOcupacionEIngresos().getCbVuelo().addActionListener(this);
        vista.getPanelPasajesYAsientos().getBtnConfirmarPago().addActionListener(this);
        vista.getPanelPasajesYAsientos().getBtnVerHistorial().addActionListener(this);
        vista.getPanelPasajesYAsientos().getBtnCancelarPasaje().addActionListener(this);
        vista.getPanelOcupacionEIngresos().getBtnCalcularMetricas().addActionListener(this);
        vista.getPanelOcupacionEIngresos().getBtnActualizarRanking().addActionListener(this);

        iniciarCombos();
        actualizarPanelControl();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object origen = evento.getSource();
        if (origen == panelOpciones.getBtnPanelControl()) {
            actualizarPanelControl(); vista.reemplazarPanel(vista.getPanelControl());
        } else if (origen == panelOpciones.getBtnRutasYCiudades()) {
            vista.reemplazarPanel(vista.getPanelRutasYCiudades());
        } else if (origen == panelOpciones.getBtnAvionesYVuelos()) {
            vista.reemplazarPanel(vista.getPanelAvionesYVuelos());
        } else if (origen == panelOpciones.getBtnTripulacion()) {
            actualizarTablaTripulantes(); actualizarCombosTripulacion();
            vista.reemplazarPanel(vista.getPanelTripulacion());
        } else if (origen == panelOpciones.getBtnPasajesYAsientos()) {
            actualizarSelectoresVuelos();
            mostrarHistorialPasajes(null);
            actualizarMapaAsientos();
            vista.reemplazarPanel(vista.getPanelPasajesYAsientos());
        } else if (origen == panelOpciones.getBtnOcupacionEIngresos()) {
            actualizarSelectoresVuelos();
            actualizarRanking();
            calcularMetricasSeleccionadas();
            vista.reemplazarPanel(vista.getPanelOcupacionEIngresos());
        } else if (origen == vista.getPanelRutasYCiudades().getBtnRegistrarCiudad()) {
            registrarCiudad();
        } else if (origen == vista.getPanelRutasYCiudades().getBtnRegistrarRuta()) {
            registrarRuta();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnRegistrarAvion()) {
            registrarAvion();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnCrearVuelo()) {
            crearVuelo();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnFiltrar()) {
            filtrar();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnTodos()) {
            mostrarTodos();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnAsignar()) {
            asignarAvion();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnSiguienteFase()) {
            siguienteFase();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnAvanzarEmbarque()) {
            avanzarEmbarque();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnVerTripulacion()) {
            verTripulacion();
        } else if (origen == vista.getPanelAvionesYVuelos().getCbMostrar()) {
            actualizarTablaAviones();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnBuscarAvion()) {
            buscarAvion();
        } else if (origen == vista.getPanelAvionesYVuelos().getBtnBuscarVuelo()) {
            buscarVuelo();
        } else if (origen == vista.getPanelTripulacion().getBtnRegistrarTripulante()) {
            registrarTripulante();
        } else if (origen == vista.getPanelTripulacion().getBtnAsignarTripulante()) {
            asignarTripulante();
        } else if (origen == vista.getPanelPasajesYAsientos().getCbVuelo()) {
            seleccionarVueloPasajes();
        } else if (origen == vista.getPanelOcupacionEIngresos().getCbVuelo()) {
            seleccionarVueloMetricas();
        } else if (origen == vista.getPanelPasajesYAsientos().getBtnConfirmarPago()) {
            registrarPasajePagado();
        } else if (origen == vista.getPanelPasajesYAsientos().getBtnVerHistorial()) {
            consultarHistorial();
        } else if (origen == vista.getPanelPasajesYAsientos().getBtnCancelarPasaje()) {
            cancelarPasaje();
        } else if (origen == vista.getPanelOcupacionEIngresos().getBtnCalcularMetricas()) {
            calcularMetricas();
        } else if (origen == vista.getPanelOcupacionEIngresos().getBtnActualizarRanking()) {
            actualizarRanking();
        }
    }

    // -------------------- Metodos para actualizar la vista

    private void iniciarCombos() {
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        panel.getCbOrigen().addItem("Todas");
        panel.getCbDestino().addItem("Todas");
        panel.getCbAvion().addItem("Todos");
        panel.getCbMostrar().addItem("Todos");
        panel.getCbMostrar().addItem("Libres");
        panel.getCbMostrar().addItem("En operación");
        
        for (int i = 0; i < modelo.getCiudades().size(); i++) {
            Ciudad ciudad = modelo.getCiudades().get(i);
            panel.getCbOrigen().addItem(ciudad);
            panel.getCbDestino().addItem(ciudad);
        }
        for (int i = 0; i < modelo.getRutas().size(); i++) {
            Ruta ruta = modelo.getRutas().get(i);
            panel.getCbRuta().addItem(ruta);
        }
        for (int i = 0; i < modelo.getAviones().size(); i++) {
            Avion avion = modelo.getAviones().get(i);
            panel.getCbAvion().addItem(avion);
        }
        
        actualizarAvionesLibres();
        actualizarCombosTripulacion();
        actualizarSelectoresVuelos();
    }

    private void actualizarAvionesLibres() {
        
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        panel.getCbAvionLibreOp().removeAllItems();
        panel.getCbAvionLibreOp().addItem("Sin avión");
        panel.getCbAvionLibre().removeAllItems();
        panel.getCbAvionLibre().addItem("Seleccione avión");
        
        for (int i = 0; i < modelo.getAviones().size(); i++) {
            Avion avion = modelo.getAviones().get(i);
            if (avion.estaLibre()) {
                panel.getCbAvionLibreOp().addItem(avion);
                panel.getCbAvionLibre().addItem(avion);
            }
        }
    }

    private void actualizarCombosTripulacion() {
        JPTripulacion panel = vista.getPanelTripulacion();
        panel.getCbVueloRegistro().removeAllItems();
        panel.getCbVueloRegistro().addItem("Sin asignar");
        panel.getCbVueloAsignar().removeAllItems();
        for (int i = 0; i < modelo.getVuelos().size(); i++) {
            Vuelo vuelo = modelo.getVuelos().get(i);
            panel.getCbVueloRegistro().addItem(vuelo);
            panel.getCbVueloAsignar().addItem(vuelo);
        }
        panel.getCbTripulante().removeAllItems();
        for (int i = 0; i < modelo.getTripulantes().size(); i++) {
            Tripulante tripulante = modelo.getTripulantes().get(i);
            if (tripulante.estaLibre()) {
                panel.getCbTripulante().addItem(tripulante);
            }
        }
    }

    private void actualizarTablaAviones() {
        DefaultTableModel tabla = vista.getPanelAvionesYVuelos().getModeloAviones();
        tabla.setRowCount(0);
        String filtro = "Todos";
        if (vista.getPanelAvionesYVuelos().getCbMostrar().getSelectedItem() != null) {
            filtro = vista.getPanelAvionesYVuelos().getCbMostrar().getSelectedItem().toString();
        }
        for (int i = 0; i < modelo.getAviones().size(); i++) {
            Avion avion = modelo.getAviones().get(i);
            String estado = "En operación";
            if (avion.estaLibre()) {
                estado = "Libre";
            }
            if (filtro.equals("Libres") && !avion.estaLibre()) {
                continue;
            }
            if (filtro.equals("En operación") && avion.estaLibre()) {
                continue;
            }
            tabla.addRow(new Object[]{avion.getMatricula(), avion.getModelo(), avion.getColor(),
                    avion.getCapacidad(), avion.getAsientosPrioritarios(), estado});
        }
    }

    private void mostrarVuelos(ArrayList<Vuelo> lista) {
        DefaultTableModel tabla = vista.getPanelAvionesYVuelos().getModeloVuelos();
        tabla.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            Vuelo vuelo = lista.get(i);
            String matricula = "Sin asignar";
            if (vuelo.getAvion() != null) {
                matricula = vuelo.getAvion().getMatricula();
            }
            tabla.addRow(new Object[]{vuelo.getCodigo(), vuelo.getRuta(), vuelo.getFechaHoraSalida(),
                    vuelo.calcularFechaHoraLlegada(), matricula, vuelo.getCapacidad(),
                    vuelo.calcularOcupacion() + "%", vuelo.getEstadoVuelo(), vuelo.getEstadoEmbarque()});
        }
    }

    private Vuelo vueloSeleccionado() {
        int fila = vista.getPanelAvionesYVuelos().getTablaVuelos().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un vuelo en la tabla.");
            return null;
        }
        String codigo = vista.getPanelAvionesYVuelos().getTablaVuelos().getValueAt(fila, 0).toString();
        return modelo.buscarVuelo(codigo);
    }

    private void refrescarDatosOperacion() {
        actualizarTablaAviones();
        mostrarVuelos(modelo.getVuelos());
        actualizarAvionesLibres();
        actualizarCombosTripulacion();
        actualizarSelectoresVuelos();
        actualizarRanking();
        actualizarPanelControl();
    }

    // -------------------- Rutas y ciudades

    private void registrarCiudad() {
        JPRutasYCiudades panel = vista.getPanelRutasYCiudades();
        String iata = panel.getTxtIata().getText().trim().toUpperCase();
        String nombre = panel.getTxtNombreCiudad().getText().trim();
        String codigoPais = panel.getTxtCodigoPais().getText().trim().toUpperCase();
        String nombrePais = panel.getTxtNombrePais().getText().trim();
        if (iata.equals("") || nombre.equals("") || codigoPais.equals("") || nombrePais.equals("")) {
            mensaje("Debe llenar todos los campos."); return;
        }
        if (!Ciudad.esCodigoValido(iata)) {
            mensaje("El código IATA debe tener 3 letras.");
            return;
        }
        if (!codigoPais.matches("[A-Z]{2}")) {
            mensaje("El código del país debe tener 2 letras (ejemplo: CO).");
            return;
        }

        Pais pais = null;
        for (int i = 0; i < modelo.getPaises().size(); i++) {
            Pais existente = modelo.getPaises().get(i);
            if (existente.getCodigo().equalsIgnoreCase(codigoPais)) {
                pais = existente;
                break;
            }
        }
        if (pais == null) {
            pais = new Pais(codigoPais, nombrePais, new ArrayList<Ciudad>());
        } else {
            if (!pais.getNombre().equalsIgnoreCase(nombrePais)) {
                mensaje("El código del país ya está registrado con otro nombre.");
                return;
            }
        }

        Ciudad ciudad = new Ciudad(iata, nombre, true, pais, modelo,
                new ArrayList<Ruta>(), new ArrayList<Ruta>());
        if (modelo.registrarCiudad(ciudad)) {
            if (!modelo.getPaises().contains(pais)) {
                modelo.registrarPais(pais);
            }
            panel.getModeloTablaCiudad().addRow(new Object[]{iata, nombre, pais.getNombre(), pais.getCodigo(), "Habilitada"});
            panel.getCbCiudadesSalida().addItem(ciudad); panel.getCbCiudadesLlegada().addItem(ciudad);
            vista.getPanelAvionesYVuelos().getCbOrigen().addItem(ciudad);
            vista.getPanelAvionesYVuelos().getCbDestino().addItem(ciudad);
            panel.limpiarCamposPanelRegistroCiudad(); mensaje("Ciudad registrada correctamente.");
        } else {
            mensaje("No se pudo registrar. Verifique que el código IATA no exista.");
        }
    }

    private void registrarRuta() {
        
        JPRutasYCiudades panel = vista.getPanelRutasYCiudades();
        Ciudad salida = (Ciudad) panel.getCbCiudadesSalida().getSelectedItem();
        Ciudad llegada = (Ciudad) panel.getCbCiudadesLlegada().getSelectedItem();
        if (salida == null || llegada == null) {
            mensaje("Primero registre las ciudades.");
            return;
        }
        int duracion, tope;
        try {
            duracion = Integer.parseInt(panel.getTxtDuracion().getText().trim());
            tope = Integer.parseInt(panel.getTxtTope().getText().trim());
        } catch (NumberFormatException e) { 
            mensaje("Duración y tope deben ser números enteros."); 
            return; 
        }
        if (modelo.registrarRuta(salida, llegada, duracion, tope)) {
            Ruta ruta = modelo.buscarRuta(salida, llegada);
            panel.getModeloTablaRuta().addRow(new Object[]{salida.getNombre(), llegada.getNombre(), duracion, tope});
            vista.getPanelAvionesYVuelos().getCbRuta().addItem(ruta);
            panel.limpiarCamposPanelRegistroRuta(); mensaje("Ruta registrada correctamente.");
        } else {
            mensaje("No se pudo registrar: las ciudades deben ser distintas, habilitadas y la ruta no debe existir.");
        }
    }

    // -------------------- Aviones y vuelos

    private void registrarAvion() {
        
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        String matricula = panel.getTxtMatricula().getText().trim().toUpperCase();
        String modeloAvion = panel.getTxtModelo().getText().trim();
        String color = panel.getTxtColor().getText().trim();
        if (modeloAvion.equals("") || color.equals("")) {
            mensaje("Debe llenar el modelo y el color.");
            return;
        }
        if (!Avion.esMatriculaValida(matricula)) {
            mensaje("La matrícula debe tener el formato HK-1234.");
            return;
        }
        int capacidad, prioritarios;
        try {
            capacidad = Integer.parseInt(panel.getTxtCapacidadTotal().getText().trim());
            prioritarios = Integer.parseInt(panel.getTxtAsientosPrioritarios().getText().trim());
        } catch (NumberFormatException e) { 
            mensaje("Capacidad y asientos prioritarios deben ser números enteros."); 
            return; 
        }
        Avion avion = new Avion(matricula, modeloAvion, color, capacidad, prioritarios,
                new ArrayList<Vuelo>(), modelo);
        if (modelo.registrarAvion(avion)) {
            panel.getTxtMatricula().setText("");
            panel.getTxtModelo().setText("");
            panel.getTxtColor().setText("");
            panel.getTxtCapacidadTotal().setText("");
            panel.getTxtAsientosPrioritarios().setText("");
            panel.getCbAvion().addItem(avion);
            refrescarDatosOperacion();
            mensaje("Avión registrado correctamente.");
        } else {
            mensaje("No se pudo registrar. Revise la matrícula, capacidad y asientos prioritarios.");
        }
    }

    private void crearVuelo() {
        
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        String codigo = panel.getTxtCodigo().getText().trim().toUpperCase();
        Ruta ruta = (Ruta) panel.getCbRuta().getSelectedItem();
        if (!Vuelo.esCodigoValido(codigo)) {
            mensaje("El código debe tener el formato VUE-123.");
            return;
        }
        if (ruta == null) {
            mensaje("Primero registre una ruta.");
            return;
        }
        String[] fechaTexto = panel.getTxtFecha().getText().trim().split("/");
        String[] horaTexto = panel.getTxtHora().getText().trim().split(":");
        if (fechaTexto.length != 3 || horaTexto.length != 2) {
            mensaje("Use fecha DD/MM/AAAA y hora HH:MM.");
            return;
        }
        Fecha fecha; Hora hora; int capacidad;
        try {
            fecha = new Fecha(Integer.parseInt(fechaTexto[0]), Integer.parseInt(fechaTexto[1]), Integer.parseInt(fechaTexto[2]));
            hora = new Hora(Integer.parseInt(horaTexto[0]), Integer.parseInt(horaTexto[1]));
            capacidad = Integer.parseInt(panel.getTxtCapacidad().getText().trim());
        } catch (NumberFormatException e) { mensaje("Fecha, hora y capacidad deben contener números válidos."); return; }
        if (!fecha.esValida() || !hora.esValida() || capacidad <= 0) {
            mensaje("Revise la fecha, hora y capacidad del vuelo.");
            return;
        }
        Avion avion = null;
        if (panel.getCbAvionLibreOp().getSelectedIndex() > 0) {
            avion = (Avion) panel.getCbAvionLibreOp().getSelectedItem();
        }
        Vuelo vuelo = new Vuelo(codigo, fecha, hora, capacidad, avion, ruta,
                new ArrayList<Tripulante>(), new ArrayList<Pasaje>());
        if (modelo.registrarVuelo(vuelo)) {
            panel.getTxtCodigo().setText("");
            panel.getTxtFecha().setText("");
            panel.getTxtHora().setText("");
            panel.getTxtCapacidad().setText("");
            refrescarDatosOperacion();
            mensaje("Vuelo creado correctamente.");
        } else {
            mensaje("No se pudo crear. Verifique código único, fecha futura y capacidad del avión.");
        }
    }

    private void filtrar() {
        
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        Ciudad origen = null;
        Ciudad destino = null;
        Avion avion = null;
        if (panel.getCbOrigen().getSelectedIndex() > 0) {
            origen = (Ciudad) panel.getCbOrigen().getSelectedItem();
        }
        if (panel.getCbDestino().getSelectedIndex() > 0) {
            destino = (Ciudad) panel.getCbDestino().getSelectedItem();
        }
        if (panel.getCbAvion().getSelectedIndex() > 0) {
            avion = (Avion) panel.getCbAvion().getSelectedItem();
        }
        
        ArrayList<Vuelo> resultado = new ArrayList<Vuelo>();
        for (int i = 0; i < modelo.getVuelos().size(); i++) {
            Vuelo vuelo = modelo.getVuelos().get(i);
            if (origen == null || vuelo.getRuta().getCiudadSalida() == origen) {
                if (destino == null || vuelo.getRuta().getCiudadLlegada() == destino) {
                    if (avion == null || vuelo.getAvion() == avion) {
                        resultado.add(vuelo);
                    }
                }
            }
        }
        mostrarVuelos(resultado);
    }

    private void mostrarTodos() {
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        panel.getCbOrigen().setSelectedIndex(0); panel.getCbDestino().setSelectedIndex(0); panel.getCbAvion().setSelectedIndex(0);
        mostrarVuelos(modelo.getVuelos());
    }

    // Busca un avión por la matrícula y lo muestra en la tabla
    private void buscarAvion() {
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        String matricula = panel.getTxtBuscarMatricula().getText().trim().toUpperCase();
        Avion avion = modelo.buscarAvion(matricula);
        if (avion == null) {
            mensaje("No existe un avión con esa matrícula.");
            return;
        }
        DefaultTableModel tabla = panel.getModeloAviones(); tabla.setRowCount(0);
        String estado = "En operación";
        if (avion.estaLibre()) {
            estado = "Libre";
        }
        tabla.addRow(new Object[]{avion.getMatricula(), avion.getModelo(), avion.getColor(),
                avion.getCapacidad(), avion.getAsientosPrioritarios(), estado});
    }

    // Busca un vuelo por el código y selecciona su fila en la tabla
    private void buscarVuelo() {
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        String codigo = panel.getTxtBuscarCodigoVuelo().getText().trim().toUpperCase();
        Vuelo vuelo = modelo.buscarVuelo(codigo);
        if (vuelo == null) {
            mensaje("No existe un vuelo con ese código.");
            return;
        }
        ArrayList<Vuelo> resultado = new ArrayList<Vuelo>(); resultado.add(vuelo);
        mostrarVuelos(resultado);
        panel.getTablaVuelos().setRowSelectionInterval(0, 0);
    }

    private void asignarAvion() {
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        JPAvionesYVuelos panel = vista.getPanelAvionesYVuelos();
        if (panel.getCbAvionLibre().getSelectedIndex() <= 0) {
            mensaje("Seleccione un avión libre.");
            return;
        }
        Avion avion = (Avion) panel.getCbAvionLibre().getSelectedItem();
        if (vuelo.asignarAvion(avion)) {
            refrescarDatosOperacion();
            mensaje("Avión asignado al vuelo " + vuelo.getCodigo() + ".");
        } else {
            mensaje("No se pudo asignar. Compruebe que el vuelo no haya despegado y que el avión cubra capacidad y asientos prioritarios.");
        }
    }

    private void siguienteFase() {
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        if (vuelo.avanzarEstadoVuelo()) {
            refrescarDatosOperacion();
            mensaje("Nuevo estado del vuelo: " + vuelo.getEstadoVuelo());
        } else {
            mensaje("No se puede avanzar el estado. Para despegar necesita avión, tripulación completa y maletas a bordo.");
        }
    }

    private void avanzarEmbarque() {
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        if (vuelo.avanzarEstadoEmbarque()) {
            mostrarVuelos(modelo.getVuelos());
            mensaje("Nuevo estado de maletas: " + vuelo.getEstadoEmbarque());
        } else {
            mensaje("No se puede cambiar el estado de maletas en la fase actual del vuelo.");
        }
    }

    private void verTripulacion() {
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        String texto = "Tripulación del vuelo " + vuelo.getCodigo() + ":\n\n";
        if (vuelo.getTripulantes().isEmpty()) {
            texto += "No hay tripulantes asignados.";
        }
        for (int i = 0; i < vuelo.getTripulantes().size(); i++) {
            Tripulante tripulante = vuelo.getTripulantes().get(i);
            texto += tripulante.getNombre() + " - " + tripulante.getId() + " - " + tripulante.getRol() + "\n";
        }
        JOptionPane.showMessageDialog(vista, texto, "Tripulación", JOptionPane.INFORMATION_MESSAGE);
    }

    // -------------------- Tripulacion

    private void registrarTripulante() {
        JPTripulacion panel = vista.getPanelTripulacion();
        String id = panel.getTxtId().getText().trim(); String nombre = panel.getTxtNombre().getText().trim();
        if (id.equals("") || nombre.equals("")) {
            mensaje("Complete identificación y nombre.");
            return;
        }
        Rol rol = Rol.valueOf(panel.getCbRol().getSelectedItem().toString());
        String codigoVuelo = null;
        if (panel.getCbVueloRegistro().getSelectedIndex() > 0) {
            Vuelo vuelo = (Vuelo) panel.getCbVueloRegistro().getSelectedItem(); codigoVuelo = vuelo.getCodigo();
        }
        Tripulante tripulante = new Tripulante(id, nombre, rol, modelo, new ArrayList<Vuelo>());
        if (modelo.registrarTripulante(tripulante, codigoVuelo)) {
            panel.getTxtId().setText(""); panel.getTxtNombre().setText("");
            actualizarTablaTripulantes(); actualizarCombosTripulacion(); refrescarDatosOperacion();
            mensaje("Tripulante registrado correctamente.");
        } else {
            mensaje("No se pudo registrar. Verifique identificación única, datos y disponibilidad del vuelo.");
        }
    }

    private void asignarTripulante() {
        
        JPTripulacion panel = vista.getPanelTripulacion();
        Tripulante tripulante = (Tripulante) panel.getCbTripulante().getSelectedItem();
        Vuelo vuelo = (Vuelo) panel.getCbVueloAsignar().getSelectedItem();
        if (tripulante == null || vuelo == null) {
            mensaje("Seleccione un tripulante y un vuelo.");
            return;
        }
        if (modelo.asignarTripulanteAVuelo(tripulante.getId(), vuelo.getCodigo())) {
            actualizarTablaTripulantes(); actualizarCombosTripulacion(); refrescarDatosOperacion();
            mensaje("Tripulante asignado correctamente.");
        } else {
            mensaje("No se pudo asignar. El vuelo pudo haber despegado, el tripulante estar ocupado o el rol obligatorio ya estar cubierto.");
        }
    }

    private void actualizarTablaTripulantes() {
        DefaultTableModel tabla = vista.getPanelTripulacion().getModeloTablaTripulantes(); tabla.setRowCount(0);
        for (int i = 0; i < modelo.getTripulantes().size(); i++) {
            Tripulante tripulante = modelo.getTripulantes().get(i);
            String estado = "Libre", vueloTexto = "-";
            if (!tripulante.estaLibre()) {
                estado = "En operación";
                for (int indiceVuelo = 0; indiceVuelo < tripulante.getVuelos().size(); indiceVuelo++) {
                    Vuelo vuelo = tripulante.getVuelos().get(indiceVuelo);
                    if (vuelo.estaActivo()) {
                        vueloTexto = vuelo.getCodigo();
                        break;
                    }
                }
            }
            tabla.addRow(new Object[]{tripulante.getId(), tripulante.getNombre(), tripulante.getRol(), estado, vueloTexto});
        }
    }

    private String codigoVueloPasajes() {
        Object seleccionado = vista.getPanelPasajesYAsientos().getCbVuelo().getSelectedItem();
        if (seleccionado instanceof Vuelo) {
            return ((Vuelo) seleccionado).getCodigo();
        }
        return "";
    }

    private String codigoVueloMetricas() {
        Object seleccionado = vista.getPanelOcupacionEIngresos().getCbVuelo().getSelectedItem();
        if (seleccionado instanceof Vuelo) {
            return ((Vuelo) seleccionado).getCodigo();
        }
        return "";
    }

    // -------------------- Pasajeros y pasajes

    // Registra un pasajero sin obligar a crear un pasaje en ese mismo momento
    private void registrarPasajero() {
        JPPasajesYAsientos panel = vista.getPanelPasajesYAsientos();
        String id = panel.getTxtIdPasajero().getText().trim();
        String nombre = panel.getTxtNombreCompleto().getText().trim();

        if (id.equals("")) {
            mensaje("Ingrese la identificación del pasajero.");
            return;
        }
        if (nombre.equals("")) {
            mensaje("Ingrese el nombre completo del pasajero.");
            return;
        }
        if (!Persona.esIdValido(id)) {
            mensaje("La identificación debe contener solamente números.");
            return;
        }
        if (modelo.buscarPasajero(id) != null) {
            mensaje("Ya existe un pasajero con esa identificación.");
            return;
        }

        Pasajero pasajero = new Pasajero(id, nombre, new ArrayList<Pasaje>());
        if (modelo.registrarPasajero(pasajero)) {
            mensaje("Pasajero registrado correctamente.");
        } else {
            mensaje("No se pudo registrar el pasajero. Revise los datos.");
        }
    }

    private void registrarPasajePagado() {
        
        JPPasajesYAsientos panel = vista.getPanelPasajesYAsientos();
        String codigo = codigoVueloPasajes();
        String id = panel.getTxtIdPasajero().getText().trim();
        String nombre = panel.getTxtNombreCompleto().getText().trim();
        int asiento, precio;
        
        try {
            asiento = Integer.parseInt(panel.getTxtAsiento().getText().trim());
            precio = Integer.parseInt(panel.getTxtPrecioBase().getText().trim());
        } catch (NumberFormatException e) { mensaje("Asiento y precio base deben ser números enteros."); return; }
        
        Vuelo vuelo = modelo.buscarVuelo(codigo);
        if (vuelo == null) {
            mensaje("No existe el vuelo indicado.");
            return;
        }
        if (!vuelo.admiteVentas()) {
            mensaje("Solo se registran pasajes en vuelos disponibles o abordando.");
            return;
        }
        if (id.equals("") || nombre.equals("")) {
            mensaje("Complete la identificación y el nombre del pasajero.");
            return;
        }
        if (asiento < 1 || asiento > vuelo.getCapacidad()) {
            mensaje("El asiento debe estar entre 1 y la capacidad del vuelo.");
            return;
        }
        if (precio <= 0 || !vuelo.getRuta().precioBaseValido(precio)) {
            mensaje("El precio base debe ser mayor que cero y no superar el tope de la ruta.");
            return;
        }

        Pasajero pasajero = modelo.buscarPasajero(id);
        if (pasajero == null) {
            pasajero = new Pasajero(id, nombre, new ArrayList<Pasaje>());
        } else {
            if (!pasajero.getNombre().equalsIgnoreCase(nombre)) {
                mensaje("La identificación ya pertenece a un pasajero con otro nombre.");
                return;
            }
        }
        ClasePasaje clase = ClasePasaje.valueOf(panel.getCbClase().getSelectedItem().toString());
        if (clase == ClasePasaje.PRIORITARIA && vuelo.getAvion() == null) {
            mensaje("Asigne un avión antes de registrar pasajes prioritarios.");
            return;
        }
        if (pasajero.tienePasajeNoCancelado(vuelo)) {
            mensaje("El pasajero ya tiene un pasaje activo para este vuelo.");
            return;
        }
        if (vuelo.asientoOcupado(asiento)) {
            mensaje("Ese asiento ya está ocupado.");
            return;
        }
        if (clase == ClasePasaje.PRIORITARIA && vuelo.contarPasajesPrioritarios() >= vuelo.getAvion().getAsientosPrioritarios()) {
            mensaje("No quedan asientos prioritarios disponibles en este avión.");
            return;
        }
        if (modelo.buscarPasajero(id) == null) {
            if (!modelo.registrarPasajero(pasajero)) {
                mensaje("No se pudo registrar el pasajero. Verifique su identificación.");
                return;
            }
        }
        
        // El modelo crea el pasaje directamente en estado PAGADO, el pago en efectivo ya fue recibido presencialmente
        if (modelo.registrarPasaje(codigo, pasajero, asiento, clase, precio)) {
            panel.getTxtIdPasajero().setText(""); panel.getTxtNombreCompleto().setText("");
            panel.getTxtAsiento().setText(""); panel.getTxtPrecioBase().setText("");
            mostrarHistorialPasajes(codigo); actualizarPanelControl();
            actualizarMapaAsientos();
            actualizarRanking();
            mensaje("Pasaje registrado como PAGADO. Precio final: $" + modelo.buscarPasaje(codigo, asiento).calcularPrecioFinal());
        } else {
            mensaje("No se pudo registrar el pasaje. Revise las reglas del vuelo.");
        }
    }

    // El botón original muestra los pasajes del ID escrito en el formulario.
    private void consultarHistorial() {
        
        JPPasajesYAsientos panel = vista.getPanelPasajesYAsientos();
        String id = panel.getTxtIdPasajero().getText().trim();
        
        if (id.equals("")) {
            mensaje("Escriba el ID del pasajero para consultar su historial.");
            return;
        }
        Pasajero pasajero = modelo.buscarPasajero(id);
        if (pasajero == null) {
            mensaje("No existe un pasajero con esa identificación.");
            return;
        }
        DefaultTableModel tabla = panel.getModeloPasajes();
        tabla.setRowCount(0);
        
        for (int i = 0; i < pasajero.getPasajes().size(); i++) {
            agregarFilaPasaje(tabla, pasajero.getPasajes().get(i));
        }
    }

    private void agregarFilaPasaje(DefaultTableModel tabla, Pasaje pasaje) {
        tabla.addRow(new Object[]{pasaje.getNumeroAsiento(), pasaje.getPasajero().getId(),
                pasaje.getPasajero().getNombre(), pasaje.getClase(), pasaje.getPrecioBase(),
                pasaje.calcularPrecioFinal(), pasaje.getEstado()});
    }

    private void mostrarHistorialPasajes(String codigoVuelo) {
        
        DefaultTableModel tabla = vista.getPanelPasajesYAsientos().getModeloPasajes(); tabla.setRowCount(0);
        for (int i = 0; i < modelo.getVuelos().size(); i++) {
            Vuelo vuelo = modelo.getVuelos().get(i);
            if (codigoVuelo != null && !vuelo.getCodigo().equalsIgnoreCase(codigoVuelo)) {
                continue;
            }
            for (int i2 = 0; i2 < vuelo.getPasajes().size(); i2++) {
                Pasaje pasaje = vuelo.getPasajes().get(i2);
                agregarFilaPasaje(tabla, pasaje);
            }
        }
    }

    private void cancelarPasaje() {
        
        JPPasajesYAsientos panel = vista.getPanelPasajesYAsientos();
        Vuelo vuelo = (Vuelo) panel.getCbVuelo().getSelectedItem();
        
        if (vuelo == null) {
            mensaje("Seleccione un vuelo.");
            return;
        }
        int fila = panel.getTablaPasajes().getSelectedRow();
        if (fila < 0) {
            mensaje("Seleccione el pasaje en la tabla para cancelarlo.");
            return;
        }
        
        int asiento = Integer.parseInt(panel.getTablaPasajes().getValueAt(fila, 0).toString());
        String id = panel.getTablaPasajes().getValueAt(fila, 1).toString();
        Pasaje pasaje = modelo.buscarPasaje(vuelo.getCodigo(), asiento);
        
        if (pasaje == null || !pasaje.getPasajero().getId().equals(id)) {
            mensaje("Seleccione un pasaje del vuelo actual. Puede actualizar la lista seleccionando el vuelo.");
            return;
        }
        if (modelo.cancelarPasaje(vuelo.getCodigo(), asiento)) {
            mostrarHistorialPasajes(vuelo.getCodigo());
            actualizarMapaAsientos();
            actualizarRanking();
            actualizarPanelControl();
            mensaje("Pasaje cancelado correctamente.");
        } else {
            mensaje("No es posible cancelar ese pasaje según su estado o el del vuelo.");
        }
    }

    // -------------------- Ocupacion e ingresos

    private void calcularMetricas() {
        
        JPOcupacionEIngresos panel = vista.getPanelOcupacionEIngresos();
        String codigo = codigoVueloMetricas();
        Vuelo vuelo = modelo.buscarVuelo(codigo);
        if (vuelo == null) {
            mensaje("No existe un vuelo con ese código.");
            return;
        }
        
        int pasajesPagados = 0;
        for (int indice = 0; indice < vuelo.getPasajes().size(); indice++) {
            Pasaje pasaje = vuelo.getPasajes().get(indice);
            if (pasaje.getEstado() == EstadoPasaje.PAGADO) {
                pasajesPagados++;
            }
        }
        
        panel.getLblOcupacionValor().setText(modelo.calcularOcupacionVuelo(codigo) + "%");
        panel.getLblIngresoValor().setText("$ " + modelo.calcularIngresoVuelo(codigo));
        panel.getLblAsientosValor().setText(pasajesPagados + " / " + vuelo.getCapacidad());
    }

    // Muestra todos los vuelos ordenados por ocupacion y los empates comparten puesto.
    private void actualizarRanking() {
        
        JPOcupacionEIngresos panel = vista.getPanelOcupacionEIngresos();
        DefaultTableModel tabla = panel.getModeloRanking();
        tabla.setRowCount(0);
        ArrayList<Vuelo> ordenados = new ArrayList<Vuelo>();
        
        for (int i = 0; i < modelo.getVuelos().size(); i++) {
            ordenados.add(modelo.getVuelos().get(i));
        }
        for (int i = 0; i < ordenados.size(); i++) {
            for (int j = i + 1; j < ordenados.size(); j++) {
                if (ordenados.get(j).calcularOcupacion() > ordenados.get(i).calcularOcupacion()) {
                    Vuelo temporal = ordenados.get(i);
                    ordenados.set(i, ordenados.get(j));
                    ordenados.set(j, temporal);
                }
            }
        }
        
        int puesto = 0;
        int ocupacionAnterior = -1;
        
        for (int i = 0; i < ordenados.size(); i++) {
            Vuelo vuelo = ordenados.get(i);
            if (vuelo.calcularOcupacion() != ocupacionAnterior) {
                puesto = i + 1;
                ocupacionAnterior = vuelo.calcularOcupacion();
            }
            String matricula = "Sin avión";
            if (vuelo.getAvion() != null) {
                matricula = vuelo.getAvion().getMatricula();
            }
            tabla.addRow(new Object[]{puesto, vuelo.getCodigo(), vuelo.getRuta(), matricula,
                vuelo.contarPasajesNoCancelados(), vuelo.getCapacidad(),
                vuelo.calcularOcupacion(), vuelo.calcularIngresoConfirmado()});
        }
        
        if (ordenados.isEmpty()) {
            panel.getLblMensajeRanking().setText("No hay vuelos registrados.");
        } else {
            panel.getLblMensajeRanking().setText("Mayor ocupación: " + ordenados.get(0).calcularOcupacion() + "% (empates comparten puesto)");
        }
    }

    // Recarga los selectores cuando cambian los vuelos
    private void actualizarSelectoresVuelos() {
        
        JPPasajesYAsientos pasajes = vista.getPanelPasajesYAsientos();
        JPOcupacionEIngresos metricas = vista.getPanelOcupacionEIngresos();
        String anteriorPasajes = codigoVueloPasajes();
        String anteriorMetricas = codigoVueloMetricas();
        pasajes.getCbVuelo().removeAllItems();
        metricas.getCbVuelo().removeAllItems();
        
        for (int i = 0; i < modelo.getVuelos().size(); i++) {
            Vuelo vuelo = modelo.getVuelos().get(i);
            pasajes.getCbVuelo().addItem(vuelo);
            metricas.getCbVuelo().addItem(vuelo);
        }
        for (int i = 0; i < pasajes.getCbVuelo().getItemCount(); i++) {
            Vuelo vuelo = (Vuelo) pasajes.getCbVuelo().getItemAt(i);
            if (vuelo.getCodigo().equalsIgnoreCase(anteriorPasajes)) {
                pasajes.getCbVuelo().setSelectedIndex(i);
            }
            if (vuelo.getCodigo().equalsIgnoreCase(anteriorMetricas)) {
                metricas.getCbVuelo().setSelectedIndex(i);
            }
        }
        
        pasajes.getLblMensajeVuelos().setVisible(modelo.getVuelos().isEmpty());
    }

    private void seleccionarVueloPasajes() {
        JPPasajesYAsientos panel = vista.getPanelPasajesYAsientos();
        if (panel.getCbVuelo().getSelectedItem() != null) {
            Vuelo vuelo = (Vuelo) panel.getCbVuelo().getSelectedItem();
            mostrarHistorialPasajes(vuelo.getCodigo());
        }
        actualizarMapaAsientos();
    }

    private void seleccionarVueloMetricas() {
        JPOcupacionEIngresos panel = vista.getPanelOcupacionEIngresos();
        if (panel.getCbVuelo().getSelectedItem() != null) {
            Vuelo vuelo = (Vuelo) panel.getCbVuelo().getSelectedItem();
            calcularMetricasSeleccionadas();
        }
    }

    private void calcularMetricasSeleccionadas() {
        
        JPOcupacionEIngresos panel = vista.getPanelOcupacionEIngresos();
        if (!codigoVueloMetricas().equals("")) {
            calcularMetricas();
        }
    }

    // Dibuja el mapa de asientos
    private void actualizarMapaAsientos() {
        
        JPPasajesYAsientos panel = vista.getPanelPasajesYAsientos();
        JPanel mapa = panel.getPanelMapaContenedor();
        mapa.removeAll();
        String codigo = codigoVueloPasajes();
        Vuelo vuelo = modelo.buscarVuelo(codigo);
        
        if (vuelo == null) {
            mapa.add(new JLabel("Seleccione un vuelo para ver los asientos."));
        } else {
            mapa.setLayout(new GridLayout(0, 6, 5, 5));
            int prioritarios = 0;
            if (vuelo.getAvion() != null) {
                prioritarios = vuelo.getAvion().getAsientosPrioritarios();
            }
            for (int i = 1; i <= vuelo.getCapacidad(); i++) {
                JLabel asiento = new JLabel(String.valueOf(i), SwingConstants.CENTER);
                asiento.setOpaque(true);
                asiento.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                if (vuelo.asientoOcupado(i)) {
                    asiento.setBackground(new Color(217, 24, 71));
                    asiento.setForeground(Color.WHITE);
                } else if (i <= prioritarios) {
                    asiento.setBackground(new Color(255, 235, 156));
                } else {
                    asiento.setBackground(new Color(214, 235, 252));
                }
                mapa.add(asiento);
            }
        }
        mapa.revalidate();
        mapa.repaint();
    }

    // -------------------- Resumen del panel principal

    private void actualizarPanelControl() {
        
        JPControl panel = vista.getPanelControl();
        int activos = 0, enAire = 0, asignados = 0, tripulantesActivos = 0, vuelosProgramados = 0;
        int libresAvion = 0, libresTripulantes = 0, pasajesPagados = 0, ingreso = 0, sumaOcupacion = 0;
        StringBuilder pendientes = new StringBuilder();
        StringBuilder flota = new StringBuilder();
        
        for (int i = 0; i < modelo.getVuelos().size(); i++) {
            Vuelo vuelo = modelo.getVuelos().get(i);
            if (vuelo.estaActivo()) {
                activos++;
                if (vuelo.getEstadoVuelo() == EstadoVuelo.EN_EL_AIRE) {
                    enAire++;
                } else {
                    pendientes.append(vuelo.getCodigo()).append(" - ").append(vuelo.getEstadoVuelo()).append("\n");
                }
            }
            if (vuelo.getEstadoVuelo() == EstadoVuelo.DISPONIBLE || vuelo.getEstadoVuelo() == EstadoVuelo.ABORDANDO) {
                vuelosProgramados++;
            }
            sumaOcupacion += vuelo.calcularOcupacion();
            for (int indicePasaje = 0; indicePasaje < vuelo.getPasajes().size(); indicePasaje++) {
                Pasaje pasaje = vuelo.getPasajes().get(indicePasaje);
                if (!pasaje.estaCancelado()) {
                    pasajesPagados++;
                    ingreso += pasaje.calcularPrecioFinal();
                }
            }
        }
        for (int i = 0; i < modelo.getAviones().size(); i++) {
            Avion avion = modelo.getAviones().get(i);
            if (avion.estaLibre()) {
                libresAvion++;
            }
            else {
                asignados++;
                flota.append(avion.getMatricula()).append(" - En operación\n");
            }
        }
        for (int i = 0; i < modelo.getTripulantes().size(); i++) {
            Tripulante tripulante = modelo.getTripulantes().get(i);
            if (tripulante.estaLibre()) {
                libresTripulantes++;
            } else {
                tripulantesActivos++;
            }
        }
        
        int ocupacionPromedio = 0;
        if (!modelo.getVuelos().isEmpty()) {
            ocupacionPromedio = sumaOcupacion / modelo.getVuelos().size();
        }
        
        panel.getLblVActivos().setText(String.valueOf(activos));
        panel.getLblSubVActivos().setText(enAire + " en aire - " + (activos - enAire) + " en otras fases");
        panel.getLblEstadoFLota().setText(String.valueOf(modelo.getAviones().size()));
        panel.getLblSubEstadoFLota().setText(asignados + " asignados - " + libresAvion + " libres");
        panel.getLblPilotosYTripulacion().setText(String.valueOf(modelo.getTripulantes().size()));
        panel.getLblSubPilotosYTripulacion().setText(tripulantesActivos + " activos - " + libresTripulantes + " libres");
        panel.getLblVProgramados().setText(String.valueOf(vuelosProgramados));
        panel.getLblSubVProgramados().setText("ocupación promedio " + ocupacionPromedio + "%");
        panel.getLblPasajesEIngresos().setText("$" + ingreso);
        panel.getLblSubPasajesEIngresos().setText(pasajesPagados + " pasajes confirmados");
        
        if (pendientes.length() == 0) {
            panel.getAreaIzquierda().setText("No hay vuelos activos pendientes.");
        } else {
            panel.getAreaIzquierda().setText(pendientes.toString());
        }
        if (flota.length() == 0) {
            panel.getAreaDerecha().setText("Todos los aviones están libres.");
        } else {
            panel.getAreaDerecha().setText(flota.toString());
        }
    }

    private void mensaje(String texto) {
        JOptionPane.showMessageDialog(vista, texto);
    }
}

package vista;

import modelo.Aerolinea;
import modelo.Avion;
import modelo.Ciudad;
import modelo.FechaHora;
import modelo.Ruta;
import modelo.Vuelo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JFPanelAvionesVuelos extends JPanel {

    private Aerolinea aerolinea;
    // true mientras se recargan los combos (evita que el ItemListener cambie cosas solo)
    private boolean actualizando;

    // --- FORMULARIO: REGISTRAR AVIÓN ---
    private JTextField txtMatricula;
    private JTextField txtModelo;
    private JTextField txtColor;
    private JTextField txtCapacidadTotal;
    private JTextField txtAsientosPrioritarios;
    private JButton btnRegistrarAvion;

    // --- FORMULARIO: PROGRAMAR VUELO ---
    private JTextField txtCodigoVuelo;
    private JComboBox<Ruta> cmbRuta;
    private JTextField txtFechaSalida;
    private JTextField txtHoraSalida;
    private JTextField txtCapacidadVuelo;
    private JComboBox<Avion> cmbAvionInicial; // solo aviones libres; null = sin avión
    private JButton btnCrearVuelo;

    // --- TABLA DE AVIONES ---
    private JComboBox<String> cmbFiltroDisponibilidad;
    private JTable tblAviones;
    private DefaultTableModel modeloTablaAviones;

    // --- FILTROS Y TABLA DE VUELOS ---
    private JComboBox<Ciudad> cmbFiltroOrigen;
    private JComboBox<Ciudad> cmbFiltroDestino;
    private JComboBox<Avion> cmbFiltroAvion;
    private JButton btnFiltrarVuelos;
    private JButton btnLimpiarFiltros;
    private JTable tblVuelos;
    private DefaultTableModel modeloTablaVuelos;

    // --- ACCIONES SOBRE EL VUELO SELECCIONADO ---
    private JComboBox<Avion> cmbAsignarCambiarAvion; // solo aviones libres
    private JButton btnAsignarAvion;
    private JButton btnVerTripulacionVuelo;
    private JButton btnAvanzarEstadoVuelo;

    public JFPanelAvionesVuelos(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
        setLayout(new BorderLayout(15, 0));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(243, 246, 251));

        add(crearColumnaFormularios(), BorderLayout.WEST);
        add(crearColumnaTablas(), BorderLayout.CENTER);
        actualizarDatos();
    }

    // =========================================================================
    // COLUMNA IZQUIERDA: dos formularios apilados
    // =========================================================================
    private JComponent crearColumnaFormularios() {
        JPanel columna = new JPanel();
        columna.setLayout(new BoxLayout(columna, BoxLayout.Y_AXIS));
        columna.setBackground(new Color(243, 246, 251));

        columna.add(crearFormularioAvion());
        columna.add(Box.createRigidArea(new Dimension(0, 10)));
        columna.add(crearFormularioVuelo());

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(new Color(243, 246, 251));
        contenedor.add(columna, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(contenedor, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(243, 246, 251));
        scroll.setPreferredSize(new Dimension(335, 0));
        return scroll;
    }

    private JPanel crearFormularioAvion() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(new Color(243, 246, 251));
        pnl.setBorder(BorderFactory.createTitledBorder("Registrar Avión"));

        txtMatricula = new JTextField();
        txtMatricula.setToolTipText("Formato obligatorio: HK-XXXX (ej. HK-4821)");
        txtModelo = new JTextField();
        txtModelo.setToolTipText("Ej. Airbus A320");
        txtColor = new JTextField();
        txtCapacidadTotal = new JTextField();
        txtCapacidadTotal.setToolTipText("Número entero mayor que 0 (ej. 150)");
        txtAsientosPrioritarios = new JTextField();
        txtAsientosPrioritarios.setToolTipText("Número entero entre 0 y la capacidad total (ej. 12)");
        btnRegistrarAvion = Utilidades.botonAccion("Registrar Avión", false);

        Utilidades.agregarFila(pnl, 0, "Matrícula (HK-XXXX)", txtMatricula);
        Utilidades.agregarFila(pnl, 1, "Modelo", txtModelo);
        Utilidades.agregarFila(pnl, 2, "Color", txtColor);
        Utilidades.agregarFila(pnl, 3, "Capacidad Total", txtCapacidadTotal);
        Utilidades.agregarFila(pnl, 4, "Asientos Prioritarios", txtAsientosPrioritarios);
        Utilidades.agregarFilaCompleta(pnl, 5, btnRegistrarAvion);
        return pnl;
    }

    private JPanel crearFormularioVuelo() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(new Color(243, 246, 251));
        pnl.setBorder(BorderFactory.createTitledBorder("Programar Nuevo Vuelo"));

        txtCodigoVuelo = new JTextField();
        txtCodigoVuelo.setToolTipText("Formato: VUE- seguido de 3 números (ej. VUE-045)");
        cmbRuta = new JComboBox<>();
        cmbRuta.setPreferredSize(new Dimension(150, 26)); // evita que una ruta larga ensanche el formulario
        Utilidades.textoLegible(cmbRuta);
        // Fecha por defecto = mañana (el modelo solo acepta salidas futuras)
        txtFechaSalida = new JTextField(FechaHora.ahora().sumarMinutos(24 * 60).getFecha().toString());
        txtFechaSalida.setToolTipText("Formato DD/MM/AAAA, fecha futura (ej. 15/03/2030)");
        txtHoraSalida = new JTextField("08:00");
        txtHoraSalida.setToolTipText("Formato HH:MM de 00:00 a 23:59 (ej. 08:30)");
        txtCapacidadVuelo = new JTextField();
        txtCapacidadVuelo.setToolTipText("Se completa con la capacidad del avión elegido; puedes bajarla para habilitar un cupo menor");
        cmbAvionInicial = new JComboBox<>();
        cmbAvionInicial.setPreferredSize(new Dimension(150, 26));
        Utilidades.textoParaNulo(cmbAvionInicial, "Sin avión (asignar después)");
        cmbAvionInicial.setToolTipText("Solo aparecen aviones sin vuelo activo");
        // Autocompletar capacidad al elegir un avión libre (editable después)
        cmbAvionInicial.addItemListener(e -> {
            if (e.getStateChange() != java.awt.event.ItemEvent.SELECTED || actualizando) return;
            Object sel = cmbAvionInicial.getSelectedItem();
            if (sel instanceof Avion) {
                txtCapacidadVuelo.setText(String.valueOf(((Avion) sel).getCapacidad()));
            }
        });
        btnCrearVuelo = Utilidades.botonAccion("Crear Vuelo", false);

        Utilidades.agregarFila(pnl, 0, "Código (VUE-XXX)", txtCodigoVuelo);
        Utilidades.agregarFila(pnl, 1, "Ruta", cmbRuta);
        Utilidades.agregarFila(pnl, 2, "Fecha (DD/MM/AAAA)", txtFechaSalida);
        Utilidades.agregarFila(pnl, 3, "Hora (HH:MM)", txtHoraSalida);
        Utilidades.agregarFila(pnl, 4, "Capacidad", txtCapacidadVuelo);
        Utilidades.agregarFila(pnl, 5, "Avión libre (opcional)", cmbAvionInicial);
        Utilidades.agregarFilaCompleta(pnl, 6, btnCrearVuelo);
        return pnl;
    }

    // =========================================================================
    // COLUMNA DERECHA: aviones (arriba) y vuelos (abajo)
    // =========================================================================
    private JComponent crearColumnaTablas() {
        JPanel columna = new JPanel(new GridBagLayout());
        columna.setBackground(new Color(243, 246, 251));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;

        c.gridy = 0;
        c.weighty = 0.3;
        c.insets = new Insets(0, 0, 10, 0);
        columna.add(crearPanelTablaAviones(), c);

        c.gridy = 1;
        c.weighty = 0.7;
        c.insets = new Insets(0, 0, 0, 0);
        columna.add(crearPanelVuelos(), c);
        return columna;
    }

    private JPanel crearPanelTablaAviones() {
        JPanel pnl = new JPanel(new BorderLayout(0, 4));
        pnl.setBackground(new Color(243, 246, 251));
        pnl.setBorder(BorderFactory.createTitledBorder("Aviones y Disponibilidad"));

        JPanel pnlFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        pnlFiltro.setBackground(new Color(243, 246, 251));
        cmbFiltroDisponibilidad = new JComboBox<>(new String[]{"Todos", "Libres", "Asignados a vuelo"});
        pnlFiltro.add(new JLabel("Mostrar:"));
        pnlFiltro.add(cmbFiltroDisponibilidad);
        pnl.add(pnlFiltro, BorderLayout.NORTH);

        String[] columnas = {"Matrícula", "Modelo", "Color", "Capacidad", "Prioritarios", "Estado Operativo"};
        modeloTablaAviones = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblAviones = new JTable(modeloTablaAviones);
        pnl.add(new JScrollPane(tblAviones), BorderLayout.CENTER);
        return pnl;
    }

    private JPanel crearPanelVuelos() {
        JPanel pnl = new JPanel(new BorderLayout(0, 6));
        pnl.setBackground(new Color(243, 246, 251));
        pnl.setBorder(BorderFactory.createTitledBorder("Vuelos Programados"));

        // --- Filtros ---
        JPanel pnlFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        pnlFiltros.setBackground(new Color(245, 246, 250));
        cmbFiltroOrigen = new JComboBox<>();
        cmbFiltroDestino = new JComboBox<>();
        cmbFiltroAvion = new JComboBox<>();
        Utilidades.textoParaNulo(cmbFiltroOrigen, "Todas");
        Utilidades.textoParaNulo(cmbFiltroDestino, "Todas");
        Utilidades.textoParaNulo(cmbFiltroAvion, "Todos");
        for (JComboBox<?> filtro : new JComboBox<?>[]{cmbFiltroOrigen, cmbFiltroDestino, cmbFiltroAvion}) {
            filtro.setPreferredSize(new Dimension(78, 26));
        }
        btnFiltrarVuelos = new JButton("Filtrar");
        btnLimpiarFiltros = new JButton("Todos");

        pnlFiltros.add(new JLabel("Origen:"));
        pnlFiltros.add(cmbFiltroOrigen);
        pnlFiltros.add(new JLabel("Destino:"));
        pnlFiltros.add(cmbFiltroDestino);
        pnlFiltros.add(new JLabel("Avión:"));
        pnlFiltros.add(cmbFiltroAvion);
        pnlFiltros.add(btnFiltrarVuelos);
        pnlFiltros.add(btnLimpiarFiltros);
        pnl.add(pnlFiltros, BorderLayout.NORTH);

        // --- Tabla ---
        String[] columnas = {"Código", "Ruta", "Salida", "Llegada Est.", "Avión", "Capacidad", "Ocupación %", "Estado Vuelo", "Maletas"};
        modeloTablaVuelos = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblVuelos = new JTable(modeloTablaVuelos);
        tblVuelos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pnl.add(new JScrollPane(tblVuelos), BorderLayout.CENTER);

        // --- Acciones sobre el vuelo seleccionado ---
        JPanel pnlAcciones = new JPanel();
        pnlAcciones.setLayout(new BoxLayout(pnlAcciones, BoxLayout.Y_AXIS));
        pnlAcciones.setBackground(new Color(243, 246, 251));
        pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones sobre el vuelo seleccionado en la tabla"));

        JPanel filaAvion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        filaAvion.setBackground(new Color(243, 246, 251));
        cmbAsignarCambiarAvion = new JComboBox<>();
        Utilidades.textoParaNulo(cmbAsignarCambiarAvion, "Elegir avión libre");
        cmbAsignarCambiarAvion.setPreferredSize(new Dimension(200, 26));
        btnAsignarAvion = new JButton("Asignar / Cambiar Avión");
        btnVerTripulacionVuelo = new JButton("Ver Tripulación del Vuelo");
        filaAvion.add(new JLabel("Avión libre:"));
        filaAvion.add(cmbAsignarCambiarAvion);
        filaAvion.add(btnAsignarAvion);

        JPanel filaFase = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        filaFase.setBackground(new Color(243, 246, 251));
        btnAvanzarEstadoVuelo = Utilidades.botonAccion("Siguiente Fase del Vuelo →", false);
        filaFase.add(btnAvanzarEstadoVuelo);
        filaFase.add(btnVerTripulacionVuelo);

        JPanel filaNota = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaNota.setBackground(new Color(243, 246, 251));
        JLabel lblNota = new JLabel("<html><body style='width:480px'><i>Cada clic avanza una fase: DISPONIBLE → ABORDANDO → "
            + "DESPEGUE → EN_EL_AIRE → ATERRIZAJE → EN_EL_DESTINO. Las ventas de pasajes se cierran en DESPEGUE, las maletas "
            + "cambian solas y, al llegar al destino, se liberan el avión y la tripulación.</i></body></html>");
        lblNota.setForeground(new Color(90, 95, 110));
        lblNota.setFont(new Font("SansSerif", Font.PLAIN, 11));
        filaNota.add(lblNota);

        pnlAcciones.add(filaAvion);
        pnlAcciones.add(filaFase);
        pnlAcciones.add(filaNota);
        pnl.add(pnlAcciones, BorderLayout.SOUTH);
        return pnl;
    }

    // =========================================================================
    // ACTUALIZACIÓN DE DATOS
    // =========================================================================
    
    private String codigoVueloActivo(Avion a) {
        for (Vuelo v : a.getVuelos()) {
            if (v.estaActivo()) {
                return v.getCodigo();
            }
        }
        return null;
    }

    public boolean estaActualizando() { return actualizando; }

    public void actualizarDatos() {
        if (aerolinea == null) return;
        actualizando = true;
        try {
            recargarDatos();
        } finally {
            actualizando = false;
        }
    }

    private void recargarDatos() {

        Object avionElegido = cmbAvionInicial.getSelectedItem();
        Object rutaElegida = cmbRuta.getSelectedItem();
        int filaVuelo = tblVuelos.getSelectedRow();
        String codigoVueloElegido = (filaVuelo != -1) ? modeloTablaVuelos.getValueAt(filaVuelo, 0).toString() : null;

        // 1. Rutas
        cmbRuta.removeAllItems();
        for (Ruta r : aerolinea.getRutas()) {
            cmbRuta.addItem(r);
        }
        if (rutaElegida != null) {
            cmbRuta.setSelectedItem(rutaElegida);
        }

        // 2. Aviones libres (crear vuelo y asignar/cambiar) y filtro por avión
        cmbAvionInicial.removeAllItems();
        cmbAvionInicial.addItem(null);
        cmbAsignarCambiarAvion.removeAllItems();
        cmbFiltroAvion.removeAllItems();
        cmbFiltroAvion.addItem(null);
        for (Avion a : aerolinea.getAviones()) {
            cmbFiltroAvion.addItem(a);
            if (a.estaLibre()) {
                cmbAvionInicial.addItem(a);
                cmbAsignarCambiarAvion.addItem(a);
            }
        }
        if (avionElegido != null) {
            cmbAvionInicial.setSelectedItem(avionElegido);
        }

        // 3. Filtros por ciudad
        cmbFiltroOrigen.removeAllItems();
        cmbFiltroOrigen.addItem(null);
        cmbFiltroDestino.removeAllItems();
        cmbFiltroDestino.addItem(null);
        for (Ciudad c : aerolinea.getCiudades()) {
            cmbFiltroOrigen.addItem(c);
            cmbFiltroDestino.addItem(c);
        }

        // 4. Tablas
        actualizarTablaAviones();
        llenarTablaVuelos(aerolinea.getVuelos());
        if (codigoVueloElegido != null) {
            seleccionarVuelo(codigoVueloElegido);
        }
    }

    public void actualizarTablaAviones() {
        modeloTablaAviones.setRowCount(0);
        String filtro = (String) cmbFiltroDisponibilidad.getSelectedItem();

        for (Avion a : aerolinea.getAviones()) {
            String codigoVuelo = codigoVueloActivo(a);
            boolean asignado = codigoVuelo != null;

            if ("Libres".equals(filtro) && asignado) continue;
            if ("Asignados a vuelo".equals(filtro) && !asignado) continue;

            modeloTablaAviones.addRow(new Object[]{
                a.getMatricula(),
                a.getModelo(),
                a.getColor(),
                a.getCapacidad(),
                a.getAsientosPrioritarios(),
                asignado ? "Asignado a " + codigoVuelo : "Libre"
            });
        }
    }

    public void llenarTablaVuelos(ArrayList<Vuelo> listaVuelos) {
        modeloTablaVuelos.setRowCount(0);
        for (Vuelo v : listaVuelos) {
            String matriculaAvion = (v.getAvion() != null) ? v.getAvion().getMatricula() : "Sin asignar";
            // TODO [Modelo]: getFechaHoraSalida() / calcularFechaHoraLlegada() aún pueden de null
            String llegada = "—";
            try {
                if (v.getFechaHoraSalida() != null) {
                    llegada = String.valueOf(v.calcularFechaHoraLlegada());
                }
            } catch (Exception ignored) {
                llegada = "—";
            }
            modeloTablaVuelos.addRow(new Object[]{
                v.getCodigo(),
                v.getRuta(),
                v.getFechaSalida() + " " + v.getHoraSalida(),
                llegada,
                matriculaAvion,
                v.getCapacidad(),
                v.calcularOcupacion() + "%",
                v.getEstadoVuelo(),
                v.getEstadoEmbarque()
            });
        }
    }

    
    public void seleccionarVuelo(String codigo) {
        for (int i = 0; i < modeloTablaVuelos.getRowCount(); i++) {
            if (modeloTablaVuelos.getValueAt(i, 0).toString().equals(codigo)) {
                tblVuelos.setRowSelectionInterval(i, i);
                return;
            }
        }
    }

    // Getters para el controlador
    // TODO [Controlador]: conectar estos componentes en Control.java
    public JTextField getTxtMatricula() { return txtMatricula; }
    public JTextField getTxtModelo() { return txtModelo; }
    public JTextField getTxtColor() { return txtColor; }
    public JTextField getTxtCapacidadTotal() { return txtCapacidadTotal; }
    public JTextField getTxtAsientosPrioritarios() { return txtAsientosPrioritarios; }
    public JButton getBtnRegistrarAvion() { return btnRegistrarAvion; }
    public JComboBox<String> getCmbFiltroDisponibilidad() { return cmbFiltroDisponibilidad; }
    public JTable getTblAviones() { return tblAviones; }
    public DefaultTableModel getModeloTablaAviones() { return modeloTablaAviones; }

    // Getters para el controlador
    // TODO [Controlador]: conectar estos componentes en Control.java
    public JTextField getTxtCodigoVuelo() { return txtCodigoVuelo; }
    public JComboBox<Ruta> getCmbRuta() { return cmbRuta; }
    public JTextField getTxtFechaSalida() { return txtFechaSalida; }
    public JTextField getTxtHoraSalida() { return txtHoraSalida; }
    public JTextField getTxtCapacidadVuelo() { return txtCapacidadVuelo; }
    public JComboBox<Avion> getCmbAvionInicial() { return cmbAvionInicial; }
    public JButton getBtnCrearVuelo() { return btnCrearVuelo; }

    public JComboBox<Ciudad> getCmbFiltroOrigen() { return cmbFiltroOrigen; }
    public JComboBox<Ciudad> getCmbFiltroDestino() { return cmbFiltroDestino; }
    public JComboBox<Avion> getCmbFiltroAvion() { return cmbFiltroAvion; }
    public JButton getBtnFiltrarVuelos() { return btnFiltrarVuelos; }
    public JButton getBtnLimpiarFiltros() { return btnLimpiarFiltros; }

    public JTable getTblVuelos() { return tblVuelos; }
    public DefaultTableModel getModeloTablaVuelos() { return modeloTablaVuelos; }

    public JComboBox<Avion> getCmbAsignarCambiarAvion() { return cmbAsignarCambiarAvion; }
    public JButton getBtnAsignarAvion() { return btnAsignarAvion; }
    public JButton getBtnAvanzarEstadoVuelo() { return btnAvanzarEstadoVuelo; }
    public JButton getBtnVerTripulacionVuelo() { return btnVerTripulacionVuelo; }
}

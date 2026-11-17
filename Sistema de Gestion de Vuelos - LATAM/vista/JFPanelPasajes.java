package vista;

import modelo.Aerolinea;
import modelo.ClasePasaje;
import modelo.EstadoPasaje;
import modelo.Pasaje;
import modelo.Pasajero;
import modelo.Vuelo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JFPanelPasajes extends JPanel {

    // Colores del mapa de asientos
    private static final Color COLOR_PRIORITARIA_LIBRE = new Color(255, 226, 140);
    private static final Color COLOR_ECONOMICA_LIBRE = new Color(214, 232, 255);
    private static final Color COLOR_OCUPADA = new Color(232, 17, 75);
    private static final Color BORDE_PRIORITARIA = new Color(196, 140, 0);

    private static final String MENSAJE_SIN_VUELOS = "Sin vuelos: primero programa uno en 'Aviones y Vuelos'";

    private Aerolinea aerolinea;

    // --- SELECCIÓN DE VUELO ---
    private JComboBox<Vuelo> cmbVueloSeleccionado;
    private JLabel lblInfoTopeVuelo;

    // --- VENTA DE PASAJES (el pasajero se registra automáticamente) ---
    private JTextField txtIdPasajero;
    private JTextField txtNombrePasajero;
    private JTextField txtNumeroAsiento;
    private JComboBox<ClasePasaje> cmbClasePasaje;
    private JTextField txtPrecioBase;
    private JLabel lblPrecioFinalCalculado;
    private JButton btnRegistrarPasaje;

    // --- MAPA VISUAL DE SILLAS ---
    private JPanel pnlCuadriculaAsientos;

    // --- PASAJES DEL VUELO, CANCELACIÓN E HISTORIAL ---
    private JTable tblPasajesVuelo;
    private DefaultTableModel modeloTablaPasajes;
    private JButton btnCancelarPasaje;
    private JButton btnVerHistorial;

    public JFPanelPasajes(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
        setLayout(new BorderLayout(15, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(243, 246, 251));

        inicializarBarraVuelo();
        inicializarCuerpoCentral();
        actualizarDatos();
    }

    private void inicializarBarraVuelo() {
        JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        pnlSuperior.setBackground(new Color(245, 246, 250));
        pnlSuperior.setBorder(BorderFactory.createTitledBorder("Vuelo"));

        cmbVueloSeleccionado = new JComboBox<>();
        Utilidades.textoLegible(cmbVueloSeleccionado);
        cmbVueloSeleccionado.setPreferredSize(new Dimension(280, 28));

        lblInfoTopeVuelo = new JLabel(MENSAJE_SIN_VUELOS);
        lblInfoTopeVuelo.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblInfoTopeVuelo.setForeground(new Color(27, 0, 136));

        pnlSuperior.add(new JLabel("Seleccionar vuelo:"));
        pnlSuperior.add(cmbVueloSeleccionado);
        pnlSuperior.add(lblInfoTopeVuelo);

        add(pnlSuperior, BorderLayout.NORTH);
    }

    private void inicializarCuerpoCentral() {
        // --- IZQUIERDA: VENTA + MAPA DE ASIENTOS ---
        JPanel pnlIzquierdo = new JPanel(new BorderLayout(0, 10));
        pnlIzquierdo.setBackground(new Color(243, 246, 251));
        pnlIzquierdo.setPreferredSize(new Dimension(430, 0));

        JPanel pnlTaquilla = new JPanel(new GridBagLayout());
        pnlTaquilla.setBackground(new Color(243, 246, 251));
        pnlTaquilla.setBorder(BorderFactory.createTitledBorder("Vender Pasaje (pago en efectivo)"));

        txtIdPasajero = new JTextField();
        txtIdPasajero.setToolTipText("Solo números. Si el pasajero no existe se registra automáticamente");
        txtNombrePasajero = new JTextField();
        txtNumeroAsiento = new JTextField();
        txtNumeroAsiento.setToolTipText("Número entero entre 1 y la capacidad del vuelo, o haz clic en una silla libre del mapa");
        cmbClasePasaje = new JComboBox<>(ClasePasaje.values());
        txtPrecioBase = new JTextField();

        lblPrecioFinalCalculado = new JLabel("$0");
        lblPrecioFinalCalculado.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblPrecioFinalCalculado.setForeground(new Color(0, 128, 75));
        lblPrecioFinalCalculado.setToolTipText("Económica = precio base; Prioritaria = precio base + 20%");

        btnRegistrarPasaje = Utilidades.botonAccion("Confirmar Pago y Emitir", false);

        Utilidades.agregarFila(pnlTaquilla, 0, "ID Pasajero", txtIdPasajero);
        Utilidades.agregarFila(pnlTaquilla, 1, "Nombre Completo", txtNombrePasajero);
        Utilidades.agregarFila(pnlTaquilla, 2, "N.º de Asiento", txtNumeroAsiento);
        Utilidades.agregarFila(pnlTaquilla, 3, "Clase", cmbClasePasaje);
        Utilidades.agregarFila(pnlTaquilla, 4, "Precio Base ($)", txtPrecioBase);
        Utilidades.agregarFila(pnlTaquilla, 5, "Precio Final", lblPrecioFinalCalculado);
        Utilidades.agregarFilaCompleta(pnlTaquilla, 6, btnRegistrarPasaje);

        pnlIzquierdo.add(pnlTaquilla, BorderLayout.NORTH);

        // Mapa de cabina (6 columnas por fila) con leyenda de colores
        JPanel pnlContenedorMapa = new JPanel(new BorderLayout(0, 4));
        pnlContenedorMapa.setBackground(new Color(243, 246, 251));
        pnlContenedorMapa.setBorder(BorderFactory.createTitledBorder("Mapa de Asientos"));

        JPanel pnlLeyenda = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        pnlLeyenda.setBackground(new Color(243, 246, 251));
        pnlLeyenda.add(crearItemLeyenda("Prioritaria", COLOR_PRIORITARIA_LIBRE));
        pnlLeyenda.add(crearItemLeyenda("Económica", COLOR_ECONOMICA_LIBRE));
        pnlLeyenda.add(crearItemLeyenda("Ocupada", COLOR_OCUPADA));
        pnlContenedorMapa.add(pnlLeyenda, BorderLayout.NORTH);

        pnlCuadriculaAsientos = new JPanel(new GridLayout(0, 6, 6, 6));
        pnlCuadriculaAsientos.setBackground(new Color(243, 246, 251));
        pnlCuadriculaAsientos.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // El contenedor evita que las sillas se estiren cuando hay pocas filas
        JPanel pnlSillas = new JPanel(new BorderLayout());
        pnlSillas.setBackground(new Color(243, 246, 251));
        pnlSillas.add(pnlCuadriculaAsientos, BorderLayout.NORTH);
        pnlContenedorMapa.add(new JScrollPane(pnlSillas), BorderLayout.CENTER);

        pnlIzquierdo.add(pnlContenedorMapa, BorderLayout.CENTER);
        add(pnlIzquierdo, BorderLayout.WEST);

        // --- DERECHA: PASAJES DEL VUELO, CANCELACIÓN E HISTORIAL ---
        JPanel pnlDerecho = new JPanel(new BorderLayout(0, 10));
        pnlDerecho.setBackground(new Color(243, 246, 251));
        pnlDerecho.setBorder(BorderFactory.createTitledBorder("Pasajes del Vuelo"));

        String[] columnas = {"Asiento", "ID Pasajero", "Pasajero", "Clase", "Precio Base", "Precio Final", "Estado"};
        modeloTablaPasajes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPasajesVuelo = new JTable(modeloTablaPasajes);
        tblPasajesVuelo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pnlDerecho.add(new JScrollPane(tblPasajesVuelo), BorderLayout.CENTER);

        JPanel pnlSurDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        pnlSurDerecho.setBackground(new Color(243, 246, 251));

        btnVerHistorial = Utilidades.botonAccion("Ver Historial del Pasajero", false);
        btnVerHistorial.setToolTipText("Escribe un ID en 'ID Pasajero' o selecciona una fila de la tabla");
        btnCancelarPasaje = Utilidades.botonAccion("Cancelar Pasaje Seleccionado", true);
        btnCancelarPasaje.setToolTipText("Cancela el pasaje seleccionado y libera el asiento");

        pnlSurDerecho.add(btnVerHistorial);
        pnlSurDerecho.add(btnCancelarPasaje);
        pnlDerecho.add(pnlSurDerecho, BorderLayout.SOUTH);

        add(pnlDerecho, BorderLayout.CENTER);
    }

    private JLabel crearItemLeyenda(String texto, Color color) {
        JLabel lbl = new JLabel(" " + texto + " ");
        lbl.setOpaque(true);
        lbl.setBackground(color);
        lbl.setForeground(color == COLOR_OCUPADA ? Color.WHITE : Color.BLACK);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lbl.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
        return lbl;
    }

    public void actualizarDatos() {
        if (aerolinea == null) return;

        Vuelo vueloPrevio = (Vuelo) cmbVueloSeleccionado.getSelectedItem();

        cmbVueloSeleccionado.removeAllItems();
        for (Vuelo v : aerolinea.getVuelos()) {
            cmbVueloSeleccionado.addItem(v);
        }
        if (vueloPrevio != null) {
            cmbVueloSeleccionado.setSelectedItem(vueloPrevio);
        }

        actualizarDetalleVuelo();
    }

    public void actualizarDetalleVuelo() {
        modeloTablaPasajes.setRowCount(0);
        pnlCuadriculaAsientos.removeAll();

        Vuelo vuelo = (Vuelo) cmbVueloSeleccionado.getSelectedItem();
        if (vuelo == null) {
            lblInfoTopeVuelo.setText(MENSAJE_SIN_VUELOS);
            pnlCuadriculaAsientos.revalidate();
            pnlCuadriculaAsientos.repaint();
            return;
        }

        lblInfoTopeVuelo.setText("Tope tarifario: $" + vuelo.getRuta().getTopeTarifario() + "  |  Estado: " + vuelo.getEstadoVuelo());

        for (Pasaje p : vuelo.getPasajes()) {
            modeloTablaPasajes.addRow(new Object[]{
                p.getNumeroAsiento(),
                p.getPasajero().getId(),
                p.getPasajero().getNombre(),
                p.getClase(),
                p.getPrecioBase(),
                p.calcularPrecioFinal(),
                p.getEstado()
            });
        }

        dibujarMapaAsientos(vuelo);
    }

    
    private void dibujarMapaAsientos(final Vuelo vuelo) {
        pnlCuadriculaAsientos.removeAll();

        int capacidad = vuelo.getCapacidad();
        // Los cupos prioritarios los define el avión; sin avión no hay sillas prioritarias
        final int capPrioritaria = (vuelo.getAvion() != null) ? vuelo.getAvion().getAsientosPrioritarios() : 0;

        for (int i = 1; i <= capacidad; i++) {
            final int numSilla = i;
            final boolean prioritaria = numSilla <= capPrioritaria;

            JButton btnSilla = new JButton(String.valueOf(numSilla));
            btnSilla.setMargin(new Insets(2, 2, 2, 2));
            btnSilla.setFont(new Font("SansSerif", Font.BOLD, 11));
            btnSilla.setFocusPainted(false);
            btnSilla.setOpaque(true);
            btnSilla.setContentAreaFilled(true);
            btnSilla.setBorder(prioritaria
                ? BorderFactory.createLineBorder(BORDE_PRIORITARIA, 2)
                : BorderFactory.createLineBorder(new Color(150, 170, 205), 1));

            if (vuelo.asientoOcupado(numSilla)) {
                btnSilla.setBackground(COLOR_OCUPADA);
                btnSilla.setForeground(Color.WHITE);
                btnSilla.setToolTipText("Ocupado: clic para ver al pasajero");
                btnSilla.addActionListener(e -> mostrarOcupanteAsiento(vuelo, numSilla));
            } else {
                btnSilla.setBackground(prioritaria ? COLOR_PRIORITARIA_LIBRE : COLOR_ECONOMICA_LIBRE);
                btnSilla.setForeground(Color.BLACK);
                btnSilla.setToolTipText((prioritaria ? "Prioritaria" : "Económica") + " libre: clic para elegirla");
                btnSilla.addActionListener(e -> {
                    txtNumeroAsiento.setText(String.valueOf(numSilla));
                    cmbClasePasaje.setSelectedItem(prioritaria ? ClasePasaje.PRIORITARIA : ClasePasaje.ECONOMICA);
                });
            }
            pnlCuadriculaAsientos.add(btnSilla);
        }

        pnlCuadriculaAsientos.revalidate();
        pnlCuadriculaAsientos.repaint();
    }

    
    private void mostrarOcupanteAsiento(Vuelo vuelo, int numSilla) {
        Pasaje pasaje = vuelo.buscarPasajePorAsiento(numSilla);
        if (pasaje == null) {
            return;
        }
        Pasajero p = pasaje.getPasajero();

        for (int i = 0; i < modeloTablaPasajes.getRowCount(); i++) {
            boolean mismoAsiento = modeloTablaPasajes.getValueAt(i, 0).toString().equals(String.valueOf(numSilla));
            boolean activo = !EstadoPasaje.CANCELADO.equals(modeloTablaPasajes.getValueAt(i, 6));
            if (mismoAsiento && activo) {
                tblPasajesVuelo.setRowSelectionInterval(i, i);
                break;
            }
        }

        JOptionPane.showMessageDialog(this,
            "Asiento " + numSilla + " (" + pasaje.getClase() + ")\n"
            + "Pasajero: " + p.getNombre() + "\n"
            + "ID: " + p.getId(),
            "Asiento ocupado", JOptionPane.INFORMATION_MESSAGE);
    }

    
    public void mostrarHistorialPasajero(Pasajero p) {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"Vuelo", "Ruta", "Fecha / Hora", "Asiento", "Clase", "Precio Final ($)", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        int totalPagado = 0;
        int cancelados = 0;
        for (Pasaje pas : p.getPasajes()) {
            Vuelo v = pas.getVuelo();
            modelo.addRow(new Object[]{
                v.getCodigo(),
                v.getRuta(),
                v.getFechaSalida() + " " + v.getHoraSalida(),
                pas.getNumeroAsiento(),
                pas.getClase(),
                pas.calcularPrecioFinal(),
                pas.getEstado()
            });
            if (pas.estaCancelado()) {
                cancelados++;
            } else {
                totalPagado += pas.calcularPrecioFinal();
            }
        }

        JLabel lblResumen = new JLabel(p.getNombre() + " (ID: " + p.getId() + ")  -  "
            + (p.getPasajes().isEmpty() ? "todavía no tiene pasajes" : p.getPasajes().size() + " pasaje(s): " + (p.getPasajes().size() - cancelados) + " pagado(s), "
                + cancelados + " cancelado(s). Total vigente $" + totalPagado));
        lblResumen.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblResumen.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JDialog dialogo = new JDialog(SwingUtilities.getWindowAncestor(this), "Historial del Pasajero", Dialog.ModalityType.APPLICATION_MODAL);
        dialogo.setLayout(new BorderLayout());
        dialogo.add(lblResumen, BorderLayout.NORTH);
        dialogo.add(new JScrollPane(new JTable(modelo)), BorderLayout.CENTER);
        dialogo.setSize(720, 320);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    // Getters para el controlador
    // TODO [Controlador]: conectar estos componentes en Control.java
    public JComboBox<Vuelo> getCmbVueloSeleccionado() { return cmbVueloSeleccionado; }
    public JLabel getLblInfoTopeVuelo() { return lblInfoTopeVuelo; }

    public JTextField getTxtIdPasajero() { return txtIdPasajero; }
    public JTextField getTxtNombrePasajero() { return txtNombrePasajero; }
    public JTextField getTxtNumeroAsiento() { return txtNumeroAsiento; }
    public JComboBox<ClasePasaje> getCmbClasePasaje() { return cmbClasePasaje; }
    public JTextField getTxtPrecioBase() { return txtPrecioBase; }
    public JLabel getLblPrecioFinalCalculado() { return lblPrecioFinalCalculado; }
    public JButton getBtnRegistrarPasaje() { return btnRegistrarPasaje; }

    public JPanel getPnlCuadriculaAsientos() { return pnlCuadriculaAsientos; }

    public JTable getTblPasajesVuelo() { return tblPasajesVuelo; }
    public DefaultTableModel getModeloTablaPasajes() { return modeloTablaPasajes; }
    public JButton getBtnCancelarPasaje() { return btnCancelarPasaje; }
    public JButton getBtnVerHistorial() { return btnVerHistorial; }
}

package vista;

import modelo.Aerolinea;
import modelo.EstadoVuelo;
import modelo.Tripulante;
import modelo.Rol;
import modelo.Vuelo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JFPanelTripulacion extends JPanel {

    private Aerolinea aerolinea;

    // --- FORMULARIO ---
    private JTextField txtIdentificacion;
    private JTextField txtNombreCompleto;
    private JComboBox<Rol> cmbRol;
    private JComboBox<Vuelo> cmbVueloAsignar; // null = "Sin asignar"
    private JButton btnRegistrarTripulante;
    private JButton btnAsignarAVuelo;

    // --- TABLA Y FILTRO ---
    private JComboBox<String> cmbFiltroEstado;
    private JTable tblTripulacion;
    private DefaultTableModel modeloTablaTripulacion;

    public JFPanelTripulacion(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;

        setLayout(new BorderLayout(15, 0));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(243, 246, 251));

        crearFormulario();
        crearTabla();
        actualizarDatos();
    }

    // =========================================================================
    // FORMULARIO (lado izquierdo)
    // =========================================================================
    private void crearFormulario() {
        JPanel pnlIzquierdo = new JPanel(new BorderLayout());
        pnlIzquierdo.setBackground(new Color(243, 246, 251));
        pnlIzquierdo.setPreferredSize(new Dimension(360, 0));
        pnlIzquierdo.setBorder(BorderFactory.createTitledBorder("Registrar Tripulante"));

        // GridBagLayout permite filas ordenadas (etiqueta + campo)
        JPanel pnlCampos = new JPanel(new GridBagLayout());
        pnlCampos.setBackground(new Color(243, 246, 251));
        pnlCampos.setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));

        txtIdentificacion = new JTextField();
        txtIdentificacion.setToolTipText("Solo números, sin puntos ni guiones (ej. 1001)");
        txtNombreCompleto = new JTextField();
        cmbRol = new JComboBox<>(Rol.values());

        cmbVueloAsignar = new JComboBox<>();
        Utilidades.textoParaNulo(cmbVueloAsignar, "Sin asignar");
        cmbVueloAsignar.setToolTipText("Opcional: al registrar, el tripulante queda asignado a este vuelo");

        btnRegistrarTripulante = Utilidades.botonAccion("Registrar Tripulante", false);
        btnAsignarAVuelo = Utilidades.botonAccion("Asignar tripulante de la tabla al vuelo", true);
        btnAsignarAVuelo.setToolTipText("Selecciona un tripulante libre en la tabla, elige el vuelo y pulsa aquí");

        JLabel lblAyuda = new JLabel(
            "<html><body style='width:280px'><i>Para un tripulante ya registrado: selecciónalo en la tabla, "
            + "elige el vuelo arriba y usa el botón rojo.</i></body></html>"
        );
        lblAyuda.setForeground(new Color(90, 95, 110));

        // Agregar filas con ayuda de Utilidades
        Utilidades.agregarFila(pnlCampos, 0, "Identificación", txtIdentificacion);
        Utilidades.agregarFila(pnlCampos, 1, "Nombre Completo", txtNombreCompleto);
        Utilidades.agregarFila(pnlCampos, 2, "Rol a bordo", cmbRol);
        Utilidades.agregarFila(pnlCampos, 3, "Asignar a vuelo (opcional)", cmbVueloAsignar);
        Utilidades.agregarFilaCompleta(pnlCampos, 4, btnRegistrarTripulante);
        Utilidades.agregarFilaCompleta(pnlCampos, 5, btnAsignarAVuelo);
        Utilidades.agregarFilaCompleta(pnlCampos, 6, lblAyuda);

        pnlIzquierdo.add(pnlCampos, BorderLayout.NORTH);
        add(pnlIzquierdo, BorderLayout.WEST);
    }

    // =========================================================================
    // TABLA (lado derecho)
    // =========================================================================
    private void crearTabla() {
        JPanel pnlDerecho = new JPanel(new BorderLayout(0, 10));
        pnlDerecho.setBackground(new Color(243, 246, 251));
        pnlDerecho.setBorder(BorderFactory.createTitledBorder("Tripulación y Disponibilidad"));

        // Filtro arriba
        JPanel pnlFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlFiltro.setBackground(new Color(243, 246, 251));
        cmbFiltroEstado = new JComboBox<>(new String[]{"Todos", "Libres", "Asignados a vuelo"});
        pnlFiltro.add(new JLabel("Mostrar:"));
        pnlFiltro.add(cmbFiltroEstado);
        pnlDerecho.add(pnlFiltro, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Identificación", "Nombre Completo", "Rol", "Estado Operativo"};
        modeloTablaTripulacion = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblTripulacion = new JTable(modeloTablaTripulacion);
        tblTripulacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pnlDerecho.add(new JScrollPane(tblTripulacion), BorderLayout.CENTER);

        add(pnlDerecho, BorderLayout.CENTER);
    }

    // =========================================================================
    // ACTUALIZAR DATOS
    // =========================================================================

    
    private String codigoVueloAsignado(Tripulante t) {
        for (Vuelo v : t.getVuelos()) {
            if (v.estaActivo()) {
                return v.getCodigo();
            }
        }
        return null;
    }

    public void actualizarDatos() {
        if (aerolinea == null) return;

        // 1. Llenar combo de vuelos (solo los que aún no han despegado)
        Vuelo seleccionado = (Vuelo) cmbVueloAsignar.getSelectedItem();
        cmbVueloAsignar.removeAllItems();
        cmbVueloAsignar.addItem(null); // "Sin asignar"
        for (Vuelo v : aerolinea.getVuelos()) {
            if (v.getEstadoVuelo().ordinal() < EstadoVuelo.DESPEGUE.ordinal()) {
                cmbVueloAsignar.addItem(v);
            }
        }
        if (seleccionado != null) {
            cmbVueloAsignar.setSelectedItem(seleccionado);
        }

        // 2. Llenar tabla respetando el filtro
        modeloTablaTripulacion.setRowCount(0);
        String filtro = (String) cmbFiltroEstado.getSelectedItem();

        for (Tripulante t : aerolinea.getTripulantes()) {
            String codigoVuelo = codigoVueloAsignado(t);
            boolean asignado = (codigoVuelo != null);

            if ("Libres".equals(filtro) && asignado) continue;
            if ("Asignados a vuelo".equals(filtro) && !asignado) continue;

            modeloTablaTripulacion.addRow(new Object[]{
                t.getId(),
                t.getNombre(),
                t.getRol(),
                asignado ? "Asignado a " + codigoVuelo : "Libre"
            });
        }
    }

    // Getters para el controlador
    // TODO [Controlador]: conectar estos componentes en Control.java
    public JTextField getTxtIdentificacion() { return txtIdentificacion; }
    public JTextField getTxtNombreCompleto() { return txtNombreCompleto; }
    public JComboBox<Rol> getCmbRol() { return cmbRol; }
    public JButton getBtnRegistrarTripulante() { return btnRegistrarTripulante; }
    public JComboBox<Vuelo> getCmbVueloAsignar() { return cmbVueloAsignar; }
    public JButton getBtnAsignarAVuelo() { return btnAsignarAVuelo; }
    public JComboBox<String> getCmbFiltroEstado() { return cmbFiltroEstado; }
    public JTable getTblTripulacion() { return tblTripulacion; }
    public DefaultTableModel getModeloTablaTripulacion() { return modeloTablaTripulacion; }
}

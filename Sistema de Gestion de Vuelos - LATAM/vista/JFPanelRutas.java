package vista;

import modelo.Aerolinea;
import modelo.Ciudad;
import modelo.Ruta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JFPanelRutas extends JPanel {

    private Aerolinea aerolinea;

    // --- COMPONENTES DE CIUDADES ---
    private JTextField txtCodigoCiudad;
    private JTextField txtNombreCiudad;
    private JTextField txtCodigoPais;
    private JTextField txtNombrePais;
    private JButton btnRegistrarCiudad;
    private JTable tblCiudades;
    private DefaultTableModel modeloTablaCiudades;

    // --- COMPONENTES DE RUTAS ---
    private JComboBox<Ciudad> cmbCiudadSalida;
    private JComboBox<Ciudad> cmbCiudadLlegada;
    private JTextField txtDuracionMin;
    private JTextField txtTopeTarifario;
    private JButton btnRegistrarRuta;
    private JTable tblRutas;
    private DefaultTableModel modeloTablaRutas;

    public JFPanelRutas(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;

        // GridLayout(1, 2) = una fila, dos columnas
        setLayout(new GridLayout(1, 2, 15, 0));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(243, 246, 251));

        crearPanelCiudades();
        crearPanelRutas();
        actualizarDatos();
    }

    // =========================================================================
    // COLUMNA IZQUIERDA: Ciudades
    // =========================================================================
    private void crearPanelCiudades() {
        JPanel pnlIzquierdo = new JPanel(new BorderLayout(0, 12));
        pnlIzquierdo.setBackground(new Color(243, 246, 251));
        pnlIzquierdo.setBorder(BorderFactory.createTitledBorder("Ciudades Habilitadas (IATA)"));

        // Formulario (GridLayout: 5 filas, 2 columnas)
        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 8, 8));
        pnlForm.setBackground(new Color(243, 246, 251));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        txtCodigoCiudad = new JTextField();
        txtCodigoCiudad.setToolTipText("Exactamente 3 letras (ej. BOG, MDE, PEI)");
        txtNombreCiudad = new JTextField();
        txtCodigoPais = new JTextField("CO");
        txtNombrePais = new JTextField("Colombia");
        btnRegistrarCiudad = Utilidades.botonAccion("Registrar Ciudad", false);

        pnlForm.add(new JLabel("Código IATA (3 letras):"));
        pnlForm.add(txtCodigoCiudad);
        pnlForm.add(new JLabel("Nombre Ciudad:"));
        pnlForm.add(txtNombreCiudad);
        pnlForm.add(new JLabel("Código País (ej. CO):"));
        pnlForm.add(txtCodigoPais);
        pnlForm.add(new JLabel("Nombre País:"));
        pnlForm.add(txtNombrePais);
        pnlForm.add(new JLabel(""));
        pnlForm.add(btnRegistrarCiudad);

        pnlIzquierdo.add(pnlForm, BorderLayout.NORTH);

        // Tabla de ciudades
        String[] columnas = {"IATA", "Ciudad", "País", "Cód. País"};
        modeloTablaCiudades = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // la tabla no se edita a mano
            }
        };
        tblCiudades = new JTable(modeloTablaCiudades);
        pnlIzquierdo.add(new JScrollPane(tblCiudades), BorderLayout.CENTER);

        add(pnlIzquierdo);
    }

    // =========================================================================
    // COLUMNA DERECHA: Rutas
    // =========================================================================
    private void crearPanelRutas() {
        JPanel pnlDerecho = new JPanel(new BorderLayout(0, 12));
        pnlDerecho.setBackground(new Color(243, 246, 251));
        pnlDerecho.setBorder(BorderFactory.createTitledBorder("Rutas Habilitadas y Topes Tarifarios"));

        // Formulario
        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 8, 8));
        pnlForm.setBackground(new Color(243, 246, 251));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        cmbCiudadSalida = new JComboBox<>();
        Utilidades.textoLegible(cmbCiudadSalida);
        cmbCiudadLlegada = new JComboBox<>();
        Utilidades.textoLegible(cmbCiudadLlegada);
        txtDuracionMin = new JTextField();
        txtTopeTarifario = new JTextField();
        btnRegistrarRuta = Utilidades.botonAccion("Registrar Ruta", false);

        pnlForm.add(new JLabel("Ciudad Salida:"));
        pnlForm.add(cmbCiudadSalida);
        pnlForm.add(new JLabel("Ciudad Llegada:"));
        pnlForm.add(cmbCiudadLlegada);
        pnlForm.add(new JLabel("Duración (min, entero):"));
        pnlForm.add(txtDuracionMin);
        pnlForm.add(new JLabel("Tope Tarifario ($, entero):"));
        pnlForm.add(txtTopeTarifario);
        pnlForm.add(new JLabel(""));
        pnlForm.add(btnRegistrarRuta);

        pnlDerecho.add(pnlForm, BorderLayout.NORTH);

        // Tabla de rutas
        String[] columnas = {"Origen", "Destino", "Duración (min)", "Tope Base ($)"};
        modeloTablaRutas = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblRutas = new JTable(modeloTablaRutas);
        pnlDerecho.add(new JScrollPane(tblRutas), BorderLayout.CENTER);

        add(pnlDerecho);
    }

    // =========================================================================
    // ACTUALIZAR DATOS (lo llama el controlador y la ventana principal)
    // =========================================================================
    public void actualizarDatos() {
        // 1. Limpiar tabla y combos
        modeloTablaCiudades.setRowCount(0);
        cmbCiudadSalida.removeAllItems();
        cmbCiudadLlegada.removeAllItems();

        // 2. Llenar ciudades
        for (Ciudad c : aerolinea.getCiudades()) {
            modeloTablaCiudades.addRow(new Object[]{
                c.getCodigo(),
                c.getNombre(),
                c.getPais().getNombre(),
                c.getPais().getCodigo()
            });
            cmbCiudadSalida.addItem(c);
            cmbCiudadLlegada.addItem(c);
        }

        // 3. Llenar rutas
        modeloTablaRutas.setRowCount(0);
        for (Ruta r : aerolinea.getRutas()) {
            modeloTablaRutas.addRow(new Object[]{
                r.getCiudadSalida().getCodigo(),
                r.getCiudadLlegada().getCodigo(),
                r.getDuracionEstimada(),
                r.getTopeTarifario()
            });
        }
    }

    // Getters para el controlador
    // TODO [Controlador]: conectar estos componentes en Control.java
    public JTextField getTxtCodigoCiudad() { return txtCodigoCiudad; }
    public JTextField getTxtNombreCiudad() { return txtNombreCiudad; }
    public JTextField getTxtCodigoPais() { return txtCodigoPais; }
    public JTextField getTxtNombrePais() { return txtNombrePais; }
    public JButton getBtnRegistrarCiudad() { return btnRegistrarCiudad; }
    public DefaultTableModel getModeloTablaCiudades() { return modeloTablaCiudades; }

    public JComboBox<Ciudad> getCmbCiudadSalida() { return cmbCiudadSalida; }
    public JComboBox<Ciudad> getCmbCiudadLlegada() { return cmbCiudadLlegada; }
    public JTextField getTxtDuracionMin() { return txtDuracionMin; }
    public JTextField getTxtTopeTarifario() { return txtTopeTarifario; }
    public JButton getBtnRegistrarRuta() { return btnRegistrarRuta; }
    public DefaultTableModel getModeloTablaRutas() { return modeloTablaRutas; }
}

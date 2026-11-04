package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modelo.*;

public class JFPanelRutas extends JPanel implements ActionListener
{
    private Aerolinea aerolinea;

    // Paises
    private JTextField txtPaisCodigo, txtPaisNombre;
    private JButton btnRegistrarPais;
    private DefaultTableModel mdlPaises;
    private JTable tblPaises;

    // Ciudades
    private JTextField txtCiudadCodigo, txtCiudadNombre;
    private JComboBox cmbPais;
    private JCheckBox boxHabilitada;
    private JButton btnRegistrarCiudad, btnHabilitar;
    private DefaultTableModel mdlCiudades;
    private JTable tblCiudades;

    // Rutas
    private JComboBox cmbSalida, cmbLlegada;
    private JTextField txtDuracion, txtTope;
    private JButton btnRegistrarRuta;
    private DefaultTableModel mdlRutas;
    private JTable tblRutas;
    private ArrayList<Ciudad> ciudadesHabilitadas; // ciudades de los combos de ruta (mismo orden)

    public JFPanelRutas(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
        ciudadesHabilitadas = new ArrayList<Ciudad>();
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Paises", panelPaises());
        tabs.addTab("Ciudades", panelCiudades());
        tabs.addTab("Rutas", panelRutas());
        add(tabs, BorderLayout.CENTER);
    }

    // ---------------- Pestana paises ----------------
    public JPanel panelPaises(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(3, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar pais"));

        palFormulario.add(new JLabel("Codigo (ejemplo CO)"));
        txtPaisCodigo = new JTextField(12);
        palFormulario.add(txtPaisCodigo);

        palFormulario.add(new JLabel("Nombre"));
        txtPaisNombre = new JTextField();
        palFormulario.add(txtPaisNombre);

        palFormulario.add(new JLabel(""));
        btnRegistrarPais = new JButton("Registrar pais");
        btnRegistrarPais.addActionListener(this);
        palFormulario.add(btnRegistrarPais);

        JPanel palIzquierdo = new JPanel(new BorderLayout());
        palIzquierdo.add(palFormulario, BorderLayout.NORTH);

        mdlPaises = new DefaultTableModel(new String[]{"Codigo", "Nombre", "Ciudades"}, 0);
        tblPaises = Utilidades.crearTabla(mdlPaises);

        JPanel palPaises = new JPanel(new BorderLayout());
        palPaises.add(palIzquierdo, BorderLayout.WEST);
        palPaises.add(new JScrollPane(tblPaises), BorderLayout.CENTER);
        return palPaises;
    }

    // ---------------- Pestana ciudades ----------------
    public JPanel panelCiudades(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(6, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar ciudad"));

        palFormulario.add(new JLabel("Codigo (3 letras, ejemplo PEI)"));
        txtCiudadCodigo = new JTextField(12);
        palFormulario.add(txtCiudadCodigo);

        palFormulario.add(new JLabel("Nombre"));
        txtCiudadNombre = new JTextField();
        palFormulario.add(txtCiudadNombre);

        palFormulario.add(new JLabel("Pais"));
        cmbPais = new JComboBox();
        palFormulario.add(cmbPais);

        palFormulario.add(new JLabel(""));
        boxHabilitada = new JCheckBox("Habilitada");
        palFormulario.add(boxHabilitada);

        palFormulario.add(new JLabel(""));
        btnRegistrarCiudad = new JButton("Registrar ciudad");
        btnRegistrarCiudad.addActionListener(this);
        palFormulario.add(btnRegistrarCiudad);

        palFormulario.add(new JLabel(""));
        btnHabilitar = new JButton("Habilitar la seleccionada");
        btnHabilitar.addActionListener(this);
        palFormulario.add(btnHabilitar);

        JPanel palIzquierdo = new JPanel(new BorderLayout());
        palIzquierdo.add(palFormulario, BorderLayout.NORTH);

        mdlCiudades = new DefaultTableModel(new String[]{"Codigo", "Nombre", "Pais", "Habilitada"}, 0);
        tblCiudades = Utilidades.crearTabla(mdlCiudades);

        JPanel palCiudades = new JPanel(new BorderLayout());
        palCiudades.add(palIzquierdo, BorderLayout.WEST);
        palCiudades.add(new JScrollPane(tblCiudades), BorderLayout.CENTER);
        return palCiudades;
    }

    // ---------------- Pestana rutas ----------------
    public JPanel panelRutas(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(5, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar ruta (solo ciudades habilitadas)"));

        palFormulario.add(new JLabel("Ciudad de salida"));
        cmbSalida = new JComboBox();
        palFormulario.add(cmbSalida);

        palFormulario.add(new JLabel("Ciudad de llegada"));
        cmbLlegada = new JComboBox();
        palFormulario.add(cmbLlegada);

        palFormulario.add(new JLabel("Duracion (minutos)"));
        txtDuracion = new JTextField(12);
        palFormulario.add(txtDuracion);

        palFormulario.add(new JLabel("Tope tarifario ($)"));
        txtTope = new JTextField();
        palFormulario.add(txtTope);

        palFormulario.add(new JLabel(""));
        btnRegistrarRuta = new JButton("Registrar ruta");
        btnRegistrarRuta.addActionListener(this);
        palFormulario.add(btnRegistrarRuta);

        JPanel palIzquierdo = new JPanel(new BorderLayout());
        palIzquierdo.add(palFormulario, BorderLayout.NORTH);

        mdlRutas = new DefaultTableModel(new String[]{"Salida", "Llegada", "Duracion (min)", "Tope tarifario"}, 0);
        tblRutas = Utilidades.crearTabla(mdlRutas);

        JPanel palRutas = new JPanel(new BorderLayout());
        palRutas.add(palIzquierdo, BorderLayout.WEST);
        palRutas.add(new JScrollPane(tblRutas), BorderLayout.CENTER);
        return palRutas;
    }

    // Recarga las tres tablas y los combos
    public void actualizar(){
        // Paises
        mdlPaises.setRowCount(0);
        cmbPais.removeAllItems();
        ArrayList<Pais> paises = Utilidades.segura(aerolinea.getPaises());
        for (int i = 0; i < paises.size(); i++) {
            Pais p = paises.get(i);
            Object[] fila = {p.getCodigo(), p.getNombre(), Utilidades.segura(p.getCiudades()).size()};
            mdlPaises.addRow(fila);
            cmbPais.addItem(p.getNombre() + " (" + p.getCodigo() + ")");
        }
        Utilidades.ajustarColumnas(tblPaises);

        // Ciudades
        mdlCiudades.setRowCount(0);
        cmbSalida.removeAllItems();
        cmbLlegada.removeAllItems();
        ciudadesHabilitadas.clear();
        ArrayList<Ciudad> ciudades = Utilidades.segura(aerolinea.getCiudades());
        for (int i = 0; i < ciudades.size(); i++) {
            Ciudad c = ciudades.get(i);
            String nombrePais = "-";
            if (c.getPais() != null) {
                nombrePais = c.getPais().getNombre();
            }
            String habilitada = "No";
            if (c.isHabilitada()) {
                habilitada = "Si";
                cmbSalida.addItem(c.getNombre() + " (" + c.getCodigo() + ")");
                cmbLlegada.addItem(c.getNombre() + " (" + c.getCodigo() + ")");
                ciudadesHabilitadas.add(c);
            }
            Object[] fila = {c.getCodigo(), c.getNombre(), nombrePais, habilitada};
            mdlCiudades.addRow(fila);
        }
        Utilidades.ajustarColumnas(tblCiudades);

        // Rutas
        mdlRutas.setRowCount(0);
        ArrayList<Ruta> rutas = Utilidades.segura(aerolinea.getRutas());
        for (int i = 0; i < rutas.size(); i++) {
            Ruta r = rutas.get(i);
            Object[] fila = {Utilidades.textoCiudad(r.getCiudadSalida()), Utilidades.textoCiudad(r.getCiudadLlegada()), r.getDuracionEstimada(), Utilidades.textoDinero(r.getTopeTarifario())};
            mdlRutas.addRow(fila);
        }
        Utilidades.ajustarColumnas(tblRutas);
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnRegistrarPais) {
            registrarPais();
        } else if (e.getSource() == btnRegistrarCiudad) {
            registrarCiudad();
        } else if (e.getSource() == btnHabilitar) {
            habilitarCiudad();
        } else if (e.getSource() == btnRegistrarRuta) {
            registrarRuta();
        }
    }

    private void registrarPais(){
        try {
            String codigo = Utilidades.leerTexto(txtPaisCodigo, "Codigo").toUpperCase();
            String nombre = Utilidades.leerTexto(txtPaisNombre, "Nombre");
            Pais pais = new Pais(codigo, nombre, new ArrayList<Ciudad>());
            if (aerolinea.registrarPais(pais)) {
                txtPaisCodigo.setText("");
                txtPaisNombre.setText("");
                actualizar();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar el pais.\nRevise que el codigo no exista ya.");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void registrarCiudad(){
        try {
            String codigo = Utilidades.leerTexto(txtCiudadCodigo, "Codigo").toUpperCase();
            String nombre = Utilidades.leerTexto(txtCiudadNombre, "Nombre");
            int posPais = cmbPais.getSelectedIndex();
            if (posPais == -1) {
                throw new IllegalArgumentException("Primero registre un pais");
            }
            Pais pais = Utilidades.segura(aerolinea.getPaises()).get(posPais);
            Ciudad ciudad = new Ciudad(codigo, nombre, boxHabilitada.isSelected(), pais, aerolinea, new ArrayList<Ruta>(), new ArrayList<Ruta>());
            if (aerolinea.registrarCiudad(ciudad)) {
                txtCiudadCodigo.setText("");
                txtCiudadNombre.setText("");
                boxHabilitada.setSelected(false);
                actualizar();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar la ciudad.\nRevise que el codigo tenga 3 letras y no exista ya.");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void habilitarCiudad(){
        int fila = tblCiudades.getSelectedRow();
        if (fila == -1) {
            Utilidades.mostrarError(this, "Seleccione una ciudad de la tabla");
            return;
        }
        String codigo = (String) mdlCiudades.getValueAt(fila, 0);
        if (aerolinea.habilitarCiudad(codigo)) {
            actualizar();
        } else {
            Utilidades.mostrarError(this, "No se pudo habilitar la ciudad");
        }
    }

    private void registrarRuta(){
        try {
            int posSalida = cmbSalida.getSelectedIndex();
            int posLlegada = cmbLlegada.getSelectedIndex();
            if (posSalida == -1 || posLlegada == -1) {
                throw new IllegalArgumentException("Se necesitan ciudades habilitadas");
            }
            Ciudad salida = ciudadesHabilitadas.get(posSalida);
            Ciudad llegada = ciudadesHabilitadas.get(posLlegada);
            int duracion = Utilidades.leerEntero(txtDuracion, "Duracion");
            int tope = Utilidades.leerEntero(txtTope, "Tope tarifario");
            if (aerolinea.registrarRuta(salida, llegada, duracion, tope)) {
                txtDuracion.setText("");
                txtTope.setText("");
                actualizar();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar la ruta. Revise que:\n- las ciudades sean distintas y esten habilitadas\n- la ruta no exista ya (Bogota-Pereira es distinta de Pereira-Bogota)\n- duracion y tope sean mayores que 0");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }
}

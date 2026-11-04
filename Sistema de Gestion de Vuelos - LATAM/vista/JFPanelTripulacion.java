package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modelo.*;

public class JFPanelTripulacion extends JPanel implements ActionListener
{
    private Aerolinea aerolinea;

    private JTextField txtId, txtNombre, txtBuscar;
    private JComboBox cmbRol, cmbVueloRegistro, cmbVueloAsignar;
    private JButton btnRegistrar, btnBuscar, btnVerTodos, btnAsignar;
    private DefaultTableModel mdlTripulantes;
    private JTable tblTripulantes;

    public JFPanelTripulacion(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
        setLayout(new BorderLayout());
        add(panelFormulario(), BorderLayout.WEST);
        add(panelTabla(), BorderLayout.CENTER);
    }

    public JPanel panelFormulario(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(5, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar tripulante"));

        palFormulario.add(new JLabel("Identificacion"));
        txtId = new JTextField(12);
        palFormulario.add(txtId);

        palFormulario.add(new JLabel("Nombre completo"));
        txtNombre = new JTextField();
        palFormulario.add(txtNombre);

        palFormulario.add(new JLabel("Rol"));
        cmbRol = new JComboBox(Utilidades.textosEnum(Rol.values()));
        palFormulario.add(cmbRol);

        palFormulario.add(new JLabel("Asignar a vuelo"));
        cmbVueloRegistro = new JComboBox();
        palFormulario.add(cmbVueloRegistro);

        palFormulario.add(new JLabel(""));
        btnRegistrar = new JButton("Registrar tripulante");
        btnRegistrar.addActionListener(this);
        palFormulario.add(btnRegistrar);

        JPanel palIzquierdo = new JPanel(new BorderLayout());
        palIzquierdo.add(palFormulario, BorderLayout.NORTH);
        return palIzquierdo;
    }

    public JPanel panelTabla(){
        JPanel palTabla = new JPanel(new BorderLayout());
        palTabla.setBorder(BorderFactory.createTitledBorder("Tripulantes"));

        // Fila 1: buscar
        JPanel palBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palBusqueda.add(new JLabel("Identificacion"));
        txtBuscar = new JTextField(10);
        palBusqueda.add(txtBuscar);
        btnBuscar = new JButton("Buscar");
        btnVerTodos = new JButton("Ver todos");
        btnBuscar.addActionListener(this);
        btnVerTodos.addActionListener(this);
        palBusqueda.add(btnBuscar);
        palBusqueda.add(btnVerTodos);

        // Fila 2: asignar el tripulante seleccionado a un vuelo
        JPanel palAsignar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palAsignar.add(new JLabel("Asignar el tripulante seleccionado al vuelo"));
        cmbVueloAsignar = new JComboBox();
        palAsignar.add(cmbVueloAsignar);
        btnAsignar = new JButton("Asignar");
        btnAsignar.addActionListener(this);
        palAsignar.add(btnAsignar);

        JPanel palBarra = new JPanel(new GridLayout(2, 1));
        palBarra.add(palBusqueda);
        palBarra.add(palAsignar);

        String[] columnas = {"Identificacion", "Nombre", "Rol", "Estado"};
        mdlTripulantes = new DefaultTableModel(columnas, 0);
        tblTripulantes = Utilidades.crearTabla(mdlTripulantes);

        palTabla.add(palBarra, BorderLayout.NORTH);
        palTabla.add(new JScrollPane(tblTripulantes), BorderLayout.CENTER);
        return palTabla;
    }

    // Recarga la tabla y los combos de vuelos
    public void actualizar(){
        mdlTripulantes.setRowCount(0);
        ArrayList<Tripulante> tripulantes = Utilidades.segura(aerolinea.getTripulantes());
        for (int i = 0; i < tripulantes.size(); i++) {
            agregarFila(tripulantes.get(i));
        }
        Utilidades.ajustarColumnas(tblTripulantes);

        // El combo del registro tiene una opcion extra al inicio ("ninguno"), por eso su posicion va corrida en 1
        cmbVueloRegistro.removeAllItems();
        cmbVueloAsignar.removeAllItems();
        cmbVueloRegistro.addItem("(ninguno)");
        ArrayList<Vuelo> vuelos = Utilidades.segura(aerolinea.getVuelos());
        for (int i = 0; i < vuelos.size(); i++) {
            String texto = vuelos.get(i).getCodigo() + " - " + Utilidades.textoRuta(vuelos.get(i).getRuta());
            cmbVueloRegistro.addItem(texto);
            cmbVueloAsignar.addItem(texto);
        }
    }

    private void agregarFila(Tripulante t){
        String estado = "Libre";
        if (!t.estaLibre()) {
            estado = "Asignado a un vuelo activo";
        }
        Object[] fila = {t.getId(), t.getNombre(), Utilidades.textoEnum(t.getRol()), estado};
        mdlTripulantes.addRow(fila);
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnRegistrar) {
            registrar();
        } else if (e.getSource() == btnBuscar) {
            buscar();
        } else if (e.getSource() == btnVerTodos) {
            actualizar();
        } else if (e.getSource() == btnAsignar) {
            asignar();
        }
    }

    private void registrar(){
        try {
            String id = Utilidades.leerTexto(txtId, "Identificacion");
            String nombre = Utilidades.leerTexto(txtNombre, "Nombre");
            Rol rol = Rol.values()[cmbRol.getSelectedIndex()];

            // Si se eligio "(ninguno)" (posicion 0) no se asigna vuelo
            String codigoVuelo = null;
            int posicion = cmbVueloRegistro.getSelectedIndex();
            if (posicion > 0) {
                codigoVuelo = Utilidades.segura(aerolinea.getVuelos()).get(posicion - 1).getCodigo();
            }

            Tripulante tripulante = new Tripulante(id, nombre, rol, aerolinea, new ArrayList<Vuelo>());
            if (aerolinea.registrarTripulante(tripulante, codigoVuelo)) {
                Utilidades.mostrarMensaje(this, "Tripulante registrado correctamente");
                txtId.setText("");
                txtNombre.setText("");
                actualizar();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar el tripulante.\nRevise que la identificacion sea solo numeros y no exista ya,\ny que si eligio un vuelo, este no haya despegado y el tripulante este libre.");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void asignar(){
        int fila = tblTripulantes.getSelectedRow();
        int posicion = cmbVueloAsignar.getSelectedIndex();
        if (fila == -1 || posicion == -1) {
            Utilidades.mostrarError(this, "Seleccione un tripulante de la tabla y un vuelo");
            return;
        }
        String id = (String) mdlTripulantes.getValueAt(fila, 0);
        String codigoVuelo = Utilidades.segura(aerolinea.getVuelos()).get(posicion).getCodigo();
        if (aerolinea.asignarTripulanteAVuelo(id, codigoVuelo)) {
            Utilidades.mostrarMensaje(this, "Tripulante asignado al vuelo " + codigoVuelo);
            actualizar();
        } else {
            Utilidades.mostrarError(this, "No se pudo asignar.\nEl tripulante debe estar libre (sin vuelo activo) y el vuelo no puede haber despegado.");
        }
    }

    private void buscar(){
        try {
            String id = Utilidades.leerTexto(txtBuscar, "Identificacion");
            Tripulante tripulante = aerolinea.buscarTripulante(id);
            if (tripulante == null) {
                Utilidades.mostrarError(this, "No existe un tripulante con identificacion " + id);
                return;
            }
            mdlTripulantes.setRowCount(0);
            agregarFila(tripulante);
            Utilidades.ajustarColumnas(tblTripulantes);
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }
}

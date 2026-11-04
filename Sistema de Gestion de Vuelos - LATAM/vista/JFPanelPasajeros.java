package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modelo.*;

public class JFPanelPasajeros extends JPanel implements ActionListener
{
    private Aerolinea aerolinea;

    private JTextField txtId, txtNombre, txtBuscar;
    private JButton btnRegistrar, btnBuscar, btnVerTodos, btnVerPasajes;
    private DefaultTableModel mdlPasajeros;
    private JTable tblPasajeros;
    private ArrayList<Pasajero> pasajerosMostrados; // pasajeros que se ven ahora en la tabla (mismo orden)

    public JFPanelPasajeros(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
        pasajerosMostrados = new ArrayList<Pasajero>();
        setLayout(new BorderLayout());
        add(panelFormulario(), BorderLayout.WEST);
        add(panelTabla(), BorderLayout.CENTER);
    }

    public JPanel panelFormulario(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(3, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar pasajero"));

        palFormulario.add(new JLabel("Identificacion"));
        txtId = new JTextField(12);
        palFormulario.add(txtId);

        palFormulario.add(new JLabel("Nombre completo"));
        txtNombre = new JTextField();
        palFormulario.add(txtNombre);

        palFormulario.add(new JLabel(""));
        btnRegistrar = new JButton("Registrar pasajero");
        btnRegistrar.addActionListener(this);
        palFormulario.add(btnRegistrar);

        JPanel palIzquierdo = new JPanel(new BorderLayout());
        palIzquierdo.add(palFormulario, BorderLayout.NORTH);
        return palIzquierdo;
    }

    public JPanel panelTabla(){
        JPanel palTabla = new JPanel(new BorderLayout());
        palTabla.setBorder(BorderFactory.createTitledBorder("Pasajeros"));

        JPanel palBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palBusqueda.add(new JLabel("Identificacion"));
        txtBuscar = new JTextField(10);
        palBusqueda.add(txtBuscar);
        btnBuscar = new JButton("Buscar");
        btnVerTodos = new JButton("Ver todos");
        btnVerPasajes = new JButton("Ver pasajes del pasajero seleccionado");
        btnBuscar.addActionListener(this);
        btnVerTodos.addActionListener(this);
        btnVerPasajes.addActionListener(this);
        palBusqueda.add(btnBuscar);
        palBusqueda.add(btnVerTodos);
        palBusqueda.add(btnVerPasajes);

        String[] columnas = {"Identificacion", "Nombre", "Cantidad de pasajes"};
        mdlPasajeros = new DefaultTableModel(columnas, 0);
        tblPasajeros = Utilidades.crearTabla(mdlPasajeros);

        palTabla.add(palBusqueda, BorderLayout.NORTH);
        palTabla.add(new JScrollPane(tblPasajeros), BorderLayout.CENTER);
        return palTabla;
    }

    public void actualizar(){
        mdlPasajeros.setRowCount(0);
        ArrayList<Pasajero> pasajeros = Utilidades.segura(aerolinea.getPasajeros());
        pasajerosMostrados = pasajeros;
        for (int i = 0; i < pasajeros.size(); i++) {
            agregarFila(pasajeros.get(i));
        }
        Utilidades.ajustarColumnas(tblPasajeros);
    }

    private void agregarFila(Pasajero p){
        Object[] fila = {p.getId(), p.getNombre(), Utilidades.segura(p.getPasajes()).size()};
        mdlPasajeros.addRow(fila);
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnRegistrar) {
            registrar();
        } else if (e.getSource() == btnBuscar) {
            buscar();
        } else if (e.getSource() == btnVerTodos) {
            actualizar();
        } else if (e.getSource() == btnVerPasajes) {
            verPasajes();
        }
    }

    private void registrar(){
        try {
            String id = Utilidades.leerTexto(txtId, "Identificacion");
            String nombre = Utilidades.leerTexto(txtNombre, "Nombre");
            Pasajero pasajero = new Pasajero(id, nombre, new ArrayList<Pasaje>());
            if (aerolinea.registrarPasajero(pasajero)) {
                Utilidades.mostrarMensaje(this, "Pasajero registrado correctamente");
                txtId.setText("");
                txtNombre.setText("");
                actualizar();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar el pasajero.\nLa identificacion debe ser solo numeros (sin puntos, guiones ni espacios) y no existir ya.");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void buscar(){
        try {
            String id = Utilidades.leerTexto(txtBuscar, "Identificacion");
            Pasajero pasajero = aerolinea.buscarPasajero(id);
            if (pasajero == null) {
                Utilidades.mostrarError(this, "No existe un pasajero con identificacion " + id);
                return;
            }
            mdlPasajeros.setRowCount(0);
            pasajerosMostrados = new ArrayList<Pasajero>();
            pasajerosMostrados.add(pasajero);
            agregarFila(pasajero);
            Utilidades.ajustarColumnas(tblPasajeros);
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void verPasajes(){
        int fila = tblPasajeros.getSelectedRow();
        if (fila == -1) {
            Utilidades.mostrarError(this, "Seleccione un pasajero de la tabla");
            return;
        }
        Pasajero pasajero = pasajerosMostrados.get(fila); // la fila de la tabla coincide con la posicion en la lista
        String id = pasajero.getId();
        ArrayList<Pasaje> pasajes = Utilidades.segura(pasajero.getPasajes());
        if (pasajes.size() == 0) {
            Utilidades.mostrarMensaje(this, "Este pasajero todavia no tiene pasajes");
            return;
        }
        Utilidades.mostrarTabla(this, "Pasajes del pasajero " + id, Utilidades.crearModeloPasajes(pasajes));
    }
}

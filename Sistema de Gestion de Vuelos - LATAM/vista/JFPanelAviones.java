package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modelo.*;

public class JFPanelAviones extends JPanel implements ActionListener
{
    private Aerolinea aerolinea;

    private JTextField txtMatricula, txtModelo, txtColor, txtCapacidad, txtPrioritarios;
    private JTextField txtBuscar;
    private JButton btnRegistrar, btnBuscar, btnVerTodos;
    private DefaultTableModel mdlAviones;
    private JTable tblAviones;

    public JFPanelAviones(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
        setLayout(new BorderLayout());
        add(panelFormulario(), BorderLayout.WEST);
        add(panelTabla(), BorderLayout.CENTER);
    }

    public JPanel panelFormulario(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(6, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar avion"));

        palFormulario.add(new JLabel("Matricula (HK-0000)"));
        txtMatricula = new JTextField(12);
        palFormulario.add(txtMatricula);

        palFormulario.add(new JLabel("Modelo"));
        txtModelo = new JTextField();
        palFormulario.add(txtModelo);

        palFormulario.add(new JLabel("Color"));
        txtColor = new JTextField();
        palFormulario.add(txtColor);

        palFormulario.add(new JLabel("Capacidad total"));
        txtCapacidad = new JTextField();
        palFormulario.add(txtCapacidad);

        palFormulario.add(new JLabel("Asientos prioritarios"));
        txtPrioritarios = new JTextField();
        palFormulario.add(txtPrioritarios);

        palFormulario.add(new JLabel(""));
        btnRegistrar = new JButton("Registrar avion");
        btnRegistrar.addActionListener(this);
        palFormulario.add(btnRegistrar);

        JPanel palIzquierdo = new JPanel(new BorderLayout());
        palIzquierdo.add(palFormulario, BorderLayout.NORTH);
        return palIzquierdo;
    }

    public JPanel panelTabla(){
        JPanel palTabla = new JPanel(new BorderLayout());
        palTabla.setBorder(BorderFactory.createTitledBorder("Aviones de la aerolinea"));

        JPanel palBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palBusqueda.add(new JLabel("Buscar por matricula"));
        txtBuscar = new JTextField(10);
        palBusqueda.add(txtBuscar);
        btnBuscar = new JButton("Buscar");
        btnVerTodos = new JButton("Ver todos");
        btnBuscar.addActionListener(this);
        btnVerTodos.addActionListener(this);
        palBusqueda.add(btnBuscar);
        palBusqueda.add(btnVerTodos);

        String[] columnas = {"Matricula", "Modelo", "Color", "Capacidad", "Prioritarios", "Estado"};
        mdlAviones = new DefaultTableModel(columnas, 0);
        tblAviones = Utilidades.crearTabla(mdlAviones);

        palTabla.add(palBusqueda, BorderLayout.NORTH);
        palTabla.add(new JScrollPane(tblAviones), BorderLayout.CENTER);
        return palTabla;
    }

    // Recarga la tabla con todos los aviones del modelo
    public void actualizar(){
        mdlAviones.setRowCount(0);
        ArrayList<Avion> aviones = Utilidades.segura(aerolinea.getAviones());
        for (int i = 0; i < aviones.size(); i++) {
            agregarFila(aviones.get(i));
        }
        Utilidades.ajustarColumnas(tblAviones);
    }

    private void agregarFila(Avion a){
        String estado = "Libre";
        if (!a.estaLibre()) {
            estado = "Asignado a un vuelo activo";
        }
        Object[] fila = {a.getMatricula(), a.getModelo(), a.getColor(), a.getCapacidad(), a.getAsientosPrioritarios(), estado};
        mdlAviones.addRow(fila);
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnRegistrar) {
            registrar();
        } else if (e.getSource() == btnBuscar) {
            buscar();
        } else if (e.getSource() == btnVerTodos) {
            actualizar();
        }
    }

    private void registrar(){
        try {
            String matricula = Utilidades.leerTexto(txtMatricula, "Matricula").toUpperCase();
            String modelo = Utilidades.leerTexto(txtModelo, "Modelo");
            String color = Utilidades.leerTexto(txtColor, "Color");
            int capacidad = Utilidades.leerEntero(txtCapacidad, "Capacidad");
            int prioritarios = Utilidades.leerEntero(txtPrioritarios, "Asientos prioritarios");

            Avion avion = new Avion(matricula, modelo, color, capacidad, prioritarios, new ArrayList<Vuelo>(), aerolinea);
            if (aerolinea.registrarAvion(avion)) {
                Utilidades.mostrarMensaje(this, "Avion registrado correctamente");
                limpiar();
                actualizar();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar el avion.\nRevise que la matricula tenga el formato HK-1234 y no exista ya,\ny que la capacidad sea mayor que 0 y los prioritarios no la superen.");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void buscar(){
        try {
            String matricula = Utilidades.leerTexto(txtBuscar, "Matricula").toUpperCase();
            Avion avion = aerolinea.buscarAvion(matricula);
            if (avion == null) {
                Utilidades.mostrarError(this, "No existe un avion con la matricula " + matricula);
                return;
            }
            mdlAviones.setRowCount(0);
            agregarFila(avion);
            Utilidades.ajustarColumnas(tblAviones);
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void limpiar(){
        txtMatricula.setText("");
        txtModelo.setText("");
        txtColor.setText("");
        txtCapacidad.setText("");
        txtPrioritarios.setText("");
    }
}

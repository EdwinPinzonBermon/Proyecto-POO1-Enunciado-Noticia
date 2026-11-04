package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modelo.*;

public class JFPanelPasajes extends JPanel implements ActionListener
{
    private Aerolinea aerolinea;
    private boolean cargando; // true mientras se llenan los combos (para que no reaccionen)

    // Pestana "Vender pasaje"
    private JComboBox cmbVuelo, cmbClase;
    private JLabel lblInfoVuelo;
    private JTextField txtId, txtNombre, txtAsiento, txtPrecio;
    private JButton btnVender;
    private ArrayList<Vuelo> vuelosEnVenta; // vuelos que aparecen en cmbVuelo (en el mismo orden)

    // Pestana "Consultar y cancelar"
    private JComboBox cmbVueloConsulta;
    private JTextField txtAsientoBuscar;
    private JButton btnListarVuelo, btnBuscarPasaje, btnCancelar;
    private DefaultTableModel mdlPasajes;
    private JTable tblPasajes;

    public JFPanelPasajes(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
        vuelosEnVenta = new ArrayList<Vuelo>();
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Vender pasaje", panelVender());
        tabs.addTab("Consultar y cancelar", panelConsultar());
        add(tabs, BorderLayout.CENTER);
    }

    public JPanel panelVender(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(8, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar pasaje (el pasajero ya pago en efectivo)"));

        palFormulario.add(new JLabel("Vuelo"));
        cmbVuelo = new JComboBox();
        cmbVuelo.addActionListener(this);
        palFormulario.add(cmbVuelo);

        palFormulario.add(new JLabel(""));
        lblInfoVuelo = new JLabel("");
        palFormulario.add(lblInfoVuelo);

        palFormulario.add(new JLabel("Identificacion del pasajero"));
        txtId = new JTextField(14);
        palFormulario.add(txtId);

        palFormulario.add(new JLabel("Nombre completo"));
        txtNombre = new JTextField();
        palFormulario.add(txtNombre);

        palFormulario.add(new JLabel("Numero de asiento"));
        txtAsiento = new JTextField();
        palFormulario.add(txtAsiento);

        palFormulario.add(new JLabel("Clase"));
        cmbClase = new JComboBox(Utilidades.textosEnum(ClasePasaje.values()));
        palFormulario.add(cmbClase);

        palFormulario.add(new JLabel("Precio base ($)"));
        txtPrecio = new JTextField();
        palFormulario.add(txtPrecio);

        palFormulario.add(new JLabel(""));
        btnVender = new JButton("Registrar pasaje");
        btnVender.addActionListener(this);
        palFormulario.add(btnVender);

        JPanel palNorte = new JPanel(new BorderLayout());
        palNorte.add(palFormulario, BorderLayout.CENTER);
        palNorte.add(new JLabel("  Si la identificacion ya existe se usa ese pasajero; si no, se registra uno nuevo."), BorderLayout.SOUTH);

        JPanel palVender = new JPanel(new BorderLayout());
        palVender.add(palNorte, BorderLayout.NORTH);
        return palVender;
    }

    public JPanel panelConsultar(){
        JPanel palConsultar = new JPanel(new BorderLayout());

        // Barra superior con los botones
        JPanel palFila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palFila1.add(new JLabel("Vuelo"));
        cmbVueloConsulta = new JComboBox();
        palFila1.add(cmbVueloConsulta);
        btnListarVuelo = new JButton("Listar pasajes del vuelo");
        btnListarVuelo.addActionListener(this);
        palFila1.add(btnListarVuelo);
        palFila1.add(new JLabel("   Asiento"));
        txtAsientoBuscar = new JTextField(5);
        palFila1.add(txtAsientoBuscar);
        btnBuscarPasaje = new JButton("Buscar pasaje");
        btnBuscarPasaje.addActionListener(this);
        palFila1.add(btnBuscarPasaje);

        JPanel palFila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCancelar = new JButton("Cancelar pasaje seleccionado");
        btnCancelar.addActionListener(this);
        palFila2.add(btnCancelar);

        JPanel palBarra = new JPanel(new GridLayout(2, 1));
        palBarra.add(palFila1);
        palBarra.add(palFila2);

        mdlPasajes = Utilidades.crearModeloPasajes();
        tblPasajes = Utilidades.crearTabla(mdlPasajes);

        palConsultar.add(palBarra, BorderLayout.NORTH);
        palConsultar.add(new JScrollPane(tblPasajes), BorderLayout.CENTER);
        return palConsultar;
    }

    // Recarga los combos de vuelos
    public void actualizar(){
        cargando = true;
        cmbVuelo.removeAllItems();
        cmbVueloConsulta.removeAllItems();
        vuelosEnVenta.clear();
        ArrayList<Vuelo> vuelos = Utilidades.segura(aerolinea.getVuelos());
        for (int i = 0; i < vuelos.size(); i++) {
            Vuelo v = vuelos.get(i);
            String texto = v.getCodigo() + " - " + Utilidades.textoRuta(v.getRuta()) + " (" + Utilidades.textoSalida(v) + ")";
            cmbVueloConsulta.addItem(texto);
            // Solo se venden pasajes en vuelos Disponibles o Abordando
            if (v.admiteVentas()) {
                cmbVuelo.addItem(texto);
                vuelosEnVenta.add(v);
            }
        }
        cargando = false;
        mostrarInfoVuelo();
        mdlPasajes.setRowCount(0);
    }

    // Muestra asientos libres y tope de precio del vuelo elegido
    private void mostrarInfoVuelo(){
        int posicion = cmbVuelo.getSelectedIndex();
        if (posicion == -1) {
            lblInfoVuelo.setText("");
            return;
        }
        Vuelo v = vuelosEnVenta.get(posicion);
        lblInfoVuelo.setText("Libres: " + aerolinea.consultarAsientosLibres(v.getCodigo()) + " de " + v.getCapacidad()
            + " | Tope precio base: " + Utilidades.textoDinero(v.getRuta().getTopeTarifario()));
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == cmbVuelo) {
            if (!cargando) {
                mostrarInfoVuelo();
            }
        } else if (e.getSource() == btnVender) {
            vender();
        } else if (e.getSource() == btnListarVuelo) {
            listarDelVuelo();
        } else if (e.getSource() == btnBuscarPasaje) {
            buscarPasaje();
        } else if (e.getSource() == btnCancelar) {
            cancelar();
        }
    }

    private void vender(){
        try {
            int posicion = cmbVuelo.getSelectedIndex();
            if (posicion == -1) {
                throw new IllegalArgumentException("No hay vuelos disponibles para vender pasajes");
            }
            Vuelo vuelo = vuelosEnVenta.get(posicion);
            String id = Utilidades.leerTexto(txtId, "Identificacion");
            String nombre = Utilidades.leerTexto(txtNombre, "Nombre");
            int asiento = Utilidades.leerEntero(txtAsiento, "Numero de asiento");
            int precio = Utilidades.leerEntero(txtPrecio, "Precio base");
            ClasePasaje clase = ClasePasaje.values()[cmbClase.getSelectedIndex()];

            // Si el pasajero no existe se registra primero
            Pasajero pasajero = aerolinea.buscarPasajero(id);
            if (pasajero == null) {
                pasajero = new Pasajero(id, nombre, new ArrayList<Pasaje>());
                if (!aerolinea.registrarPasajero(pasajero)) {
                    Utilidades.mostrarError(this, "No se pudo registrar el pasajero.\nLa identificacion debe ser solo numeros.");
                    return;
                }
            }

            if (aerolinea.registrarPasaje(vuelo.getCodigo(), pasajero, asiento, clase, precio)) {
                Pasaje pasaje = aerolinea.buscarPasaje(vuelo.getCodigo(), asiento);
                Utilidades.mostrarMensaje(this, "Pasaje registrado (estado Pagado)\nPrecio final: " + Utilidades.textoDinero(pasaje.calcularPrecioFinal()));
                txtAsiento.setText("");
                txtPrecio.setText("");
                mostrarInfoVuelo();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar el pasaje. Revise que:\n"
                    + "- el asiento este entre 1 y la capacidad del vuelo y no este ocupado\n"
                    + "- el precio base sea mayor que 0 y no supere el tope de la ruta\n"
                    + "- el pasajero no tenga ya un pasaje activo en este vuelo\n"
                    + "- si es prioritario, el vuelo tenga avion con asientos prioritarios libres");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void mostrarLista(ArrayList<Pasaje> pasajes){
        mdlPasajes.setRowCount(0);
        for (int i = 0; i < pasajes.size(); i++) {
            Utilidades.agregarFilaPasaje(mdlPasajes, pasajes.get(i));
        }
        Utilidades.ajustarColumnas(tblPasajes);
        if (pasajes.size() == 0) {
            Utilidades.mostrarMensaje(this, "No hay pasajes para mostrar");
        }
    }

    private void listarDelVuelo(){
        int posicion = cmbVueloConsulta.getSelectedIndex();
        if (posicion == -1) {
            Utilidades.mostrarError(this, "No hay vuelos registrados");
            return;
        }
        Vuelo vuelo = Utilidades.segura(aerolinea.getVuelos()).get(posicion);
        mostrarLista(Utilidades.segura(vuelo.getPasajes()));
    }

    private void buscarPasaje(){
        try {
            int posicion = cmbVueloConsulta.getSelectedIndex();
            if (posicion == -1) {
                throw new IllegalArgumentException("No hay vuelos registrados");
            }
            String codigo = Utilidades.segura(aerolinea.getVuelos()).get(posicion).getCodigo();
            int asiento = Utilidades.leerEntero(txtAsientoBuscar, "Asiento");
            Pasaje pasaje = aerolinea.buscarPasaje(codigo, asiento);
            if (pasaje == null) {
                Utilidades.mostrarError(this, "No existe un pasaje activo en el asiento " + asiento + " del vuelo " + codigo);
                return;
            }
            mdlPasajes.setRowCount(0);
            Utilidades.agregarFilaPasaje(mdlPasajes, pasaje);
            Utilidades.ajustarColumnas(tblPasajes);
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void cancelar(){
        int fila = tblPasajes.getSelectedRow();
        if (fila == -1) {
            Utilidades.mostrarError(this, "Seleccione un pasaje de la tabla");
            return;
        }
        String codigo = (String) mdlPasajes.getValueAt(fila, 0);
        int asiento = (Integer) mdlPasajes.getValueAt(fila, 1);
        if (!Utilidades.confirmar(this, "Cancelar el pasaje del asiento " + asiento + " del vuelo " + codigo + "?\nEl asiento quedara libre.")) {
            return;
        }
        if (aerolinea.cancelarPasaje(codigo, asiento)) {
            Utilidades.mostrarMensaje(this, "Pasaje cancelado. El asiento " + asiento + " quedo libre");
            mdlPasajes.setRowCount(0);
        } else {
            Utilidades.mostrarError(this, "No se pudo cancelar.\nEl pasaje no puede estar ya cancelado y el vuelo debe estar Disponible o Abordando.");
        }
    }
}

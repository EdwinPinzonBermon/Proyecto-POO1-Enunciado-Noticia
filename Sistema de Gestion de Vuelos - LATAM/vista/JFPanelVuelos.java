package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import modelo.*;

public class JFPanelVuelos extends JPanel implements ActionListener
{
    private Aerolinea aerolinea;

    // Pestana "Lista y gestion"
    private JComboBox cmbTipoFiltro, cmbValorFiltro, cmbAvionAsignar;
    private JTextField txtBuscar;
    private JButton btnFiltrar, btnBuscar, btnAvanzarEstado, btnAvanzarEmbarque, btnVerTripulacion, btnVerPasajes, btnAsignarAvion;
    private DefaultTableModel mdlVuelos;
    private JTable tblVuelos;
    private ArrayList<Avion> avionesLibres; // aviones que aparecen en cmbAvionAsignar (mismo orden)
    private ArrayList<Vuelo> vuelosMostrados; // vuelos que se ven ahora en la tabla (mismo orden)

    // Pestana "Registrar vuelo"
    private JTextField txtCodigo, txtFecha, txtHora, txtCapacidad;
    private JComboBox cmbRuta, cmbAvionRegistro;
    private JButton btnRegistrar;

    public JFPanelVuelos(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
        avionesLibres = new ArrayList<Avion>();
        vuelosMostrados = new ArrayList<Vuelo>();
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Lista y gestion", panelLista());
        tabs.addTab("Registrar vuelo", panelRegistro());
        add(tabs, BorderLayout.CENTER);
    }

    public JPanel panelLista(){
        JPanel palLista = new JPanel(new BorderLayout());

        // Fila 1: filtros y busqueda por codigo
        JPanel palFila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palFila1.add(new JLabel("Filtrar por"));
        String[] tiposFiltro = {"Todos", "Ciudad de salida", "Ciudad de llegada", "Avion"};
        cmbTipoFiltro = new JComboBox(tiposFiltro);
        cmbTipoFiltro.addActionListener(this);
        palFila1.add(cmbTipoFiltro);
        cmbValorFiltro = new JComboBox();
        cmbValorFiltro.setEnabled(false);
        palFila1.add(cmbValorFiltro);
        btnFiltrar = new JButton("Aplicar filtro");
        btnFiltrar.addActionListener(this);
        palFila1.add(btnFiltrar);
        palFila1.add(new JLabel("   Buscar codigo"));
        txtBuscar = new JTextField(8);
        palFila1.add(txtBuscar);
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        palFila1.add(btnBuscar);

        // Fila 2: acciones sobre el vuelo seleccionado en la tabla
        JPanel palFila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palFila2.add(new JLabel("Vuelo seleccionado:"));
        btnAvanzarEstado = new JButton("Avanzar estado del vuelo");
        btnAvanzarEmbarque = new JButton("Avanzar embarque de maletas");
        btnVerTripulacion = new JButton("Ver tripulacion");
        btnVerPasajes = new JButton("Ver pasajes");
        btnAvanzarEstado.addActionListener(this);
        btnAvanzarEmbarque.addActionListener(this);
        btnVerTripulacion.addActionListener(this);
        btnVerPasajes.addActionListener(this);
        palFila2.add(btnAvanzarEstado);
        palFila2.add(btnAvanzarEmbarque);
        palFila2.add(btnVerTripulacion);
        palFila2.add(btnVerPasajes);

        // Fila 3: asignar o cambiar el avion del vuelo seleccionado
        JPanel palFila3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        palFila3.add(new JLabel("Asignar o cambiar avion (solo aviones libres):"));
        cmbAvionAsignar = new JComboBox();
        palFila3.add(cmbAvionAsignar);
        btnAsignarAvion = new JButton("Asignar avion");
        btnAsignarAvion.addActionListener(this);
        palFila3.add(btnAsignarAvion);

        JPanel palBarra = new JPanel(new GridLayout(3, 1));
        palBarra.add(palFila1);
        palBarra.add(palFila2);
        palBarra.add(palFila3);

        String[] columnas = {"Codigo", "Ruta", "Salida", "Llegada estimada", "Avion", "Capacidad", "Asientos libres", "Estado", "Embarque"};
        mdlVuelos = new DefaultTableModel(columnas, 0);
        tblVuelos = Utilidades.crearTabla(mdlVuelos);

        palLista.add(palBarra, BorderLayout.NORTH);
        palLista.add(new JScrollPane(tblVuelos), BorderLayout.CENTER);
        return palLista;
    }

    public JPanel panelRegistro(){
        JPanel palFormulario = new JPanel();
        palFormulario.setLayout(new GridLayout(7, 2, 4, 6));
        palFormulario.setBorder(BorderFactory.createTitledBorder("Registrar vuelo"));

        palFormulario.add(new JLabel("Codigo (VUE-000)"));
        txtCodigo = new JTextField(14);
        palFormulario.add(txtCodigo);

        palFormulario.add(new JLabel("Ruta"));
        cmbRuta = new JComboBox();
        palFormulario.add(cmbRuta);

        palFormulario.add(new JLabel("Fecha de salida (dd/mm/aaaa)"));
        txtFecha = new JTextField();
        palFormulario.add(txtFecha);

        palFormulario.add(new JLabel("Hora de salida (hh:mm)"));
        txtHora = new JTextField();
        palFormulario.add(txtHora);

        palFormulario.add(new JLabel("Capacidad de pasajeros"));
        txtCapacidad = new JTextField();
        palFormulario.add(txtCapacidad);

        palFormulario.add(new JLabel("Avion (opcional)"));
        cmbAvionRegistro = new JComboBox();
        palFormulario.add(cmbAvionRegistro);

        palFormulario.add(new JLabel(""));
        btnRegistrar = new JButton("Registrar vuelo");
        btnRegistrar.addActionListener(this);
        palFormulario.add(btnRegistrar);

        JPanel palNorte = new JPanel(new BorderLayout());
        palNorte.add(palFormulario, BorderLayout.CENTER);
        palNorte.add(new JLabel("  La duracion y la hora de llegada se calculan solas con la ruta. El avion y la tripulacion se pueden asignar despues."), BorderLayout.SOUTH);

        JPanel palRegistro = new JPanel(new BorderLayout());
        palRegistro.add(palNorte, BorderLayout.NORTH);
        return palRegistro;
    }

    // Recarga combos y tabla
    public void actualizar(){
        // Rutas
        cmbRuta.removeAllItems();
        ArrayList<Ruta> rutas = Utilidades.segura(aerolinea.getRutas());
        for (int i = 0; i < rutas.size(); i++) {
            cmbRuta.addItem(Utilidades.textoRuta(rutas.get(i)));
        }

        // Aviones libres (el combo del registro tiene una opcion extra al inicio, por eso su posicion va corrida en 1)
        cmbAvionRegistro.removeAllItems();
        cmbAvionAsignar.removeAllItems();
        avionesLibres.clear();
        cmbAvionRegistro.addItem("(sin avion por ahora)");
        ArrayList<Avion> aviones = Utilidades.segura(aerolinea.getAviones());
        for (int i = 0; i < aviones.size(); i++) {
            Avion a = aviones.get(i);
            if (a.estaLibre()) {
                String texto = a.getMatricula() + " - " + a.getModelo() + " (" + a.getCapacidad() + " asientos, " + a.getAsientosPrioritarios() + " prioritarios)";
                cmbAvionRegistro.addItem(texto);
                cmbAvionAsignar.addItem(texto);
                avionesLibres.add(a);
            }
        }

        cargarValoresFiltro();
        llenarTabla(Utilidades.segura(aerolinea.getVuelos()));
    }

    // Llena el segundo combo del filtro segun lo elegido en el primero
    private void cargarValoresFiltro(){
        cmbValorFiltro.removeAllItems();
        int tipo = cmbTipoFiltro.getSelectedIndex(); // 0 todos, 1 salida, 2 llegada, 3 avion
        cmbValorFiltro.setEnabled(tipo != 0);
        if (tipo == 1 || tipo == 2) {
            ArrayList<Ciudad> ciudades = Utilidades.segura(aerolinea.getCiudades());
            for (int i = 0; i < ciudades.size(); i++) {
                cmbValorFiltro.addItem(ciudades.get(i).getNombre() + " (" + ciudades.get(i).getCodigo() + ")");
            }
        } else if (tipo == 3) {
            ArrayList<Avion> aviones = Utilidades.segura(aerolinea.getAviones());
            for (int i = 0; i < aviones.size(); i++) {
                cmbValorFiltro.addItem(aviones.get(i).getMatricula() + " - " + aviones.get(i).getModelo());
            }
        }
    }

    private void llenarTabla(ArrayList<Vuelo> vuelos){
        vuelosMostrados = vuelos;
        mdlVuelos.setRowCount(0);
        for (int i = 0; i < vuelos.size(); i++) {
            Vuelo v = vuelos.get(i);
            String avion = "Sin avion";
            if (v.getAvion() != null) {
                avion = v.getAvion().getMatricula();
            }
            Object[] fila = {v.getCodigo(), Utilidades.textoRuta(v.getRuta()), Utilidades.textoSalida(v),
                Utilidades.textoFechaHora(v.calcularFechaHoraLlegada()), avion, v.getCapacidad(), v.asientosLibres(),
                Utilidades.textoEnum(v.getEstadoVuelo()), Utilidades.textoEnum(v.getEstadoEmbarque())};
            mdlVuelos.addRow(fila);
        }
        Utilidades.ajustarColumnas(tblVuelos);
    }

    // Devuelve el vuelo seleccionado en la tabla (o null si no hay ninguno)
    private Vuelo vueloSeleccionado(){
        int fila = tblVuelos.getSelectedRow();
        if (fila == -1) {
            Utilidades.mostrarError(this, "Seleccione un vuelo de la tabla");
            return null;
        }
        return vuelosMostrados.get(fila); // la fila de la tabla coincide con la posicion en la lista
    }

    // ---------------- Eventos ----------------

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == cmbTipoFiltro) {
            cargarValoresFiltro();
        } else if (e.getSource() == btnFiltrar) {
            filtrar();
        } else if (e.getSource() == btnBuscar) {
            buscar();
        } else if (e.getSource() == btnAvanzarEstado) {
            avanzarEstado();
        } else if (e.getSource() == btnAvanzarEmbarque) {
            avanzarEmbarque();
        } else if (e.getSource() == btnVerTripulacion) {
            verTripulacion();
        } else if (e.getSource() == btnVerPasajes) {
            verPasajes();
        } else if (e.getSource() == btnAsignarAvion) {
            asignarAvion();
        } else if (e.getSource() == btnRegistrar) {
            registrar();
        }
    }

    private void registrar(){
        try {
            String codigo = Utilidades.leerTexto(txtCodigo, "Codigo").toUpperCase();
            int posRuta = cmbRuta.getSelectedIndex();
            if (posRuta == -1) {
                throw new IllegalArgumentException("Primero registre una ruta");
            }
            Ruta ruta = Utilidades.segura(aerolinea.getRutas()).get(posRuta);
            Fecha fecha = Utilidades.leerFecha(txtFecha);
            Hora hora = Utilidades.leerHora(txtHora);
            int capacidad = Utilidades.leerEntero(txtCapacidad, "Capacidad");

            // Posicion 0 es "(sin avion por ahora)"
            Avion avion = null;
            int posAvion = cmbAvionRegistro.getSelectedIndex();
            if (posAvion > 0) {
                avion = avionesLibres.get(posAvion - 1);
            }

            Vuelo vuelo = new Vuelo(codigo, fecha, hora, capacidad, avion, ruta, new ArrayList<Tripulante>(), new ArrayList<Pasaje>());
            if (aerolinea.registrarVuelo(vuelo)) {
                Utilidades.mostrarMensaje(this, "Vuelo " + codigo + " registrado\nDuracion: " + vuelo.calcularDuracion() + " min\nLlegada estimada: "
                    + Utilidades.textoFechaHora(vuelo.calcularFechaHoraLlegada()));
                txtCodigo.setText("");
                txtFecha.setText("");
                txtHora.setText("");
                txtCapacidad.setText("");
                actualizar();
            } else {
                Utilidades.mostrarError(this, "No se pudo registrar el vuelo. Revise que:\n"
                    + "- el codigo tenga el formato VUE-045 y no exista ya\n"
                    + "- la salida no sea anterior al momento actual\n"
                    + "- la capacidad sea mayor que 0\n"
                    + "- el avion elegido este libre y tenga capacidad suficiente");
            }
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void filtrar(){
        int tipo = cmbTipoFiltro.getSelectedIndex();
        int pos = cmbValorFiltro.getSelectedIndex();
        if (tipo == 0 || pos == -1) {
            llenarTabla(Utilidades.segura(aerolinea.getVuelos()));
        } else if (tipo == 1) {
            llenarTabla(Utilidades.segura(aerolinea.filtrarVuelosPorSalida(Utilidades.segura(aerolinea.getCiudades()).get(pos))));
        } else if (tipo == 2) {
            llenarTabla(Utilidades.segura(aerolinea.filtrarVuelosPorLlegada(Utilidades.segura(aerolinea.getCiudades()).get(pos))));
        } else {
            llenarTabla(Utilidades.segura(aerolinea.filtrarVuelosPorAvion(Utilidades.segura(aerolinea.getAviones()).get(pos))));
        }
    }

    private void buscar(){
        try {
            String codigo = Utilidades.leerTexto(txtBuscar, "Codigo").toUpperCase();
            Vuelo vuelo = aerolinea.buscarVuelo(codigo);
            if (vuelo == null) {
                Utilidades.mostrarError(this, "No existe un vuelo con el codigo " + codigo);
                return;
            }
            ArrayList<Vuelo> encontrado = new ArrayList<Vuelo>();
            encontrado.add(vuelo);
            llenarTabla(encontrado);
        } catch (IllegalArgumentException ex) {
            Utilidades.mostrarError(this, ex.getMessage());
        }
    }

    private void avanzarEstado(){
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        if (!Utilidades.confirmar(this, "Avanzar el estado del vuelo " + vuelo.getCodigo() + " al siguiente estado?")) {
            return;
        }
        if (aerolinea.actualizarEstadoVuelo(vuelo.getCodigo())) {
            filtrar();
        } else {
            Utilidades.mostrarError(this, "No se pudo avanzar el estado.\nPara pasar a Despegue el vuelo necesita avion asignado,\ntripulacion completa (1 capitan, 1 copiloto y al menos 1 azafata)\ny las maletas a bordo.");
        }
    }

    private void avanzarEmbarque(){
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        if (!Utilidades.confirmar(this, "Avanzar el embarque de maletas del vuelo " + vuelo.getCodigo() + " al siguiente estado?")) {
            return;
        }
        if (aerolinea.actualizarEstadoEmbarque(vuelo.getCodigo())) {
            filtrar();
        } else {
            Utilidades.mostrarError(this, "No se pudo avanzar el embarque.\n- Durante embarque: el vuelo debe estar Abordando\n- A bordo: el vuelo Abordando y el embarque en curso\n- Durante desembarque: el vuelo en Aterrizaje o En el destino");
        }
    }

    private void verTripulacion(){
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Identificacion", "Nombre", "Rol"}, 0);
        ArrayList<Tripulante> tripulantes = Utilidades.segura(vuelo.getTripulantes());
        for (int i = 0; i < tripulantes.size(); i++) {
            Tripulante t = tripulantes.get(i);
            Object[] fila = {t.getId(), t.getNombre(), Utilidades.textoEnum(t.getRol())};
            modelo.addRow(fila);
        }
        String estado = "INCOMPLETA";
        if (vuelo.tripulacionCompleta()) {
            estado = "completa";
        }
        Utilidades.mostrarTabla(this, "Tripulacion de " + vuelo.getCodigo() + " (" + estado + ")", modelo);
    }

    private void verPasajes(){
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        ArrayList<Pasaje> pasajes = Utilidades.segura(vuelo.getPasajes());
        Utilidades.mostrarTabla(this, "Pasajes del vuelo " + vuelo.getCodigo(), Utilidades.crearModeloPasajes(pasajes));
    }

    private void asignarAvion(){
        Vuelo vuelo = vueloSeleccionado();
        if (vuelo == null) {
            return;
        }
        int pos = cmbAvionAsignar.getSelectedIndex();
        if (pos == -1) {
            Utilidades.mostrarError(this, "No hay aviones libres para asignar");
            return;
        }
        Avion avion = avionesLibres.get(pos);
        if (aerolinea.asignarAvionAVuelo(vuelo.getCodigo(), avion.getMatricula())) {
            Utilidades.mostrarMensaje(this, "Avion " + avion.getMatricula() + " asignado al vuelo " + vuelo.getCodigo());
            actualizar();
        } else {
            Utilidades.mostrarError(this, "No se pudo asignar el avion.\nDebe estar libre, cubrir la capacidad del vuelo y los pasajes prioritarios ya registrados,\ny el vuelo no puede haber despegado.");
        }
    }
}

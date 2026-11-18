package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JPAvionesYVuelos extends JPanel
{
    private JPanel panelIzquierda, panelCentro, panelRegistroAvion, panelSub1RA, panelSub2RA 
    ,panelProgramarVuelo, panelSub1PV, panelSub2PV, 
    panelAvionesYDisponibilidad, panelVuelosProgramados, panelAcciones, 
    panelSub1AYD, panelSub2AYD, panelSub1VP, panelSub2VP, panelSub1A, 
    panelSub2A, panelSub3A;
    private JLabel lblMatricula, lblModelo, lblColor, lblCapacidadTotal, 
    lblAsientoPrioritarios, lblCodigo, lblRuta, lblFecha, 
    lblHora, lblCapacidad, lblAvionLibreOp, lblMostrar, 
    lblOrigen, lblDestino, lblAvion, lblAvionLibre;
    private JTextField txtMatricula, txtModelo, txtColor, txtCapacidadTotal, txtAsientosPrioritarios,
    txtCodigo, txtFecha, txtHora, txtCapacidad;
    private JComboBox cbRuta, cbAvionLibreOp, cbMostrar, cbOrigen, cbDestino, cbAvion, cbAvionLibre;
    private JButton btnRegistrarAvion, btnCrearVuelo, btnFiltrar, btnTodos, btnAsignar, btnSiguienteFase, btnVerTripulacion;
    private JScrollPane scrollAviones, scrollVuelos;
    private JTable tablaAviones, tablaVuelos;
    private DefaultTableModel modeloAviones, modeloVuelos;

    public JPAvionesYVuelos(){
        setLayout(new BorderLayout());
        initComponentes();
    }

    private void initComponentes(){
        //PANEL IZQUIERDO
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
        panelIzquierda.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        panelRegistroAvion = new JPanel();
        panelRegistroAvion.setLayout(new BoxLayout(panelRegistroAvion, BoxLayout.Y_AXIS));
        panelRegistroAvion.setBorder(BorderFactory.createTitledBorder("Registrar Avion"));

        panelSub1RA = new JPanel(new GridLayout(5,2,5,5));
        lblMatricula = new JLabel("Matrícula:");
        lblModelo = new JLabel("Modelo:");
        lblColor = new JLabel("Color:");
        lblCapacidadTotal = new JLabel("Capacidad total:");
        lblAsientoPrioritarios = new JLabel("Asientos prioritarios:");
        txtMatricula = new JTextField();
        txtModelo = new JTextField();
        txtColor = new JTextField();
        txtCapacidadTotal = new JTextField();
        txtAsientosPrioritarios = new JTextField();
        panelSub1RA.add(lblMatricula); panelSub1RA.add(txtMatricula);
        panelSub1RA.add(lblModelo); panelSub1RA.add(txtModelo);
        panelSub1RA.add(lblColor); panelSub1RA.add(txtColor);
        panelSub1RA.add(lblCapacidadTotal); panelSub1RA.add(txtCapacidadTotal);
        panelSub1RA.add(lblAsientoPrioritarios); panelSub1RA.add(txtAsientosPrioritarios);
        panelRegistroAvion.add(panelSub1RA);

        panelSub2RA = new JPanel(new BorderLayout());
        btnRegistrarAvion = new JButton("Registrar Avion");
        btnRegistrarAvion.setBackground(new Color(14, 4, 124));
        btnRegistrarAvion.setForeground(Color.WHITE);
        btnRegistrarAvion.setFocusPainted(false);
        panelSub2RA.add(btnRegistrarAvion, BorderLayout.CENTER);
        panelRegistroAvion.add(panelSub2RA);

        panelRegistroAvion.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelRegistroAvion.getPreferredSize().height));
        panelIzquierda.add(panelRegistroAvion);
        panelIzquierda.add(Box.createRigidArea(new Dimension(0, 15)));

        panelProgramarVuelo = new JPanel();
        panelProgramarVuelo.setLayout(new BoxLayout(panelProgramarVuelo, BoxLayout.Y_AXIS));
        panelProgramarVuelo.setBorder(BorderFactory.createTitledBorder("Programar Nuevo Vuelo"));

        panelSub1PV = new JPanel(new GridLayout(6,2,5,5));
        lblCodigo = new JLabel("Código (VUE-XXX):");
        lblRuta = new JLabel("Ruta:");
        lblFecha = new JLabel("Fecha(DD/MM/AAAA):");
        lblHora = new JLabel("Hora(HH:MM):");
        lblCapacidad = new JLabel("Capacidad:");
        lblAvionLibreOp = new JLabel("Avión libre(Opcional):");
        txtCodigo = new JTextField();
        txtFecha = new JTextField();
        txtHora = new JTextField();
        txtCapacidad = new JTextField();
        cbRuta = new JComboBox();
        cbAvionLibreOp = new JComboBox();
        panelSub1PV.add(lblCodigo); panelSub1PV.add(txtCodigo);
        panelSub1PV.add(lblRuta); panelSub1PV.add(cbRuta);
        panelSub1PV.add(lblFecha); panelSub1PV.add(txtFecha);
        panelSub1PV.add(lblHora); panelSub1PV.add(txtHora);
        panelSub1PV.add(lblCapacidad); panelSub1PV.add(txtCapacidad);
        panelSub1PV.add(lblAvionLibreOp); panelSub1PV.add(cbAvionLibreOp);
        panelProgramarVuelo.add(panelSub1PV);

        panelSub2PV = new JPanel(new BorderLayout());
        btnCrearVuelo = new JButton("Crear Vuelo");
        btnCrearVuelo.setBackground(new Color(14, 4, 124));
        btnCrearVuelo.setForeground(Color.WHITE);
        btnCrearVuelo.setFocusPainted(false);
        panelSub2PV.add(btnCrearVuelo, BorderLayout.CENTER);
        panelProgramarVuelo.add(panelSub2PV);

        panelProgramarVuelo.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelProgramarVuelo.getPreferredSize().height));
        panelIzquierda.add(panelProgramarVuelo);
        panelIzquierda.add(Box.createVerticalGlue());

        //PANEL CENTRO (Con GridBagLayout para repartir altura) 
        panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 10, 0); 

        // 1. Aviones y Disponibilidad
        panelAvionesYDisponibilidad = new JPanel(new BorderLayout());
        panelAvionesYDisponibilidad.setBorder(BorderFactory.createTitledBorder("Aviones y Disponibilidad"));

        panelSub1AYD = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        lblMostrar = new JLabel("Mostrar:");
        cbMostrar = new JComboBox();
        panelSub1AYD.add(lblMostrar);
        panelSub1AYD.add(cbMostrar);
        panelAvionesYDisponibilidad.add(panelSub1AYD, BorderLayout.NORTH);

        panelSub2AYD = new JPanel(new BorderLayout());
        String [] columnasAviones = {"Matricula","Modelo","Color","Capacidad","Prioritarios","Estado Operativo"};
        modeloAviones = new DefaultTableModel(columnasAviones,0){
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaAviones = new JTable(modeloAviones);
        scrollAviones = new JScrollPane(tablaAviones);
        panelSub2AYD.add(scrollAviones, BorderLayout.CENTER);
        panelAvionesYDisponibilidad.add(panelSub2AYD, BorderLayout.CENTER);

        gbc.gridy = 0;
        gbc.weighty = 0.5; 
        panelCentro.add(panelAvionesYDisponibilidad, gbc);

        // 2. Vuelos Programados
        panelVuelosProgramados = new JPanel(new BorderLayout());
        panelVuelosProgramados.setBorder(BorderFactory.createTitledBorder("Vuelos Programados"));

        panelSub1VP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblOrigen = new JLabel("Origen:");
        lblDestino = new JLabel("Destino:");
        lblAvion = new JLabel("Avión:");
        cbOrigen = new JComboBox();
        cbDestino = new JComboBox();
        cbAvion = new JComboBox();
        btnFiltrar = new JButton("Filtrar");
        btnTodos = new JButton("Todos");

        panelSub1VP.add(lblOrigen); panelSub1VP.add(cbOrigen);
        panelSub1VP.add(lblDestino); panelSub1VP.add(cbDestino);
        panelSub1VP.add(lblAvion); panelSub1VP.add(cbAvion);
        panelSub1VP.add(btnFiltrar); panelSub1VP.add(btnTodos);
        panelVuelosProgramados.add(panelSub1VP, BorderLayout.NORTH);

        panelSub2VP = new JPanel(new BorderLayout());
        String [] columnasVuelos = {"Codigo","Ruta","Salida","Llegada Est.", "Avion","Capacidad","Ocupacion","Estado Vuelo","Maletas"};
        modeloVuelos = new DefaultTableModel(columnasVuelos,0){
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaVuelos = new JTable(modeloVuelos);
        scrollVuelos = new JScrollPane(tablaVuelos);
        panelSub2VP.add(scrollVuelos, BorderLayout.CENTER);
        panelVuelosProgramados.add(panelSub2VP, BorderLayout.CENTER);

        gbc.gridy = 1;
        gbc.weighty = 0.5; 
        panelCentro.add(panelVuelosProgramados, gbc);

        // 3. Acciones sobre el vuelo
        panelAcciones = new JPanel();
        panelAcciones.setLayout(new BoxLayout(panelAcciones, BoxLayout.Y_AXIS));
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones sobre el vuelo seleccionado en la tabla"));

        panelSub1A = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblAvionLibre = new JLabel("Avión libre:");
        cbAvionLibre = new JComboBox();
        btnAsignar = new JButton("Asignar / Cambiar Avión");
        panelSub1A.add(lblAvionLibre);
        panelSub1A.add(cbAvionLibre);
        panelSub1A.add(btnAsignar);

        panelSub2A = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnSiguienteFase = new JButton("Siguiente Fase del Vuelo ->");
        btnSiguienteFase.setBackground(new Color(14, 4, 124));
        btnSiguienteFase.setForeground(Color.WHITE);
        btnSiguienteFase.setFocusPainted(false);
        btnVerTripulacion = new JButton("Ver Tripulación del Vuelo");
        panelSub2A.add(btnSiguienteFase);
        panelSub2A.add(btnVerTripulacion);

        panelSub3A = new JPanel(new BorderLayout());
        JTextArea txtInfoFases = new JTextArea("Cada clic avanza una fase: DISPONIBLE -> ABORDANDO -> DESPEGUE -> EN_EL_AIRE -> ATERRIZAJE -> EN_EL_DESTINO. Las ventas de pasajes se cierran en DESPEGUE, las maletas cambian solas y, al llegar al destino, se liberan el avión y la tripulación.");
        txtInfoFases.setLineWrap(true);
        txtInfoFases.setWrapStyleWord(true);
        txtInfoFases.setEditable(false);
        txtInfoFases.setOpaque(false);
        txtInfoFases.setForeground(Color.GRAY);
        txtInfoFases.setFont(new Font("SansSerif", Font.ITALIC, 11));
        panelSub3A.add(txtInfoFases, BorderLayout.CENTER);

        panelAcciones.add(panelSub1A);
        panelAcciones.add(panelSub2A);
        panelAcciones.add(panelSub3A);

        gbc.gridy = 2;
        gbc.weighty = 0.0; 
        gbc.insets = new Insets(0, 0, 0, 0);
        panelCentro.add(panelAcciones, gbc);

        this.add(panelCentro, BorderLayout.CENTER);
        this.add(panelIzquierda, BorderLayout.WEST);
    }

    public JPanel getPanelIzquierda() {
        return panelIzquierda;
    }

    public JPanel getPanelCentro() {
        return panelCentro;
    }

    public JPanel getPanelRegistroAvion() {
        return panelRegistroAvion;
    }

    public JPanel getPanelSub1RA() {
        return panelSub1RA;
    }

    public JPanel getPanelSub2RA() {
        return panelSub2RA;
    }

    public JPanel getPanelProgramarVuelo() {
        return panelProgramarVuelo;
    }

    public JPanel getPanelSub1PV() {
        return panelSub1PV;
    }

    public JPanel getPanelSub2PV() {
        return panelSub2PV;
    }

    public JPanel getPanelAvionesYDisponibilidad() {
        return panelAvionesYDisponibilidad;
    }

    public JPanel getPanelVuelosProgramados() {
        return panelVuelosProgramados;
    }

    public JPanel getPanelAcciones() {
        return panelAcciones;
    }

    public JPanel getPanelSub1AYD() {
        return panelSub1AYD;
    }

    public JPanel getPanelSub2AYD() {
        return panelSub2AYD;
    }

    public JPanel getPanelSub1VP() {
        return panelSub1VP;
    }

    public JPanel getPanelSub2VP() {
        return panelSub2VP;
    }

    public JPanel getPanelSub1A() {
        return panelSub1A;
    }

    public JPanel getPanelSub2A() {
        return panelSub2A;
    }

    public JPanel getPanelSub3A() {
        return panelSub3A;
    }

    public JLabel getLblMatricula() {
        return lblMatricula;
    }

    public JLabel getLblModelo() {
        return lblModelo;
    }

    public JLabel getLblColor() {
        return lblColor;
    }

    public JLabel getLblCapacidadTotal() {
        return lblCapacidadTotal;
    }

    public JLabel getLblAsientoPrioritarios() {
        return lblAsientoPrioritarios;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public JLabel getLblRuta() {
        return lblRuta;
    }

    public JLabel getLblFecha() {
        return lblFecha;
    }

    public JLabel getLblHora() {
        return lblHora;
    }

    public JLabel getLblCapacidad() {
        return lblCapacidad;
    }

    public JLabel getLblAvionLibreOp() {
        return lblAvionLibreOp;
    }

    public JLabel getLblMostrar() {
        return lblMostrar;
    }

    public JLabel getLblOrigen() {
        return lblOrigen;
    }

    public JLabel getLblDestino() {
        return lblDestino;
    }

    public JLabel getLblAvion() {
        return lblAvion;
    }

    public JLabel getLblAvionLibre() {
        return lblAvionLibre;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public JTextField getTxtModelo() {
        return txtModelo;
    }

    public JTextField getTxtColor() {
        return txtColor;
    }

    public JTextField getTxtCapacidadTotal() {
        return txtCapacidadTotal;
    }

    public JTextField getTxtAsientosPrioritarios() {
        return txtAsientosPrioritarios;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JTextField getTxtHora() {
        return txtHora;
    }

    public JTextField getTxtCapacidad() {
        return txtCapacidad;
    }

    public JComboBox getCbRuta() {
        return cbRuta;
    }

    public JComboBox getCbAvionLibreOp() {
        return cbAvionLibreOp;
    }

    public JComboBox getCbMostrar() {
        return cbMostrar;
    }

    public JComboBox getCbOrigen() {
        return cbOrigen;
    }

    public JComboBox getCbDestino() {
        return cbDestino;
    }

    public JComboBox getCbAvion() {
        return cbAvion;
    }

    public JComboBox getCbAvionLibre() {
        return cbAvionLibre;
    }

    public JButton getBtnRegistrarAvion() {
        return btnRegistrarAvion;
    }

    public JButton getBtnCrearVuelo() {
        return btnCrearVuelo;
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }

    public JButton getBtnTodos() {
        return btnTodos;
    }

    public JButton getBtnAsignar() {
        return btnAsignar;
    }

    public JButton getBtnSiguienteFase() {
        return btnSiguienteFase;
    }

    public JButton getBtnVerTripulacion() {
        return btnVerTripulacion;
    }

    public JScrollPane getScrollAviones() {
        return scrollAviones;
    }

    public JScrollPane getScrollVuelos() {
        return scrollVuelos;
    }

    public JTable getTablaAviones() {
        return tablaAviones;
    }

    public JTable getTablaVuelos() {
        return tablaVuelos;
    }

    public DefaultTableModel getModeloAviones() {
        return modeloAviones;
    }

    public DefaultTableModel getModeloVuelos() {
        return modeloVuelos;
    }
}
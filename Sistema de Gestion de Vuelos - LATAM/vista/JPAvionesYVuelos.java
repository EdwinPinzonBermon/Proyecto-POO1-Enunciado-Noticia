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
    panelSub2A, panelSub3A, panelBusquedaVuelo;
    private JLabel lblMatricula, lblModelo, lblColor, lblCapacidadTotal, 
    lblAsientoPrioritarios, lblCodigo, lblRuta, lblFecha, 
    lblHora, lblCapacidad, lblAvionLibreOp, lblMostrar, 
    lblOrigen, lblDestino, lblAvion, lblAvionLibre;
    private JTextField txtMatricula, txtModelo, txtColor, txtCapacidadTotal, txtAsientosPrioritarios,
    txtCodigo, txtFecha, txtHora, txtCapacidad, txtBuscarMatricula, txtBuscarCodigoVuelo;
    private JComboBox cbRuta, cbAvionLibreOp, cbMostrar, cbOrigen, cbDestino, cbAvion, cbAvionLibre;
    private JButton btnRegistrarAvion, btnCrearVuelo, btnFiltrar, btnTodos, btnAsignar, btnSiguienteFase, btnAvanzarEmbarque, btnVerTripulacion, btnBuscarAvion, btnBuscarVuelo;
    private JScrollPane scrollAviones, scrollVuelos;
    private JSplitPane separadorVertical;
    private JTable tablaAviones, tablaVuelos;
    private DefaultTableModel modeloAviones, modeloVuelos;

    public JPAvionesYVuelos(){
        setLayout(new BorderLayout());
        initComponentes();
    }

    private void initComponentes(){
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
        panelIzquierda.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelIzquierda.setPreferredSize(new Dimension(420, 0));
        panelIzquierda.setMinimumSize(new Dimension(360, 0));

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
        panelSub1RA.add(lblMatricula);
        panelSub1RA.add(txtMatricula);
        panelSub1RA.add(lblModelo);
        panelSub1RA.add(txtModelo);
        panelSub1RA.add(lblColor);
        panelSub1RA.add(txtColor);
        panelSub1RA.add(lblCapacidadTotal);
        panelSub1RA.add(txtCapacidadTotal);
        panelSub1RA.add(lblAsientoPrioritarios);
        panelSub1RA.add(txtAsientosPrioritarios);
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
        panelSub1PV.add(lblCodigo);
        panelSub1PV.add(txtCodigo);
        panelSub1PV.add(lblRuta);
        panelSub1PV.add(cbRuta);
        panelSub1PV.add(lblFecha);
        panelSub1PV.add(txtFecha);
        panelSub1PV.add(lblHora);
        panelSub1PV.add(txtHora);
        panelSub1PV.add(lblCapacidad);
        panelSub1PV.add(txtCapacidad);
        panelSub1PV.add(lblAvionLibreOp);
        panelSub1PV.add(cbAvionLibreOp);
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

        panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panelAvionesYDisponibilidad = new JPanel(new BorderLayout());
        panelAvionesYDisponibilidad.setBorder(BorderFactory.createTitledBorder("Aviones y Disponibilidad"));
        
        panelSub1AYD = new JPanel(); 
        panelSub1AYD.setLayout(new BoxLayout(panelSub1AYD, BoxLayout.X_AXIS));
        lblMostrar = new JLabel("Mostrar:");
        cbMostrar = new JComboBox();
        panelSub1AYD.add(lblMostrar);
        panelSub1AYD.add(Box.createHorizontalStrut(10));
        panelSub1AYD.add(cbMostrar);
        panelSub1AYD.add(Box.createHorizontalStrut(15));
        panelSub1AYD.add(new JLabel("Buscar matrícula:"));
        txtBuscarMatricula = new JTextField(8);
        btnBuscarAvion = new JButton("Buscar avión");
        panelSub1AYD.add(txtBuscarMatricula);
        panelSub1AYD.add(btnBuscarAvion);
        panelAvionesYDisponibilidad.add(panelSub1AYD, BorderLayout.NORTH);
        
        panelSub2AYD = new JPanel(new BorderLayout());
        String [] columnasAviones = {"Matricula","Modelo","Color","Capacidad","Prioritarios","Estado Operativo"};
        modeloAviones = new DefaultTableModel(columnasAviones,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAviones = new JTable(modeloAviones);
        scrollAviones = new JScrollPane(tablaAviones);
        tablaAviones.setRowHeight(22);
        tablaAviones.setFillsViewportHeight(true);
        scrollAviones.setPreferredSize(new Dimension(500, 190));
        panelSub2AYD.add(scrollAviones, BorderLayout.CENTER);
        panelAvionesYDisponibilidad.add(panelSub2AYD, BorderLayout.CENTER);

        panelVuelosProgramados = new JPanel(new BorderLayout(5, 5));
        panelVuelosProgramados.setBorder(BorderFactory.createTitledBorder("Vuelos programados"));
        
        panelSub1VP = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        lblOrigen = new JLabel("Origen:");
        lblDestino = new JLabel("Destino:");
        lblAvion = new JLabel("Avion:");
        cbOrigen = new JComboBox();
        cbDestino = new JComboBox();
        cbAvion = new JComboBox();
        btnFiltrar = new JButton("Filtrar");
        btnTodos = new JButton("Todos");
        panelSub1VP.add(lblOrigen);
        panelSub1VP.add(Box.createHorizontalStrut(5));
        panelSub1VP.add(cbOrigen);
        panelSub1VP.add(Box.createHorizontalStrut(8));
        panelSub1VP.add(lblDestino);
        panelSub1VP.add(Box.createHorizontalStrut(5));
        panelSub1VP.add(cbDestino);
        panelSub1VP.add(Box.createHorizontalStrut(8));
        panelSub1VP.add(lblAvion);
        panelSub1VP.add(Box.createHorizontalStrut(5));
        panelSub1VP.add(cbAvion);
        panelSub1VP.add(Box.createHorizontalStrut(8));
        panelSub1VP.add(btnFiltrar);
        panelSub1VP.add(Box.createHorizontalStrut(5));
        panelSub1VP.add(btnTodos);

        JPanel panelFiltrosYBusqueda = new JPanel();
        panelFiltrosYBusqueda.setLayout(new BoxLayout(panelFiltrosYBusqueda, BoxLayout.Y_AXIS));
        panelFiltrosYBusqueda.add(panelSub1VP);

        panelBusquedaVuelo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 3));
        panelBusquedaVuelo.add(new JLabel("Buscar código de vuelo:"));
        txtBuscarCodigoVuelo = new JTextField(10);
        btnBuscarVuelo = new JButton("Buscar vuelo");
        panelBusquedaVuelo.add(txtBuscarCodigoVuelo);
        panelBusquedaVuelo.add(btnBuscarVuelo);
        panelFiltrosYBusqueda.add(panelBusquedaVuelo);
        panelVuelosProgramados.add(panelFiltrosYBusqueda, BorderLayout.NORTH);

        panelSub2VP = new JPanel(new BorderLayout());
        String [] columnasVuelos = {"Codigo","Ruta","Salida","Llegada Est.", "Avion","Capacidad","Ocupacion","Estado Vuelo","Maletas"};
        modeloVuelos = new DefaultTableModel(columnasVuelos,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaVuelos = new JTable(modeloVuelos);
        tablaVuelos.setRowHeight(22);
        tablaVuelos.setFillsViewportHeight(true);
        scrollVuelos = new JScrollPane(tablaVuelos);
        scrollVuelos.setPreferredSize(new Dimension(500, 230));
        panelSub2VP.add(scrollVuelos, BorderLayout.CENTER);
        panelVuelosProgramados.add(panelSub2VP, BorderLayout.CENTER);

        separadorVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                panelAvionesYDisponibilidad, panelVuelosProgramados);
        separadorVertical.setResizeWeight(0.42);
        separadorVertical.setDividerLocation(0.42);
        separadorVertical.setContinuousLayout(true);
        separadorVertical.setOneTouchExpandable(true);
        separadorVertical.setDividerSize(8);
        separadorVertical.setBorder(null);
        panelAvionesYDisponibilidad.setMinimumSize(new Dimension(250, 150));
        panelVuelosProgramados.setMinimumSize(new Dimension(250, 190));
        panelCentro.add(separadorVertical, BorderLayout.CENTER);

        panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones sobre el vuelo seleccionado"));
        lblAvionLibre = new JLabel("Avión libre:");
        cbAvionLibre = new JComboBox();
        cbAvionLibre.setPreferredSize(new Dimension(190, 26));
        btnAsignar = new JButton("Asignar / cambiar avión");
        btnSiguienteFase = new JButton("Avanzar estado del vuelo");
        btnAvanzarEmbarque = new JButton("Avanzar estado de maletas");
        btnVerTripulacion = new JButton("Ver tripulación");
        btnAsignar.setPreferredSize(new Dimension(150, 26));
        btnSiguienteFase.setPreferredSize(new Dimension(160, 26));
        btnAvanzarEmbarque.setPreferredSize(new Dimension(160, 26));
        btnVerTripulacion.setPreferredSize(new Dimension(125, 26));
        btnAsignar.setBackground(new Color(14, 4, 124));
        btnAsignar.setForeground(Color.WHITE);
        btnAsignar.setFocusPainted(false);

        btnSiguienteFase.setBackground(new Color(14, 4, 124));
        btnSiguienteFase.setForeground(Color.WHITE);
        btnSiguienteFase.setFocusPainted(false);

        btnAvanzarEmbarque.setBackground(new Color(14, 4, 124));
        btnAvanzarEmbarque.setForeground(Color.WHITE);
        btnAvanzarEmbarque.setFocusPainted(false);

        btnVerTripulacion.setBackground(new Color(14, 4, 124));
        btnVerTripulacion.setForeground(Color.WHITE);
        btnVerTripulacion.setFocusPainted(false);
        panelAcciones.add(lblAvionLibre);
        panelAcciones.add(cbAvionLibre);
        panelAcciones.add(btnAsignar);
        panelAcciones.add(btnSiguienteFase);
        panelAcciones.add(btnAvanzarEmbarque);
        panelAcciones.add(btnVerTripulacion);

        this.add(panelCentro, BorderLayout.CENTER);
        this.add(panelIzquierda, BorderLayout.WEST);
        this.add(panelAcciones, BorderLayout.SOUTH);
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

    public JButton getBtnAvanzarEmbarque() {
        return btnAvanzarEmbarque;
    }

    public JTextField getTxtBuscarMatricula() { return txtBuscarMatricula; }
    public JTextField getTxtBuscarCodigoVuelo() { return txtBuscarCodigoVuelo; }
    public JButton getBtnBuscarAvion() { return btnBuscarAvion; }
    public JButton getBtnBuscarVuelo() { return btnBuscarVuelo; }

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
package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JPPasajesYAsientos extends JPanel
{
    private JPanel panelVuelo, panelIzquierda, panelDerecha, panelVenderPasaje, panelSub1VP, panelSub2VP, 
    panelMapaAsientos, panelLeyenda, panelMapaContenedor, panelPasajesVuelo, panelBotonesDerecha;
    private JLabel lblSeleccionarVuelo, lblMensajeVuelos, lblIdPasajero, lblNombreCompleto, lblAsiento, 
    lblClase, lblPrecioBase, lblPrecioFinalTitulo, lblPrecioFinal, lblLeyendaPrioritaria, 
    lblLeyendaEconomica, lblLeyendaOcupada;
    private JTextField txtIdPasajero, txtNombreCompleto, txtAsiento, txtPrecioBase;
    private JComboBox cbVuelo, cbClase;
    private JButton btnConfirmarPago, btnVerHistorial, btnCancelarPasaje;
    private JScrollPane scrollPasajes;
    private JTable tablaPasajes;
    private DefaultTableModel modeloPasajes;

    public JPPasajesYAsientos(){
        setLayout(new BorderLayout());
        initComponentes();
    }
    
    private void initComponentes(){
        panelVuelo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelVuelo.setBorder(BorderFactory.createTitledBorder("Vuelo"));
        
        lblSeleccionarVuelo = new JLabel("Seleccionar vuelo:");
        cbVuelo = new JComboBox();
        cbVuelo.setPreferredSize(new Dimension(400, 25));
        
        lblMensajeVuelos = new JLabel("Sin vuelos: primero programa uno en 'Aviones y Vuelos'");
        lblMensajeVuelos.setForeground(new Color(0, 0, 150));
        lblMensajeVuelos.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        panelVuelo.add(lblSeleccionarVuelo);
        panelVuelo.add(cbVuelo);
        panelVuelo.add(Box.createHorizontalStrut(20));
        panelVuelo.add(lblMensajeVuelos);
        
        this.add(panelVuelo, BorderLayout.NORTH);

        panelIzquierda = new JPanel(new GridBagLayout());
        panelIzquierda.setPreferredSize(new Dimension(450, 0));
        GridBagConstraints gbcIzq = new GridBagConstraints();
        gbcIzq.fill = GridBagConstraints.BOTH;
        gbcIzq.weightx = 1.0;
        gbcIzq.gridx = 0;
        gbcIzq.insets = new Insets(0, 0, 10, 0);

        panelVenderPasaje = new JPanel(new BorderLayout());
        panelVenderPasaje.setBorder(BorderFactory.createTitledBorder("Vender Pasaje (pago en efectivo)"));
        
        panelSub1VP = new JPanel(new GridLayout(6, 2, 5, 10));
        panelSub1VP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblIdPasajero = new JLabel("ID Pasajero");
        lblNombreCompleto = new JLabel("Nombre Completo");
        lblAsiento = new JLabel("N.º de Asiento");
        lblClase = new JLabel("Clase");
        lblPrecioBase = new JLabel("Precio Base ($)");
        lblPrecioFinalTitulo = new JLabel("Precio Final");
        
        txtIdPasajero = new JTextField();
        txtNombreCompleto = new JTextField();
        txtAsiento = new JTextField();
        
        cbClase = new JComboBox();
        cbClase.addItem("ECONOMICA");
        cbClase.addItem("PRIORITARIA");
        
        txtPrecioBase = new JTextField();
        
        lblPrecioFinal = new JLabel("$0");
        lblPrecioFinal.setForeground(new Color(0, 128, 0));
        lblPrecioFinal.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        panelSub1VP.add(lblIdPasajero);
        panelSub1VP.add(txtIdPasajero);
        panelSub1VP.add(lblNombreCompleto);
        panelSub1VP.add(txtNombreCompleto);
        panelSub1VP.add(lblAsiento);
        panelSub1VP.add(txtAsiento);
        panelSub1VP.add(lblClase);
        panelSub1VP.add(cbClase);
        panelSub1VP.add(lblPrecioBase);
        panelSub1VP.add(txtPrecioBase);
        panelSub1VP.add(lblPrecioFinalTitulo);
        panelSub1VP.add(lblPrecioFinal);
        
        panelVenderPasaje.add(panelSub1VP, BorderLayout.CENTER);
        
        panelSub2VP = new JPanel(new BorderLayout());
        panelSub2VP.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        btnConfirmarPago = new JButton("Confirmar Pago y Emitir");
        btnConfirmarPago.setBackground(new Color(14, 4, 124));
        btnConfirmarPago.setForeground(Color.WHITE);
        btnConfirmarPago.setFocusPainted(false);
        panelSub2VP.add(btnConfirmarPago, BorderLayout.CENTER);
        
        panelVenderPasaje.add(panelSub2VP, BorderLayout.SOUTH);
        
        gbcIzq.gridy = 0;
        gbcIzq.weighty = 0.0;
        panelIzquierda.add(panelVenderPasaje, gbcIzq);

        panelMapaAsientos = new JPanel(new BorderLayout());
        panelMapaAsientos.setBorder(BorderFactory.createTitledBorder("Mapa de Asientos"));
        
        panelLeyenda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        lblLeyendaPrioritaria = new JLabel("  Prioritaria  ", SwingConstants.CENTER);
        lblLeyendaPrioritaria.setOpaque(true);
        lblLeyendaPrioritaria.setBackground(new Color(255, 235, 156));
        lblLeyendaPrioritaria.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        lblLeyendaEconomica = new JLabel("  Económica  ", SwingConstants.CENTER);
        lblLeyendaEconomica.setOpaque(true);
        lblLeyendaEconomica.setBackground(new Color(214, 235, 252));
        lblLeyendaEconomica.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        lblLeyendaOcupada = new JLabel("  Ocupada  ", SwingConstants.CENTER);
        lblLeyendaOcupada.setOpaque(true);
        lblLeyendaOcupada.setBackground(new Color(217, 24, 71));
        lblLeyendaOcupada.setForeground(Color.WHITE);
        lblLeyendaOcupada.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        panelLeyenda.add(lblLeyendaPrioritaria);
        panelLeyenda.add(lblLeyendaEconomica);
        panelLeyenda.add(lblLeyendaOcupada);
        
        panelMapaAsientos.add(panelLeyenda, BorderLayout.NORTH);
        
        panelMapaContenedor = new JPanel();
        JScrollPane scrollMapa = new JScrollPane(panelMapaContenedor);
        panelMapaAsientos.add(scrollMapa, BorderLayout.CENTER);
        
        gbcIzq.gridy = 1;
        gbcIzq.weighty = 1.0;
        gbcIzq.insets = new Insets(0, 0, 0, 0);
        panelIzquierda.add(panelMapaAsientos, gbcIzq);

        this.add(panelIzquierda, BorderLayout.WEST);

        panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        panelPasajesVuelo = new JPanel(new BorderLayout());
        panelPasajesVuelo.setBorder(BorderFactory.createTitledBorder("Pasajes del Vuelo"));
        
        String [] columnasPasajes = {"Asiento", "ID Pasajero", "Pasajero", "Clase", "Precio Base", "Precio Final", "Estado"};
        modeloPasajes = new DefaultTableModel(columnasPasajes, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPasajes = new JTable(modeloPasajes);
        scrollPasajes = new JScrollPane(tablaPasajes);
        
        panelPasajesVuelo.add(scrollPasajes, BorderLayout.CENTER);
        
        panelBotonesDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        btnVerHistorial = new JButton("Ver Historial del Pasajero");
        btnVerHistorial.setBackground(new Color(14, 4, 124));
        btnVerHistorial.setForeground(Color.WHITE);
        btnVerHistorial.setFocusPainted(false);
        
        btnCancelarPasaje = new JButton("Cancelar Pasaje Seleccionado");
        btnCancelarPasaje.setBackground(new Color(217, 24, 71));
        btnCancelarPasaje.setForeground(Color.WHITE);
        btnCancelarPasaje.setFocusPainted(false);
        
        panelBotonesDerecha.add(btnVerHistorial);
        panelBotonesDerecha.add(btnCancelarPasaje);
        
        panelPasajesVuelo.add(panelBotonesDerecha, BorderLayout.SOUTH);
        panelDerecha.add(panelPasajesVuelo, BorderLayout.CENTER);

        this.add(panelDerecha, BorderLayout.CENTER);
    }

    public JPanel getPanelVuelo() {
        return panelVuelo;
    }

    public JPanel getPanelIzquierda() {
        return panelIzquierda;
    }

    public JPanel getPanelDerecha() {
        return panelDerecha;
    }

    public JPanel getPanelVenderPasaje() {
        return panelVenderPasaje;
    }

    public JPanel getPanelSub1VP() {
        return panelSub1VP;
    }

    public JPanel getPanelSub2VP() {
        return panelSub2VP;
    }

    public JPanel getPanelMapaAsientos() {
        return panelMapaAsientos;
    }

    public JPanel getPanelLeyenda() {
        return panelLeyenda;
    }

    public JPanel getPanelMapaContenedor() {
        return panelMapaContenedor;
    }

    public JPanel getPanelPasajesVuelo() {
        return panelPasajesVuelo;
    }

    public JPanel getPanelBotonesDerecha() {
        return panelBotonesDerecha;
    }

    public JLabel getLblSeleccionarVuelo() {
        return lblSeleccionarVuelo;
    }

    public JLabel getLblMensajeVuelos() {
        return lblMensajeVuelos;
    }

    public JLabel getLblIdPasajero() {
        return lblIdPasajero;
    }

    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }

    public JLabel getLblAsiento() {
        return lblAsiento;
    }

    public JLabel getLblClase() {
        return lblClase;
    }

    public JLabel getLblPrecioBase() {
        return lblPrecioBase;
    }

    public JLabel getLblPrecioFinalTitulo() {
        return lblPrecioFinalTitulo;
    }

    public JLabel getLblPrecioFinal() {
        return lblPrecioFinal;
    }

    public JLabel getLblLeyendaPrioritaria() {
        return lblLeyendaPrioritaria;
    }

    public JLabel getLblLeyendaEconomica() {
        return lblLeyendaEconomica;
    }

    public JLabel getLblLeyendaOcupada() {
        return lblLeyendaOcupada;
    }

    public JTextField getTxtIdPasajero() {
        return txtIdPasajero;
    }

    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public JTextField getTxtAsiento() {
        return txtAsiento;
    }

    public JTextField getTxtPrecioBase() {
        return txtPrecioBase;
    }

    public JComboBox getCbVuelo() {
        return cbVuelo;
    }

    public JComboBox getCbClase() {
        return cbClase;
    }

    public JButton getBtnConfirmarPago() {
        return btnConfirmarPago;
    }

    public JButton getBtnVerHistorial() {
        return btnVerHistorial;
    }

    public JButton getBtnCancelarPasaje() {
        return btnCancelarPasaje;
    }

    public JScrollPane getScrollPasajes() {
        return scrollPasajes;
    }

    public JTable getTablaPasajes() {
        return tablaPasajes;
    }

    public DefaultTableModel getModeloPasajes() {
        return modeloPasajes;
    }
}
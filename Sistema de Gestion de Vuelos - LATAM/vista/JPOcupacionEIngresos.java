package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JPOcupacionEIngresos extends JPanel
{
    private JPanel panelMetricas, panelSub1M, panelSub2M, panelCaja1, panelCaja2, panelCaja3, 
    panelRanking, panelSub1R, panelSub2R;
    private JLabel lblSeleccionarVuelo, lblOcupacionTitulo, lblOcupacionValor, 
    lblIngresoTitulo, lblIngresoValor, lblAsientosTitulo, lblAsientosValor, lblMensajeRanking;
    private JComboBox cbVuelo;
    private JButton btnCalcularMetricas, btnActualizarRanking;
    private JScrollPane scrollRanking;
    private JTable tablaRanking;
    private DefaultTableModel modeloRanking;

    public JPOcupacionEIngresos(){
        setLayout(new BorderLayout());
        initComponentes();
    }

    private void initComponentes(){
        panelMetricas = new JPanel(new BorderLayout());
        panelMetricas.setBorder(BorderFactory.createTitledBorder("Ocupación e Ingreso Confirmado por Vuelo"));
        
        panelSub1M = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        lblSeleccionarVuelo = new JLabel("Seleccionar Vuelo:");
        lblSeleccionarVuelo.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        cbVuelo = new JComboBox();
        cbVuelo.setPreferredSize(new Dimension(350, 25));
        
        btnCalcularMetricas = new JButton("Calcular Métricas");
        btnCalcularMetricas.setBackground(new Color(14, 4, 124));
        btnCalcularMetricas.setForeground(Color.WHITE);
        btnCalcularMetricas.setFocusPainted(false);
        
        panelSub1M.add(lblSeleccionarVuelo);
        panelSub1M.add(cbVuelo);
        panelSub1M.add(btnCalcularMetricas);
        
        panelMetricas.add(panelSub1M, BorderLayout.NORTH);
        
        panelSub2M = new JPanel(new GridLayout(1, 3, 15, 0));
        panelSub2M.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));
        
        panelCaja1 = new JPanel(new GridLayout(2, 1, 0, 5));
        panelCaja1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        lblOcupacionTitulo = new JLabel("Ocupación del Vuelo");
        lblOcupacionTitulo.setForeground(Color.GRAY);
        lblOcupacionValor = new JLabel("0%");
        lblOcupacionValor.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblOcupacionValor.setForeground(new Color(14, 4, 124));
        panelCaja1.add(lblOcupacionTitulo);
        panelCaja1.add(lblOcupacionValor);
        
        panelCaja2 = new JPanel(new GridLayout(2, 1, 0, 5));
        panelCaja2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        lblIngresoTitulo = new JLabel("Ingreso Confirmado");
        lblIngresoTitulo.setForeground(Color.GRAY);
        lblIngresoValor = new JLabel("$ 0");
        lblIngresoValor.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblIngresoValor.setForeground(new Color(0, 128, 0));
        panelCaja2.add(lblIngresoTitulo);
        panelCaja2.add(lblIngresoValor);
        
        panelCaja3 = new JPanel(new GridLayout(2, 1, 0, 5));
        panelCaja3.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        lblAsientosTitulo = new JLabel("Asientos Pagados / Capacidad");
        lblAsientosTitulo.setForeground(Color.GRAY);
        lblAsientosValor = new JLabel("0 / 0");
        lblAsientosValor.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblAsientosValor.setForeground(Color.DARK_GRAY);
        panelCaja3.add(lblAsientosTitulo);
        panelCaja3.add(lblAsientosValor);
        
        panelSub2M.add(panelCaja1);
        panelSub2M.add(panelCaja2);
        panelSub2M.add(panelCaja3);
        
        panelMetricas.add(panelSub2M, BorderLayout.CENTER);
        
        this.add(panelMetricas, BorderLayout.NORTH);

        panelRanking = new JPanel(new BorderLayout());
        panelRanking.setBorder(BorderFactory.createTitledBorder("Ranking de Ocupación (de mayor a menor)"));
        
        panelSub1R = new JPanel(new BorderLayout());
        panelSub1R.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        lblMensajeRanking = new JLabel("Sin vuelos: primero programa uno en 'Aviones y Vuelos'");
        lblMensajeRanking.setForeground(new Color(199, 0, 57));
        lblMensajeRanking.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        JPanel panelBotonR = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        btnActualizarRanking = new JButton("Actualizar Ranking");
        btnActualizarRanking.setBackground(new Color(199, 0, 57));
        btnActualizarRanking.setForeground(Color.WHITE);
        btnActualizarRanking.setFocusPainted(false);
        panelBotonR.add(btnActualizarRanking);
        
        panelSub1R.add(lblMensajeRanking, BorderLayout.WEST);
        panelSub1R.add(panelBotonR, BorderLayout.EAST);
        
        panelRanking.add(panelSub1R, BorderLayout.NORTH);
        
        panelSub2R = new JPanel(new BorderLayout());
        panelSub2R.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        String [] columnasRanking = {"Puesto", "Código Vuelo", "Ruta", "Avión", "Pasajes Pagados", "Capacidad", "Ocupación (%)", "Ingreso Confirmado ($)"};
        modeloRanking = new DefaultTableModel(columnasRanking, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaRanking = new JTable(modeloRanking);
        scrollRanking = new JScrollPane(tablaRanking);
        panelSub2R.add(scrollRanking, BorderLayout.CENTER);
        
        panelRanking.add(panelSub2R, BorderLayout.CENTER);
        
        this.add(panelRanking, BorderLayout.CENTER);
    }

    public JPanel getPanelMetricas() {
        return panelMetricas;
    }

    public JPanel getPanelSub1M() {
        return panelSub1M;
    }

    public JPanel getPanelSub2M() {
        return panelSub2M;
    }

    public JPanel getPanelCaja1() {
        return panelCaja1;
    }

    public JPanel getPanelCaja2() {
        return panelCaja2;
    }

    public JPanel getPanelCaja3() {
        return panelCaja3;
    }

    public JPanel getPanelRanking() {
        return panelRanking;
    }

    public JPanel getPanelSub1R() {
        return panelSub1R;
    }

    public JPanel getPanelSub2R() {
        return panelSub2R;
    }

    public JLabel getLblSeleccionarVuelo() {
        return lblSeleccionarVuelo;
    }

    public JLabel getLblOcupacionTitulo() {
        return lblOcupacionTitulo;
    }

    public JLabel getLblOcupacionValor() {
        return lblOcupacionValor;
    }

    public JLabel getLblIngresoTitulo() {
        return lblIngresoTitulo;
    }

    public JLabel getLblIngresoValor() {
        return lblIngresoValor;
    }

    public JLabel getLblAsientosTitulo() {
        return lblAsientosTitulo;
    }

    public JLabel getLblAsientosValor() {
        return lblAsientosValor;
    }

    public JLabel getLblMensajeRanking() {
        return lblMensajeRanking;
    }

    public JComboBox getCbVuelo() {
        return cbVuelo;
    }

    public JButton getBtnCalcularMetricas() {
        return btnCalcularMetricas;
    }

    public JButton getBtnActualizarRanking() {
        return btnActualizarRanking;
    }

    public JScrollPane getScrollRanking() {
        return scrollRanking;
    }

    public JTable getTablaRanking() {
        return tablaRanking;
    }

    public DefaultTableModel getModeloRanking() {
        return modeloRanking;
    }
}
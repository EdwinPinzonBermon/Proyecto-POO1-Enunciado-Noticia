package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JPTripulacion extends JPanel
{
    private JPanel panelIzquierda, panelDerecha, panelRegistroTripulante, panelSub1RT, panelSub2RT, panelSub3RT, 
    panelTripulacionYDisponibilidad, panelSub1TD, panelSub2TD;
    private JLabel lblIdentificacion, lblNombreCompleto, lblRol, lblAsignarVueloOp, lblMostrar;
    private JTextField txtIdentificacion, txtNombreCompleto;
    private JComboBox cbRol, cbAsignarVueloOp, cbMostrar;
    private JButton btnRegistrarTripulante, btnAsignarTripulante;
    private JScrollPane scrollTripulacion;
    private JTable tablaTripulacion;
    private DefaultTableModel modeloTripulacion;

    public JPTripulacion(){
        setLayout(new BorderLayout());
        initComponentes();
    }

    private void initComponentes(){
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
        panelIzquierda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelIzquierda.setPreferredSize(new Dimension(350, 0));

        panelRegistroTripulante = new JPanel();
        panelRegistroTripulante.setLayout(new BoxLayout(panelRegistroTripulante, BoxLayout.Y_AXIS));
        panelRegistroTripulante.setBorder(BorderFactory.createTitledBorder("Registrar Tripulante"));

        panelSub1RT = new JPanel(new GridLayout(4, 2, 5, 10));
        lblIdentificacion = new JLabel("Identificación");
        lblNombreCompleto = new JLabel("Nombre Completo");
        lblRol = new JLabel("Rol a bordo");
        lblAsignarVueloOp = new JLabel("Asignar a vuelo (opcional)");
        
        txtIdentificacion = new JTextField();
        txtNombreCompleto = new JTextField();
        
        cbRol = new JComboBox();
        cbRol.addItem("CAPITAN");
        cbRol.addItem("COPILOTO");
        cbRol.addItem("AZAFATA");
        cbRol.addItem("AYUDANTE");
        
        cbAsignarVueloOp = new JComboBox();
        
        panelSub1RT.add(lblIdentificacion);
        panelSub1RT.add(txtIdentificacion);
        panelSub1RT.add(lblNombreCompleto);
        panelSub1RT.add(txtNombreCompleto);
        panelSub1RT.add(lblRol);
        panelSub1RT.add(cbRol);
        panelSub1RT.add(lblAsignarVueloOp);
        panelSub1RT.add(cbAsignarVueloOp);
        
        panelSub1RT.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelSub1RT.getPreferredSize().height));
        
        panelRegistroTripulante.add(panelSub1RT);
        panelRegistroTripulante.add(Box.createRigidArea(new Dimension(0, 15)));

        panelSub2RT = new JPanel(new BorderLayout());
        btnRegistrarTripulante = new JButton("Registrar Tripulante");
        btnRegistrarTripulante.setBackground(new Color(14, 4, 124));
        btnRegistrarTripulante.setForeground(Color.WHITE);
        btnRegistrarTripulante.setFocusPainted(false);
        panelSub2RT.add(btnRegistrarTripulante, BorderLayout.CENTER);
        panelSub2RT.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelSub2RT.getPreferredSize().height));
        
        panelRegistroTripulante.add(panelSub2RT);
        panelRegistroTripulante.add(Box.createRigidArea(new Dimension(0, 10)));

        panelSub3RT = new JPanel();
        panelSub3RT.setLayout(new BoxLayout(panelSub3RT, BoxLayout.Y_AXIS));
        
        btnAsignarTripulante = new JButton("Asignar tripulante de la tabla al vuelo");
        btnAsignarTripulante.setBackground(new Color(199, 0, 57));
        btnAsignarTripulante.setForeground(Color.WHITE);
        btnAsignarTripulante.setFocusPainted(false);
        btnAsignarTripulante.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextArea txtInfo = new JTextArea("Para un tripulante ya registrado: selecciónalo en la tabla, el vuelo arriba y usa el botón rojo.");
        txtInfo.setRows(2);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);
        txtInfo.setEditable(false);
        txtInfo.setOpaque(false);
        txtInfo.setForeground(Color.GRAY);
        txtInfo.setFont(new Font("SansSerif", Font.ITALIC, 11));
        txtInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtInfo.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));

        panelSub3RT.add(btnAsignarTripulante);
        panelSub3RT.add(txtInfo);
        panelSub3RT.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelSub3RT.getPreferredSize().height));
        
        panelRegistroTripulante.add(panelSub3RT);

        panelIzquierda.add(panelRegistroTripulante);
        panelIzquierda.add(Box.createVerticalGlue());

        panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        panelTripulacionYDisponibilidad = new JPanel(new BorderLayout());
        panelTripulacionYDisponibilidad.setBorder(BorderFactory.createTitledBorder("Tripulación y Disponibilidad"));

        panelSub1TD = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblMostrar = new JLabel("Mostrar:");
        cbMostrar = new JComboBox();
        cbMostrar.addItem("Todos");
        panelSub1TD.add(lblMostrar);
        panelSub1TD.add(cbMostrar);
        panelTripulacionYDisponibilidad.add(panelSub1TD, BorderLayout.NORTH);

        panelSub2TD = new JPanel(new BorderLayout());
        String [] columnasTripulacion = {"Identificación","Nombre Completo","Rol","Estado Operativo"};
        modeloTripulacion = new DefaultTableModel(columnasTripulacion, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTripulacion = new JTable(modeloTripulacion);
        scrollTripulacion = new JScrollPane(tablaTripulacion);
        panelSub2TD.add(scrollTripulacion, BorderLayout.CENTER);
        panelTripulacionYDisponibilidad.add(panelSub2TD, BorderLayout.CENTER);

        panelDerecha.add(panelTripulacionYDisponibilidad, BorderLayout.CENTER);

        this.add(panelIzquierda, BorderLayout.WEST);
        this.add(panelDerecha, BorderLayout.CENTER);
    }

    public JButton getBtnRegistrarTripulante() {
        return btnRegistrarTripulante;
    }

    public JButton getBtnAsignarTripulante() {
        return btnAsignarTripulante;
    }

    public JPanel getPanelIzquierda() {
        return panelIzquierda;
    }

    public JPanel getPanelDerecha() {
        return panelDerecha;
    }

    public JPanel getPanelRegistroTripulante() {
        return panelRegistroTripulante;
    }

    public JPanel getPanelSub1RT() {
        return panelSub1RT;
    }

    public JPanel getPanelSub2RT() {
        return panelSub2RT;
    }

    public JPanel getPanelSub3RT() {
        return panelSub3RT;
    }

    public JPanel getPanelTripulacionYDisponibilidad() {
        return panelTripulacionYDisponibilidad;
    }

    public JPanel getPanelSub1TD() {
        return panelSub1TD;
    }

    public JPanel getPanelSub2TD() {
        return panelSub2TD;
    }

    public JLabel getLblIdentificacion() {
        return lblIdentificacion;
    }

    public JLabel getLblNombreCompleto() {
        return lblNombreCompleto;
    }

    public JLabel getLblRol() {
        return lblRol;
    }

    public JLabel getLblAsignarVueloOp() {
        return lblAsignarVueloOp;
    }

    public JLabel getLblMostrar() {
        return lblMostrar;
    }

    public JTextField getTxtIdentificacion() {
        return txtIdentificacion;
    }

    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public JComboBox getCbRol() {
        return cbRol;
    }

    public JComboBox getCbAsignarVueloOp() {
        return cbAsignarVueloOp;
    }

    public JComboBox getCbMostrar() {
        return cbMostrar;
    }

    public JScrollPane getScrollTripulacion() {
        return scrollTripulacion;
    }

    public JTable getTablaTripulacion() {
        return tablaTripulacion;
    }

    public DefaultTableModel getModeloTripulacion() {
        return modeloTripulacion;
    }
}
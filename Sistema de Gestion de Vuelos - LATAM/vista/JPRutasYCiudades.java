package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JPRutasYCiudades extends JPanel
{
    private JPanel panelIzquierda, panelDerecha, panelRegistroCiudad, panelTablaCiudad, panelRegistroRuta, panelTablaRuta;
    private JButton btnRegistrarCiudad, btnRegistrarRuta;
    private JLabel lblIata, lblNombreCiudad, lblCodigoPais, lblNombrePais, lblCiudadSalida, lblCiudadLlegada, lblDuracion, lblTope;
    private JTextField txtIata, txtNombreCiudad, txtCodigoPais, txtNombrePais, txtDuracion, txtTope;
    private JComboBox cbCiudadesSalida, cbCiudadesLlegada;
    private JScrollPane scrollCiudad, scrollRuta;
    private JTable tablaCiudad, tablaRuta;
    private DefaultTableModel modeloTablaCiudad, modeloTablaRuta;

    public JPRutasYCiudades(){
        setLayout(new GridLayout(1,2,10,10));
        initComponentes();
    }

    private void initComponentes(){
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
        panelIzquierda.setBorder(BorderFactory.createTitledBorder("Ciudades Habilitadas(IATA)"));
        panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));
        panelDerecha.setBorder(BorderFactory.createTitledBorder("Rutas Habilitadas y Topes Tarifarios"));

        panelRegistroCiudad = new JPanel(new GridLayout(5,2,10,10));
        panelRegistroCiudad.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        lblIata = new JLabel("Código IATA(3 letras):");
        lblNombreCiudad = new JLabel("Nombre de la Ciudad:");
        lblCodigoPais = new JLabel("Código del País(ej: CO):");
        lblNombrePais = new JLabel("Nombre del País:");
        txtIata = new JTextField();
        txtNombreCiudad = new JTextField();
        txtCodigoPais = new JTextField();
        txtNombrePais = new JTextField();
        panelRegistroCiudad.add(lblIata);
        panelRegistroCiudad.add(txtIata);
        panelRegistroCiudad.add(lblNombreCiudad);
        panelRegistroCiudad.add(txtNombreCiudad);
        panelRegistroCiudad.add(lblCodigoPais);
        panelRegistroCiudad.add(txtCodigoPais);
        panelRegistroCiudad.add(lblNombrePais);
        panelRegistroCiudad.add(txtNombrePais);
        panelRegistroCiudad.add(new JLabel(""));
        btnRegistrarCiudad = new JButton("Registrar Ciudad");
        btnRegistrarCiudad.setBackground(new Color(14, 4, 124));
        btnRegistrarCiudad.setForeground(Color.WHITE);
        btnRegistrarCiudad.setFocusPainted(false);
        panelRegistroCiudad.add(btnRegistrarCiudad);

        String [] columnasCiudad = {"IATA","Ciudad","País","Cod.País"};
        modeloTablaCiudad = new DefaultTableModel(columnasCiudad,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; 
        tablaCiudad = new JTable(modeloTablaCiudad);
        scrollCiudad = new JScrollPane(tablaCiudad);

        panelIzquierda.add(panelRegistroCiudad);
        panelIzquierda.add(scrollCiudad);

        String [] columnasRuta = {"Origen","Destino","Duracion(min)","Tope Base($)"};
        modeloTablaRuta = new DefaultTableModel(columnasRuta,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaRuta = new JTable(modeloTablaRuta);
        scrollRuta = new JScrollPane(tablaRuta);

        panelRegistroRuta = new JPanel(new GridLayout(5,2,10,10));
        panelRegistroRuta.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        lblCiudadSalida = new JLabel("Ciudad Salida:");
        lblCiudadLlegada = new JLabel("Ciudad Llegada:");
        lblDuracion = new JLabel("Duración(min):");
        lblTope = new JLabel("Tope Tarifario($):");
        cbCiudadesLlegada = new JComboBox();
        cbCiudadesSalida = new JComboBox();
        txtDuracion = new JTextField();
        txtTope = new JTextField();
        panelRegistroRuta.add(lblCiudadSalida);
        panelRegistroRuta.add(cbCiudadesSalida);
        panelRegistroRuta.add(lblCiudadLlegada);
        panelRegistroRuta.add(cbCiudadesLlegada);
        panelRegistroRuta.add(lblDuracion);
        panelRegistroRuta.add(txtDuracion);
        panelRegistroRuta.add(lblTope);
        panelRegistroRuta.add(txtTope);
        panelRegistroRuta.add(new JLabel(""));
        btnRegistrarRuta = new JButton("Registrar Ruta");
        btnRegistrarRuta.setBackground(new Color(14, 4, 124));
        btnRegistrarRuta.setForeground(Color.WHITE);
        btnRegistrarRuta.setFocusPainted(false);  
        panelRegistroRuta.add(btnRegistrarRuta);

        panelDerecha.add(panelRegistroRuta);
        panelDerecha.add(scrollRuta);

        this.add(panelIzquierda);
        this.add(panelDerecha);
    }

    public void limpiarCamposPanelRegistroCiudad(){
        txtIata.setText("");
        txtNombreCiudad.setText("");
        txtCodigoPais.setText("");
        txtNombrePais.setText("");
    }

    public void limpiarCamposPanelRegistroRuta(){
        txtDuracion.setText("");
        txtTope.setText("");
    }

    public JPanel getPanelIzquierda() {
        return panelIzquierda;
    }

    public JPanel getPanelDerecha() {
        return panelDerecha;
    }

    public JPanel getPanelRegistroCiudad() {
        return panelRegistroCiudad;
    }

    public JPanel getPanelTablaCiudad() {
        return panelTablaCiudad;
    }

    public JPanel getPanelRegistroRuta() {
        return panelRegistroRuta;
    }

    public JPanel getPanelTablaRuta() {
        return panelTablaRuta;
    }

    public JButton getBtnRegistrarCiudad() {
        return btnRegistrarCiudad;
    }

    public JButton getBtnRegistrarRuta() {
        return btnRegistrarRuta;
    }

    public JLabel getLblIata() {
        return lblIata;
    }

    public JLabel getLblNombreCiudad() {
        return lblNombreCiudad;
    }

    public JLabel getLblCodigoPais() {
        return lblCodigoPais;
    }

    public JLabel getLblNombrePais() {
        return lblNombrePais;
    }

    public JLabel getLblCiudadSalida() {
        return lblCiudadSalida;
    }

    public JLabel getLblCiudadLlegada() {
        return lblCiudadLlegada;
    }

    public JLabel getLblDuracion() {
        return lblDuracion;
    }

    public JLabel getLblTope() {
        return lblTope;
    }

    public JTextField getTxtIata() {
        return txtIata;
    }

    public JTextField getTxtNombreCiudad() {
        return txtNombreCiudad;
    }

    public JTextField getTxtCodigoPais() {
        return txtCodigoPais;
    }

    public JTextField getTxtNombrePais() {
        return txtNombrePais;
    }

    public JTextField getTxtDuracion() {
        return txtDuracion;
    }

    public JTextField getTxtTope() {
        return txtTope;
    }

    public JComboBox getCbCiudadesSalida() {
        return cbCiudadesSalida;
    }

    public JComboBox getCbCiudadesLlegada() {
        return cbCiudadesLlegada;
    }

    public JScrollPane getScrollCiudad() {
        return scrollCiudad;
    }

    public JScrollPane getScrollRuta() {
        return scrollRuta;
    }

    public JTable getTablaCiudad() {
        return tablaCiudad;
    }

    public JTable getTablaRuta() {
        return tablaRuta;
    }

    public DefaultTableModel getModeloTablaCiudad() {
        return modeloTablaCiudad;
    }

    public DefaultTableModel getModeloTablaRuta() {
        return modeloTablaRuta;
    }
}
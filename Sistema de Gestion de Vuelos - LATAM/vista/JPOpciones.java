package vista;
import javax.swing.*;
import java.awt.*;

public class JPOpciones extends JPanel
{
    private JLabel lblResumen, lblConfiguracionBase, lblFlotaYOperacion, lblVentasYMetricas;
    private JButton btnPanelControl, btnRutasYCiudades, btnAvionesYVuelos, btnTripulacion, btnPasajesYAsientos, btnOcupacionEIngresos;

    public JPOpciones(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(new Color(22, 34, 59));
        initComponentes();
    }

    private void initComponentes(){
        lblResumen = new JLabel("RESUMEN");    
        lblResumen.setForeground(new Color(130, 145, 165));

        lblConfiguracionBase = new JLabel("1- CONFIGURACION BASE"); 
        lblConfiguracionBase.setForeground(new Color(130, 145, 165));

        lblFlotaYOperacion = new JLabel("2- FLOTA Y OPERACION"); 
        lblFlotaYOperacion.setForeground(new Color(130, 145, 165));

        lblVentasYMetricas = new JLabel("3- VENTAS Y METRICAS");
        lblVentasYMetricas.setForeground(new Color(130, 145, 165));

        btnPanelControl = new JButton("Panel de Control");
        btnPanelControl.setBackground(new Color(22, 34, 59));
        btnPanelControl.setForeground(Color.WHITE);
        btnPanelControl.setBorderPainted(false);
        btnPanelControl.setFocusPainted(false);

        btnRutasYCiudades = new JButton("Rutas y Ciudades");
        btnRutasYCiudades.setBackground(new Color(22, 34, 59));
        btnRutasYCiudades.setForeground(Color.WHITE);
        btnRutasYCiudades.setBorderPainted(false);
        btnRutasYCiudades.setFocusPainted(false);

        btnAvionesYVuelos = new JButton("Aviones y Vuelos");
        btnAvionesYVuelos.setBackground(new Color(22, 34, 59));
        btnAvionesYVuelos.setForeground(Color.WHITE);
        btnAvionesYVuelos.setBorderPainted(false);
        btnAvionesYVuelos.setFocusPainted(false);

        btnTripulacion = new JButton("Tripulacion");
        btnTripulacion.setBackground(new Color(22, 34, 59));
        btnTripulacion.setForeground(Color.WHITE);
        btnTripulacion.setBorderPainted(false);
        btnTripulacion.setFocusPainted(false);

        btnPasajesYAsientos = new JButton("Pasajes y Asientos");
        btnPasajesYAsientos.setBackground(new Color(22, 34, 59));
        btnPasajesYAsientos.setForeground(Color.WHITE);
        btnPasajesYAsientos.setBorderPainted(false);
        btnPasajesYAsientos.setFocusPainted(false);

        btnOcupacionEIngresos = new JButton("Ocupacion e Ingresos");
        btnOcupacionEIngresos.setBackground(new Color(22, 34, 59));
        btnOcupacionEIngresos.setForeground(Color.WHITE);
        btnOcupacionEIngresos.setBorderPainted(false);
        btnOcupacionEIngresos.setFocusPainted(false);

        this.add(lblResumen);
        this.add(Box.createVerticalStrut(8));
        this.add(btnPanelControl);
        this.add(Box.createVerticalStrut(10));
        this.add(lblConfiguracionBase);
        this.add(Box.createVerticalStrut(8));
        this.add(btnRutasYCiudades);
        this.add(Box.createVerticalStrut(10));
        this.add(lblFlotaYOperacion);
        this.add(Box.createVerticalStrut(8));
        this.add(btnAvionesYVuelos);
        this.add(Box.createVerticalStrut(10));
        this.add(btnTripulacion);
        this.add(Box.createVerticalStrut(10));
        this.add(lblVentasYMetricas);
        this.add(Box.createVerticalStrut(8));
        this.add(btnPasajesYAsientos);
        this.add(Box.createVerticalStrut(10));
        this.add(btnOcupacionEIngresos);
    }

    public JLabel getLblResumen() {
        return lblResumen;
    }

    public JLabel getLblConfiguracionBase() {
        return lblConfiguracionBase;
    }

    public JLabel getLblFlotaYOperacion() {
        return lblFlotaYOperacion;
    }

    public JLabel getLblVentasYMetricas() {
        return lblVentasYMetricas;
    }

    public JButton getBtnPanelControl() {
        return btnPanelControl;
    }

    public JButton getBtnRutasYCiudades() {
        return btnRutasYCiudades;
    }

    public JButton getBtnAvionesYVuelos() {
        return btnAvionesYVuelos;
    }

    public JButton getBtnTripulacion() {
        return btnTripulacion;
    }

    public JButton getBtnPasajesYAsientos() {
        return btnPasajesYAsientos;
    }

    public JButton getBtnOcupacionEIngresos() {
        return btnOcupacionEIngresos;
    }

}
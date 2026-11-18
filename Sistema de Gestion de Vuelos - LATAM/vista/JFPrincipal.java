package vista;
import javax.swing.*;
import java.awt.*;

public class JFPrincipal extends JFrame
{
    private JPanel panelMedio;
    private JPOpciones panelOpciones;
    private JPEncabezado panelEncabezado;
    private JPControl panelControl;
    private JPRutasYCiudades panelRutasYCiudades;
    private JPAvionesYVuelos panelAvionesYVuelos;
    private JPTripulacion panelTripulacion;
    private JPPasajesYAsientos panelPasajesYAsientos;
    private JPOcupacionEIngresos panelOcupacionEIngresos;

    public JFPrincipal(){
        setTitle("LATAM Airlines Colombia - Centro de Control de Operaciones");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        initComponentes();
        setSize(1150, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void reemplazarPanel(JPanel panelNuevo){                      
        panelMedio.removeAll();
        panelMedio.add(panelNuevo, BorderLayout.CENTER);
        panelMedio.revalidate();
        panelMedio.repaint();
    }

    private void initComponentes(){
        panelOpciones = new JPOpciones();
        panelEncabezado = new JPEncabezado();
        this.add(panelEncabezado, BorderLayout.NORTH);
        this.add(panelOpciones, BorderLayout.WEST);

        panelMedio = new JPanel(new BorderLayout());
        panelControl = new JPControl();
        panelRutasYCiudades = new JPRutasYCiudades();
        panelAvionesYVuelos = new JPAvionesYVuelos();
        panelTripulacion = new JPTripulacion();
        panelPasajesYAsientos = new JPPasajesYAsientos();
        panelOcupacionEIngresos = new JPOcupacionEIngresos();

        panelMedio.add(panelControl, BorderLayout.CENTER);
        this.add(panelMedio, BorderLayout.CENTER);
    }

    public JPanel getPanelMedio() {
        return panelMedio;
    }

    public JPOpciones getPanelOpciones() {
        return panelOpciones;
    }

    public JPEncabezado getPanelEncabezado() {
        return panelEncabezado;
    }

    public JPControl getPanelControl() {
        return panelControl;
    }

    public JPRutasYCiudades getPanelRutasYCiudades() {
        return panelRutasYCiudades;
    }

    public JPAvionesYVuelos getPanelAvionesYVuelos() {
        return panelAvionesYVuelos;
    }

    public JPTripulacion getPanelTripulacion() {
        return panelTripulacion;
    }

    public JPPasajesYAsientos getPanelPasajesYAsientos() {
        return panelPasajesYAsientos;
    }

    public JPOcupacionEIngresos getPanelOcupacionEIngresos() {
        return panelOcupacionEIngresos;
    }
}
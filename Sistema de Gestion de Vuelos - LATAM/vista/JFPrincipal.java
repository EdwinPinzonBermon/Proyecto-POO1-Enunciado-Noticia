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

//Start GetterSetterExtension Source Code

    /**SET Method Propertie panelMedio*/
    public void setPanelMedio(JPanel panelMedio){
        this.panelMedio = panelMedio;
    }//end method setPanelMedio

    /**SET Method Propertie panelOpciones*/
    public void setPanelOpciones(JPOpciones panelOpciones){
        this.panelOpciones = panelOpciones;
    }//end method setPanelOpciones

    /**SET Method Propertie panelEncabezado*/
    public void setPanelEncabezado(JPEncabezado panelEncabezado){
        this.panelEncabezado = panelEncabezado;
    }//end method setPanelEncabezado

    /**SET Method Propertie panelControl*/
    public void setPanelControl(JPControl panelControl){
        this.panelControl = panelControl;
    }//end method setPanelControl

    /**SET Method Propertie panelRutasYCiudades*/
    public void setPanelRutasYCiudades(JPRutasYCiudades panelRutasYCiudades){
        this.panelRutasYCiudades = panelRutasYCiudades;
    }//end method setPanelRutasYCiudades

    /**SET Method Propertie panelAvionesYVuelos*/
    public void setPanelAvionesYVuelos(JPAvionesYVuelos panelAvionesYVuelos){
        this.panelAvionesYVuelos = panelAvionesYVuelos;
    }//end method setPanelAvionesYVuelos

    /**SET Method Propertie panelTripulacion*/
    public void setPanelTripulacion(JPTripulacion panelTripulacion){
        this.panelTripulacion = panelTripulacion;
    }//end method setPanelTripulacion

    /**SET Method Propertie panelPasajesYAsientos*/
    public void setPanelPasajesYAsientos(JPPasajesYAsientos panelPasajesYAsientos){
        this.panelPasajesYAsientos = panelPasajesYAsientos;
    }//end method setPanelPasajesYAsientos

    /**SET Method Propertie panelOcupacionEIngresos*/
    public void setPanelOcupacionEIngresos(JPOcupacionEIngresos panelOcupacionEIngresos){
        this.panelOcupacionEIngresos = panelOcupacionEIngresos;
    }//end method setPanelOcupacionEIngresos

//End GetterSetterExtension Source Code


}//End class
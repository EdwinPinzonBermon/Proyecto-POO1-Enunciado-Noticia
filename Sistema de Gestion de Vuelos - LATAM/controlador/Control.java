package controlador;
import java.util.ArrayList;
import modelo.*;
import vista.*;

/**
 * Write a description of class Control here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Control
{

    private Aerolinea aerolinea;
    private JFVentanaPrincipal ventana;

    public Control(Aerolinea aerolinea, JFVentanaPrincipal ventana) {
        aerolinea = new Aerolinea("LATAM Airlines Colombia", new ArrayList<Avion>(), new ArrayList<Tripulante>(),
                                  new ArrayList<Pais>(), new ArrayList<Ciudad>(), new ArrayList<Ruta>(),
                                  new ArrayList<Vuelo>(), new ArrayList<Pasajero>());
        ventana = new JFVentanaPrincipal(aerolinea);
    }
}

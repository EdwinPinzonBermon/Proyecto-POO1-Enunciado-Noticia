import vista.*;
import controlador.*;
import modelo.*;
import java.util.ArrayList;

public class main
{
    public static void main(String [] args){
        JFPrincipal vista = new JFPrincipal();
        Aerolinea modelo = new Aerolinea("LATAM Airlines", new ArrayList<Avion>(), new ArrayList<Tripulante>(), new ArrayList<Pais>(), new ArrayList<Ciudad>(), new ArrayList<Ruta>(), new ArrayList<Vuelo>(), new ArrayList<Pasajero>());
        Control controlador = new Control(vista, modelo);
        vista.setVisible(true);
    }
}
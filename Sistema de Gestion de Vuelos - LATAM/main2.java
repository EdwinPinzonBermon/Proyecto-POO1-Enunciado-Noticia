
import vista.*;
import controlador.*;
import modelo.*;
import java.util.ArrayList;

public class main2
{
    public static void main(String [] args){
        JFPrincipal vista = new JFPrincipal();
        Aerolinea modelo = new Aerolinea("LATAM Airlines", new ArrayList<Avion>(), new ArrayList<Tripulante>(), new ArrayList<Pais>(), new ArrayList<Ciudad>(), new ArrayList<Ruta>(), new ArrayList<Vuelo>(), new ArrayList<Pasajero>());
        Control controlador = new Control(vista, modelo);
        DatosPrueba datos = new DatosPrueba(vista, modelo); // BORRAR estas dos líneas para iniciar sin datos
        datos.cargar();
        // Refresca el resumen principal después de cargar los datos de demostración.
        vista.getPanelOpciones().getBtnPanelControl().doClick();
        vista.setVisible(true);
    }
}
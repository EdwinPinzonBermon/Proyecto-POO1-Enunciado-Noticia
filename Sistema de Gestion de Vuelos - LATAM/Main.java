import controlador.Control;
import vista.*;
import modelo.*;
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String[] args) {
          Aerolinea modelo = new Aerolinea();
  // inicializar listas con set* si el constructor vacío no las crea
  DatosEjemplo.cargar(modelo);
  JFVentanaPrincipal vista = new JFVentanaPrincipal(modelo);
  new Control(modelo, vista);
  vista.setVisible(true);
    }
}
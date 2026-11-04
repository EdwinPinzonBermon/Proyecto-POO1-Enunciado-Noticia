package modelo;
import java.util.ArrayList;

public class Pais {

    private String codigo;
    private String nombre;
    private ArrayList<Ciudad> ciudades;

    public Pais() {
    }

    public Pais(String codigo, String nombre, ArrayList<Ciudad> ciudades) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudades = ciudades;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public boolean agregarCiudad(Ciudad ciudad) {
        if (ciudad == null || buscarCiudad(ciudad.getCodigo()) != null) {
            return false;
        }
        ciudades.add(ciudad);
        return true;
    }

    public Ciudad buscarCiudad(String codigo) {
        for (int i = 0; i < ciudades.size(); i++) {
            if (ciudades.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return ciudades.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
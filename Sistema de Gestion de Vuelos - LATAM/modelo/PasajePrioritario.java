package modelo;

public class PasajePrioritario extends Pasaje {

    private static final int PORCENTAJE_RECARGO = 20;

    public PasajePrioritario() {
    }

    public PasajePrioritario(int numeroAsiento, int precioBase, Vuelo vuelo, Pasajero pasajero) {
        super(numeroAsiento, precioBase, vuelo, pasajero);
    }

    @Override
    public ClasePasaje getClase() {
        return ClasePasaje.PRIORITARIA;
    }

    public int calcularRecargo() {
        //Falta por implementar 
        return 0;
    }

    @Override
    public int calcularPrecioFinal() {
        //Falta por implementar 
        return 0;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
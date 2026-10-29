package modelo;

public class FechaHora {

    private Fecha fecha;
    private Hora hora;

    public FechaHora() {
    }

    public FechaHora(Fecha fecha, Hora hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public static FechaHora ahora() {
        //Falta por implementar 
        return null;
    }

    public boolean esAnteriorA(FechaHora otra) {
        //Falta por implementar 
        return false;
    }

    public FechaHora sumarMinutos(int minutos) {
        //Falta por implementar 
        return null;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
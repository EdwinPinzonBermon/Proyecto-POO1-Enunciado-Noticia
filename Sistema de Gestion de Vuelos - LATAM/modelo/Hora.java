package modelo;

public class Hora {

    private int hora;
    private int minuto;

    public Hora() {
    }

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public boolean esValida() {
        return hora >= 0 && hora <= 23 && minuto >= 0 && minuto <= 59;
    }

    public boolean esAnteriorA(Hora otra) {
        if (hora != otra.hora) {
            return hora < otra.hora;
        }
        return minuto < otra.minuto;
    }

    @Override
    public String toString() {
        //Falta por implementar 
        return null;
    }
}
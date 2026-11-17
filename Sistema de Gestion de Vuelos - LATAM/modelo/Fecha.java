package modelo;

public class Fecha {

    private int dia;
    private int mes;
    private int anio;

    public Fecha() {
    }

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean esValida() {
        if (anio < 1 || mes < 1 || mes > 12 || dia < 1) {
            return false;
        }
        int diasDelMes = 31;
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            diasDelMes = 30;
        } else if (mes == 2) {
            boolean bisiesto = (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
            if (bisiesto) {
                diasDelMes = 29;
            } else {
                diasDelMes = 28;
            }
        }
        return dia <= diasDelMes;
    }

    public boolean esAnteriorA(Fecha otra) {
        if (anio != otra.anio) {
            return anio < otra.anio;
        }
        if (mes != otra.mes) {
            return mes < otra.mes;
        }
        return dia < otra.dia;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }
}
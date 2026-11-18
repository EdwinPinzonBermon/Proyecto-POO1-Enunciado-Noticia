package vista;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class JPControl extends JPanel {
    private JPanel panelTarjetas, panelTarjeta1, panelTarjeta2, panelTarjeta3, 
    panelTarjeta4, panelTarjeta5, panelCentral, panelIzquierdo, panelDerecho;
    private JLabel lblVActivos, lblSubVActivos, lblEstadoFLota, lblSubEstadoFLota, 
    lblPilotosYTripulacion, lblSubPilotosYTripulacion, lblVProgramados, 
    lblSubVProgramados, lblPasajesEIngresos, lblSubPasajesEIngresos;
    private JTextArea areaIzquierda, areaDerecha;
    private JScrollPane scrollIzquierdo, scrollDerecho;

    public JPControl() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        initComponentes();
    }

    private void initComponentes() {
        panelTarjetas = new JPanel(new GridLayout(1, 5, 10, 0));

        panelTarjeta1 = new JPanel();
        panelTarjeta1.setLayout(new BoxLayout(panelTarjeta1, BoxLayout.Y_AXIS));
        panelTarjeta1.setBackground(Color.WHITE);
        Border lineaColor1 = BorderFactory.createLineBorder(Color.BLUE);
        panelTarjeta1.setBorder(BorderFactory.createTitledBorder(lineaColor1, "Vuelos Activos"));
        lblVActivos = new JLabel("0");
        lblVActivos.setFont(lblVActivos.getFont().deriveFont(Font.BOLD, 20f));
        lblSubVActivos = new JLabel("0 en aire - 0 en plataforma");
        panelTarjeta1.add(lblVActivos);
        panelTarjeta1.add(Box.createVerticalStrut(5));
        panelTarjeta1.add(lblSubVActivos);

        panelTarjeta2 = new JPanel();
        panelTarjeta2.setLayout(new BoxLayout(panelTarjeta2, BoxLayout.Y_AXIS));
        panelTarjeta2.setBackground(Color.WHITE);
        Border lineaColor2 = BorderFactory.createLineBorder(Color.BLUE);
        panelTarjeta2.setBorder(BorderFactory.createTitledBorder(lineaColor2, "Estado de Flota"));
        lblEstadoFLota = new JLabel("0");
        lblEstadoFLota.setFont(lblEstadoFLota.getFont().deriveFont(Font.BOLD, 20f));
        lblSubEstadoFLota = new JLabel("0 asignados - 0 libres");
        panelTarjeta2.add(lblEstadoFLota);
        panelTarjeta2.add(Box.createVerticalStrut(5));
        panelTarjeta2.add(lblSubEstadoFLota);

        panelTarjeta3 = new JPanel();
        panelTarjeta3.setLayout(new BoxLayout(panelTarjeta3, BoxLayout.Y_AXIS));
        panelTarjeta3.setBackground(Color.WHITE);
        Border lineaColor3 = BorderFactory.createLineBorder(Color.BLUE);
        panelTarjeta3.setBorder(BorderFactory.createTitledBorder(lineaColor3, "Pilotos y Tripulacion"));
        lblPilotosYTripulacion = new JLabel("0");
        lblPilotosYTripulacion.setFont(lblPilotosYTripulacion.getFont().deriveFont(Font.BOLD, 20f));
        lblSubPilotosYTripulacion = new JLabel("0 activos - 0 libres");
        panelTarjeta3.add(lblPilotosYTripulacion);
        panelTarjeta3.add(Box.createVerticalStrut(5));
        panelTarjeta3.add(lblSubPilotosYTripulacion);

        panelTarjeta4 = new JPanel();
        panelTarjeta4.setLayout(new BoxLayout(panelTarjeta4, BoxLayout.Y_AXIS));
        panelTarjeta4.setBackground(Color.WHITE);
        Border lineaColor4 = BorderFactory.createLineBorder(Color.BLUE);
        panelTarjeta4.setBorder(BorderFactory.createTitledBorder(lineaColor4, "Vuelos Programados"));
        lblVProgramados = new JLabel("0");
        lblVProgramados.setFont(lblVProgramados.getFont().deriveFont(Font.BOLD, 20f));
        lblSubVProgramados = new JLabel("ocupacion promedio 0%");
        panelTarjeta4.add(lblVProgramados);
        panelTarjeta4.add(Box.createVerticalStrut(5));
        panelTarjeta4.add(lblSubVProgramados);

        panelTarjeta5 = new JPanel();
        panelTarjeta5.setLayout(new BoxLayout(panelTarjeta5, BoxLayout.Y_AXIS));
        panelTarjeta5.setBackground(Color.WHITE);
        Border lineaColor5 = BorderFactory.createLineBorder(Color.BLUE);
        panelTarjeta5.setBorder(BorderFactory.createTitledBorder(lineaColor5, "Pasajes e Ingresos"));
        lblPasajesEIngresos = new JLabel("$0");
        lblPasajesEIngresos.setFont(lblPasajesEIngresos.getFont().deriveFont(Font.BOLD, 20f));
        lblSubPasajesEIngresos = new JLabel("0 pasajes confirmados");
        panelTarjeta5.add(lblPasajesEIngresos);
        panelTarjeta5.add(Box.createVerticalStrut(5));
        panelTarjeta5.add(lblSubPasajesEIngresos);

        panelTarjetas.add(panelTarjeta1);
        panelTarjetas.add(panelTarjeta2);
        panelTarjetas.add(panelTarjeta3);
        panelTarjetas.add(panelTarjeta4);
        panelTarjetas.add(panelTarjeta5);

        panelCentral = new JPanel(new GridLayout(1, 2, 10, 0));

        panelIzquierdo = new JPanel(new BorderLayout());
        areaIzquierda = new JTextArea();
        areaIzquierda.setEditable(false);
        areaIzquierda.append("");
        areaIzquierda.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Atención Operativa y Pendientes", TitledBorder.LEFT, TitledBorder.TOP, areaIzquierda.getFont().deriveFont(Font.BOLD, 18f), null));
        scrollIzquierdo = new JScrollPane(areaIzquierda);
        panelIzquierdo.add(scrollIzquierdo);

        panelDerecho = new JPanel(new BorderLayout());
        areaDerecha = new JTextArea();
        areaDerecha.setEditable(false);
        areaDerecha.append("");
        areaDerecha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Estado de Aviones: En Aire / En Tierra", TitledBorder.LEFT, TitledBorder.TOP, areaDerecha.getFont().deriveFont(Font.BOLD, 18f), null));
        scrollDerecho = new JScrollPane(areaDerecha);
        panelDerecho.add(scrollDerecho);

        panelCentral.add(panelIzquierdo);
        panelCentral.add(panelDerecho);

        this.add(panelTarjetas, BorderLayout.NORTH);
        this.add(panelCentral, BorderLayout.CENTER);
    }

    public JPanel getPanelTarjetas() {
        return panelTarjetas;
    }

    public JPanel getPanelTarjeta1() {
        return panelTarjeta1;
    }

    public JPanel getPanelTarjeta2() {
        return panelTarjeta2;
    }

    public JPanel getPanelTarjeta3() {
        return panelTarjeta3;
    }

    public JPanel getPanelTarjeta4() {
        return panelTarjeta4;
    }

    public JPanel getPanelTarjeta5() {
        return panelTarjeta5;
    }

    public JPanel getPanelCentral() {
        return panelCentral;
    }

    public JPanel getPanelIzquierdo() {
        return panelIzquierdo;
    }

    public JPanel getPanelDerecho() {
        return panelDerecho;
    }

    public JLabel getLblVActivos() {
        return lblVActivos;
    }

    public JLabel getLblSubVActivos() {
        return lblSubVActivos;
    }

    public JLabel getLblEstadoFLota() {
        return lblEstadoFLota;
    }

    public JLabel getLblSubEstadoFLota() {
        return lblSubEstadoFLota;
    }

    public JLabel getLblPilotosYTripulacion() {
        return lblPilotosYTripulacion;
    }

    public JLabel getLblSubPilotosYTripulacion() {
        return lblSubPilotosYTripulacion;
    }

    public JLabel getLblVProgramados() {
        return lblVProgramados;
    }

    public JLabel getLblSubVProgramados() {
        return lblSubVProgramados;
    }

    public JLabel getLblPasajesEIngresos() {
        return lblPasajesEIngresos;
    }

    public JLabel getLblSubPasajesEIngresos() {
        return lblSubPasajesEIngresos;
    }

    public JTextArea getAreaIzquierda() {
        return areaIzquierda;
    }

    public JTextArea getAreaDerecha() {
        return areaDerecha;
    }

    public JScrollPane getScrollIzquierdo() {
        return scrollIzquierdo;
    }

    public JScrollPane getScrollDerecho() {
        return scrollDerecho;
    }
}
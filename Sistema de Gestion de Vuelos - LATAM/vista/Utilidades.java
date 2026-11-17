package vista;

import javax.swing.*;
import java.awt.*;
import modelo.*;

public class Utilidades {

    public static final Color AZUL_OSCURO = new Color(11, 31, 92);
    public static final Color AZUL_MEDIO = new Color(30, 79, 216);
    public static final Color CELESTE = new Color(232, 240, 254);

    // ---------------- Mensajes ----------------

    public static void mostrarError(Component padre, String mensaje) {
        // padre = la ventana o panel desde donde se muestra el aviso
        // mensaje = el texto que verá el usuario
        JOptionPane.showMessageDialog(padre, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static void mostrarMensaje(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Listo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(padre, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        // YES_OPTION es 0: el usuario oprimió "Sí"
        if (respuesta == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    // ---------------- Leer números ----------------

    
    public static int leerEntero(JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim();
        if (texto.equals("")) {
            return -1;
        }
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    
    public static Fecha leerFecha(JTextField campo) {
        String texto = campo.getText().trim();
        if (texto.equals("")) {
            return null;
        }

        // Buscar las dos barras /
        int barra1 = texto.indexOf('/');
        if (barra1 < 0) {
            return null;
        }
        int barra2 = texto.indexOf('/', barra1 + 1);
        if (barra2 < 0) {
            return null;
        }

        // Cortar el texto en día, mes y año a mano
        String sDia = texto.substring(0, barra1);
        String sMes = texto.substring(barra1 + 1, barra2);
        String sAnio = texto.substring(barra2 + 1);

        try {
            int dia = Integer.parseInt(sDia.trim());
            int mes = Integer.parseInt(sMes.trim());
            int anio = Integer.parseInt(sAnio.trim());
            Fecha fecha = new Fecha(dia, mes, anio);
            if (!fecha.esValida()) {
                return null;
            }
            return fecha;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    
    public static Hora leerHora(JTextField campo) {
        String texto = campo.getText().trim();
        if (texto.equals("")) {
            return null;
        }

        int dosPuntos = texto.indexOf(':');
        if (dosPuntos < 0) {
            return null;
        }

        String sHora = texto.substring(0, dosPuntos);
        String sMin = texto.substring(dosPuntos + 1);

        try {
            int h = Integer.parseInt(sHora.trim());
            int m = Integer.parseInt(sMin.trim());
            Hora hora = new Hora(h, m);
            if (!hora.esValida()) {
                return null;
            }
            return hora;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    // ---------------- Botones y formularios ----------------

    public static JButton botonAccion(String texto, boolean enfasis) {
        JButton boton = new JButton(texto);
        if (enfasis) {
            boton.setBackground(new Color(232, 17, 75)); // rojo
        } else {
            boton.setBackground(new Color(27, 0, 136)); // azul
        }
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        return boton;
    }

    
    public static void agregarFila(JPanel panel, int fila, String etiqueta, JComponent campo) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = fila;
        c.weightx = 0;
        panel.add(new JLabel(etiqueta), c);

        c.gridx = 1;
        c.weightx = 1;
        panel.add(campo, c);
    }

    
    public static void agregarFilaCompleta(JPanel panel, int fila, JComponent componente) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = fila;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(8, 6, 5, 6);
        panel.add(componente, c);
    }

    // Estos dos métodos se dejan vacíos para no romper llamadas viejas en los paneles.
    // El combo ya muestra bien el texto gracias al toString() del modelo.
    public static void textoParaNulo(JComboBox combo, String textoNulo) {
        // no hace nada (versión sencilla)
    }

    public static void textoLegible(JComboBox combo) {
        // no hace nada (versión sencilla)
    }
}

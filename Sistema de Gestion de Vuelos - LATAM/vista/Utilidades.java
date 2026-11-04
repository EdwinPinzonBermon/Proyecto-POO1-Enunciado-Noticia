package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import modelo.*;

/**
 * Metodos de ayuda que usan todas las pantallas (mensajes, leer campos, dar formato).
 * Todos son static: se usan asi -> Utilidades.mostrarError(this, "mensaje");
 */
public class Utilidades
{
    // ---------------- Colores del programa (azul de aerolinea) ----------------
    public static final Color AZUL_OSCURO = new Color(11, 31, 92);
    public static final Color AZUL_MEDIO = new Color(30, 79, 216);
    public static final Color CELESTE = new Color(232, 240, 254);
    public static final Color CELESTE_OSCURO = new Color(190, 210, 245);
    public static final Color ACENTO = new Color(0, 180, 216);

    // Cambia los colores por defecto de Swing. Se llama UNA vez, antes de crear los componentes.
    public static void aplicarTema(){
        UIManager.put("Panel.background", CELESTE);
        UIManager.put("OptionPane.background", CELESTE);
        UIManager.put("CheckBox.background", CELESTE);
        UIManager.put("Label.foreground", AZUL_OSCURO);
        UIManager.put("TitledBorder.titleColor", AZUL_OSCURO);
        UIManager.put("TitledBorder.font", new Font("SansSerif", Font.BOLD, 12));
        UIManager.put("TabbedPane.background", CELESTE_OSCURO);
        UIManager.put("TabbedPane.selected", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", AZUL_MEDIO);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("Button.select", AZUL_OSCURO);
    }

    // Carga una imagen de la carpeta "imagenes" del proyecto. Si no la encuentra devuelve null (el programa sigue funcionando).
    public static ImageIcon cargarIcono(String ruta){
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            return null;
        }
        return new ImageIcon(ruta);
    }

    // Deja un boton azul con letra blanca
    public static void estilizarBoton(JButton boton){
        boton.setBackground(AZUL_MEDIO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Recorre todos los componentes de un panel y estiliza los botones que encuentre
    // (usa recursividad: el metodo se llama a si mismo para revisar los paneles que estan dentro de otros paneles)
    public static void aplicarEstilo(Container contenedor){
        Component[] componentes = contenedor.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            Component c = componentes[i];
            if (c instanceof JButton) {
                estilizarBoton((JButton) c);
            } else if (c instanceof JComboBox || c instanceof JScrollPane) {
                // se dejan igual (tienen botones internos propios)
            } else if (c instanceof Container) {
                aplicarEstilo((Container) c);
            }
        }
    }

    // ---------------- Mensajes ----------------
    public static void mostrarError(Component padre, String mensaje){
        JOptionPane.showMessageDialog(padre, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static void mostrarMensaje(Component padre, String mensaje){
        JOptionPane.showMessageDialog(padre, mensaje, "Listo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje){
        int respuesta = JOptionPane.showConfirmDialog(padre, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    // ---------------- Leer campos (lanzan IllegalArgumentException si algo esta mal) ----------------
    public static String leerTexto(JTextField campo, String nombreCampo){
        String texto = campo.getText().trim();
        if (texto.equals("")) {
            throw new IllegalArgumentException("Complete el campo " + nombreCampo);
        }
        return texto;
    }

    public static int leerEntero(JTextField campo, String nombreCampo){
        String texto = leerTexto(campo, nombreCampo);
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " debe ser un numero entero");
        }
    }

    // La fecha se escribe asi: 15/03/2030
    public static Fecha leerFecha(JTextField campo){
        String texto = leerTexto(campo, "Fecha");
        String[] partes = texto.split("/");
        try {
            if (partes.length != 3) {
                throw new NumberFormatException();
            }
            Fecha fecha = new Fecha(Integer.parseInt(partes[0].trim()), Integer.parseInt(partes[1].trim()), Integer.parseInt(partes[2].trim()));
            if (!fecha.esValida()) {
                throw new NumberFormatException();
            }
            return fecha;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("La fecha debe ser valida y escribirse dd/mm/aaaa (ejemplo 15/03/2030)");
        }
    }

    // La hora se escribe asi: 06:30
    public static Hora leerHora(JTextField campo){
        String texto = leerTexto(campo, "Hora");
        String[] partes = texto.split(":");
        try {
            if (partes.length != 2) {
                throw new NumberFormatException();
            }
            Hora hora = new Hora(Integer.parseInt(partes[0].trim()), Integer.parseInt(partes[1].trim()));
            if (!hora.esValida()) {
                throw new NumberFormatException();
            }
            return hora;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("La hora debe ser valida y escribirse hh:mm (ejemplo 06:30)");
        }
    }

    // Si un metodo del modelo todavia no esta implementado y devuelve null, se usa una lista vacia
    // para que la pantalla no se caiga.
    public static <T> ArrayList<T> segura(ArrayList<T> lista){
        if (lista == null) {
            return new ArrayList<T>();
        }
        return lista;
    }

    // ---------------- Dar formato a los datos para mostrarlos ----------------
    // (si el dato viene null se muestra un guion "-")
    public static String textoFecha(Fecha f){
        if (f == null) {
            return "-";
        }
        return String.format("%02d/%02d/%04d", f.getDia(), f.getMes(), f.getAnio());
    }

    public static String textoHora(Hora h){
        if (h == null) {
            return "-";
        }
        return String.format("%02d:%02d", h.getHora(), h.getMinuto());
    }

    public static String textoFechaHora(FechaHora fh){
        if (fh == null) {
            return "-";
        }
        return textoFecha(fh.getFecha()) + " " + textoHora(fh.getHora());
    }

    // "15/03/2030 06:30" a partir de la fecha y la hora de salida del vuelo
    public static String textoSalida(Vuelo v){
        return textoFecha(v.getFechaSalida()) + " " + textoHora(v.getHoraSalida());
    }

    public static String textoDinero(int valor){
        return "$" + String.format("%,d", valor).replace(',', '.');
    }

    // EN_EL_AIRE -> "En el aire"
    public static String textoEnum(Enum<?> valor){
        if (valor == null) {
            return "-";
        }
        String texto = valor.name().replace('_', ' ').toLowerCase();
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }

    // Para llenar un combo con los valores de un enum: new JComboBox(Utilidades.textosEnum(Rol.values()))
    public static String[] textosEnum(Enum<?>[] valores){
        String[] textos = new String[valores.length];
        for (int i = 0; i < valores.length; i++) {
            textos[i] = textoEnum(valores[i]);
        }
        return textos;
    }

    public static String textoCiudad(Ciudad c){
        if (c == null) {
            return "-";
        }
        return c.getNombre();
    }

    public static String textoRuta(Ruta r){
        if (r == null) {
            return "-";
        }
        return textoCiudad(r.getCiudadSalida()) + " - " + textoCiudad(r.getCiudadLlegada());
    }

    // ---------------- Tablas ----------------
    public static JTable crearTabla(DefaultTableModel modelo){
        JTable tabla = new JTable(modelo);
        tabla.setDefaultEditor(Object.class, null); // las celdas no se pueden editar
        tabla.setRowHeight(24);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Colores: encabezado azul oscuro, fila seleccionada azul
        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setBackground(AZUL_OSCURO);
        encabezado.setForeground(Color.WHITE);
        encabezado.setFont(new Font("SansSerif", Font.BOLD, 12));
        tabla.setSelectionBackground(AZUL_MEDIO);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(CELESTE_OSCURO);
        tabla.setFillsViewportHeight(true); // el fondo vacio de la tabla tambien se ve blanco
        return tabla;
    }

    // Ajusta el ancho de cada columna al texto que tiene (llamar despues de llenar la tabla)
    public static void ajustarColumnas(JTable tabla){
        for (int c = 0; c < tabla.getColumnCount(); c++) {
            int ancho = tabla.getColumnName(c).length() * 9;
            for (int f = 0; f < tabla.getRowCount(); f++) {
                Object valor = tabla.getValueAt(f, c);
                if (valor != null) {
                    ancho = Math.max(ancho, valor.toString().length() * 8);
                }
            }
            tabla.getColumnModel().getColumn(c).setPreferredWidth(ancho + 16);
        }
    }

    public static void mostrarTabla(Component padre, String titulo, DefaultTableModel modelo){
        JTable tabla = crearTabla(modelo);
        ajustarColumnas(tabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(680, 260));
        JOptionPane.showMessageDialog(padre, scroll, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    // Tabla de pasajes (la usan varias pantallas)
    public static DefaultTableModel crearModeloPasajes(){
        String[] columnas = {"Vuelo", "Asiento", "Pasajero", "Identificacion", "Clase", "Precio base", "Precio final", "Estado"};
        return new DefaultTableModel(columnas, 0);
    }

    public static void agregarFilaPasaje(DefaultTableModel modelo, Pasaje p){
        if (p == null || p.getVuelo() == null || p.getPasajero() == null) {
            return; // pasaje incompleto: no se muestra
        }
        Object[] fila = {p.getVuelo().getCodigo(), p.getNumeroAsiento(), p.getPasajero().getNombre(), p.getPasajero().getId(),
            textoEnum(p.getClase()), textoDinero(p.getPrecioBase()), textoDinero(p.calcularPrecioFinal()), textoEnum(p.getEstado())};
        modelo.addRow(fila);
    }

    public static DefaultTableModel crearModeloPasajes(ArrayList<Pasaje> pasajes){
        DefaultTableModel modelo = crearModeloPasajes();
        for (int i = 0; i < pasajes.size(); i++) {
            agregarFilaPasaje(modelo, pasajes.get(i));
        }
        return modelo;
    }
}

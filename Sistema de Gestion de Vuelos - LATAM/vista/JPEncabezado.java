package vista;
import javax.swing.*;
import java.awt.*;

public class JPEncabezado extends JPanel
{
    private JLabel lblLatam, lblAdmin;

    public JPEncabezado(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,40,10,30));
        setBackground(Color.WHITE);
        initComponentes();
    }

    private void initComponentes(){
        lblLatam = new JLabel("LATAM Airlines Colombia");
        lblLatam.setFont(lblLatam.getFont().deriveFont(Font.BOLD, 18));

        lblAdmin = new JLabel("Admin");
        lblAdmin.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        lblAdmin.setOpaque(true);
        lblAdmin.setBackground(new Color(22, 34, 59));
        lblAdmin.setForeground(Color.WHITE);
        lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(lblLatam, BorderLayout.WEST);
        this.add(lblAdmin, BorderLayout.EAST);
    }

    public JLabel getLblLatam() {
        return lblLatam;
    }

    public JLabel getLblAdmin() {
        return lblAdmin;
    }
}
package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Header extends JPanel {
    private ImageIcon imagen;
    private String nombre;

    public Header(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void paint(Graphics g) {
        Dimension tamaño = getSize();
        imagen = new ImageIcon(getClass().getResource(nombre));
        g.drawImage(imagen.getImage(), 0, 0, null);
        imagen.getImage().flush(); // Libera los recursos de la imagen
        setOpaque(false);
        super.paint(g);
    }
}

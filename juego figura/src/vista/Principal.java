package vista;

import javax.swing.JFrame;

public class Principal {
    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true); // Agrega esta línea para hacer visible la ventana
    }
}
package vista;

import modelo.Jugador;
import logica.JuegoFigura;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaJuego extends JFrame {
    private JLabel lblNombre;
    private JLabel lblNivel;
    private JLabel lblIntentos;
    private JLabel lblFallos;
    private JButton btnTerminarJuego;
    private JButton[] figurasBotones;

    private Jugador jugador;
    private JuegoFigura juego;

    public VentanaJuego(Jugador jugador) {
        this.jugador = jugador;
        juego = new JuegoFigura();
        juego.iniciarNuevoNivel();

        setTitle("Juego de Figuras - Nivel " + juego.getNivel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Usar BorderLayout como administrador de diseño principal
        setLayout(new BorderLayout());

        // Panel para la figura de muestra a la izquierda
        JPanel panelFiguraMuestra = new JPanel();
        ImageIcon figuraMuestra = new ImageIcon("imagenes/muestra1.jpg");
        JLabel lblFiguraMuestra = new JLabel(figuraMuestra);
        panelFiguraMuestra.add(lblFiguraMuestra);

        // Panel para las tres figuras en una cuadrícula a la derecha
        JPanel panelFiguras = new JPanel(new GridLayout(2, 2));

        figurasBotones = new JButton[4];
        for (int i = 0; i < figurasBotones.length; i++) {
            figurasBotones[i] = new JButton("Figura " + (i + 1));
            figurasBotones[i].setEnabled(false); // Deshabilitar los botones hasta que se inicie el juego
            panelFiguras.add(figurasBotones[i]);
        }

        // Panel para los datos en la columna a la derecha
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));

        lblNombre = new JLabel("Jugador: " + jugador.getNombre());
        lblNivel = new JLabel("Nivel: " + juego.getNivel());
        lblIntentos = new JLabel("Intentos: 0");
        lblFallos = new JLabel("Fallos: 0");

        panelDatos.add(lblNombre);
        panelDatos.add(lblNivel);
        panelDatos.add(lblIntentos);
        panelDatos.add(lblFallos);

        // Panel para el botón de terminar el juego en la parte inferior izquierda
        JPanel panelBotonTerminar = new JPanel();
        btnTerminarJuego = new JButton("Terminar Juego");
        panelBotonTerminar.add(btnTerminarJuego);

        // Agregar los paneles al BorderLayout
        add(panelFiguraMuestra, BorderLayout.WEST); // Panel de figura de muestra a la izquierda
        add(panelFiguras, BorderLayout.CENTER); // Panel de las tres figuras en una cuadrícula
        add(panelDatos, BorderLayout.EAST); // Panel de datos en la parte derecha
        add(panelBotonTerminar, BorderLayout.SOUTH); // Botón en la parte inferior

        btnTerminarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarResumen();
            }
        });
    }

    private void mostrarResumen() {
        String mensaje = "Resumen del juego:\n";
        mensaje += "Cantidad de figuras: " + juego.getNivel() + "\n";
        mensaje += "Intentos: " + juego.getIntentos() + "\n";
        mensaje += "Fallos: " + juego.getFallos() + "\n";

        JOptionPane.showMessageDialog(this, mensaje, "Resumen del Juego", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); // Cierra la aplicación después de mostrar el resumen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador jugador = new Jugador("Ejemplo");
            new VentanaJuego(jugador).setVisible(true);
        });
    }
}

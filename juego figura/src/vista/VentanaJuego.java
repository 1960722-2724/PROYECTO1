package vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.Jugador;

public class VentanaJuego extends JFrame {
    private JLabel lblNombre;
    private JLabel lblNivel;
    private JLabel lblIntentos;
    private JLabel lblFallos;
    private JLabel lblFiguraMuestra;
    private JButton btnTerminarJuego;
    private JLabel[] figurasLabels;
    private Jugador jugador;
    private List<String> rutasDisponibles;

    public VentanaJuego(Jugador jugador) {
        super("Juego de Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        this.jugador = jugador;
        rutasDisponibles = new ArrayList<>();

        initComponents();
        setupLayout();
        setupActions();
    }

    private void initComponents() {
        lblNombre = new JLabel("Jugador: " + jugador.getNombre());
        lblNivel = new JLabel("Nivel: 1");
        lblIntentos = new JLabel("Intentos: 0");
        lblFallos = new JLabel("Fallos: 0");
        lblFiguraMuestra = new JLabel();

        btnTerminarJuego = new JButton("Terminar Juego");
        figurasLabels = new JLabel[4]; // Cambiamos a 4 JLabels ya que solo habrá 4 opciones

        String rutaMuestra = seleccionarRutaAleatoria(); // Seleccionamos la ruta de la imagen de muestra
        ImageIcon iconoMuestra = new ImageIcon(rutaMuestra);
        lblFiguraMuestra.setIcon(iconoMuestra);
        lblFiguraMuestra.setName(rutaMuestra);

        System.out.println("Ruta de la imagen de muestra: " + rutaMuestra);

        for (int i = 0; i < figurasLabels.length; i++) {
            // Las imágenes de las opciones deben ser diferentes a la imagen de muestra
            String rutaOpcion;
            do {
                rutaOpcion = seleccionarRutaAleatoria();
            } while (rutaOpcion.equals(rutaMuestra));

            ImageIcon iconoOpcion = new ImageIcon(rutaOpcion);
            JLabel labelOpcion = new JLabel(iconoOpcion);
            labelOpcion.setName(rutaOpcion);
            figurasLabels[i] = labelOpcion;

            System.out.println("Ruta de opción " + (i + 1) + ": " + rutaOpcion);
        }
    }

    private String seleccionarRutaAleatoria() {
        if (rutasDisponibles.isEmpty()) {
            reinicializarRutasDisponibles();
        }

        Random random = new Random();
        int opcionAleatoriaIndex = random.nextInt(rutasDisponibles.size());
        String rutaSeleccionada = rutasDisponibles.get(opcionAleatoriaIndex);

        rutasDisponibles.remove(opcionAleatoriaIndex);

        return rutaSeleccionada;
    }

    private void reinicializarRutasDisponibles() {
        rutasDisponibles.clear();

        for (int i = 1; i <= 5; i++) {
            rutasDisponibles.add("src/imagenes/nivel1/" + i + ".png");
        }
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelFiguraMuestra = new JPanel();
        panelFiguraMuestra.add(lblFiguraMuestra);

        JPanel panelFiguras = new JPanel(new GridLayout(2, 2));
        for (JLabel label : figurasLabels) {
            panelFiguras.add(label);
        }

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.add(lblNombre);
        panelDatos.add(lblNivel);
        panelDatos.add(lblIntentos);
        panelDatos.add(lblFallos);

        JPanel panelBotonTerminar = new JPanel();
        panelBotonTerminar.add(btnTerminarJuego);

        add(panelFiguraMuestra, BorderLayout.WEST);
        add(panelFiguras, BorderLayout.CENTER);
        add(panelDatos, BorderLayout.EAST);
        add(panelBotonTerminar, BorderLayout.SOUTH);
    }

    private void setupActions() {
        // Agrega acciones de botones y clics como se hacía anteriormente
    }

    // Resto del código (actualizarVentana, mostrarResumen, main) igual que antes

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador jugador = new Jugador("Ejemplo");
            new VentanaJuego(jugador).setVisible(true);
        });
    }
}

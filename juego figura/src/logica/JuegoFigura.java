package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JuegoFigura {
    private List<Integer> figuras;
    private int figuraCorrecta;
    private int nivel;
    private int intentos;
    private int fallos;

    public JuegoFigura() {
        figuras = new ArrayList<>();
        nivel = 1;
        intentos = 0;
        fallos = 0;
    }

    public void iniciarNuevoNivel() {
        generarFigurasAleatorias();
        figuraCorrecta = generarFiguraCorrecta();
    }

    public List<Integer> getFiguras() {
        return figuras;
    }

    public int getFiguraCorrecta() {
        return figuraCorrecta;
    }

    public int getNivel() {
        return nivel;
    }

    public int getIntentos() {
        return intentos;
    }

    public int getFallos() {
        return fallos;
    }

    public void aumentarIntentos() {
        intentos++;
    }

    public void aumentarFallos() {
        fallos++;
    }

    public void avanzarNivel() {
        nivel++;
    }

    private void generarFigurasAleatorias() {
        figuras.clear();
        Random random = new Random();

        // Agrega las figuras al azar (cambia esto según tus necesidades)
        for (int i = 0; i < 4; i++) {
            figuras.add(random.nextInt(3) + 1); // Figuras del 1 al 3 (cambia esto según tus figuras)
        }

        // Baraja las figuras para que estén en orden aleatorio
        Collections.shuffle(figuras);
    }

    private int generarFiguraCorrecta() {
        // Elige una figura aleatoria como la correcta
        Random random = new Random();
        return figuras.get(random.nextInt(4));
    }
}

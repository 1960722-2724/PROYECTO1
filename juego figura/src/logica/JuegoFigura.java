package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JuegoFigura {
    private List<Integer> figurasAleatorias;
    private int figuraCorrecta;
    private int nivel;
    private int intentos;
    private int fallos;
    private boolean nuevoNivelListo;

    public JuegoFigura() {
        this.nivel = 1;
        this.intentos = 0;
        this.fallos = 0;
        this.figurasAleatorias = new ArrayList<>();
        iniciarNuevoNivel();
    }

    public void iniciarNuevoNivel() {
        generarFigurasAleatorias();
        nuevoNivelListo = false; // Reiniciar la bandera de nuevo nivel listo
    }

    public List<Integer> getFigurasAleatorias() {
        return figurasAleatorias;
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

    public boolean esNuevoNivelListo() {
        return nuevoNivelListo;
    }

    public void incrementarIntentos() {
        intentos++;
    }

    public void incrementarFallos() {
        fallos++;
    }

    public void avanzarNivel() {
        nivel++;
        iniciarNuevoNivel(); // Iniciar un nuevo nivel
        nuevoNivelListo = true; // Indicar que se ha completado el nuevo nivel
    }

    private void generarFigurasAleatorias() {
        figurasAleatorias.clear();
        Random random = new Random();

        // Elegir una figura aleatoria como la correcta
        figuraCorrecta = random.nextInt(4) + 1;

        // Agregar las otras figuras al azar (asegurarse de que al menos una sea la correcta)
        for (int i = 0; i < 4; i++) {
            if (i == figuraCorrecta - 1) {
                figurasAleatorias.add(figuraCorrecta);
            } else {
                int figuraAleatoria;
                do {
                    figuraAleatoria = random.nextInt(4) + 1;
                } while (figurasAleatorias.contains(figuraAleatoria));
                figurasAleatorias.add(figuraAleatoria);
            }
        }

        // Barajar las figuras para que estén en orden aleatorio
        Collections.shuffle(figurasAleatorias);
    }
}

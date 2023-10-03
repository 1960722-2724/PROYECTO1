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
    private boolean avanzarNivel;

    public JuegoFigura(int nivel) {
        this.nivel = nivel;
        this.intentos = 0;
        this.fallos = 0;
        this.figuras = new ArrayList<>();
        //this.figuraCorrecta = generarFiguraCorrecta();
        iniciarNuevoNivel();
    }

    public void iniciarNuevoNivel() {
        generarFigurasAleatorias();
        figuraCorrecta = generarFiguraCorrecta();
        avanzarNivel = false; // Reiniciar la bandera de avance de nivel
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

    public boolean debeAvanzarNivel() {
        return avanzarNivel;
    }

    public void aumentarIntentos() {
        intentos++;
    }

    public void aumentarFallos() {
        fallos++;
    }

    public void avanzarNivel() {
        nivel++;
        iniciarNuevoNivel(); // Iniciar un nuevo nivel
        avanzarNivel = true; // Indicar que se debe avanzar al siguiente nivel
    }

    private void generarFigurasAleatorias() {
        figuras.clear();
        Random random = new Random();

        // Elegir una figura aleatoria como la correcta
        figuraCorrecta = random.nextInt(4) + 1;

        // Agregar las otras figuras al azar (asegurarse de que al menos una sea la correcta)
        for (int i = 0; i < 4; i++) {
            if (i == figuraCorrecta - 1) {
                figuras.add(figuraCorrecta);
            } else {
                int figuraAleatoria;
                do {
                    figuraAleatoria = random.nextInt(4) + 1;
                } while (figuras.contains(figuraAleatoria));
                figuras.add(figuraAleatoria);
            }
        }

        // Barajar las figuras para que estén en orden aleatorio
        Collections.shuffle(figuras);
    }

    private int generarFiguraCorrecta() {
        // Elige una figura aleatoria como la correcta
        Random random = new Random();
        return figuras.get(random.nextInt(4));
    }
}

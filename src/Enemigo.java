import java.util.Random;

public class Enemigo {
    private int fila, columna;
    private int salud = 1;
    private Random rand = new Random();

    public Enemigo(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public void mover(int limite) {
        int movimiento = rand.nextInt(3) - 1;
        columna = Math.max(0, Math.min(limite - 1, columna + movimiento));
    }

    public void recibirDanio(int d) {
        salud -= d;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
}

public class Jugador {
    private String nombre;
    private int fila, columna;
    private int salud = 5;
    private int puntaje = 0;
    private int ataqueDobleTurnos = 0;

    public Jugador(String nombre, int fila, int columna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
    }

    public void mover(char direccion, int limite) {
        switch (direccion) {
            case 'w' -> fila = Math.max(0, fila - 1);
            case 's' -> fila = Math.min(limite - 1, fila + 1);
            case 'a' -> columna = Math.max(0, columna - 1);
            case 'd' -> columna = Math.min(limite - 1, columna + 1);
        }
    }

    public void recibirDanio(int d) {
        salud -= d;
    }

    public void curar(int c) {
        salud += c;
    }

    public void aumentarPuntaje(int p) {
        puntaje += p;
    }

    public void activarAtaqueDoble() {
        ataqueDobleTurnos = 3;
    }

    public boolean tieneAtaqueDoble() {
        return ataqueDobleTurnos > 0;
    }

    public void reducirTurnoAtaque() {
        if (ataqueDobleTurnos > 0) ataqueDobleTurnos--;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public int getSalud() { return salud; }
    public int getPuntaje() { return puntaje; }
}

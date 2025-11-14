public class Mejora {
    private int fila, columna;
    private String tipo;

    public Mejora(int fila, int columna, String tipo) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    public void caer() {
        fila++;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public String getTipo() { return tipo; }
}

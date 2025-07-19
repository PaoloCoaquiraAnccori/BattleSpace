// Esta clase podría usarse para mejoras o power-ups futuros
public class Mejora {
    private String tipo;
    private int valor;

    public Mejora(String tipo, int valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() { return tipo; }
    public int getValor() { return valor; }
}

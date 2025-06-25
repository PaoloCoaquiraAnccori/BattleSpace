public class Mejora {
    private String tipo; // "vida" o "ataque"
    private int valor;

    public Mejora(String tipo, int valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    public String descripcion() {
        return "Mejora de " + tipo + " (+" + valor + ")";
    }
}

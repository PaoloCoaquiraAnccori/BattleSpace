public class Enemigo {
    private String tipo;
    private int salud;
    private int ataque;

    public Enemigo (String tipo, int salud, int ataque){
        this.tipo = tipo;
        this.salud = salud;
        this.ataque = ataque;
    }

    public void atacar(Jugador j) {
        j.recibirDanio(ataque);
    }

    public void recibirDanio(int cantidad) {
        salud -= cantidad;
        if (salud < 0) salud = 0;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public void mostrarEstado() {
        System.out.println("Tipo: " + tipo + " | Salud: " + salud);
    }

    public String getTipo() {
        return tipo;
    }

    public int getSalud() {
        return salud;
    }

    public int getAtaque() {
        return ataque;
    }
}

public class Jugador {
    private String nombre;
    private int salud;
    private int ataque;
    private int puntaje;

    public Jugador(String nombre, int salud, int ataque) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.puntaje = 0;
    }

    public void atacar(Enemigo enemigo) {
        enemigo.recibirDanio(ataque);
        puntaje += 10;
    }

    public void recibirDanio(int cantidad) {
        salud -= cantidad;
        if (salud < 0) salud = 0;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public void usarMejora(Mejora m) {
        if (m.getTipo().equals("vida")) {
            salud += m.getValor();
        } else if (m.getTipo().equals("ataque")) {
            ataque += m.getValor();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getPuntaje() {
        return puntaje;
    }
}

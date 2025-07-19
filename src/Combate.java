import java.util.*;

public class Combate {
    private Jugador jugador;
    private Enemigo[] enemigos;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public Combate(Jugador jugador, Enemigo[] enemigos) {
        this.jugador = jugador;
        this.enemigos = enemigos;
    }

    public void jugarJugador() {
        if (enemigos.length == 0) return;
        System.out.println("\nElige enemigo a atacar:");
        for (int i = 0; i < enemigos.length; i++) {
            System.out.println((i + 1) + ". " + enemigos[i].getTipo() + " (Salud: " + enemigos[i].getSalud() + ")");
        }
        int opcion = scanner.nextInt() - 1;
        if (opcion >= 0 && opcion < enemigos.length) {
            jugador.atacar(enemigos[opcion]);
            if (!enemigos[opcion].estaVivo()) {
                verificarMejora(enemigos[opcion]);
            }
        }
    }

    public void jugarEnemigos() {
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) {
                e.atacar(jugador);
                System.out.println(e.getTipo() + " ataca al jugador por " + e.getAtaque() + " de daño.");
            }
        }
    }

    public void eliminarEnemigosDerrotados() {
        enemigos = Arrays.stream(enemigos)
                         .filter(Enemigo::estaVivo)
                         .toArray(Enemigo[]::new);
    }

    public void verificarMejora(Enemigo e) {
        if (random.nextBoolean()) {
            Mejora mejora;
            if (random.nextBoolean()) {
                mejora = new Mejora("vida", 20);
            } else {
                mejora = new Mejora("ataque", 5);
            }
            System.out.println("\n¡El enemigo soltó una mejora! " + mejora.descripcion());
            jugador.usarMejora(mejora);
        }
    }

    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}

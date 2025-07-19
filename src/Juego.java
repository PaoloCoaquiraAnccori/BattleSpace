import java.util.*;

public class Juego {
    private Jugador jugador;
    private boolean juegoTerminado;
    private int turno;
    private Combate combate;
    private Scanner scanner = new Scanner(System.in);

    public void iniciarJuego() {
        System.out.print("Ingrese el nombre de su nave: ");
        String nombre = scanner.nextLine();
        jugador = new Jugador(nombre, 100, 20);
        Enemigo[] enemigos = generarEnemigos(3);
        combate = new Combate(jugador, enemigos);
        juegoTerminado = false;
        turno = 1;
        System.out.println("\n--- ¡Bienvenido a Battle Space! ---\n");
    }

    public void ejecutarTurno() {
        System.out.println("\n--- Turno " + turno + " ---");
        mostrarEstado();
        combate.jugarJugador();
        combate.eliminarEnemigosDerrotados();
        combate.jugarEnemigos();
        turno++;
    }

    public void verificarFinDelJuego() {
        if (!jugador.estaVivo()) {
            juegoTerminado = true;
            System.out.println("\n¡Has sido destruido! Game Over.");
        } else if (combate.getEnemigos().length == 0) {
            juegoTerminado = true;
            System.out.println("\n¡Has derrotado a todos los enemigos! ¡Victoria!");
        }
    }

    public void mostrarEstado() {
        System.out.println("Jugador: " + jugador.getNombre() + " | Salud: " + jugador.getSalud() + " | Ataque: " + jugador.getAtaque() + " | Puntaje: " + jugador.getPuntaje());
        for (Enemigo e : combate.getEnemigos()) {
            e.mostrarEstado();
        }
    }

    public Enemigo[] generarEnemigos(int cantidad) {
        Enemigo[] lista = new Enemigo[cantidad];
        String[] tipos = {"Alien", "Drone", "Cazador"};
        Random r = new Random();
        for (int i = 0; i < cantidad; i++) {
            String tipo = tipos[r.nextInt(tipos.length)];
            int salud = 30 + r.nextInt(30); // 30 a 59
            int ataque = 5 + r.nextInt(10); // 5 a 14
            lista[i] = new Enemigo(tipo, salud, ataque);
        }
        return lista;
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciarJuego();
        while (!juego.juegoTerminado) {
            juego.ejecutarTurno();
            juego.verificarFinDelJuego();
        }
    }
}

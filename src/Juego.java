import java.util.*;

public class Juego {
    private static final int TAMANO = 10;
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private List<int[]> disparosJugador;
    private List<int[]> disparosEnemigos;
    private List<Mejora> mejoras;
    private Random random;
    private Scanner scanner;

    public Juego() {
        scanner = new Scanner(System.in);
        random = new Random();
        enemigos = new ArrayList<>();
        disparosJugador = new ArrayList<>();
        disparosEnemigos = new ArrayList<>();
        mejoras = new ArrayList<>();
    }

    public void iniciar() {
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        jugador = new Jugador(nombre, TAMANO - 1, TAMANO / 2);

        for (int i = 0; i < 3; i++) {
            enemigos.add(new Enemigo(random.nextInt(3), random.nextInt(TAMANO)));
        }

        while (jugador.estaVivo()) {
            mostrarTablero();
            System.out.println("Salud: " + jugador.getSalud() + " | Puntaje: " + jugador.getPuntaje());
            System.out.print("Mover (w/a/s/d), disparar (f), salir (x): ");
            char accion = scanner.nextLine().toLowerCase().charAt(0);

            if (accion == 'x') break;
            if (accion == 'f') {
                disparosJugador.add(new int[]{jugador.getFila() - 1, jugador.getColumna()});
                if (jugador.tieneAtaqueDoble()) {
                    if (jugador.getColumna() > 0)
                        disparosJugador.add(new int[]{jugador.getFila() - 1, jugador.getColumna() - 1});
                    if (jugador.getColumna() < TAMANO - 1)
                        disparosJugador.add(new int[]{jugador.getFila() - 1, jugador.getColumna() + 1});
                }
            } else {
                jugador.mover(accion, TAMANO);
            }

            jugador.reducirTurnoAtaque();
            moverEnemigos();
            moverDisparos();
            moverMejoras();
            verificarColisiones();
        }

        System.out.println("Juego terminado. Puntaje final: " + jugador.getPuntaje());
    }

    private void mostrarTablero() {
        char[][] tablero = new char[TAMANO][TAMANO];
        for (char[] fila : tablero) Arrays.fill(fila, '.');

        tablero[jugador.getFila()][jugador.getColumna()] = 'J';

        for (Enemigo e : enemigos) {
            if (e.estaVivo()) tablero[e.getFila()][e.getColumna()] = 'E';
        }

        for (int[] d : disparosJugador) {
            if (estaEnRango(d[0], d[1])) tablero[d[0]][d[1]] = '|';
        }

        for (int[] d : disparosEnemigos) {
            if (estaEnRango(d[0], d[1])) tablero[d[0]][d[1]] = '*';
        }

        for (Mejora m : mejoras) {
            if (estaEnRango(m.getFila(), m.getColumna())) tablero[m.getFila()][m.getColumna()] = 'M';
        }

        for (char[] fila : tablero) {
            for (char c : fila) System.out.print(c + " ");
            System.out.println();
        }
    }

    private void moverEnemigos() {
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) {
                e.mover(TAMANO);
                if (random.nextInt(100) < 20) {
                    disparosEnemigos.add(new int[]{e.getFila() + 1, e.getColumna()});
                }
            }
        }
    }

    private void moverDisparos() {
        disparosJugador.removeIf(d -> --d[0] < 0);
        disparosEnemigos.removeIf(d -> ++d[0] >= TAMANO);
    }

    private void moverMejoras() {
        Iterator<Mejora> it = mejoras.iterator();
        while (it.hasNext()) {
            Mejora m = it.next();
            m.caer();
            if (m.getFila() >= TAMANO) it.remove();
        }
    }

    private void verificarColisiones() {
        // Disparos jugador vs enemigos
        Iterator<int[]> disparosIt = disparosJugador.iterator();
        while (disparosIt.hasNext()) {
            int[] d = disparosIt.next();
            for (Enemigo e : enemigos) {
                if (e.estaVivo() && e.getFila() == d[0] && e.getColumna() == d[1]) {
                    e.recibirDanio(1);
                    jugador.aumentarPuntaje(10);
                    disparosIt.remove();

                    if (!e.estaVivo()) {
                        if (random.nextInt(100) < 50) {
                            String tipo = random.nextBoolean() ? "salud" : "ataque";
                            mejoras.add(new Mejora(e.getFila(), e.getColumna(), tipo));
                        }
                        enemigos.remove(e);
                        enemigos.add(new Enemigo(random.nextInt(3), random.nextInt(TAMANO)));
                    }
                    break;
                }
            }
        }

        // Disparos enemigos vs jugador
        disparosEnemigos.removeIf(d -> {
            if (jugador.getFila() == d[0] && jugador.getColumna() == d[1]) {
                jugador.recibirDanio(1);
                return true;
            }
            return false;
        });

        // Jugador recoge mejora
        Iterator<Mejora> it = mejoras.iterator();
        while (it.hasNext()) {
            Mejora m = it.next();
            if (jugador.getFila() == m.getFila() && jugador.getColumna() == m.getColumna()) {
                if (m.getTipo().equals("salud")) {
                    jugador.curar(1);
                } else if (m.getTipo().equals("ataque")) {
                    jugador.activarAtaqueDoble();
                }
                it.remove();
            }
        }
    }

    private boolean estaEnRango(int fila, int col) {
        return fila >= 0 && fila < TAMANO && col >= 0 && col < TAMANO;
    }

    public static void main(String[] args) {
        new Juego().iniciar();
    }
}

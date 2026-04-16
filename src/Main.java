import Modelos.Clases.Equipo;
import Modelos.Clases.Jugador;
import Modelos.Clases.Partida;
import Modelos.Clases.Torneo;
import Modelos.Enumeradores.Resultado_del_Partido;
import Modelos.Enumeradores.Rol_del_Jugador;
import Modelos.Enumeradores.Estado_de_Torneo;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase principal - Punto de entrada del sistema
 * Aquí se ejecuta todo el programa
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(" SISTEMA DE TORNEOS UAM E-SPORTS");
        System.out.println("=".repeat(40));

        // ========================================
        // 1. CREAR JUGADORES DEL EQUIPO T1
        // ========================================
        System.out.println("\n Creando jugadores del equipo T1...");

        // Faker - El mejor jugador de la historia de LOL
        Jugador faker = new Jugador("J001", "Faker", "Lee Sang-hyeok",
                LocalDate.of(1996, 5, 7), "Corea");
        faker.setRol(Rol_del_Jugador.Capitan);  // Es el capitán
        faker.actualizarRanking(2800);      // Muy alto ranking

        // Gumayusi - Jugador estrella
        Jugador gumayusi = new Jugador("J002", "Gumayusi", "Lee Min-hyeong",
                LocalDate.of(2002, 2, 6), "Corea");
        gumayusi.actualizarRanking(2650);

        // Keria - Soporte
        Jugador keria = new Jugador("J003", "Keria", "Ryu Min-seok",
                LocalDate.of(2002, 10, 14), "Corea");
        keria.actualizarRanking(2700);

        // Registrar estadísticas de Faker
        faker.registrarEstadistica("kills", 150);
        faker.registrarEstadistica("deaths", 50);
        faker.registrarEstadistica("assists", 200);
        System.out.println(" KDA de Faker: " + String.format("%.2f", faker.obtenerKDA()));

        // ========================================
        // 2. CREAR EQUIPO T1
        // ========================================
        System.out.println("\n Creando equipo T1...");
        Equipo t1 = new Equipo("E001", "T1", "T1", "Corea");

        // Agregar jugadores al equipo
        t1.agregarJugador(faker);
        t1.agregarJugador(gumayusi);
        t1.agregarJugador(keria);

        System.out.println(" T1 creado con " + t1.getJugadores().size() + " jugadores");
        System.out.println(" Ranking promedio T1: " + String.format("%.0f", t1.calcularRankingPromedio()));

        // ========================================
        // 3. CREAR JUGADORES DEL EQUIPO GEN.G
        // ========================================
        System.out.println("\n Creando jugadores del equipo Gen.G...");

        Jugador chovy = new Jugador("J004", "Chovy", "Jeong Ji-hoon",
                LocalDate.of(2001, 3, 3), "Corea");
        chovy.setRol(Rol_del_Jugador.Capitan);
        chovy.actualizarRanking(2750);

        Jugador ruler = new Jugador("J005", "Ruler", "Park Jae-hyuk",
                LocalDate.of(1998, 12, 29), "Corea");
        ruler.actualizarRanking(2600);

        Jugador kiin = new Jugador("J006", "Kiin", "Kim Gi-in",
                LocalDate.of(2000, 5, 28), "Corea");
        kiin.actualizarRanking(2550);

        // ========================================
        // 4. CREAR EQUIPO GEN.G
        // ========================================
        System.out.println("\n Creando equipo Gen.G...");
        Equipo genG = new Equipo("E002", "Gen.G", "GEN", "Corea");

        genG.agregarJugador(chovy);
        genG.agregarJugador(ruler);
        genG.agregarJugador(kiin);

        System.out.println(" Gen.G creado con " + genG.getJugadores().size() + " jugadores");

        // ========================================
        // 5. CREAR TORNEO WORLDS 2024
        // ========================================
        System.out.println("\n Creando torneo Worlds 2024...");
        Torneo worlds = new Torneo("T001", "Worlds 2024", "League of Legends",
                LocalDate.of(2024, 10, 1),
                LocalDate.of(2024, 11, 2),
                1000000.0,  // $1 millón de dólares
                16);         // Máximo 16 equipos

        // Inscribir equipos al torneo
        worlds.inscribirEquipo(t1);
        worlds.inscribirEquipo(genG);

        // ========================================
        // 6. INICIAR TORNEO
        // ========================================
        worlds.iniciarTorneo();

        // ========================================
        // 7. JUGAR PARTIDA FINAL
        // ========================================
        System.out.println("\n️  Jugando partida final: T1 vs Gen.G...");

        Partida finalMatch = new Partida("P001", worlds, t1, genG,
                LocalDateTime.of(2024, 11, 2, 20, 0));
        worlds.agregarPartida(finalMatch);

        // T1 gana 3-2 en series
        finalMatch.finalizar(Resultado_del_Partido.Equipo_A, "T1 gana 3-2 en series");
        System.out.println(" Resultado: T1 vence a Gen.G");

        // ========================================
        // 8. FINALIZAR TORNEO Y MOSTRAR RESULTADOS
        // ========================================
        worlds.finalizarTorneo();

        // ========================================
        // 9. PRUEBA: Intentar agregar jugador menor de edad
        // ========================================
        System.out.println("\n Prueba de validación (debe fallar):");
        Jugador prodigio = new Jugador("J007", "Prodigy", "Niño Genio",
                LocalDate.of(2015, 1, 1), "Corea");
        System.out.println("¿Es mayor de edad? " + prodigio.esMayorDeEdad());
        t1.agregarJugador(prodigio);  // Esto debe mostrar error
    }
}
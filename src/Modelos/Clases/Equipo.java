package Modelos.Clases;

import Modelos.Enumeradores.Rol_del_Jugador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// ========== CONSTRUCTOR ==========
/**
 * Crea un nuevo jugador
 */


/**
 * Representa un equipo de jugadores en el sistema de torneos
 *
 * REGLAS DE NEGOCIO:
 * - Mínimo 3 jugadores, máximo 7
 * - Debe tener exactamente 1 CAPITAN
 * - Todos los jugadores deben ser mayores de edad
 */
public class Equipo {

    // ========== CONSTANTES (Reglas del negocio) ==========
    private static final int MIN_JUGADORES = 3;
    private static final int MAX_JUGADORES = 7;

    // ========== ATRIBUTOS ==========
    private String idEquipo;
    private String nombre;           // Nombre completo (ej: "T1")
    private String tag;              // Abreviatura (ej: "T1")
    private LocalDate fechaCreacion; // Cuándo se fundó el equipo
    private String pais;             // País de origen
    private List<Jugador> jugadores; // Lista de jugadores (3-7)
    private int puntosTorneo;        // Puntos acumulados en torneo actual
    private List<Partida> historialPartidas;

    // ========== CONSTRUCTOR ==========
    public Equipo(String idEquipo, String nombre, String tag, String pais) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.tag = tag;
        this.pais = pais;
        this.fechaCreacion = LocalDate.now(); // Fecha de hoy
        this.jugadores = new ArrayList<>();
        this.puntosTorneo = 0;
        this.historialPartidas = new ArrayList<>();
    }

    // ========== MÉTODOS DE NEGOCIO ==========

    /**
     * Agrega un jugador al equipo SI cumple las reglas:
     * 1. Hay espacio (máximo 7)
     * 2. El jugador es mayor de edad
     */
    public boolean agregarJugador(Jugador jugador) {
        // Verificar límite máximo
        if (jugadores.size() >= MAX_JUGADORES) {
            System.out.println("Error: Máximo " + MAX_JUGADORES + " jugadores permitidos");
            return false;
        }

        // Verificar mayoría de edad
        if (!jugador.esMayorDeEdad()) {
            System.out.println(" Error: " + jugador.getNickname() + " es menor de edad");
            return false;
        }

        jugadores.add(jugador);
        jugador.setEquipoActual(this);
        return true;
    }

    /**
     * Remueve un jugador del equipo por su ID
     */
    public boolean removerJugador(String jugadorId) {
        for (Jugador j : jugadores) {
            if (j.getIdJugador().equals(jugadorId)) {
                j.setEquipoActual(null);
                return jugadores.remove(j);
            }
        }
        return false;
    }

    /**
     * Busca y retorna el capitán del equipo
     */
    public Jugador obtenerCapitan() {
        for (Jugador j : jugadores) {
            if (j.getRol() == Rol_del_Jugador.Capitan) {
                return j;
            }
        }
        return null; // No hay capitán
    }

    /**
     * Calcula el ranking promedio de todos los jugadores
     */
    public double calcularRankingPromedio() {
        if (jugadores.isEmpty()) return 0;

        int suma = 0;
        for (Jugador j : jugadores) {
            suma += j.getRankingIndividual();
        }
        return (double) suma / jugadores.size();
    }

    /**
     * Valida que el equipo cumpla TODAS las reglas para inscribirse:
     * - Mínimo 3 jugadores
     * - Exactamente 1 capitán
     */
    public boolean validarPlantilla() {
        if (jugadores.size() < MIN_JUGADORES) {
            return false;
        }

        int capitanes = 0;
        for (Jugador j : jugadores) {
            if (j.getRol() == Rol_del_Jugador.Capitan) {
                capitanes++;
            }
        }
        return capitanes == 1;
    }

    /**
     * Agrega puntos al equipo en el torneo actual
     */
    public void agregarPuntos(int puntos) {
        this.puntosTorneo += puntos;
    }

    // ========== GETTERS ==========
    public String getIdEquipo() { return idEquipo; }
    public String getNombre() { return nombre; }
    public String getTag() { return tag; }
    public String getPais() { return pais; }
    public List<Jugador> getJugadores() { return new ArrayList<>(jugadores); }
    public int getPuntosTorneo() { return puntosTorneo; }
}
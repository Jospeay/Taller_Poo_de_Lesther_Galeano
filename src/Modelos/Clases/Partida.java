package Modelos.Clases;

import Modelos.Enumeradores.Resultado_del_Partido;
import java.time.LocalDateTime;

/**
 * Representa una partida/enfrentamiento entre dos equipos
 */
public class Partida {

    // ========== ATRIBUTOS ==========
    private String idPartida;
    private Torneo torneo;        // Torneo al que pertenece
    private Equipo equipoA;         // Equipo "local"
    private Equipo equipoB;         // Equipo "visitante"
    private LocalDateTime fechaHora;
    private Resultado_del_Partido resultado;
    private String detalles;        // Marcador, mapas jugados, etc.

    // ========== CONSTRUCTOR ==========
    public Partida(String idPartida, Torneo torneo, Equipo equipoA,
                   Equipo equipoB, LocalDateTime fechaHora) {
        this.idPartida = idPartida;
        this.torneo = torneo;
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.fechaHora = fechaHora;
        this.resultado = Resultado_del_Partido.Pendiente; // Al crear, está pendiente
    }

    // ========== MÉTODOS DE NEGOCIO ==========

    /**
     * Finaliza la partida, registra el resultado y asigna puntos:
     * - Victoria = 3 puntos
     * - Empate = 1 punto cada uno
     * - Derrota = 0 puntos
     */
    public void finalizar(Resultado_del_Partido resultado, String detalles) {
        this.resultado = resultado;
        this.detalles = detalles;

        switch (resultado) {
            case Equipo_A:
                equipoA.agregarPuntos(3);
                break;
            case Equipo_B:
                equipoB.agregarPuntos(3);
                break;
            case Empate:
                equipoA.agregarPuntos(1);
                equipoB.agregarPuntos(1);
                break;
            case Pendiente:
                // No hacer nada, partida no jugada
                break;
        }
    }

    /**
     * Retorna el equipo ganador, o null si es empate/pendiente
     */
    public Equipo obtenerGanador() {
        return switch (resultado) {
            case Equipo_A -> equipoA;
            case Equipo_B -> equipoB;
            default -> null; // EMPATE o PENDIENTE
        };
    }

    // ========== GETTERS ==========
    public String getIdPartida() { return idPartida; }
    public Equipo getEquipoA() { return equipoA; }
    public Equipo getEquipoB() { return equipoB; }
    public Resultado_del_Partido getResultado() { return resultado; }
    public boolean isFinalizada() {
        return resultado != Resultado_del_Partido.Pendiente;
    }
}

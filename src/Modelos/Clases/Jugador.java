package Modelos.Clases;

import Modelos.Enumeradores.Rol_del_Jugador;

import java.time.Period;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa un jugador individual en el sistema de torneos
 *
 * ATRIBUTOS:
 * - idJugador: Identificador único (J001, J002, etc.)
 * - nickname: Nombre en el juego (NoobMaster, Messi, etc.)
 * - nombreReal: Nombre completo real
 * - fechaNacimiento: Para calcular edad y verificar mayoría
 * - pais: Nacionalidad
 * - rol: Capitan, Jugador o Suplente
 * - rankingIndividual: Puntos de habilidad
 * - estadisticas: Mapa con kills, deaths, assists, etc.
 * - equipoActual: Referencia al equipo donde juega (puede ser null)
 */
public class Jugador {
    // ========== ATRIBUTOS PRIVADOS (Encapsulamiento) ==========
    private String idJugador;
    private String nickname;
    private String nombreReal;
    private LocalDate fechaNacimiento;
    private String pais;
    private Rol_del_Jugador rol;
    private int rankingIndividual;
    private Map<String, Object> estadisticas;
    private Equipo equipoActual;

    // ========== CONSTRUCTOR ==========
    /**
     * Crea un nuevo jugador
     */
    public Jugador(String idJugador, String nickname, String nombreReal,
                   LocalDate fechaNacimiento, String pais) {
        this.idJugador = idJugador;
        this.nickname = nickname;
        this.nombreReal = nombreReal;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.rol = Rol_del_Jugador.Jugador;      // Por defecto es jugador normal
        this.rankingIndividual = 1000;       // Ranking inicial base
        this.estadisticas = new HashMap<>(); // Mapa vacío de estadísticas
    }

    // ========== MÉTODOS DE NEGOCIO ==========

    /**
     * Verifica si el jugador tiene 18 años o más
     * Se usa para validar que puede participar en torneos
     */
    public boolean esMayorDeEdad() {
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        return edad >= 18;
    }

    /**
     * Actualiza el ranking del jugador con validación
     */
    public void actualizarRanking(int nuevoRanking) {
        if (nuevoRanking >= 0) {
            this.rankingIndividual = nuevoRanking;
        }
    }

    /**
     * Transfiere al jugador a otro equipo
     * Primero lo saca del equipo actual (si tiene), luego intenta entrar al nuevo
     */
    public boolean transferirA(Equipo nuevoEquipo) {
        // Si ya está en un equipo, salir de él
        if (this.equipoActual != null) {
            this.equipoActual.removerJugador(this.idJugador);
        }

        // Intentar entrar al nuevo equipo
        if (nuevoEquipo.agregarJugador(this)) {
            this.equipoActual = nuevoEquipo;
            return true;
        }
        return false;
    }

    /**
     * Registra una estadística del jugador (kills, deaths, assists, etc.)
     */
    public void registrarEstadistica(String clave, Object valor) {
        estadisticas.put(clave, valor);
    }

    /**
     * Calcula el KDA (Kills(Muertes) + Assists(Asistencias) / Deaths(Muertes del jugador))
     * Métrica común en juegos para medir rendimiento
     */
    public double obtenerKDA() {
        int kills = (int) estadisticas.getOrDefault("kills", 0);
        int deaths = (int) estadisticas.getOrDefault("deaths", 1); // 1 para evitar dividir por 0
        int assists = (int) estadisticas.getOrDefault("assists", 0);
        return (double) (kills + assists) / deaths;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Rol_del_Jugador getRol() {
        return rol;
    }

    public void setRol(Rol_del_Jugador rol) {
        this.rol = rol;
    }

    public int getRankingIndividual() {
        return rankingIndividual;
    }

    public void setRankingIndividual(int rankingIndividual) {
        this.rankingIndividual = rankingIndividual;
    }

    public Map<String, Object> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Map<String, Object> estadisticas) {
        this.estadisticas = estadisticas;
    }

    public Equipo getEquipoActual() {
        return equipoActual;
    }

    public void setEquipoActual(Equipo equipoActual) {
        this.equipoActual = equipoActual;
    }
}

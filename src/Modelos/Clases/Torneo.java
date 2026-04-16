package Modelos.Clases;

import Modelos.Enumeradores.Estado_de_Torneo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa un torneo de e-sports completo
 *
 * PREMIOS:
 * - 1° lugar: 50% del premio total
 * - 2° lugar: 30% del premio total
 * - 3° lugar: 20% del premio total
 */
public class Torneo {

    // ========== ATRIBUTOS ==========
    private String idTorneo;
    private String nombre;
    private String juego;           // Ej: "League of Legends", "Valorant"
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double premioTotal;
    private Estado_de_Torneo estado;
    private int maxEquipos;
    private List<Equipo> equiposInscritos;
    private List<Partida> partidas;

    // ========== CONSTRUCTOR ==========
    public Torneo(String idTorneo, String nombre, String juego,
                  LocalDate fechaInicio, LocalDate fechaFin,
                  double premioTotal, int maxEquipos) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
        this.juego = juego;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.premioTotal = premioTotal;
        this.maxEquipos = maxEquipos;
        this.estado = Estado_de_Torneo.Planificado;
        this.equiposInscritos = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    // ========== MÉTODOS DE NEGOCIO ==========

    /**
     * Inscribe un equipo al torneo SI:
     * - El torneo está PLANIFICADO (no iniciado)
     * - Hay cupo disponible
     * - El equipo tiene plantilla válida
     */
    public boolean inscribirEquipo(Equipo equipo) {
        if (estado != Estado_de_Torneo.Planificado) {
            System.out.println(" Las inscripciones están cerradas");
            return false;
        }

        if (equiposInscritos.size() >= maxEquipos) {
            System.out.println(" No hay cupo disponible");
            return false;
        }

        if (!equipo.validarPlantilla()) {
            System.out.println(" El equipo no cumple las reglas (3-7 jugadores + 1 capitán)");
            return false;
        }

        equiposInscritos.add(equipo);
        System.out.println(" Equipo " + equipo.getNombre() + " inscrito en " + nombre);
        return true;
    }

    /**
     * Cambia el estado a En_curso si hay al menos 2 equipos
     */
    public void iniciarTorneo() {
        if (equiposInscritos.size() < 2) {
            System.out.println(" Se necesitan al menos 2 equipos");
            return;
        }
        this.estado = Estado_de_Torneo.En_curso;
        System.out.println(" ¡El torneo " + nombre + " ha comenzado!");
    }

    /**
     * Calcula el premio según la posición final
     */
    public double calcularPremio(int posicion) {
        return switch (posicion) {
            case 1 -> premioTotal * 0.50;  // 50%
            case 2 -> premioTotal * 0.30;  // 30%
            case 3 -> premioTotal * 0.20;  // 20%
            default -> 0;                   // Demás posiciones: nada
        };
    }

    /**
     * Retorna lista de equipos ordenada por puntos (mayor a menor)
     */
    public List<Equipo> obtenerClasificacion() {
        List<Equipo> clasificacion = new ArrayList<>(equiposInscritos);
        clasificacion.sort((e1, e2) -> e2.getPuntosTorneo() - e1.getPuntosTorneo());
        return clasificacion;
    }

    /**
     * Finaliza el torneo, muestra podio y premios
     */
    public void finalizarTorneo() {
        this.estado = Estado_de_Torneo.Finalizado;
        List<Equipo> clasificacion = obtenerClasificacion();

        System.out.println("\n" + "=".repeat(50));
        System.out.println(" TORNEO FINALIZADO: " + nombre);
        System.out.println("=".repeat(50));
        System.out.printf("%-4s %-15s %-8s %-12s%n", "POS", "EQUIPO", "PTOS", "PREMIO");
        System.out.println("-".repeat(50));

        for (int i = 0; i < clasificacion.size() && i < 3; i++) {
            Equipo e = clasificacion.get(i);
            System.out.printf("%-4d %-15s %-8d $%-11.2f%n",
                    i + 1, e.getNombre(), e.getPuntosTorneo(), calcularPremio(i + 1));
        }
        System.out.println("=".repeat(50));
    }

    /**
     * Agrega una partida al calendario del torneo
     */
    public void agregarPartida(Partida partida) {
        partidas.add(partida);
    }

    // ========== GETTERS ==========
    public String getIdTorneo() { return idTorneo; }
    public String getNombre() { return nombre; }
    public String getJuego() { return juego; }
    public Estado_de_Torneo getEstado() { return estado; }
    public List<Equipo> getEquiposInscritos() {
        return Collections.unmodifiableList(equiposInscritos);
    }
    public double getPremioTotal() { return premioTotal; }
}

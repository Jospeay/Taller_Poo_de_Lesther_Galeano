# Taller_Poo_de_Lesther_Galeano
Presentacion del problema propuesto sobre un sistema de gestion de torneos de UAM-Sports

#  Sistema de Gestión de Torneos de UAM E-Sports

##  Descripción
Sistema para organizar torneos competitivos de videojuegos. Gestiona equipos, jugadores, partidas y premios.

##  Funcionalidades Principales
- Crear torneos con premios en efectivo
- Inscribir equipos (3-7 jugadores cada uno)
- Validar que jugadores sean mayores de edad
- Registrar resultados de partidas
- Calcular clasificación automática
- Distribuir premios (50%-30%-20%)

## Modelo de Clases
### Clases Principales
/ `Torneo`  Organizar el evento, inscribir equipos, gestionar premios 
/ `Equipo` Agrupar jugadores, acumular puntos, validar reglas 
/ `Jugador`  Representar participante, verificar edad, transferir entre equipos /

### Clases de Soporte
/`Partida` Registrar enfrentamientos entre dos equipos /

## Relaciones
**Torneo** `1 --- *` **Equipo** (un torneo tiene muchos equipos)
**Equipo** `1 --- 3..7` **Jugador** (un equipo tiene 3 a 7 jugadores)
**Equipo** `* --- *` **Partida** (equipos juegan muchas partidas)

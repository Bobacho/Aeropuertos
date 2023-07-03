import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controladores.ControladorAeropuertos;
import modelo.Aeropuerto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class ControladorAeropuertosTest {
    private ControladorAeropuertos controlador;

    @BeforeEach
    void setUp() throws Exception {
        controlador = new ControladorAeropuertos();
    }

    @Test
    void testGenerarConexiones() {
        assertNotNull(controlador.conexiones, "Las conexiones no deben ser nulas");

        // Verificar si todas las conexiones tienen valores booleanos asignados
        for (Boolean value : controlador.conexiones.values()) {
            assertNotNull(value, "El valor de conexión no debe ser nulo");
        }
    }

    @Test
    void testVerConexiones() {
        Aeropuerto aeropuerto = controlador.getAeropuerto(0); // Obtener el primer aeropuerto

        assertNotNull(aeropuerto, "El aeropuerto no debe ser nulo");

        // Verificar si el resultado de verConexiones() contiene la información correcta
        for (String conexion : controlador.verConexiones(aeropuerto)) {
            assertTrue(conexion.startsWith("[" + aeropuerto.getNombre()), "El resultado debe comenzar con el nombre del aeropuerto");
        }
    }
    @Test
    void testMetodoWarshall() {
        HashMap<Map<Aeropuerto, Aeropuerto>, Boolean> cierreTransitivo = controlador.metodoWarshall();

        assertNotNull(cierreTransitivo, "El cierre transitivo no debe ser nulo");

        // Verificar si el cierre transitivo contiene todas las combinaciones de aeropuertos
        for (Aeropuerto origen : controlador.getAeropuertos()) {
            for (Aeropuerto destino : controlador.getAeropuertos()) {
                Map<Aeropuerto, Aeropuerto> connectionKey = Map.of(origen, destino);
                assertTrue(cierreTransitivo.containsKey(connectionKey), "El cierre transitivo debe contener la conexión");
            }
        }
    }
    @Test
    void testGetConexion() {
        // Verificar conexiones existentes entre diferentes aeropuertos
        assertEquals(controlador.getConexion(0, 1),controlador.conexiones.get(Map.of(controlador.getAeropuerto(0),controlador.getAeropuerto(1))));
    }
}
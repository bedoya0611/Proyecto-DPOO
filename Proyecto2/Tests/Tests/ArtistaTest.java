package Tests;

package galeria.artista;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.inventario.Pieza;
import java.util.List;

public class ArtistaTest {
    private Artista artista;
    private Pieza pieza1;
    private Pieza pieza2;

    @BeforeEach
    void setUp() {
        artista = new Artista("Frida Kahlo", "001");
        pieza1 = new Pieza("Autorretrato con collar de espinas", 1940, "Ciudad de México", 
                           new ArrayList<>(Arrays.asList("Frida Kahlo")), true, true);
        pieza2 = new Pieza("La Columna Rota", 1944, "Ciudad de México", 
                           new ArrayList<>(Arrays.asList("Frida Kahlo")), false, false);
    }

    @Test
    void testAñadirObra() {
        artista.añadirObra(pieza1);
        assertEquals(1, artista.getObras().size());
        assertTrue(artista.getObras().contains(pieza1));
    }

    @Test
    void testObtenerHistoria() {
        artista.añadirObra(pieza1);
        artista.añadirObra(pieza2);
        List<String> historia = artista.obtenerHistoria();

        assertNotNull(historia);
        assertEquals(2, historia.size());
        assertTrue(historia.get(0).contains("Autorretrato con collar de espinas"));
        assertTrue(historia.get(1).contains("La Columna Rota"));
    }
}

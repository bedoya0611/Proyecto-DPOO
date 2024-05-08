package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.inventario.Pieza;
import galeria.inventario.Pintura;
import galeria.usuarios.Artista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistaTest {
    private Artista artista;
    private Pieza pieza1;
    private Pieza pieza2;

    @BeforeEach
    void setUp() {
        artista = new Artista("Frida Kahlo", 1);
        ArrayList<Artista> artistas = new ArrayList<Artista>();
        artistas.add(artista);
        pieza1 = new Pintura("Autorretrato con collar de espinas", 1940, "Ciudad de México", 
                            artistas, true, true, 1, 2, "x", "y");
        pieza2 = new Pintura("La Columna Rota", 1944, "Ciudad de México", 
                           artistas, false, false, 1, 2, "x", "y");
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

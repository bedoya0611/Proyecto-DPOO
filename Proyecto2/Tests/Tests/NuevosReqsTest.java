package Tests;

import galeria.inventario.Pieza;
import galeria.usuarios.Artista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class NuevosReqsTest {
	private Pieza pieza;
	private Artista artista;

    @BeforeEach
    void setUp() {
    	artista = new Artista("Nombre");
    	ArrayList<String> autores = new ArrayList<>();
    	autores.add(artista);
        pieza = new Pieza("Titulo", 0, "Lugar", false, false, autores);
        artista.añadirObra(pieza);
    }
    
    @Test
    void testVerificarInclusiónDeElementoPieza() {
        ArrayList<String> historiaPieza = pieza.obtenerHistoriaPieza();
		assertTrue(historiaPieza.get(0) != null);
    }

    @Test
    void testVerificarInclusiónDeElementoArtista() {
    	ArrayList<String> historiaArtista = artista.obtenerHistoria();
    	assertTrue(historiaArtista.get(0) != null);
    }
}

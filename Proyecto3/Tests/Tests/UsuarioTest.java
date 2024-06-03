package Tests;

import galeria.usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTest {
	
	private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Nombre", "Contraseña");
    }
    
    @Test
    void testConfirmarCambioPassword() {
        usuario.cambiarPassword("NuevaContraseña");
        assertTrue(usuario.verificarPassword("NuevaContraseña"));
    }

    @Test
    void testCambioPasswordVacio() {
    	usuario.cambiarPassword("");
    	assertFalse(usuario.verificarPassword(""));
    }

}

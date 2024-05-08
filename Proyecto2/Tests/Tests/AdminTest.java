package Tests;

import galeria.usuarios.Admin;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;
import galeria.ventas.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class AdminTest {

    private Admin admin;
    private Inventario inventario;
    private Pieza pieza;
    private Venta venta;
    private List<Comprador> compradores;

    @BeforeEach
    void setUp() {
        admin = new Admin("adminLogin", "adminPass", "Admin", 1);
        inventario = new Inventario();
        pieza = new Pieza("Obra", 2020, "Ciudad", new ArrayList<>(), true, true);
        venta = new Venta() { 
            private boolean pagoHecho = false;

            @Override
            public void realizarVenta() {
                this.pagoHecho = true;
            }

            @Override
            public boolean getPagoHecho() {
                return pagoHecho;
            }
        };
        compradores = new ArrayList<>();
    }

    @Test
    void testRegistrarPieza() {
        try {
            admin.registrarPieza(pieza, inventario);
            assertTrue(inventario.contienePieza(pieza)); 
        } catch (Exception e) {
            fail("No se esperaba una excepción.");
        }
    }

    @Test
    void testConfirmarVentaPagoHecho() {
        venta.setPagoHecho(true); 
        admin.confirmarVenta(venta);
        assertTrue(venta.getPagoHecho());
    }

    @Test
    void testConfirmarVentaPagoNoHecho() {
        venta.setPagoHecho(false);
        admin.confirmarVenta(venta);
        assertFalse(venta.getPagoHecho());
    }

    @Test
    void testAgregarEmpleado() {
        admin.agregarEmpleado("Empleado", 2, "empLogin", "empPass", "Operador");
        assertEquals(1, admin.getEmpleados().size());
    }

    @Test
    void testConsultarCompradorEncontrado() {
        Comprador comprador = new Comprador("compLogin", "compPass", "Comprador", 2);
        compradores.add(comprador);
        Comprador resultado = admin.consultarComprador(2, compradores);
        assertNotNull(resultado);
    }

    @Test
    void testConsultarCompradorNoEncontrado() {
        Comprador resultado = admin.consultarComprador(3, compradores);
        assertNull(resultado);
    }

    @Test
    void testVerificarComprador() {
        Comprador comprador = new Comprador("compLogin", "compPass", "Comprador", 2, true);
        assertTrue(admin.verificarComprador(comprador));
    }

    @Test
    void testModificarComprador() {
        Comprador comprador = new Comprador("compLogin", "compPass", "Comprador", 2, true);
        admin.modificarComprador(comprador, 1234567890);
        assertEquals(1234567890, comprador.getTelefono());
    }
}

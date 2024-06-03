package test;

import galeria.usuarios.Admin;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;
import galeria.inventario.Pintura;
import galeria.ventas.Venta;
import galeria.ventas.VentaFija;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

public class AdminTest {

    private Admin admin;
    private Inventario inventario;
    private Pieza pieza;
    private VentaFija venta;
    private List<Comprador> compradores;

    @BeforeEach
    void setUp() {
        admin = new Admin("adminLogin", "adminPass", "Admin", 1);
        inventario = new Inventario();
        pieza = new Pintura("Obra", 2020, "Ciudad", new ArrayList<>(), true, true, 0, 0, "x", "y");
        venta = new VentaFija(4056, new Comprador(false, null, 0, 0, null, null)) { 
            private boolean pagoHecho = false;
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
        admin.confirmarVenta(venta, inventario);
        assertTrue(venta.getPagoHecho());
    }

    @Test
    void testConfirmarVentaPagoNoHecho() {
        venta.setPagoHecho(false);
        admin.confirmarVenta(venta, inventario);
        assertFalse(venta.getPagoHecho());
    }

    @Test
    void testAgregarEmpleado() {
        admin.agregarEmpleado("Empleado", 2, "empLogin", "empPass", "Operador");
        assertEquals(1, admin.getEmpleados().size());
    }

    @Test
    void testConsultarCompradorEncontrado() {
        Comprador comprador = new Comprador(true,"Comprador",1, 2, "compLogin", "compPass");
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
        Comprador comprador = new Comprador(false,"Comprador",1, 2, "compLogin", "compPass");
        admin.verificarComprador(comprador);
        assertTrue(comprador.isVerificado());
    }
}

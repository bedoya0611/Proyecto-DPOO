package Tests;

import galeria.usuarios.Cajero;
import galeria.ventas.VentaFija;
import galeria.ventas.Subasta;
import galeria.inventario.Pieza;
import galeria.inventario.Inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CajeroTest {
    private Cajero cajero;
    private VentaFija ventaFija;
    private Subasta subasta;
    private Pieza pieza;
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        cajero = new Cajero("userLogin", "userPass", "User Name", 1234);
        pieza = new Pieza("Obra de Arte", 2020, "Madrid", new ArrayList<>(), true, true);
        inventario = new Inventario();
        inventario.agregarPieza(pieza);

        ventaFija = new VentaFija(100);
        ventaFija.pieza = pieza;
        ventaFija.inventario = inventario;

        subasta = new Subasta(100, 150, 200, pieza, inventario);
        subasta.pieza = pieza;
        subasta.inventario = inventario;
    }

    @Test
    void testRegistrarPagoVentaFijaCorrecta() {
        cajero.registrarPago(ventaFija, 100);
        assertTrue(ventaFija.pagoHecho, "El pago debería haber sido registrado como realizado");
    }

    @Test
    void testRegistrarPagoVentaFijaInsuficiente() {
        cajero.registrarPago(ventaFija, 50);
        assertFalse(ventaFija.pagoHecho, "El pago no debería haber sido registrado como realizado");
    }

    @Test
    void testRegistrarPagoSubastaCorrecta() {
        cajero.registrarPago(subasta, 200);
        assertTrue(subasta.pagoHecho, "El pago debería haber sido registrado como realizado");
    }

    @Test
    void testRegistrarPagoSubastaInsuficiente() {
        cajero.registrarPago(subasta, 100);
        assertFalse(subasta.pagoHecho, "El pago no debería haber sido registrado como realizado");
    }
}

package test;

import galeria.usuarios.Cajero;
import galeria.ventas.VentaFija;
import galeria.ventas.Subasta;
import galeria.inventario.Pieza;
import galeria.inventario.Pintura;
import galeria.Exceptions.PiezaDuplicadaException;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CajeroTest {
    private Cajero cajero;
    private VentaFija ventaFija;
    private Subasta subasta;
    private Pieza pieza;
    private Inventario inventario;

    @BeforeEach
    void setUp() throws PiezaDuplicadaException {
        cajero = new Cajero("userLogin", "userPass", "User Name", 1234);
        pieza = new Pintura("Obra de Arte", 2020, "Madrid", new ArrayList<>(), true, true, 0, 0, "x", "y");
        inventario = new Inventario();
        inventario.agregarPieza(pieza);

        ventaFija = new VentaFija(100, new Comprador(false, null, 0, 0, null, null));
        ventaFija.pieza = pieza;

        subasta = new Subasta(100, 150, 200, pieza);
        subasta.setPieza(pieza);
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

package galeria.inventario;

import java.util.ArrayList;

import galeria.Exceptions.PiezaDuplicadaException;
import galeria.Exceptions.PiezaInexistenteException;

public class Inventario {
    private ArrayList<Pieza> piezasDisponibles = new ArrayList<Pieza>();
    private ArrayList<Pieza> piezasBloqueadas = new ArrayList<Pieza>();
    private ArrayList<Pieza> piezasExhibidas = new ArrayList<Pieza>();

    public Inventario() {
    }

    public ArrayList<Pieza> getPiezasDisponibles() {
        return piezasDisponibles;
    }

    public ArrayList<Pieza> getPiezasBloqueadas() {
        return piezasBloqueadas;
    }

    public ArrayList<Pieza> getPiezasExhibidas() {
        return piezasExhibidas;
    }

    public void agregarPieza(Pieza pieza) throws PiezaDuplicadaException {
        if (contienePieza(pieza)) {
            throw new PiezaDuplicadaException("La pieza " + pieza.getTitulo() + " ya existe en el inventario.");
        }
        this.piezasDisponibles.add(pieza);
    }

    public boolean contienePieza(Pieza pieza) {
        return piezasDisponibles.contains(pieza) || piezasBloqueadas.contains(pieza) || piezasExhibidas.contains(pieza);
    }

    public void bloquearPieza(String titulo) {
        Pieza laPieza = buscarPiezaPorTitulo(titulo, piezasDisponibles);
        if (laPieza != null) {
            piezasDisponibles.remove(laPieza);
            piezasBloqueadas.add(laPieza);
        }
    }

    public void sacarDelInventario(String titulo) {
        Pieza laPieza = buscarPiezaPorTitulo(titulo, piezasDisponibles);
        if (laPieza != null) {
            piezasDisponibles.remove(laPieza);
        }
        laPieza = buscarPiezaPorTitulo(titulo, piezasBloqueadas);
        if (laPieza != null) {
            piezasBloqueadas.remove(laPieza);
        }
    }

    public void exhibirPieza(String titulo) throws PiezaInexistenteException {
        Pieza laPieza = buscarPiezaPorTitulo(titulo, piezasDisponibles);
        if (laPieza == null) {
            throw new PiezaInexistenteException("No existe la pieza " + titulo);
        }
        piezasExhibidas.add(laPieza);
    }

    public void guardarPiezaEnBodega(String titulo) throws PiezaInexistenteException {
        Pieza laPieza = buscarPiezaPorTitulo(titulo, piezasExhibidas);
        if (laPieza == null) {
            throw new PiezaInexistenteException("No existe la pieza " + titulo);
        }
        piezasExhibidas.remove(laPieza);
    }

    private Pieza buscarPiezaPorTitulo(String titulo, ArrayList<Pieza> listaPiezas) {
        for (Pieza pieza : listaPiezas) {
            if (pieza.getTitulo().equals(titulo)) {
                return pieza;
            }
        }
        return null;
    }
}

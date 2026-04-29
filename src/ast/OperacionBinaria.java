package ast;

import visitor.Visitor;

/**
 * Patron Composite: nodo interno que contiene dos sub-expresiones.
 * Suma, Resta, Multiplicacion y Division extienden esta clase.
 */
public abstract class OperacionBinaria implements Expresion {
    protected final Expresion izquierda;
    protected final Expresion derecha;

    protected OperacionBinaria(Expresion izquierda, Expresion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public Expresion getIzquierda() { return izquierda; }
    public Expresion getDerecha()   { return derecha;   }
}

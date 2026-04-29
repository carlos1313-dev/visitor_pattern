package ast;

import visitor.Visitor;

/**
 * Elemento base del patron Visitor.
 *
 * Cada nodo del arbol de expresiones implementa accept(Visitor),
 * que delega de vuelta al visitante: visitor.visit(this).
 * Ese "rebote" es el double dispatch que permite a Java seleccionar
 * el metodo correcto del Visitor en tiempo de ejecucion.
 */
public interface Expresion {
    <T> T accept(Visitor<T> visitor);
}

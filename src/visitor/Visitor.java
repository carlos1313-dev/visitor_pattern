package visitor;

import ast.*;

/**
 * Interfaz del patron Visitor.
 *
 * Declara un metodo visit() por cada tipo concreto de nodo.
 * El parametro generico <T> permite que cada Visitor devuelva
 * un tipo distinto:  EvaluarVisitor -> Double,
 *                    ImprimirPrefijoVisitor -> String,
 *                    DerivarVisitor -> Expresion.
 *
 * Regla de diseno: si se agrega un nuevo tipo de nodo (p. ej. Modulo)
 * hay que agregar visit(Modulo) aqui y en TODOS los visitors concretos.
 * Eso es el "coste" de la jerarquia estable: las operaciones crecen
 * sin tocar los nodos, pero los nodos nuevos rompen todos los visitors.
 */
public interface Visitor<T> {
    T visit(Numero numero);
    T visit(Suma suma);
    T visit(Resta resta);
    T visit(Multiplicacion mul);
    T visit(Division div);
}

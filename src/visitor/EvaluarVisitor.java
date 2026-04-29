package visitor;

import ast.*;

/**
 * Visitor 1: Evaluacion numerica.
 *
 * Recorre el arbol en post-orden (primero hijos, luego raiz)
 * y devuelve el resultado como Double.
 *
 * Ejemplo:  (3 + 4) * 2  ->  14.0
 */
public class EvaluarVisitor implements Visitor<Double> {

    @Override
    public Double visit(Numero numero) {
        if (numero.esVariable()) {
            throw new UnsupportedOperationException(
                "No se puede evaluar la variable simbolica '" +
                numero.getVariable() + "' sin asignarle un valor.");
        }
        return numero.getValor();
    }

    @Override
    public Double visit(Suma suma) {
        // accept() sobre cada hijo desencadena otro double dispatch
        double izq = suma.getIzquierda().accept(this);
        double der = suma.getDerecha().accept(this);
        return izq + der;
    }

    @Override
    public Double visit(Resta resta) {
        return resta.getIzquierda().accept(this)
             - resta.getDerecha().accept(this);
    }

    @Override
    public Double visit(Multiplicacion mul) {
        return mul.getIzquierda().accept(this)
             * mul.getDerecha().accept(this);
    }

    @Override
    public Double visit(Division div) {
        double denominador = div.getDerecha().accept(this);
        if (denominador == 0) throw new ArithmeticException("Division por cero");
        return div.getIzquierda().accept(this) / denominador;
    }
}

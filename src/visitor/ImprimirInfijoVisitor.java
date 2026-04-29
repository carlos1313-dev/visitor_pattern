package visitor;

import ast.*;

/**
 * Visitor 3: Notacion infija con parentesis completos.
 *
 *   Suma(3, 4)         ->  "(3 + 4)"
 *   Mul(Suma(3,4), 2)  ->  "((3 + 4) * 2)"
 *
 * Los parentesis en cada nodo binario garantizan que la precedencia
 * queda explicita sin necesitar reglas adicionales en el lector.
 */
public class ImprimirInfijoVisitor implements Visitor<String> {

    @Override
    public String visit(Numero numero) {
        return numero.esVariable()
            ? numero.getVariable()
            : formatear(numero.getValor());
    }

    @Override
    public String visit(Suma suma) {
        return combinar("+", suma.getIzquierda(), suma.getDerecha());
    }

    @Override
    public String visit(Resta resta) {
        return combinar("-", resta.getIzquierda(), resta.getDerecha());
    }

    @Override
    public String visit(Multiplicacion mul) {
        return combinar("*", mul.getIzquierda(), mul.getDerecha());
    }

    @Override
    public String visit(Division div) {
        return combinar("/", div.getIzquierda(), div.getDerecha());
    }

    private String combinar(String op, Expresion izq, Expresion der) {
        return "(" + izq.accept(this) + " " + op + " " + der.accept(this) + ")";
    }

    private String formatear(double v) {
        return v == Math.floor(v) && !Double.isInfinite(v)
            ? String.valueOf((long) v)
            : String.valueOf(v);
    }
}

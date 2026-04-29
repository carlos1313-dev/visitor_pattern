package visitor;

import ast.*;

/**
 * Visitor 2: Notacion polaca prefija (prefix / Polish notation).
 *
 * El operador precede a sus operandos:
 *   Suma(3, 4)         ->  "+ 3.0 4.0"
 *   Mul(Suma(3,4), 2)  ->  "* + 3.0 4.0 2.0"
 *
 * No necesita parentesis porque la aridad fija elimina la ambigüedad.
 */
public class ImprimirPrefijoVisitor implements Visitor<String> {

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
        return op + " "
            + izq.accept(this) + " "
            + der.accept(this);
    }

    private String formatear(double v) {
        // Muestra entero si no tiene decimales (3.0 -> "3", 3.5 -> "3.5")
        return v == Math.floor(v) && !Double.isInfinite(v)
            ? String.valueOf((long) v)
            : String.valueOf(v);
    }
}

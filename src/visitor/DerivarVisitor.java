package visitor;

import ast.*;
import ast.Expresiones;

/**
 * Visitor 4: Derivacion simbolica respecto a una variable.
 *
 * Implementa las reglas estandar del calculo diferencial:
 *
 *   d/dx(c)     = 0                          (constante)
 *   d/dx(x)     = 1                          (variable propia)
 *   d/dx(y)     = 0   (y ≠ x)               (otra variable)
 *   d/dx(f+g)   = f' + g'                   (suma)
 *   d/dx(f-g)   = f' - g'                   (resta)
 *   d/dx(f*g)   = f'g + fg'                 (producto)
 *   d/dx(f/g)   = (f'g - fg') / g^2          (cociente)
 *
 * Devuelve un nuevo arbol Expresion que representa la derivada.
 * No simplifica (0+x devuelve "0 + x", no "x"), pero eso se puede
 * agregar con otro Visitor llamado SimplificarVisitor.
 */
public class DerivarVisitor implements Visitor<Expresion> {

    private final String variable;

    /** @param variable  nombre de la variable a derivar, p. ej. "x" */
    public DerivarVisitor(String variable) {
        this.variable = variable;
    }

    // ── Reglas de derivacion ────────────────────────────────────────────────

    @Override
    public Expresion visit(Numero numero) {
        if (numero.esVariable()) {
            // d/dx(x) = 1, d/dx(y) = 0
            return numero.getVariable().equals(variable)
                ? Expresiones.num(1)
                : Expresiones.num(0);
        }
        // Derivada de una constante = 0
        return Expresiones.num(0);
    }

    @Override
    public Expresion visit(Suma suma) {
        // (f + g)' = f' + g'
        return Expresiones.suma(
            suma.getIzquierda().accept(this),
            suma.getDerecha().accept(this)
        );
    }

    @Override
    public Expresion visit(Resta resta) {
        // (f - g)' = f' - g'
        return Expresiones.resta(
            resta.getIzquierda().accept(this),
            resta.getDerecha().accept(this)
        );
    }

    @Override
    public Expresion visit(Multiplicacion mul) {
        // Regla del producto: (f*g)' = f'g + fg'
        Expresion f  = mul.getIzquierda();
        Expresion g  = mul.getDerecha();
        Expresion fp = f.accept(this);   // f'
        Expresion gp = g.accept(this);   // g'

        return Expresiones.suma(
            Expresiones.mul(fp, g),
            Expresiones.mul(f,  gp)
        );
    }

    @Override
    public Expresion visit(Division div) {
        // Regla del cociente: (f/g)' = (f'g - fg') / g^2
        Expresion f  = div.getIzquierda();
        Expresion g  = div.getDerecha();
        Expresion fp = f.accept(this);
        Expresion gp = g.accept(this);

        Expresion numerador   = Expresiones.resta(
            Expresiones.mul(fp, g),
            Expresiones.mul(f, gp)
        );
        Expresion denominador = Expresiones.mul(g, g);   // g^2

        return Expresiones.div(numerador, denominador);
    }
}

package ast;

import visitor.Visitor;

/**
 * Nodo hoja: un numero con nombre de variable opcional.
 *
 * Si tiene nombre de variable (p. ej. "x"), DerivarVisitor puede
 * calcular su derivada respecto a esa variable.
 */
public class Numero implements Expresion {

    private final double valor;
    private final String variable; // null si es una constante literal

    public Numero(double valor) {
        this.valor = valor;
        this.variable = null;
    }

    /** Constructor para variables simbolicas como 'x'. */
    public Numero(String variable) {
        this.valor = 0;
        this.variable = variable;
    }

    public double getValor() { return valor; }
    public String getVariable() { return variable; }
    public boolean esVariable() { return variable != null; }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        // Double dispatch: el visitante recibe un Numero concreto,
        // no un Expresion generico.
        return visitor.visit(this);
    }
}

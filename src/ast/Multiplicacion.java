package ast;

import visitor.Visitor;

public class Multiplicacion extends OperacionBinaria {
    public Multiplicacion(Expresion izquierda, Expresion derecha) {
        super(izquierda, derecha);
    }
    @Override
    public <T> T accept(Visitor<T> visitor) { return visitor.visit(this); }
}

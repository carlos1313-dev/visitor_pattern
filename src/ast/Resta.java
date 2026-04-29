package ast;

import visitor.Visitor;

public class Resta extends OperacionBinaria {
    public Resta(Expresion izquierda, Expresion derecha) {
        super(izquierda, derecha);
    }
    @Override
    public <T> T accept(Visitor<T> visitor) { return visitor.visit(this); }
}

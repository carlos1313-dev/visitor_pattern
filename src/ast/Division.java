package ast;

import visitor.Visitor;

public class Division extends OperacionBinaria {
    public Division(Expresion izquierda, Expresion derecha) {
        super(izquierda, derecha);
    }
    @Override
    public <T> T accept(Visitor<T> visitor) { return visitor.visit(this); }
}

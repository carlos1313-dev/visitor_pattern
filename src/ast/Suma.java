package ast;

import visitor.Visitor;

public class Suma extends OperacionBinaria {
    public Suma(Expresion izquierda, Expresion derecha) {
        super(izquierda, derecha);
    }
    @Override
    public <T> T accept(Visitor<T> visitor) { return visitor.visit(this); }
}

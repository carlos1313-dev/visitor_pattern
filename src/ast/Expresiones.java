package ast;

/**
 * Fabrica de nodos AST con metodos estaticos de conveniencia.
 *
 * Permite construir arboles de forma legible:
 *   Expresiones.suma(Expresiones.num(3), Expresiones.num(4))
 * en vez de:
 *   new Suma(new Numero(3), new Numero(4))
 */
public class Expresiones {

    private Expresiones() {}

    public static Numero  num(double valor)               { return new Numero(valor); }
    public static Numero  var(String nombre)              { return new Numero(nombre); }
    public static Expresion suma(Expresion i, Expresion d) { return new Suma(i, d); }
    public static Expresion resta(Expresion i, Expresion d){ return new Resta(i, d); }
    public static Expresion mul(Expresion i, Expresion d)  { return new Multiplicacion(i, d); }
    public static Expresion div(Expresion i, Expresion d)  { return new Division(i, d); }
}

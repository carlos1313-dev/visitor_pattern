import ast.*;
import visitor.*;

import static ast.Expresiones.*;

/**
 * Demostracion completa del patron Composite + Visitor.
 *
 * Crea tres arboles de expresion y los pasa a los cuatro visitors,
 * mostrando como cada uno extrae informacion diferente sin modificar
 * los nodos del arbol.
 */
public class Main {

    // ── Visitors reutilizables (sin estado mutable) ──────────────────────
    private static final EvaluarVisitor        evaluar  = new EvaluarVisitor();
    private static final ImprimirPrefijoVisitor prefijo  = new ImprimirPrefijoVisitor();
    private static final ImprimirInfijoVisitor  infijo   = new ImprimirInfijoVisitor();
    private static final DerivarVisitor         derivar  = new DerivarVisitor("x");

    public static void main(String[] args) {

        separador("EJEMPLO 1:  (3 + 4) * 2");
        // Árbol:  Multiplicacion
        //           Suma(3, 4)
        //           Numero(2)
        Expresion e1 = mul(suma(num(3), num(4)), num(2));
        demo(e1, false);

        separador("EJEMPLO 2:  (10 - 2) / (3 + 1)");
        Expresion e2 = div(resta(num(10), num(2)), suma(num(3), num(1)));
        demo(e2, false);

        separador("EJEMPLO 3:  x^2 + 2x + 1   ->   derivada simbolica respecto a x");
        // x^2 se representa como x * x (no hay nodo Potencia aun)
        Expresion xVar = var("x");
        Expresion e3 = suma(
            suma(mul(xVar, xVar), mul(num(2), xVar)),
            num(1)
        );
        demo(e3, true);
    }

    // ── Helpers ─────────────────────────────────────────────────────────────

    private static void demo(Expresion expr, boolean incluirDerivada) {
        System.out.println("  Prefijo  : " + expr.accept(prefijo));
        System.out.println("  Infijo   : " + expr.accept(infijo));

        try {
            System.out.printf("  Resultado: %.4f%n", expr.accept(evaluar));
        } catch (UnsupportedOperationException e) {
            System.out.println("  Resultado: (no evaluable - contiene variables simbolicas)");
        }

        if (incluirDerivada) {
            Expresion derivada = expr.accept(derivar);
            System.out.println();
            System.out.println("  d/dx     (prefijo) : " + derivada.accept(prefijo));
            System.out.println("  d/dx     (infijo)  : " + derivada.accept(infijo));
            System.out.println();
            System.out.println("  Nota: la derivada de x^2+2x+1 es 2x+2.");
            System.out.println("  El visitor no simplifica; agregar un");
            System.out.println("  SimplificarVisitor reduciria '0+x' a 'x'.");
        }
    }

    private static void separador(String titulo) {
        System.out.println();
        System.out.println("================================================");
        System.out.println("  " + titulo);
        System.out.println("================================================");
    }
}

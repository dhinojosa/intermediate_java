package com.xyzcorp.demos.designpatterns.composite;

/**
 * @author John Ericksen
 */
public class CompositeDemonstration {

    public static void main(String[] args) {

        // (4 + 6) * (1 / 2)
        ArithmeticExpression calculation =
                new Operand(
                        new Operand(new java.lang.Number(4), Operation.PLUS, new java.lang.Number(6)),
                        Operation.MULTIPLICATION,
                        new Operand(new java.lang.Number(1), Operation.DIVISION, new java.lang.Number(2)));

        System.out.println(calculation.toString() + " = " + calculation.calculate());
    }
}

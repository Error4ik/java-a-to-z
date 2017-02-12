package ru.job4j.calculator;

import ru.job4j.action.Cosine;
import ru.job4j.action.Sinus;
import ru.job4j.action.Tangent;
import ru.job4j.action.SquareRoot;
import ru.job4j.action.Involution;

/**
 * Extended calculator, added new functions: cosine, sinus, tangent, square root, involution.
 */
public class ExtendedCalculator extends BaseCalculator {

    /**
     * Constructor, adds action.
     */
    public ExtendedCalculator() {
        this.addAction("cos", new Cosine());
        this.addAction("sin", new Sinus());
        this.addAction("tan", new Tangent());
        this.addAction("sqrt", new SquareRoot());
        this.addAction("pow", new Involution());
    }
}

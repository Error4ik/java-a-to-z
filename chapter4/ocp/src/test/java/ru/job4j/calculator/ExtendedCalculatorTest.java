package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.Manager;
import ru.job4j.action.Cosine;
import ru.job4j.action.Sinus;
import ru.job4j.action.Tangent;
import ru.job4j.action.SquareRoot;
import ru.job4j.action.Involution;
import ru.job4j.input.Input;
import ru.job4j.input.StubInput;
import ru.job4j.view.ExtendedView;
import ru.job4j.view.IView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Extended calculator test.
 */
public class ExtendedCalculatorTest {

    /**
     * Stub value.
     */
    private final double stubValue = 0d;

    /**
     * Test Cosine class.
     * cos 45, return cosine 45.
     */
    @Test
    public void cosineTest() {
        final double inputValue = 45d;
        final double expectedValue = 0.5253219888177297;
        final Cosine cosine = new Cosine();

        final double actualValue = cosine.execute(stubValue, inputValue);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test Sinus class.
     * sin 45, return sinus 30.
     */
    @Test
    public void sinusTest() {
        final double inputValue = 30d;
        final double expectedValue = -0.9880316240928618;
        final Sinus sinus = new Sinus();

        final double actualValue = sinus.execute(stubValue, inputValue);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test Tangent class.
     * tan 45, return tangent 60.
     */
    @Test
    public void tangentTest() {
        final double inputValue = 60d;
        final double expectedValue = 0.320040389379563;
        final Tangent tangent = new Tangent();

        final double actualValue = tangent.execute(stubValue, inputValue);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test SquareRoot class.
     * sqrt 25, return square root 25.
     */
    @Test
    public void squareRootTest() {
        final double inputValue = 25d;
        final double expectedValue = 5d;
        final SquareRoot squareRoot = new SquareRoot();

        final double actualValue = squareRoot.execute(stubValue, inputValue);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test Involution class.
     * 5 pow 3, return 125.
     */
    @Test
    public void involutionTest() {
        final double inputValue = 5d;
        final double pow = 3d;
        final double expectedValue = 125d;
        final Involution involution = new Involution();

        final double actualValue = involution.execute(inputValue, pow);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Extended calculator test.
     */
    @Test
    public void extendedCalculatorTest() {
        final ExtendedCalculator calculator = new ExtendedCalculator();
        final String[] stubInputUser = {"cos 30", "10 * 5", "tan 30", "sin 45", "exit"};
        final Input input = new StubInput(stubInputUser);
        final IView view = new ExtendedView();
        final Manager manager = new Manager(calculator, input, view);
        manager.runCalculator();
    }
}
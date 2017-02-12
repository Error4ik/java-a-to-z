package ru.job4j;

import org.junit.Test;
import ru.job4j.action.Action;
import ru.job4j.action.Add;
import ru.job4j.action.Subtract;
import ru.job4j.action.Multiply;
import ru.job4j.action.Divide;
import ru.job4j.calculator.BaseCalculator;
import ru.job4j.exception.DivideByZeroException;
import ru.job4j.exception.InvalidOperationException;
import ru.job4j.input.ConsoleInput;
import ru.job4j.input.Input;
import ru.job4j.input.StubInput;
import ru.job4j.view.ConsoleView;
import ru.job4j.view.IView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test class Manager.
 */
public class ManagerTest {

    /**
     * String separator.
     */
    private final String sep = System.getProperty("line.separator");

    /**
     * Test Add class.
     * 1 + 2 return 3;
     */
    @Test
    public void whenOnePlusTwoThenReturnThree() {
        final int firstNumber = 1;
        final int secondNumber = 2;
        final double expectValue = 3d;

        final Action add = new Add();
        final double actualValue = add.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectValue));
    }

    /**
     * Test Add class.
     * 5 + 2 return 7;
     */
    @Test
    public void whenFivePlusTwoThenReturnSeven() {
        final int firstNumber = 5;
        final int secondNumber = 2;
        final double expectValue = 7d;

        final Action add = new Add();
        final double actualValue = add.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectValue));
    }

    /**
     * Test Subtract class.
     * 5 - 2 return 3;
     */
    @Test
    public void whenFiveSubtractTwoThenReturnThree() {
        final int firstNumber = 5;
        final int secondNumber = 2;
        final double expectValue = 3d;

        final Action subtract = new Subtract();
        final double actualValue = subtract.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectValue));
    }

    /**
     * Test Subtract class.
     * 2 - 5 return -3;
     */
    @Test
    public void whenTwoSubtractFiveThenReturnMinusThree() {
        final int firstNumber = 2;
        final int secondNumber = 5;
        final double expectValue = -3d;

        final Action subtract = new Subtract();
        final double actualValue = subtract.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectValue));
    }

    /**
     * Test Multiply class.
     * 3 * 2 return 6;
     */
    @Test
    public void whenThreeMultiplyTwoThenReturnSix() {
        final int firstNumber = 3;
        final int secondNumber = 2;
        final double expectedValue = 6d;

        final Action multiply = new Multiply();
        final double actualValue = multiply.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test Multiply class.
     * 5 * 2 return 10;
     */
    @Test
    public void whenFiveMultiplyTwoThenReturnTen() {
        final int firstNumber = 5;
        final int secondNumber = 2;
        final double expectedValue = 10d;

        final Action multiply = new Multiply();
        final double actualValue = multiply.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test Divide class.
     * 6 / 2 return 3;
     */
    @Test
    public void whenSixDivideTwoThenReturnThree() {
        final int firstNumber = 6;
        final int secondNumber = 2;
        final double expectedValue = 3d;

        final Action divide = new Divide();
        final double actualValue = divide.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test Divide class.
     * 3 / 3 return 1;
     */
    @Test
    public void whenThreeDivideThreeThenReturnOne() {
        final int firstNumber = 3;
        final int secondNumber = 3;
        final double expectedValue = 1d;

        final Action divide = new Divide();
        final double actualValue = divide.execute(firstNumber, secondNumber);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Test Divide class throw DivideByZeroException.
     * 6 / 0 Divide by zero exception.
     */
    @Test(expected = DivideByZeroException.class)
    public void whenDividerEqualsZeroThenThrowException() {
        final int firstNumber = 6;
        final int secondNumber = 0;

        final Action divide = new Divide();
        divide.execute(firstNumber, secondNumber);
    }


    /**
     * Test ConsoleInput class.
     * Method getInput.
     * Input string "5 + 5" return array {"5", "+", "5"}.
     */
    @Test
    public void whenInputStringThenReturnArrayString() {
        final String inputValue = "5 + 5";
        final String[] expectedValue = {"5", "+", "5"};
        System.setIn(new ByteArrayInputStream(inputValue.getBytes()));

        final Input input = new ConsoleInput();
        String[] a = input.getInput();

        assertThat(a, is(expectedValue));
    }

    /**
     * Test ConsoleView class.
     * Method showMenu.
     */
    @Test
    public void whenCallMethodShowMenuThenPrintsMenu() {
        final String expectedOut = String.format("%s%s%s%s", "Enter arithmetic operation. Eg: 5 + 5, and exit = exit the program", sep,
                "For arithmetic operations with previous results. Eg: + 10", sep);
        final IView view = new ConsoleView();

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        view.showMenu();

        assertThat(out.toString(), is(expectedOut));
    }

    /**
     * Test ConsoleView class.
     * Method showResult.
     */
    @Test
    public void whenTheNumberIsNotFractionalThenReturnInteger() {
        final double inputValue = 5.0;
        final String expectValue = String.format("%sResult: %s%s", sep, (long) inputValue, sep);

        final IView view = new ConsoleView();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        view.showResult(inputValue);

        assertThat(out.toString(), is(expectValue));
    }

    /**
     * Test ConsoleView class.
     * Method showResult.
     */
    @Test
    public void whenTheNumberFractionalThenReturnFractional() {
        final double inputValue = 5.5;
        final String expectValue = String.format("%sResult: %.2f%s", sep, inputValue, sep);

        final IView view = new ConsoleView();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        view.showResult(inputValue);

        assertThat(out.toString(), is(expectValue));
    }

    /**
     * Test CheckInput class.
     * Method checkInputValue return true.
     * if input value array is three element.
     */
    @Test
    public void whenInputValueArrayIsThreeElementThenReturnTrue() {
        final String[] inputValue = {"5", "+", "5"};
        final CheckInput checkInput = new CheckInput();

        assertTrue(checkInput.checkInputValue(inputValue));
    }

    /**
     * Test CheckInput class.
     * Method checkInputValue return false.
     */
    @Test
    public void whenValueEqualsExitThenReturnFals() {
        final String[] inputValue = {"exit"};
        final CheckInput checkInput = new CheckInput();

        assertFalse(checkInput.checkInputValue(inputValue));
    }

    /**
     * Test CheckInput class.
     * Method checkInputValue return true.
     * if input value array is two element.
     */
    @Test()
    public void whenInputValueArrayIsTwoElementThenReturnTrue() {
        final String[] inputValue = {"+", "5"};
        final CheckInput checkInput = new CheckInput();

        assertTrue(checkInput.checkInputValue(inputValue));
    }

    /**
     * Test CheckInput class.
     * Method checkInputValue throw NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void whenInputValueIncorrectThenThrowException() {
        final String[] inputValue = {"you", "+", "5"};
        final CheckInput checkInput = new CheckInput();

        checkInput.checkInputValue(inputValue);
    }

    /**
     * Test CheckInput class.
     * Method setResult and getResult;
     */
    @Test
    public void setResultTest() {
        final double inputValue = 3d;
        final CheckInput checkInput = new CheckInput();

        checkInput.setResult(inputValue);
        final double actualValue = checkInput.getResult();

        assertThat(actualValue, is(inputValue));
    }

    /**
     * Test CheckInput class.
     * All get method - getFirstNumber, getSecondNumber, getTypeOperation.
     */
    @Test
    public void allGetterTest() {
        final CheckInput checkInput = new CheckInput();
        final double expectedValue = 0d;

        assertThat(checkInput.getFirstNumber(), is(expectedValue));
        assertThat(checkInput.getSecondNumber(), is(expectedValue));
        assertNull(checkInput.getTypeOperation());
    }

    /**
     * Test Manager class.
     * Method runCalculator.
     */
    @Test
    public void runCalculatorTest() {
        final String[] inputValue = {"5 + 5", "exit"};
        final BaseCalculator calculator = new BaseCalculator();
        final Input input = new StubInput(inputValue);
        final IView view = new ConsoleView();
        final Manager manager = new Manager(calculator, input, view);

        System.setIn(new ByteArrayInputStream("5 - 2".getBytes()));
        manager.runCalculator();
    }

    /**
     * Test BaseCalculator class.
     * Method runAction.
     *
     * @throws InvalidOperationException invalid key.
     */
    @Test(expected = InvalidOperationException.class)
    public void whenInvalidKeyThenThrowException() throws InvalidOperationException {
        final BaseCalculator calculator = new BaseCalculator();
        calculator.runAction("5", 0d, 0d);
    }
}
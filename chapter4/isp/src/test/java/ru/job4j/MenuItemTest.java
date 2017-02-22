package ru.job4j;

import org.junit.Test;
import ru.job4j.action.Action;
import ru.job4j.action.PrintHelloAction;
import ru.job4j.view.ConsoleOutput;
import ru.job4j.view.Output;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test MenuItemTest.
 */
public class MenuItemTest {

    /**
     * Separator.
     */
    private final String sep = System.getProperty("line.separator");

    /**
     * MenuItem class.
     * Method addChild.
     */
    @Test
    public void whenAddedChildThenChildListIncreased() {
        final MenuItem menu = new MenuItem("Main", 1);
        final int expectedValue = 1;

        menu.addChild(new MenuItem("SubMenu", 2));

        assertThat(menu.getChildItem().size(), is(expectedValue));
    }

    /**
     * MenuItem class.
     * Method removeChild.
     */
    @Test
    public void whenRemoveChildThenChildListDecreases() {
        final MenuItem menu = new MenuItem("Main", 1);
        final int expectedValue = 1;
        final MenuItem subMenu1 = new MenuItem("SubMenu 1", 2);
        final MenuItem subMenu2 = new MenuItem("SubMenu 2", 2);

        menu.addChild(subMenu1);
        menu.addChild(subMenu2);

        menu.removeChild(subMenu1);

        assertThat(menu.getChildItem().size(), is(expectedValue));
    }

    /**
     * MenuItem class.
     * Method getId.
     */
    @Test
    public void whenCallGetIdThenReturnValidValue() {
        final MenuItem item = new MenuItem("Main", 5);
        final int expectedValue = 5;

        assertThat(item.getId(), is(expectedValue));
    }

    /**
     * MenuItem class.
     * Method setName and getName.
     */
    @Test
    public void whenSetNameAndCallGetNameThenReturnValidName() {
        final MenuItem item = new MenuItem("Main menu", 1);
        final String expectedValue = "Other menu";

        item.setName("Other menu");
        final String actualValue = item.getName();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * MenuItem class.
     * Method setAction and getAction.
     */
    @Test
    public void whenSetActionAndCallGetActionThenReturnValidAction() {
        final Action inputValue = new PrintHelloAction();
        final MenuItem item = new MenuItem("Main", 1);

        item.setAction(inputValue);

        assertThat(item.getAction(), is(inputValue));
    }

    /**
     * MenuItem class.
     * Method toString.
     */
    @Test
    public void whenCallToStringThenReturnValidString() {
        final MenuItem item = new MenuItem("Main", 10);
        final String expectedValue = "MenuItem: id = 10, name = Main";

        assertThat(item.toString(), is(expectedValue));
    }

    /**
     * Action class.
     * Method execute.
     */
    @Test
    public void whenCallExecuteThenPrintHello() {
        final MenuItem item = new MenuItem("Main", 1);
        final String expectedValue = "Hello!" + sep;
        final Action action = new PrintHelloAction();
        item.setAction(action);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        item.getAction().execute();

        assertThat(out.toString(), is(expectedValue));
    }

    /**
     * MenuItem class.
     * Method showChild.
     */
    @Test
    public void whenCallShowChildThenReturnStringAllChild() {
        final Output output = new ConsoleOutput();
        final MenuItem main = new MenuItem("Main", 1);
        final MenuItem subMenu1 = new MenuItem("SubMenu 1", 2);
        final MenuItem subMenu2 = new MenuItem("SubMenu 2", 3);
        final MenuItem subMenuChild1 = new MenuItem("Child 1", 4);
        final MenuItem subMenuChild2 = new MenuItem("Child 2", 5);
        subMenu1.addChild(subMenuChild1);
        subMenu1.addChild(subMenuChild2);
        subMenu2.addChild(subMenuChild1);
        subMenu2.addChild(subMenuChild2);
        main.addChild(subMenu1);
        main.addChild(subMenu2);
        StringBuilder sb = new StringBuilder();
        sb.append("1 -:-Main").append(sep)
                .append("2 -:-:-SubMenu 1").append(sep)
                .append("4 -:-:-:-Child 1").append(sep)
                .append("5 -:-:-:-Child 2").append(sep)
                .append("3 -:-:-SubMenu 2").append(sep)
                .append("4 -:-:-:-Child 1").append(sep)
                .append("5 -:-:-:-Child 2").append(sep)
                .append(sep);


        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        output.print(main.showChild("-"));

        assertThat(out.toString(), is(sb.toString()));
    }
}
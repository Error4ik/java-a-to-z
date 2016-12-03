package avoronin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculateTest {
    
    @Test
    public void whenSetWorldInShowTextThenReturnInThreeWorld() {
        Calculate calc = new Calculate();
        String actualText = calc.showText("World");
        assertThat(actualText, is("World World World"));
    }

    @Test
    public void whenSetNullShowTextThenReturnTwoSpaces() {
        Calculate calc = new Calculate();
        String actualText = calc.showText("");
        assertThat(actualText, is("  "));
    }
}
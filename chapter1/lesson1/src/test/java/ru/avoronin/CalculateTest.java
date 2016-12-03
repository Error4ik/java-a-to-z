package avoronin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test.
 *
 * @author Alexey Voronin
 * @version $Id$
 * @since 0.1
 */

public class CalculateTest {
    /**
     * Test add.
     */
    @Test
    public void whenSetWorldInShowTextThenReturnInThreeWorld() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculate.main(null);
        assertThat(out.toString(), is("Hello World.\r\n"));
    }
}
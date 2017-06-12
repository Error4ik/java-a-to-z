package ru.job4j.menu_tracker;

import org.junit.Test;
import ru.job4j.action.AddComment;
import ru.job4j.action.StubAction;
import ru.job4j.dao.Tracker;
import ru.job4j.input.ConsoleInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Menu menu_tracker test.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class MenuTrackerTest {

    /**
     * fillAction.
     */
    @Test
    public void whenFillActionThenCountActionsEqualTen() {
        final MenuTracker tracker =
                new MenuTracker(new Tracker(null), new ConsoleInput());
        final int expectedValue = 10;
        tracker.fillAction();

        final int actualValue = tracker.getActions().size();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * addAction.
     */
    @Test
    public void whenAddActionThenActionAddedToArrayAndReturnTrue() {
        final MenuTracker tracker =
                new MenuTracker(new Tracker(null), new ConsoleInput());
        final int expectedValue = 1;

        final boolean expected = tracker.addAction(new AddComment("1", "add"));
        final int actualValue = tracker.getActions().size();

        assertTrue(expected);
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * showMenu.
     */
    @Test
    public void whenShowMenuThenDisplayMenuToTracker() {
        final StringBuilder sb = new StringBuilder();
        final String sep = System.getProperty("line.separator");
        final MenuTracker tracker =
                new MenuTracker(new Tracker(null), new ConsoleInput());
        tracker.fillAction();
        sb.append("===========================================================").append(sep)
                .append("1. Add task").append(sep)
                .append("2. Remove Task").append(sep)
                .append("3. Edit Task").append(sep)
                .append("4. Add Comment").append(sep)
                .append("5. Remove Comment").append(sep)
                .append("6. Show All Task").append(sep)
                .append("7. Show All Comment Task").append(sep)
                .append("8. Filtered Task By Name").append(sep)
                .append("9. Filter by coincidence").append(sep)
                .append("0. Exit Tracker").append(sep)
                .append("===========================================================").append(sep);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tracker.showMenu();

        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * select.
     */
    @Test
    public void whenSelectThenExecuteOnSelectAction() {
        final StringBuilder sb = new StringBuilder();
        final String sep = System.getProperty("line.separator");
        final MenuTracker tracker =
                new MenuTracker(new Tracker(null), new ConsoleInput());
        tracker.addAction(new StubAction("1", "stub"));
        sb.append("===========================================================").append(sep)
                .append("WORK!").append(sep)
                .append("===========================================================").append(sep);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tracker.select(1);

        assertThat(out.toString(), is(sb.toString()));
    }
}

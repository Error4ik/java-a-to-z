package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.update_tracker.action.AddNewTask;
import ru.job4j.update_tracker.action.AddComment;
import ru.job4j.update_tracker.action.EditTask;
import ru.job4j.update_tracker.action.ExitTrackerProgram;
import ru.job4j.update_tracker.action.FilterByCoincidence;
import ru.job4j.update_tracker.action.FilterTaskByName;
import ru.job4j.update_tracker.action.RemoveTask;
import ru.job4j.update_tracker.action.RemoveComment;
import ru.job4j.update_tracker.action.ShowAllCommentToTask;
import ru.job4j.update_tracker.action.ShowAllTask;
import ru.job4j.update_tracker.input.ConsoleInputData;
import ru.job4j.update_tracker.input.StubInput;
import ru.job4j.update_tracker.models.Comment;
import ru.job4j.update_tracker.models.Task;
import ru.job4j.update_tracker.settings.Settings;
import ru.job4j.update_tracker.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tracker test.
 *
 * @author Alexey Voronin.
 * @since 02.06.2017.
 */
public class TrackerTest {

    /**
     * Settings.
     */
    private final Settings settings = new Settings();

    /**
     * Url to connect tracker.
     */
    private final String trackerUrl = settings.getValue("trackerUrl");

    /**
     * User name to connect.
     */
    private final String userName = settings.getValue("userName");

    /**
     * Password to connect.
     */
    private final String password = settings.getValue("password");

    /**
     * Tracker.
     */
    private Tracker tracker;

    /**
     * Init tracker and createDb class.
     */
    @Before
    public void init() {
        this.tracker = new Tracker(trackerUrl, userName, password);
        tracker.createTable();
    }

    /**
     * Drop table and DB after test.
     */
    @After
    public void dropTables() {
        this.tracker.dropTable("comments");
        this.tracker.dropTable("task");
    }

    /**
     * Add task to DB.
     */
    @Test
    public void whenAddNewTaskThenAddsTaskToDB() {
        final AddNewTask task = new AddNewTask("1", "add");
        task.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        String expectedValue = "Repair";

        String actualValue = tracker.getTask(1).getName();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Remove task and comments to task.
     */
    @Test
    public void whenTheTaskIsDeletedThenTheCommentsToTaskAreDeleted() {
        final AddNewTask task = new AddNewTask("1", "add");
        task.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddComment comment = new AddComment("1", "add");
        comment.execute(this.tracker, new StubInput(new String[]{"1", "One to task!"}));
        final AddComment comment2 = new AddComment("1", "add");
        comment.execute(this.tracker, new StubInput(new String[]{"1", "Two to task!"}));
        final RemoveTask removeTask = new RemoveTask("1", "remove");
        removeTask.execute(this.tracker, new StubInput(new String[]{"1"}));

        final Task actualTask = tracker.getTask(1);
        final Comment oneComment = tracker.getComment(1);
        final Comment twoComment = tracker.getComment(2);

        assertNull(actualTask);
        assertNull(oneComment);
        assertNull(twoComment);
    }

    /**
     * Add comment.
     */
    @Test
    public void whenAddCommentThenAddsCommentToDB() {
        final AddNewTask task = new AddNewTask("1", "add");
        task.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddComment comment = new AddComment("1", "add");
        comment.execute(this.tracker, new StubInput(new String[]{"1", "Comment to task!"}));
        String expectedValue = "Comment to task!";

        String actualValue = tracker.getComment(1).getComment();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Remove comment.
     */
    @Test
    public void whenRemoveCommentShouldReturnNull() {
        final AddNewTask task = new AddNewTask("1", "add");
        task.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddComment comment = new AddComment("1", "add");
        comment.execute(this.tracker, new StubInput(new String[]{"1", "Comment to task!"}));
        final RemoveComment removeComment = new RemoveComment("1", "remove");
        removeComment.execute(tracker, new StubInput(new String[]{"1"}));

        final Comment actualValue = tracker.getComment(1);

        assertNull(actualValue);
    }

    /**
     * Edit task.
     */
    @Test
    public void whenEditTaskShouldTaskChangeNameAndDescription() {
        final AddNewTask task = new AddNewTask("1", "add");
        task.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final EditTask editTask = new EditTask("1", "edit");
        editTask.execute(tracker, new StubInput(new String[]{"1", "Update", "Update PC"}));
        final String expectedName = "Update";
        final String expectedDescription = "Update PC";

        final String actualName = tracker.getTask(1).getName();
        final String actualDescription = tracker.getTask(1).getDescription();

        assertThat(actualName, is(expectedName));
        assertThat(actualDescription, is(expectedDescription));
    }

    /**
     * Show all task.
     */
    @Test
    public void whenShowAllTaskThenDisplayAllTaskToDB() {
        final String sep = System.getProperty("line.separator");
        final StringBuilder sb = new StringBuilder();
        final AddNewTask one = new AddNewTask("1", "add");
        one.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddNewTask two = new AddNewTask("1", "add");
        two.execute(this.tracker, new StubInput(new String[]{"Update", "Update to PO"}));
        final ShowAllTask showAllTask = new ShowAllTask("1", "show");
        sb.append("===========================================================").append(sep)
                .append("1, Repair, Repair to TV ")
                .append(new Timestamp(this.tracker.getTask(1).getCreateDate().getTime())).append(sep)
                .append("2, Update, Update to PO ")
                .append(new Timestamp(this.tracker.getTask(2).getCreateDate().getTime())).append(sep)
                .append("===========================================================").append(sep);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        showAllTask.execute(this.tracker, new ConsoleInputData());

        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * Show all comment to task.
     */
    @Test
    public void whenShowAllCommentToTaskThenDisplayAllCommentToTask() {
        final String sep = System.getProperty("line.separator");
        final StringBuilder sb = new StringBuilder();
        final AddNewTask one = new AddNewTask("1", "add");
        one.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddComment first = new AddComment("1", "add");
        first.execute(this.tracker, new StubInput(new String[]{"1", "One to task!"}));
        final AddComment second = new AddComment("1", "add");
        second.execute(this.tracker, new StubInput(new String[]{"1", "Two to task!"}));
        final ShowAllCommentToTask commentToTask = new ShowAllCommentToTask("1", "show");

        sb.append("===========================================================").append(sep)
                .append("1, ").append(new Timestamp(this.tracker.getComment(1).getCreateDate().getTime()))
                .append(", One to task!").append(sep)
                .append("2, ").append(new Timestamp(this.tracker.getComment(2).getCreateDate().getTime()))
                .append(", Two to task!").append(sep)
                .append("===========================================================").append(sep);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        commentToTask.execute(this.tracker, new StubInput(new String[]{"1"}));

        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * Filter task by name.
     */
    @Test
    public void whenFilterTaskByNameThenDisplayFilteredTask() {
        final String sep = System.getProperty("line.separator");
        final StringBuilder sb = new StringBuilder();
        final AddNewTask one = new AddNewTask("1", "add");
        one.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddNewTask two = new AddNewTask("1", "add");
        two.execute(this.tracker, new StubInput(new String[]{"Update", "Update to Soft"}));
        final AddNewTask three = new AddNewTask("1", "add");
        three.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to PC"}));
        final FilterTaskByName filterName = new FilterTaskByName("1", "filter");

        sb.append("===========================================================").append(sep)
                .append("1, Repair, Repair to TV ")
                .append(new Timestamp(this.tracker.getTask(1).getCreateDate().getTime())).append(sep)
                .append("3, Repair, Repair to PC ")
                .append(new Timestamp(this.tracker.getTask(3).getCreateDate().getTime())).append(sep)
                .append("===========================================================").append(sep);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        filterName.execute(this.tracker, new StubInput(new String[]{"Repair"}));

        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * Filter task by name.
     */
    @Test
    public void whenFilterTaskByCoincidenceThenDisplayFilteredTask() {
        final String sep = System.getProperty("line.separator");
        final StringBuilder sb = new StringBuilder();
        final AddNewTask one = new AddNewTask("1", "add");
        one.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddNewTask two = new AddNewTask("1", "add");
        two.execute(this.tracker, new StubInput(new String[]{"Update", "Update to Soft"}));
        final AddNewTask three = new AddNewTask("1", "add");
        three.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to PC"}));
        final FilterByCoincidence coincidence = new FilterByCoincidence("1", "coincidence");

        sb.append("===========================================================").append(sep)
                .append("1, Repair, Repair to TV ")
                .append(new Timestamp(this.tracker.getTask(1).getCreateDate().getTime())).append(sep)
                .append("3, Repair, Repair to PC ")
                .append(new Timestamp(this.tracker.getTask(3).getCreateDate().getTime())).append(sep)
                .append("===========================================================").append(sep);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        coincidence.execute(this.tracker, new StubInput(new String[]{"Rep"}));

        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * getAllTask.
     */
    @Test
    public void whenGetAllTaskThenReturnAllTask() {
        final AddNewTask one = new AddNewTask("1", "add");
        one.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddNewTask two = new AddNewTask("1", "add");
        two.execute(this.tracker, new StubInput(new String[]{"Update", "Update to Soft"}));
        final AddNewTask three = new AddNewTask("1", "add");
        three.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to PC"}));

        final Task task1 = new Task(1, "Repair", "Repair to TV", this.tracker.getTask(1).getCreateDate().getTime());
        final Task task2 = new Task(2, "Update", "Update to Soft", this.tracker.getTask(1).getCreateDate().getTime());
        final Task task3 = new Task(3, "Repair", "Repair to PC", this.tracker.getTask(1).getCreateDate().getTime());

        final List<Task> actualList = tracker.getAllTask();

        assertThat(actualList.get(0).getName(), is(task1.getName()));
        assertThat(actualList.get(1).getName(), is(task2.getName()));
        assertThat(actualList.get(2).getName(), is(task3.getName()));
    }

    /**
     * getAllCommentsToTask.
     */
    @Test
    public void whenGetAllCommentToTaskThenReturnAllCommentToTask() {
        final AddNewTask one = new AddNewTask("1", "add");
        one.execute(this.tracker, new StubInput(new String[]{"Repair", "Repair to TV"}));
        final AddComment first = new AddComment("1", "add");
        first.execute(this.tracker, new StubInput(new String[]{"1", "One to comment!"}));
        final AddComment second = new AddComment("1", "add");
        second.execute(this.tracker, new StubInput(new String[]{"1", "Two to comment!"}));

        final Comment comment1 = new Comment(1, 1, tracker.getComment(1).getCreateDate().getTime(), "One to comment!");
        final Comment comment2 = new Comment(2, 1, tracker.getComment(2).getCreateDate().getTime(), "Two to comment!");

        List<Comment> comments = tracker.getAllCommentToTask(1);

        assertThat(comments.get(0).getComment(), is(comment1.getComment()));
        assertThat(comments.get(1).getComment(), is(comment2.getComment()));

    }

    /**
     * Exit program message.
     */
    @Test
    public void whenExitProgramThenDisplayMessageExit() {
        final String sep = System.getProperty("line.separator");
        final ExitTrackerProgram exit = new ExitTrackerProgram("1", "exit");
        final StringBuilder sb = new StringBuilder();
        sb.append("===========================================================").append(sep)
                .append("Exit program").append(sep)
                .append("===========================================================").append(sep);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        exit.execute(this.tracker, new ConsoleInputData());

        assertThat(out.toString(), is(sb.toString()));
    }
}
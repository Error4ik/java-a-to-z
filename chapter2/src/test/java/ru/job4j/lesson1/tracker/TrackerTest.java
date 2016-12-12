package ru.job4j.lesson1.tracker;

import org.junit.Test;
import ru.job4j.lesson1.models.Comment;
import ru.job4j.lesson1.models.Task;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Tracker.
 */
public class TrackerTest {

    /**
     * Проверяет добавление задачи в трекер.
     */
    @Test
    public void addTaskTest() {
        final Task task = new Task(null, null);
        final Task[] expectedArray = new Task[1];
        expectedArray[0] = task;

        final Tracker tracker = new Tracker();
        tracker.addTask(task);

        assertThat(tracker.getAllTasks(), is(expectedArray));
    }

    /**
     * Тест удаления задачи из трекера.
     */
    @Test
    public void removeTest() {
        final Task task = new Task(null, null);
        final Tracker tracker = new Tracker();
        tracker.addTask(task);
        final boolean result = true;
        assertThat(tracker.removeTask(task), is(result));
    }

    /**
     * Тест для добавления комментария к задаче.
     */
    @Test
    public void addCommentTaskTest() {
        final Comment expectedComment = new Comment("555");
        final Tracker tracker = new Tracker();
        final Task actualTask = new Task(null, null);
        tracker.addTask(actualTask);
        tracker.addCommentTask(actualTask, expectedComment);
        final Comment[] comments = actualTask.getAllComments();
        final Comment comment = comments[0];
        assertThat(comment, is(expectedComment));
    }

    /**
     * Тест редактирования задачи.
     */
    @Test
    public void editTaskTest() {
        final Tracker tracker = new Tracker();
        final Task task = new Task("Garry", "World");
        tracker.addTask(task);
        task.setName("Alex");
        task.setDescription("Hello");
        assertThat(tracker.editTask(task), is(true));
    }

    /**
     * Тест фильтрует задачи по имени.
     */
    @Test
    public void filteredTaskToNameTest() {
        Tracker tracker = new Tracker();
        final Task task1 = new Task("Garry", "Bug Fix");
        final Task task2 = new Task("Max", "Bug Fix");
        final Task task3 = new Task("Alex", "Bug Fix");
        final Task task4 = new Task("Garry", "Bug Fix");
        final Task task5 = new Task("Garry", "Bug Fix");
        final int count = 3;
        final Task[] expectedArrayTask = new Task[count];
        expectedArrayTask[0] = task1;
        expectedArrayTask[1] = task4;
        expectedArrayTask[2] = task5;
        tracker.addTask(task1);
        tracker.addTask(task2);
        tracker.addTask(task3);
        tracker.addTask(task4);
        tracker.addTask(task5);

        final String filteredName = "Garry";
        assertThat(tracker.filteredTaskToName(filteredName), is(expectedArrayTask));
    }

    /**
     * Тест для метода getAllTasks, Должен возвращать массив заявок.
     */
    @Test
    public void getAllTasksTest() {
        final Task task1 = new Task(null, null);
        final Task task2 = new Task(null, null);
        final Task task3 = new Task(null, null);
        final int count = 3;
        final Task[] expectedArray = new Task[count];
        expectedArray[0] = task1;
        expectedArray[1] = task2;
        expectedArray[2] = task3;

        final Tracker tracker = new Tracker();
        tracker.addTask(task1);
        tracker.addTask(task2);
        tracker.addTask(task3);

        assertThat(tracker.getAllTasks(), is(expectedArray));
    }

    /**
     * Тест проверяет содержится ли задача в трекере.
     */
    @Test
    public void containsTest() {
        final int id = 10;
        final boolean result = true;
        Tracker tracker = new Tracker();
        Task task1 = new Task("Garry", "Bug Fix");

        tracker.addTask(new Task(null, null));
        tracker.addTask(task1);
        tracker.addTask(new Task(null, null));

        assertThat(tracker.contains(task1), is(result));
    }

    /**
     * Возвращает количество задач в трекере.
     */
    @Test
    public void getCountTaskTest() {
        Tracker tracker = new Tracker();
        tracker.addTask(new Task(null, null));
        tracker.addTask(new Task(null, null));
        tracker.addTask(new Task(null, null));
        tracker.addTask(new Task(null, null));
        tracker.addTask(new Task(null, null));
        final int expectedCount = 5;

        assertThat(tracker.getCountTask(), is(expectedCount));
    }

    /**
     * toString тест.
     */
    @Test
    public void toStringTest() {
        final Tracker tracker = new Tracker();
        final String expected = "Count Task: " + tracker.getCountTask();

        assertThat(tracker.toString(), is(expected));
    }

}
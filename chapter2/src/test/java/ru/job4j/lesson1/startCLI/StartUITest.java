package ru.job4j.lesson1.startCLI;

import org.junit.Test;
import ru.job4j.lesson1.action.Action;
import ru.job4j.lesson1.exception.MenuOutException;
import ru.job4j.lesson1.input.ConsoleInputData;
import ru.job4j.lesson1.input.Input;
import ru.job4j.lesson1.input.StubInput;
import ru.job4j.lesson1.models.Comment;
import ru.job4j.lesson1.models.Task;
import ru.job4j.lesson1.tracker.MenuTracker;
import ru.job4j.lesson1.tracker.Tracker;

import java.io.ByteArrayInputStream;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class StartUITest {

    /**
     * Переменная для обращения к первому элемента массива в тестаъ.
     */
    private final int arrayIndex = 0;

    /**
     * Добавление новой задачи в трекер.
     * Выбирается 1 пункт меню и передается имя и описание задачи.
     */
    @Test
    public void addTaskToTrackerTest() {
        final Tracker tracker = new Tracker();
        final Input input = new StubInput(new String[]{"1", "Name", "Desc"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));

        assertNotNull(tracker.getAllTasks()[arrayIndex]);
    }

    /**
     * Добавление новой задачи в трекер.
     * Не добавляет если не указать навания задачи.
     */
    @Test
    public void addTaskToWithoutNameTrackerTest() {
        final Tracker tracker = new Tracker();
        final Input input = new StubInput(new String[]{"1", "", "Desc"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        final int expectedCount = 0;
        assertThat(tracker.getAllTasks().length, is(expectedCount));
    }

    /**
     * Тестирует удаление задачи из трекера.
     * Создаю 2 задачи и добавляю в трекер, потом выбирается пункт меню удалить, и передается id задачи.
     */
    @Test
    public void removeTaskToTrackerTest() {
        final Tracker tracker = new Tracker();
        final Task task = new Task("name", "desc");
        tracker.addTask(task);
        tracker.addTask(new Task("name", "desc"));
        final Input input = new StubInput(new String[]{"2", String.valueOf(task.getId())});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        final int expectedCountTask = 1;
        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        assertThat(tracker.getCountTask(), is(expectedCountTask));
    }

    /**
     * Тест редактирования задачи. Создается задача после выбирается пункт меню редактировать, передаю id задачи
     * и новое имя и описание и сравниват что изменения произошли.
     */
    @Test
    public void editTaskToTrackerTest() {
        final Tracker tracker = new Tracker();
        tracker.addTask(new Task("name", "desc"));
        final Input input = new StubInput(new String[]{"3", String.valueOf(tracker.getAllTasks()[arrayIndex].getId()),
                "NewName", "NewDesc"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        assertThat(tracker.getAllTasks()[arrayIndex].getName(), is("NewName"));
        assertThat(tracker.getAllTasks()[arrayIndex].getDescription(), is("NewDesc"));
    }

    /**
     * Тест редактирования задачи. Редактирование не происходит если не ввести новое имя задачи.
     * Создается задача после выбирается пункт меню редактировать, передаю id задачи
     * и новое имя и описание и сравниват что изменения произошли.
     */
    @Test
    public void editTaskToTrackerTestWithoutName() {
        final Tracker tracker = new Tracker();
        tracker.addTask(new Task("name", "desc"));
        final Input input = new StubInput(new String[]{"3", String.valueOf(tracker.getAllTasks()[arrayIndex].getId()),
                "", "NewDesc"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        assertThat(tracker.getAllTasks()[arrayIndex].getName(), is("name"));
        assertThat(tracker.getAllTasks()[arrayIndex].getDescription(), is("desc"));
    }

    /**
     * Тест добавления комментария к задаче. Создается задача, после выбирается пункт меню добавить комментарий,
     * передается id задачи к которой нужно добавить комментарий, и сам комментарий. И сравниват коммент с тем который добавил.
     */
    @Test
    public void addCommentToTask() {
        final Tracker tracker = new Tracker();
        tracker.addTask(new Task("name", "desc"));
        final Input input = new StubInput(new String[]{"4", String.valueOf(tracker.getAllTasks()[arrayIndex].getId()),
                "New comment"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        assertThat(tracker.getAllTasks()[arrayIndex].getAllComments()[arrayIndex].getComment(), is("New comment"));
    }

    /**
     * Тест удаляет комментарий из задачи. Создает задачу, добавлят в неё коммент, выбирает пункт меню удалить комментарий
     * получает id задачи у которой нужно удалить коммент, получает id комментария который нужно удалить и сравнивает
     * что у задачи нет комментариев.
     */
    @Test
    public void removeCommentToTask() {
        final Tracker tracker = new Tracker();
        final Task task = new Task("name", "desc");
        task.addComment(new Comment("Comment"));
        tracker.addTask(task);
        final Input input = new StubInput(new String[]{"5", String.valueOf(task.getId()),
                String.valueOf(task.getAllComments()[arrayIndex].getId())});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        final int expectedCountComment = 0;
        assertThat(task.getCountComment(), is(expectedCountComment));
    }

    /**
     * Проверят что метод возвращает все пункты меню.
     * Сравнивает длинну полученного массива с колличеством действий в трекере.
     */
    @Test
    public void getAllAction() {
        final Tracker tracker = new Tracker();
        final Input input = new StubInput(new String[]{""});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        final Action[] actions = menuTracker.getAllAction();
        assertThat(menuTracker.getCountAction(), is(actions.length));
    }

    /**
     * Тест вывода всех задач на экран.
     */
    @Test
    public void showAllTaskTest() {
        final Tracker tracker = new Tracker();
        tracker.addTask(new Task("name", "desc"));
        tracker.addTask(new Task("name", "desc"));
        tracker.addTask(new Task("name", "desc"));
        tracker.addTask(new Task("name", "desc"));
        final Input input = new StubInput(new String[]{"6"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        final int expectedCount = 4;
        assertThat(tracker.getAllTasks().length, is(expectedCount));
    }

    /**
     * Тест вывода всех комментариев у задачи на экран.
     */
    @Test
    public void showAllCommentToTaskTest() {
        final Tracker tracker = new Tracker();
        final Task task = new Task("name", "desc");
        task.addComment(new Comment());
        task.addComment(new Comment());
        task.addComment(new Comment());
        tracker.addTask(task);
        final Input input = new StubInput(new String[]{"7", String.valueOf(task.getId())});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        final int expectedCount = 3;
        assertThat(task.getAllComments().length, is(expectedCount));
    }

    /**
     * Тест вывода всех задач на экран.
     */
    @Test
    public void filteredTaskTest() {
        final Tracker tracker = new Tracker();
        tracker.addTask(new Task("Alex", "desc"));
        tracker.addTask(new Task("name", "desc"));
        tracker.addTask(new Task("Alex", "desc"));
        tracker.addTask(new Task("name", "desc"));
        tracker.addTask(new Task("Alex", "desc"));
        final String filterName = "Alex";
        final Input input = new StubInput(new String[]{"8", filterName});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
        final int expectedCount = 3;
        assertThat(tracker.filteredTaskToName(filterName).length, is(expectedCount));
    }

    /**
     * Тест проверят кидаетс ли исключение, если ввод пользователя выходит за рамки меню.
     */
    @Test(expected = MenuOutException.class)
    public void testMenuOutException() {
        final Tracker tracker = new Tracker();
        final Input input = new StubInput(new String[]{"15"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
    }

//    /**
//     * Правило для запуска теста.
//     */
//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Тест выхода из программы.
     */
    @Test
    public void exitTest() {
        //exit.expectSystemExit();

        final Tracker tracker = new Tracker();
        final Input input = new StubInput(new String[]{"9"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        menuTracker.select(Integer.parseInt(input.getInput("")));
    }

    /**
     * Тест проверят работу метода main. Трекер выводит меню и выбирается в начале кнопка 6 (показать все задачи), потом
     * кнопка 9 выйти из программы.
     */
    @Test
    public void startTest() {
        final Tracker tracker = new Tracker();
        final Input input = new StubInput(new String[]{"6", "9"});
        final MenuTracker menuTracker = new MenuTracker(tracker, input);

        menuTracker.fillAction();
        new StartUI(input, menuTracker).start();
    }

    /**
     * Тест ввода пользователя.
     */
    @Test
    public void consoleInputTestException() {
        final String simulateInput = "1";
        System.setIn(new ByteArrayInputStream(simulateInput.getBytes()));
        final ConsoleInputData inputData = new ConsoleInputData();
        inputData.getInput("");
    }

    /**
     * Тест проверят кидаетс ли исключение, когда у пользователя просится ввести число, а он вводит символ.
     */
    @Test(expected = NumberFormatException.class)
    public void testThrowNumberFormatException() {
        final String simulateInput = "aw";
        System.setIn(new ByteArrayInputStream(simulateInput.getBytes()));
        final ConsoleInputData inputData = new ConsoleInputData();
        final int expected = Integer.parseInt(inputData.getInput("aaa"));
    }
}
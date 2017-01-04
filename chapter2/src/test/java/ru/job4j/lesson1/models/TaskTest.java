package ru.job4j.lesson1.models;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import java.util.Date;

import static org.junit.Assert.assertThat;

/**
 * Тест класса Task.
 */
public class TaskTest {

    /**
     * Тест проверяет что массив комментариев увеличивается.
     */
    @Test
    public void whenTheCommentsMoreThatTheDefaultValueThenIncreasingArrayReturnCountComment() {
        Task task = new Task(null, null);
        task.addComment(new Comment());
        task.addComment(new Comment());
        task.addComment(new Comment());
        task.addComment(new Comment());
        task.addComment(new Comment());
        final int countComment = 5;
        assertThat(task.getCountComment(), is(countComment));
    }

    /**
     * Тест проверяет удаление комментария из заявки.
     * Добавляю 3 комментария, потом удаляю один, и проверяю.
     */
    @Test
    public void whenRemoveObjectCommentThenReturnTrue()  {
        final Comment comment1 = new Comment("Hello");
        final Comment comment2 = new Comment("Max");
        final Comment comment3 = new Comment("Garry");

        final Comment[] expectedArray = new Comment[2];
        expectedArray[0] = comment1;
        expectedArray[1] = comment3;

        Task task = new Task(null, null);
        task.addComment(comment1);
        task.addComment(comment2);
        task.addComment(comment3);
        task.removeComment(comment2);
        assertThat(task.getAllComments(), is(expectedArray));
    }

    /**
     * Тест метода getName.
     */
    @Test
    public void getName() {
        final String actualName = "Garry";
        final String expectedName = actualName;
        final Task task = new Task(actualName, null);
        assertThat(task.getName(), is(expectedName));
    }

    /**
     * Тест метода getDescription.
     */
    @Test
    public void getDescription() {
        final String actualDesc = "Fix Bug";
        final String expectedDesc = actualDesc;
        final Task task = new Task(null, actualDesc);
        assertThat(task.getDescription(), is(expectedDesc));
    }

    /**
     * Тест метода getDate.
     */
    @Test
    public void getCreateDate() {
        final Date actualDate = new Date();
        final Date expectedDate = actualDate;
        final Task task = new Task(null, null);
        assertThat(task.getCreateDate(), is(expectedDate));
    }

    /**
     * Тест метода getAllComments.
     * Должен возвращать все комментарии которые есть в задаче.
     */
    @Test
    public void getAllComments() {
        final Comment comment1 = new Comment("Hello");
        final Comment comment2 = new Comment("Max");
        final Comment[] expectedArray = new Comment[2];
        expectedArray[0] = comment1;
        expectedArray[1] = comment2;
        final Task task = new Task(null, null);
        task.addComment(comment1);
        task.addComment(comment2);
        assertThat(task.getAllComments(), is(expectedArray));
    }

    /**
     * Тест метода getCountComment.
     */
    @Test
    public void whenAddCommentCountIncrementThenReturnCountComment() {
        final String commentOne = "CommentOne";
        final Task task = new Task(null, null);
        task.addComment(new Comment(commentOne));
        task.addComment(new Comment(commentOne));
        task.addComment(new Comment(commentOne));
        final int expectedCount = 3;
        assertThat(task.getCountComment(), is(expectedCount));
    }

    /**
     * Тест метода getCountComment.
     * Проверяет что при удалении комментариев значение countComment уменьшается.
     */
    @Test
    public void whenRemoveCommentCountDecreasesThenReturnCountComment() {
        final int expectedCount = 2;
        final Comment comment1 = new Comment("Hello");
        final Comment comment2 = new Comment("Max");
        final Comment comment3 = new Comment("Garry");

        final Task task = new Task(null, null);
        task.addComment(comment1);
        task.addComment(comment2);
        task.addComment(comment3);
        task.removeComment(comment2);
        assertThat(task.getCountComment(), is(expectedCount));
    }

    /**
     * Проверяет что 2 задачи идентичны.
     */
    @Test
    public void whenNotEqualsThenReturnFalse() {
        final String name = "Garry";
        final String description = "Fix Bug";

        boolean expected = false;

        Task task = new Task(name, description);
        Task task2 = new Task(name, description);
        assertThat(task.equals(task2), is(expected));
    }

    /**
     * Проверяет что 2 задачи идентичны.
     */
    @Test
    public void whenEqualsThenReturnTrue() {
        final String name = "Garry";
        final String description = "Fix Bug";
        boolean expected = true;

        Task task = new Task(name, description);
        assertThat(task.equals(task), is(expected));
    }

    /**
     * Проверяет что метод toString выводит нужную стрку.
     */
    @Test
    public void toStringTest() {
        final String name = "Garry";
        final String description = "Fix Bug";
        final Date date = new Date();

        Task task = new Task(name, description);
        final String expected = "{Task name: Garry},"
                + " {Task descriptions: Fix Bug}, {Created Date: " + date
                + "}, {Count comments: 0}, {id: " + task.getId() + "}"
                + System.getProperty("line.separator");

        assertThat(task.toString(), is(expected));

    }
}
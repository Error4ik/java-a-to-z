package ru.job4j.tracker.task;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Comment.
 */
public class CommentTest {

    /**
     * Проверка getComment при установке через конструктор.
     */
    @Test
    public void whenCreateCommentThenGetExpectedComment() {
        final String actual = "Hello";
        final String expected = actual;
        final Comment comment = new Comment(actual);
        assertThat(comment.getComment(), is(expected));
    }

    /**
     * Проверка getComment при установке методом setComment.
     */
    @Test
    public void whenSetCommentThenGetExpectedComment() {
        final String actual = "Hello";
        final String expected = actual;
        final Comment comment = new Comment();
        comment.setComment(actual);
        assertThat(comment.getComment(), is(expected));
    }

    /**
     * Проверяет что 2 комментария одинаковы.
     */
    @Test
    public void equalsTest() {
        final Comment comment = new Comment("Garry");
        final Comment comment1 = new Comment("Garry");
        boolean expected = true;
        assertThat(comment.equals(comment1), is(true));
    }
}
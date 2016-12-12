package ru.job4j.lesson1.models;

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
        final String expected = "Hello";
        final Comment comment = new Comment(expected);
        assertThat(comment.getComment(), is(expected));
    }

    /**
     * Проверка getComment при установке методом setComment.
     */
    @Test
    public void whenSetCommentThenGetExpectedComment() {
        final String expected = "Hello";
        final Comment comment = new Comment();
        comment.setComment(expected);
        assertThat(comment.getComment(), is(expected));
    }

    /**
     * Проверяет что 2 комментария не одинаковы.
     */
    @Test
    public void whenNotEqualsThenReturnFalse() {
        final Comment comment = new Comment("Garry");
        final Comment comment1 = new Comment("Garry");
        boolean expected = false;
        assertThat(comment.equals(comment1), is(expected));
    }

    /**
     * Проверяет что 2 комментария одинаковы.
     */
    @Test
    public void whenEqualsThenReturnTrue() {
        final Comment comment = new Comment("Garry");
        final Comment comment1 = comment;
        boolean expected = true;
        assertThat(comment.equals(comment1), is(expected));
    }

    /**
     * Проверяет что два разных объекта не равны.
     */
    @Test
    public void whenInstanceIsNotCommentThenReturnFalse() {
        final Comment comment = new Comment();
        final String string = "aaa";
        final boolean expected = false;
        assertThat(comment.equals(string), is(expected));
    }

    /**
     * Тест метода getId.
     */
    @Test
    public void getIdTest() {
        final Comment comment = new Comment();
        final int id = comment.getId();
        assertThat(comment.getId(), is(id));
    }
}
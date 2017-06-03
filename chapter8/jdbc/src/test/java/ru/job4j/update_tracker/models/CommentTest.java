package ru.job4j.update_tracker.models;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Comment.
 *
 * @author Alexey Voronin.
 * @since 02.06.2017.
 */
public class CommentTest {

    /**
     * getComment().
     */
    @Test
    public void whenGetCommentThenReturnValidComment() {
        final Comment comment = new Comment(1, 5, 0, "Hello");
        final String expectedValue = "Hello";

        final String actualValue = comment.getComment();
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * GetTaskID().
     */
    @Test
    public void whenGetTaskIDThenReturnValidID() {
        final Comment comment = new Comment(2, 5, 0, "Hello");
        final int expectedValue = 5;

        final int actualValue = comment.getTaskID();
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Get comment id.
     */
    @Test
    public void whenGetIdThenReturnCommentId() {
        final Comment comment = new Comment(1, 0, 0, null);
        final int expectedValue = 1;

        final int actualValue = comment.getId();

        assertThat(actualValue, is(expectedValue));
    }

}
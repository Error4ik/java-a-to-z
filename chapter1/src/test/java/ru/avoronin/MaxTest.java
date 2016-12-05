package ru.avoronin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ���� ������ Max.
 */
public class MaxTest {

    /**
     * ���� ���������� ������ �����, ���� ��� ������ �������.
     */
    @Test
    public void whenTheFirstMoreThanTheSecondTheFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 3;
        assertThat(first, is(max.findMaxTwoNumber(first, second)));
    }

    /**
     * ���� ���������� ������ �����, ���� ��� ������ �������.
     */
    @Test
    public void whenTheSecondMoreThanTheFirstTheSecondReturn() {
        Max max = new Max();
        final int first = 7;
        final int second = 9;
        assertThat(second, is(max.findMaxTwoNumber(first, second)));
    }

    /**
     * ���� ���������� ������ �����, ���� ��� ����� �����.
     */
    @Test
    public void whenTheFirstEqualsThanTheSecondTheFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 5;
        assertThat(first, is(max.findMaxTwoNumber(first, second)));
    }


    /**
     * ���� ���������� ������ �����, ���� ��� ������ ������� � ��������.
     */
    @Test
    public void whenTheFirstMoreThanTheSecondAndThirdTheFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 3;
        final int third = 4;
        assertThat(first, is(max.findLargeNumberOfThree(first, second, third)));
    }

    /**
     * ���� ���������� ������ �����, ���� ��� ������ ������� � ��������.
     */
    @Test
    public void whenTheSecondMoreThanTheFirstAndThirdTheSecondReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 7;
        final int third = 4;
        assertThat(second, is(max.findLargeNumberOfThree(first, second, third)));
    }

    /**
     * ���� ���������� ������ �����, ���� ��� ������ ������� � ��������.
     */
    @Test
    public void whenTheThirdMoreThanTheFirstAndSecondTheThirdReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 7;
        final int third = 11;
        assertThat(third, is(max.findLargeNumberOfThree(first, second, third)));
    }

    /**
     * ���� ���������� ������ �����, ���� ��� ������ ������� � ��������.
     */
    @Test
    public void whenTheFirstAndSecondAndThirdEqualsThenFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 5;
        final int third = 5;
        assertThat(first, is(max.findLargeNumberOfThree(first, second, third)));
    }
}
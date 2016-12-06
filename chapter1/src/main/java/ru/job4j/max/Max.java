package ru.job4j.max;

/**
 * ���� ��� ���������� ������������� �����.
 */
public class Max {

    /**
     * ����� �������� �� ���� 2 ����� � ���������� �� ������� ������.
     * @param first ������ �����.
     * @param second ������ �����.
     * @return ���������� ������� �����.
     */
    public int findMaxTwoNumber(final int first, final int second) {
        return first > second ? first : second;
    }

    /**
     * ������� ������� �� ���� �����.
     * @param first ������ �����.
     * @param second ������ �����.
     * @param third ������ �����.
     * @return ���������� ������� �� ���� �����.
     */
    public int findLargeNumberOfThree(final int first, final int second, final int third) {
        return findMaxTwoNumber(findMaxTwoNumber(first, second), third);
    }
}
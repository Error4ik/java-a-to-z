package ru.job4j.counter;

/**
 * ����� �������� ����� ������ �����.
 */
public class Counter {

    /**
     * ����� ��������� ����� ���� ������ ����� �� start �� end.
     * @param start ������ �����.
     * @param end ��������� �����.
     * @return ���������� ����� ������ �����.
     */
    public int add(final int start, final int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
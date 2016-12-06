package ru.job4j.factorial;

/**
 * Класс вычисляет факториал числа.
 */
public class Factorial {

    /**
     * Метод высчитывает факториал числа.
     * Например факториал числа 5 = 1 * 1 * 2 * 3 * 4 * 5 = 120.
     * @param number входное число для которого нужно посчитать факториал.
     * @return возвращает факториал входного числа.
     */
    public int findFactorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}
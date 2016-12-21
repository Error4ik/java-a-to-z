package ru.job4j.TestTask;

/**
 * Класс проверят парность скобок в строке.
 */
public class FindSequence {

    /**
     * Возвращает true если скобки последовательность скобок правильная.
     * @param string строка в которой осуществляется проверка.
     * @return result.
     */
    public boolean find(String string) {
        boolean result = false;
        final char[] chars = string.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[0] == ')') {
                return false;
            }
            if (chars[i] == '(') {
                count++;
            } else if (chars[i] == ')') {
                count--;
            }
        }

        if (count == 0) {
            result = true;

        }
        return result;
    }
}

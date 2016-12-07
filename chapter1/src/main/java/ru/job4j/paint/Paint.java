package ru.job4j.paint;

/**
 * Класс строит пирамиду из символов.
 */
public class Paint {

    /**
     * Метод рисуте пирамиду высотой h.
     * @param h высота пирамиды.
     * @return возвращает пустую строку если параметр h меньше или равен нулю.
     * в остальных случаях возвращает заполненную символами строку.
     */
    public String piramide(int h) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= h; i++) {
            //ставит отступы для пирамиды с лева.
            for (int j = 1; j <= h - i; j++) {
                sb.append(" ");
            }

            //ставит символы которыми рисуется пирамида.
            for (int k = 1; k <= i; k++) {
                sb.append("^ ");
            }
            sb.append(System.getProperty("line.separator"));
        }
        return h <= 0 ? "" : sb.toString();
    }
}
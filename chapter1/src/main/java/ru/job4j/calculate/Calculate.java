package ru.job4j.calculate;

/**
 * Calculate.
 *
 * @author Alexey Voronin
 * @version 1
 */
public class Calculate {
   /**
    * @param text любой текст.
    * @return Возвращает строку в которой текст повторяется три раза.
    */
   public String showText(String text) {
        return String.format("%s %s %s", text, text, text);
   }
}
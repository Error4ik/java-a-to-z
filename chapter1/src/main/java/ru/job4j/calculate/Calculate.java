package ru.job4j.calculate;

/**
 * Calculate.
 *
 * @author Alexey Voronin
 * @version 1
 */
public class Calculate {
   /**
    * @param text ����� �����.
    * @return ���������� ������ � ������� ����� ����������� ��� ����.
    */
   public String showText(String text) {
        return String.format("%s %s %s", text, text, text);
   }
}
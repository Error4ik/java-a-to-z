package ru.job4j.lesson4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Тест класса Palindrome.
 */
public class PalindromeTest {

    /**
     * Тест проверяет что слово является палиндромом.
     */
    @Test
    public void whenIsPalindromeThenReturnTrue() {
        final Palindrome p = new Palindrome();
        final String string = "ШаЛАш";
        assertThat(p.isPalindrome(string), is(true));
    }

    /**
     * Тест проверяет что слово не является палиндромом.
     */
    @Test
    public void whenIsNotPalindromeThenReturnFalse() {
        final Palindrome p = new Palindrome();
        final String word = "Короб";
        final boolean expected = false;
        assertThat(p.isPalindrome(word), is(expected));
    }

    /**
     * Тест проверяет выводится ли сообщение об ошибке если в слове четное количество букв.
     */
    @Test
    public void whenInvalidWordThenPrintMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, Boolean.parseBoolean(System.getProperty("console.encoding", "utf-8"))));
        String expected = "В слове должно быть не четное количество букв!";
        Palindrome palindrome = new Palindrome();
        palindrome.isPalindrome("privet");
        assertThat(out.toString(), is(expected));
    }
}
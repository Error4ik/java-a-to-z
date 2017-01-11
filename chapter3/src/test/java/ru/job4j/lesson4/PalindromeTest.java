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
        final String string = "ШалАш";
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
}
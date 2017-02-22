package ru.job4j.action;

/**
 *
 */
public class PrintHelloAction implements Action {

    @Override
    public void execute() {
        System.out.println("Hello!");
    }
}

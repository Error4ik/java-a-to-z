package ru.job4j.view;

/**
 * Extended view.
 */
public class ExtendedView extends ConsoleView {

    /**
     * String separator.
     */
    private final String sep = System.getProperty("line.separator");

    /**
     * Show menu calculator.
     */
    @Override
    public void showMenu() {
        super.showMenu();
        System.out.printf("%s%s%s%s", "Extended functions cosine, sinus, tangent, square root, involution", sep,
                "Eg cosine - cos 30, sinus - sin 45, tangent - tan 30, square root - sqrt 25, involution - 5 pow 5", sep);
    }
}

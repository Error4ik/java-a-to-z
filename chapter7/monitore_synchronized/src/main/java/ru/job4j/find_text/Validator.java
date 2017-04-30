package ru.job4j.find_text;

import com.google.common.base.Joiner;
import ru.job4j.find_text.exception.InvalidKeyException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Validate input value.
 *
 * @author Alexey Voronin.
 * @since 29.04.2017.
 */
public class Validator {

    /**
     * String separator.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Input arguments.
     */
    private final String[] inputArgs;

    /**
     * Root path to search.
     */
    private Path path;

    /**
     * Find text.
     */
    private String findText;

    /**
     * Should I stop the search if a file was found.
     */
    private boolean isStopSearch = false;

    /**
     * Constructor.
     *
     * @param inputArgs input arguments.
     */
    public Validator(final String[] inputArgs) {
        this.inputArgs = inputArgs;
    }

    /**
     * Checks the validity of keys.
     *
     * @return true if keys is valid, or false If the keys are not valid.
     * @throws InvalidKeyException throws if invalid key.
     */
    public boolean isCorrectKey() throws InvalidKeyException {
        boolean correct;
        if (inputArgs.length == 6 && inputArgs[0].equals("-d")
                && inputArgs[2].equals("-s") && inputArgs[4].equals("-f")) {

            this.init();
            correct = true;
        } else {
            showConditions();
            throw new InvalidKeyException("You entered not right key!");
        }
        return correct;
    }

    /**
     * Displays a hint if an error was made in the arguments.
     */
    public void showConditions() {
        System.out.printf(Joiner.on(separator).join("Line arguments must be of the form:   -d dir -s text -f flag",
                "Eg -d c:/ -s \"Hello\" -f 0",
                "{-d} key directory",
                "{dir} directory to search file",
                "{-s} key search text",
                "{text} text to find",
                "{-f} key stopSearch",
                "{flag} 1 true 0 false.", ""));
    }

    /**
     * Initializing fields.
     */
    private void init() {
        this.path = Paths.get(inputArgs[1]);
        this.findText = inputArgs[3];
        if (inputArgs[5].equals("1")) {
            this.isStopSearch = true;
        }
    }

    /**
     * Get.
     *
     * @return root path to search.
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * Get.
     *
     * @return find text.
     */
    public String getFindText() {
        return this.findText;
    }

    /**
     * Get.
     *
     * @return Should I stop the search if a file was found.
     */
    public boolean isStopSearch() {
        return this.isStopSearch;
    }
}

package ru.job4j.update_tracker.action;

/**
 * Base class to all action.
 */
public abstract class BaseAction implements Action {

    /**
     * Action name.
     */
    private final String name;

    /**
     * Action id.
     */
    private final String id;

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public BaseAction(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод для отображения порядкового номера и названия действия.
     *
     * @return action number and name.
     */
    @Override
    public String showItem() {
        return String.format("%s. %s", this.getId(), this.getName());
    }

    /**
     * Get.
     *
     * @return action name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get.
     *
     * @return action id.
     */
    @Override
    public String getId() {
        return this.id;
    }
}
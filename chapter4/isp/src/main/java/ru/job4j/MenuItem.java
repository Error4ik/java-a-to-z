package ru.job4j;

import ru.job4j.action.Action;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MenuItem {

    /**
     * Action in menu item.
     */
    private Action action;

    /**
     * Menu item id.
     */
    private int id;

    /**
     * Menu name.
     */
    private String name;

    /**
     * List child item.
     */
    private final List<MenuItem> childItem;

    /**
     * Constructor.
     * @param name menu name.
     * @param id menu id.
     */
    public MenuItem(final String name, final int id) {
        this.name = name;
        this.id = id;
        this.childItem = new ArrayList<>();
    }

    /**
     * Added child item.
     *
     * @param item item.
     * @return return true when added.
     */
    public boolean addChild(final MenuItem item) {
        this.childItem.add(item);
        return true;
    }

    /**
     * Removing child item.
     *
     * @param item item.
     * @return if remove return true.
     */
    public boolean removeChild(final MenuItem item) {
        boolean flag = false;
        if (this.childItem.contains(item)) {
            this.childItem.remove(item);
            flag = true;
        }
        return flag;
    }

    /**
     * Get.
     * @return id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set.
     *
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get.
     *
     * @return childList.
     */
    public List<MenuItem> getChildItem() {
        return childItem;
    }

    /**
     * Обходит рекрсивно всех своих потомков и запрашивает имена.
     *
     * @param pref pref.
     * @return string.
     */
    public String showChild(final String pref) {
        String prefix = String.format("%s%s", pref, ":-");
        final String sep = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" ").append(prefix).append(this.name).append(sep);
        for (MenuItem menuItem : childItem) {
            sb.append(menuItem.showChild(prefix));
        }
        return sb.toString();
    }

    /**
     * Get.
     *
     * @return action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Set.
     *
     * @param action action.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * toString.
     *
     * @return id and name menu item.
     */
    @Override
    public String toString() {
        return String.format("MenuItem: id = %d, name = %s", id, name);
    }
}

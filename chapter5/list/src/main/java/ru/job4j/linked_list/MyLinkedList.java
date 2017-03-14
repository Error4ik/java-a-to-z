package ru.job4j.linked_list;

/**
 * My linked list.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 13.03.2017.
 */
public class MyLinkedList<T> {

    /**
     * First item to the list.
     */
    private Item<T> first;

    /**
     * Last item to the list.
     */
    private Item<T> last;

    /**
     * The size of the list.
     */
    private int size;

    /**
     * Add item to the list.
     *
     * @param t item.
     */
    public void add(T t) {
        if (this.getSize() == 0) {
            this.first = new Item<>(t, null, null);
        } else if (this.getSize() == 1) {
            this.last = new Item<>(t, this.first, null);
            this.first.nextItem = this.last;
        } else {
            Item<T> newLast = new Item<>(t, this.last, null);
            this.last.nextItem = newLast;
            this.last = newLast;
        }
        this.size++;
    }

    /**
     * Get element by index.
     *
     * @param index The index of the element to be get.
     * @return element.
     */
    public T get(int index) {
        if (this.getSize() <= index) {
            return null;
        }
        int i = 0;
        Item<T> current = this.first;
        while (i != index) {
            current = current.getNextItem();
            i++;
        }
        return current.getItem();
    }

    /**
     * Remove item from list.
     *
     * @param index The index of the element to be deleted.
     * @return the deleted item..
     */
    public T remove(final int index) {
        if (this.getSize() <= index) {
            return null;
        }
        int i = 0;
        Item<T> current = this.first;
        while (i != index) {
            current = current.getNextItem();
            i++;
        }

        if (this.getSize() == 1) {
            this.first = null;
            this.last = null;
        } else {
            if (current == this.first) {
                this.first = current.getNextItem();
                this.first.previousItem = null;
            }
            if (current == this.last) {
                this.last = current.getPreviousItem();
                this.last.nextItem = null;
            }
            if (current.getNextItem() != null && current.getPreviousItem() != null) {
                current.getPreviousItem().nextItem = current.getNextItem();
                current.getNextItem().previousItem = current.getPreviousItem();
            }
        }
        this.size--;
        return current.item;
    }

    /**
     * Get size.
     *
     * @return size the list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Class Item.
     *
     * @param <T> any type.
     */
    private class Item<T> {

        /**
         * Object any type.
         */
        private T item;

        /**
         * Link to the next item in the list.
         */
        private Item<T> nextItem;

        /**
         * Link to the previous item in the list.
         */
        private Item<T> previousItem;

        /**
         * Constructor.
         *
         * @param item         any type object.
         * @param previousItem Link to the previous item in the list.
         * @param nextItem     Link to the next item in the list.
         */
        Item(final T item, final Item<T> previousItem, final Item<T> nextItem) {
            this.item = item;
            this.previousItem = previousItem;
            this.nextItem = nextItem;
        }

        /**
         * Get.
         *
         * @return item.
         */
        public T getItem() {
            return item;
        }

        /**
         * Get.
         *
         * @return nextItem.
         */
        public Item<T> getNextItem() {
            return nextItem;
        }

        /**
         * Get.
         *
         * @return previous item.
         */
        public Item<T> getPreviousItem() {
            return previousItem;
        }
    }
}

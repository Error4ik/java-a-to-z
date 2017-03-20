package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * My Directory.
 *
 * @param <K> any type key.
 * @param <V> any type value.
 * @author Alexey Voronin.
 * @since 19.03.2017.
 */
public class Directory<K, V> implements Iterable<K> {

    /**
     * Default capacity to array.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * If the map is filled to this level, then it will be doubled.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * Number of elements in the array.
     */
    private int amountItem;

    /**
     * Array to node.
     */
    private Node<K, V>[] nodes;

    /**
     * Constructor.
     * If the capacity is not set, then set the default value.
     */
    public Directory() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor.
     *
     * @param size array size.
     */
    public Directory(final int size) {
        this.nodes = new Node[size];
    }

    /**
     * Adds a key and a value to the map. If such a key already exists, then the new object replaces the previous one,
     * associated with this key. This method returns the previous value of the object or null,
     * if the key was not already contained in the map. The key can be null, but the value must be different than null.
     *
     * @param key   key.
     * @param value value.
     * @return previous value, or null if none.
     */
    public V put(final K key, final V value) {
        if ((float) this.amountItem / this.getSize() >= LOAD_FACTOR) {
            this.changeCapacity();
        }
        int index = this.hash(key);
        Node<K, V> node = this.nodes[index];
        if (node == null) {
            this.nodes[index] = new Node<K, V>(key, value, null);
            this.amountItem++;
            return null;
        } else {
            Node<K, V> oldNode = null;
            while (node != null) {
                if (node.getKey().equals(key)) {
                    V oldValue = node.getValue();
                    node.value = value;
                    return oldValue;
                }
                oldNode = node;
                node = node.getNextItem();
            }
            oldNode.nextItem = new Node<K, V>(key, value, null);
            this.amountItem++;
            return oldNode.getValue();
        }
    }

    /**
     * Returns an object that matches the specified key or null value,
     * if the card does not contain the right of the key.
     *
     * @param key key.
     * @return the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     */
    public V get(K key) {
        Node<K, V> node = this.nodes[this.hash(key)];
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            } else {
                node = node.getNextItem();
            }
        }
        return null;
    }

    /**
     * Remove object by key, and return this object.
     *
     * @param key key.
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public V delete(K key) {
        int index = this.hash(key);
        Node<K, V> node = this.nodes[index];
        Node<K, V> oldNode = null;
        V returnValue = null;
        while (node != null) {
            if (node.getKey().equals(key)) {
                if (node.getNextItem() == null && oldNode == null) {
                    returnValue = node.getValue();
                    this.nodes[index] = node.getNextItem();
                    amountItem--;
                    break;
                } else if (oldNode == null) {
                    returnValue = node.getValue();
                    this.nodes[index] = node.getNextItem();
                    amountItem--;
                    break;
                } else {
                    oldNode.nextItem = node.getNextItem();
                    returnValue = node.getValue();
                    amountItem--;
                    break;
                }
            } else {
                oldNode = node;
                node = node.getNextItem();
            }
        }
        return returnValue;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }

    /**
     * Get size.
     *
     * @return array size.
     */
    public int getSize() {
        return this.nodes.length;
    }

    /**
     * Get amountItem.
     *
     * @return the number of elements in an array.
     */
    public int getAmountItem() {
        return amountItem;
    }

    /**
     * Calculates the hashcode of the key and returns a number.
     *
     * @param key key.
     * @return hash key.
     */
    private int hash(final K key) {
        return key.hashCode() & (this.getSize() - 1);
    }

    /**
     * If array is full, changes the capacity.
     */
    private void changeCapacity() {
        final Node<K, V>[] oldNodes = nodes;
        this.nodes = new Node[this.getSize() * 2];
        this.amountItem = 0;
        for (Node<K, V> anOldNode : oldNodes) {
            if (anOldNode != null) {
                this.put(anOldNode.getKey(), anOldNode.getValue());
            }
        }
    }

    /**
     * My Iterator.
     */
    private class MyIterator implements Iterator<K> {

        /**
         * Index in array.
         */
        private int index = 0;

        /**
         * Current node.
         */
        private Node<K, V> node;

        @Override
        public boolean hasNext() {
            if (node != null) {
                return true;
            } else {
                while (node == null && Directory.this.getSize() > this.index) {
                    node = Directory.this.nodes[index++];
                }
                if (node == null) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items!");
            }
            final Node<K, V> returnNode = this.node;
            this.node = this.node.getNextItem();
            return returnNode.getKey();
        }
    }

    /**
     * Node.
     *
     * @param <K> key.
     * @param <V> value.
     */
    private static class Node<K, V> {

        /**
         * Key.
         */
        private final K key;

        /**
         * Value.
         */
        private V value;

        /**
         * Next item.
         */
        private Node<K, V> nextItem;

        /**
         * Constructor.
         *
         * @param key   key.
         * @param value value.
         * @param item  next item.
         */
        Node(final K key, final V value, final Node<K, V> item) {
            this.key = key;
            this.value = value;
            this.nextItem = item;
        }

        /**
         * Get key.
         *
         * @return key.
         */
        private K getKey() {
            return key;
        }

        /**
         * Get value.
         *
         * @return value.
         */
        private V getValue() {
            return value;
        }

        /**
         * Get item.
         *
         * @return item.
         */
        private Node<K, V> getNextItem() {
            return nextItem;
        }
    }
}

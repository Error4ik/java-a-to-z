package ru.job4j;

import java.util.TreeMap;
import java.util.Comparator;
import java.util.List;

/**
 * Bank.
 *
 * @author Alexey Voronin.
 * @since 01.04.2017.
 */
public class Bank {

    /**
     * The tree contains all the intersecting values and sorts them in descending order.
     */
    private TreeMap<Integer, Human> tree = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });


    /**
     * Receives a list of people who visited the bank and returns the time interval with the most people.
     *
     * @param list list.
     * @return Line with the interval of time and the number of people.
     */
    public String maxPeople(final List<Human> list) {
        for (Human human : list) {
            int amount = getAmount(list, human);
            tree.put(amount, human);
        }
        return String.format("The maximum number of people (%s) in the bank was from: %s - %s", tree.firstKey(),
                tree.firstEntry().getValue().getTimeIn(), tree.firstEntry().getValue().getTimeOut());
    }

    /**
     * The method counts the number of people crossing each other.
     *
     * @param list  list people.
     * @param human person for whom we find intersections.
     * @return amount.
     */
    private int getAmount(List<Human> list, Human human) {
        int amount = 0;
        for (Human aList : list) {
            if (human.getTimeIn().hashCode() >= aList.getTimeIn().hashCode()
                    && human.getTimeOut().hashCode() <= aList.getTimeOut().hashCode()) {
                amount++;
            }
        }
        return amount;
    }
}

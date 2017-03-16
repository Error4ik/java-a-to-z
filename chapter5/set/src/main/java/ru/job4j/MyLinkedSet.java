package ru.job4j;

import ru.job4j.linked_list.MyLinkedList;

/**
 * My Set to linkedList.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 15.03.2017.
 */
public class MyLinkedSet<T> extends MyLinkedList<T> {

    @Override
    public void add(T t) {
        if (t != null) {
            boolean flag = true;
            for (T value : this) {
                if (t.equals(value)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                super.add(t);
            }
        }
    }
}

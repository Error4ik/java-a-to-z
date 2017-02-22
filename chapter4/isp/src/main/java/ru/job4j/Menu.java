package ru.job4j;

import ru.job4j.action.PrintHelloAction;
import ru.job4j.view.ConsoleOutput;

/**
 * Main class test.
 */
public class Menu {

    //private final static Map<Integer, MenuItem> map = new HashMap<>();

    /**
     * Main method, create main menu and add sub menu and show in console.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        MenuItem menu = new MenuItem("Main menu", 1);

        MenuItem oneSubmenu = new MenuItem("Submenu 1", 2);
        MenuItem twoSubmenu = new MenuItem("Submenu 2", 3);

        MenuItem child1 = new MenuItem("Child 1", 4);
        MenuItem child2 = new MenuItem("Child 2", 5);

        oneSubmenu.addChild(child1);
        oneSubmenu.addChild(child2);

        MenuItem child3 = new MenuItem("Child 1", 6);
        MenuItem child4 = new MenuItem("Child 2", 7);

        child4.setAction(new PrintHelloAction());

        twoSubmenu.addChild(child3);
        twoSubmenu.addChild(child4);

        menu.addChild(oneSubmenu);
        menu.addChild(twoSubmenu);

        ConsoleOutput output = new ConsoleOutput();
        output.print(menu.showChild(""));


//        getChild(menu);
//
//        for (Integer integer : map.keySet()) {
//            execute(integer);
//        }
//
//        addItem(4, new MenuItem("Child 5", 8));
//
//        output.print(menu.showChild(""));
    }

//    public static void getChild(final MenuItem item) {
//        map.put(item.getId(), item);
//        for (MenuItem menuItem : item.getChildItem()) {
//            getChild(menuItem);
//        }
//    }
//
//    public static void execute(final int key) {
//        if (map.containsKey(key)) {
//           if (map.get(key).getAction() != null) {
//               map.get(key).getAction().execute();
//           }
//        }
//    }
//
//    public static boolean addItem(final int position, final MenuItem item) {
//        for (Integer integer : map.keySet()) {
//            if (integer == position) {
//                map.get(position).addChild(item);
//            }
//        }
//        return true;
//    }
}

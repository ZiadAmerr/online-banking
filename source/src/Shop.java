package source.src;

import java.util.*;

public class Shop {
    private static final Map<Item, Integer> inventory = new HashMap<>();

    public static Item getItem(String name) {
        for(Item item : inventory.keySet())
            if (item.getName().equals(name))
                return item;
        return null;
    }

    public static void addItem(String name, int count) {
        Item item = getItem(name);
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + count);
        } else {
            inventory.put(item, count);
        }
    }

    public static boolean sellItem(String name) {
        Item item = getItem(name);
        if (inventory.containsKey(item) && inventory.get(item) >= 1) {
            inventory.put(item, inventory.get(item) - 1);
            return true;
        }
        return false;
    }

    public static int getItemCount(String name) {
        return inventory.getOrDefault(getItem(name), 0);
    }
}
package source.src;

import java.util.*;

public class Shop {
    private static final Map<Item, Integer> inventory = new HashMap<>();

    private static Item getItem(String name) {
        if (inventory.isEmpty())
            return null;

        for(Item item : inventory.keySet())
            if (item.name.equals(name))
                return item;
        return null;
    }

    public static void addItem(String name, int count) {
        Item item = getItem(name);
        if (item == null) {
            item = new Item(name, 0, Item.ItemType.ITEM);
        }
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + count);
        } else {
            inventory.put(item, count);
        }
    }

    public static void addItem(String name, int count, float price, String type) {
        Item item = getItem(name);
        if (item == null) {
            item = new Item(name, price, Item.ItemType.valueOf(type));
            inventory.put(item, 1);
            addItem(name, count - 1);
        } else {
            if (item.price != price)
                throw new IllegalArgumentException("Item " + name + " already exists with a different price");
            if (item.type != Item.ItemType.valueOf(type))
                throw new IllegalArgumentException("Item " + name + " already exists with a different type");
            addItem(name, count);
        }
    }

    public static boolean sellItem(String name) {
        Item item = getItem(name);
        if (hasItem(name)) {
            inventory.put(item, inventory.get(item) - 1);
            return true;
        }
        return false;
    }

    public static boolean hasItem(String name) {
        Item item = getItem(name);
        return inventory.containsKey(item) && inventory.get(item) >= 1;
    }

    public static boolean itemExisted(String name) {
        return getItem(name) != null;
    }

    public static float getPrice(String name) {
        Item item = getItem(name);
        if (item == null)
            return -1;
        return item.price;
    }
}
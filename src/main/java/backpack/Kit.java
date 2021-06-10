package backpack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Kit {
    private final Item[] items;
    private final Backpack backpack;
    private Set<Set<Item>> result = new LinkedHashSet<>();
    private int maxPrice;

    public Kit(Item[] items, Backpack backpack) {
        this.items = items;
        this.backpack = backpack;
        maxPrice = 0;
    }

    public ArrayList<Backpack> getOptimalKits() {
        findAll();
        ArrayList<Backpack> backpacks = new ArrayList<>(result.size());
        for (Set<Item> currentKit: result) {
            backpacks.add(new Backpack(backpack.getMaxWeight(), currentKit));
        }
        return backpacks;
    }

    private void findAll() {
        result.clear();
        find(items.length);
    }

    private void find(int length) {
        if (length == 1) {
            return;
        }

        for (int i = 0; i < length; i++) {
            find(length - 1);
            int weight = 0;
            Set<Item> set = new HashSet<>(items.length);
            int currentPrice = 0;
            for (Item item: items) {
                weight += item.getWeight();
                if (weight > backpack.getMaxWeight()) {
                    break;
                }
                else {
                    set.add(item);
                    currentPrice += item.getPrice();
                }
            }
            if (maxPrice < currentPrice) {
                result.clear();
                maxPrice = currentPrice;
            }
            if (maxPrice == currentPrice) {
                result.add(set);
            }
            rotate(length);
        }
    }

    private void rotate(int length) {
        int pos = items.length - length;
        Item temp = items[pos];

        for (int i = pos + 1; i < items.length; i++) {
            items[i - 1] = items[i];
        }

        items[items.length - 1] = temp;
    }

}

package backpack;

import lombok.Data;

import java.util.ArrayList;
import java.util.Set;

@Data
public class Backpack {
    private int maxWeight;
    private Set<Item> items;

    public Backpack(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Backpack(int maxWeight, Set<Item> items) {
        this.maxWeight = maxWeight;
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Рюксазк с максимальным весом: %s%n", maxWeight));
        if (items.size() == 0) {
            sb.append("Нет положенных вещей");
        }
        else {
            sb.append("Вещи:");
        }
        int price = 0;
        for (Item item: items) {
            sb.append("\n").append(item.toString());
            price += item.getPrice();
        }
        sb.append(String.format("%n Общая стоимость %s", price));
        return sb.toString();
    }

}

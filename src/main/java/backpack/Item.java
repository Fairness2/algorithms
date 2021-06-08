package backpack;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Item {
    private String name;
    private int price;
    private int weight;


    @Override
    public String toString() {
        return String.format("Название: %s, цена: %s, вес: %s.", name, price, weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, weight);
    }
}

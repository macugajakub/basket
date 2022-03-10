package pl.macugajakub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    private Map<Item, Integer> items;

    public Basket() {
        this.items = new HashMap<>();
    }

    //Problem: returning whole HashMap allows changing
    // keys and values
    public Map<Item, Integer> getItems() {
        return items;
    }


    //Returns amount of existing item
    //When item doesn't belong to basket returns 0
    public int getItemAmount(Item item) {
        if (this.items.containsKey(item)) {
            return this.items.get(item);
        }
        return 0;
    }

    //Adds only one item.
    //If item already exist quantity is increased by 1.
    public void addItem(Item item) {
        int itemCounter = items.getOrDefault(item, 0);
        items.put(item, ++itemCounter);
    }

    //Adds certain amount of items given as a parameter
    //Amount must be greater than 1
    //If item already exists in a basket increase amount by given quantity
    //If item does not exist in basket put it whit given amount
    public void addItems(Item item, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity of items must be >= 1: " + quantity);
        }
        if (this.items.containsKey(item)) {
            int itemCounter = this.items.get(item) + quantity;
            this.items.replace(item, quantity + itemCounter);
        } else {
            this.items.put(item, quantity);
        }
    }

    //Removes item given as an argument.
    //If item is not in the basket returns false.
    //If quantity of this item is greater than 1, decrease quantity by 1.
    //If only one item exists removes item from basket.
    public boolean removeItem(Item item) {
        if (!this.items.containsKey(item)) {
            return false;
        }
        int itemCounter = this.items.get(item);
        if (itemCounter > 1) {
            this.items.replace(item, --itemCounter);
            return true;
        }
        this.items.remove(item);
        return true;
    }

    //Removes given amount of given item
    //If item doesn't exist return false
    //If amount of item to remove is greater or equal than actual amount
    //deletes item from basket
    public boolean removeItems(Item item, int quantity) {
        if (!items.containsKey(item)) {
            return false;
        }
        if(quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 1: " + quantity);
        }
        int actualAmount = this.getItemAmount(item);
        if (quantity > actualAmount) {
            this.items.remove(item);
            return true;
        }
        this.items.replace(item, actualAmount - quantity);
        return true;
    }

    public double costOfBasket() {
        double totalCost = 0;
        for (Item item : this.items.keySet()
             ) {
            totalCost += item.getPrice() * this.getItemAmount(item);
        }
        return totalCost;
    }

    @Override
    public String toString() {
        List<String> entries = this.items.entrySet().stream()
                .map(str -> str.getKey() + "USD x " + str.getValue())
                .collect(Collectors.toList());
        StringBuilder str = new StringBuilder();
        for (String x: entries) {
            str.append(x + '\n');
        }
        return "Basket:\n" + str;
    }
}

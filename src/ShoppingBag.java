import java.util.HashMap;
import java.util.Map;

// Create ShoppingBag class here
public class ShoppingBag<T extends PricedItem<Integer>> {

    private Map<T, Integer> shoppingBag; // map between food item (T) to its amount in the shopping bag (integer)

    public ShoppingBag() {
        shoppingBag = new HashMap<>();
    }

    public void addItem(T item) {
        if (shoppingBag.containsKey(item)) { // key exists, increment by 1
            shoppingBag.put(item, shoppingBag.get(item) + 1);
        } else { // key does not exist
            shoppingBag.put(item, 1);
        }
    }

    /*
     * calculate the total price of each item by iterating through the items in shoppingBag and multiplying each item's quantity by its price.
     * Sum up all the total prices to find the grand total of everything in shoppingBag. Return the grand total.
     */
    public int getTotalPrice() {
        int totalPrice = 0;

        for (T item : shoppingBag.keySet()) {
            int itemPrice = item.getPrice();
            int quantity = shoppingBag.get(item);
            int totalPriceForItem = itemPrice * quantity;
            totalPrice += totalPriceForItem;
        }

        return totalPrice;
    }

    // TODO: add a removeItem method
}
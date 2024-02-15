import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FoodMenu {
    private List<Food> menu;

    public FoodMenu(){
        menu = new ArrayList<>();
        menu.add(new Food("Pasta", "Tasty durum pasta", 3));
        menu.add(new Food("Pear", "Sweet kosa pear", 1));
        menu.add(new Food("Bread", "Crispy sourdough bread", 2));
        menu.add(new Food("The Malaysian", "Wheat noodles with green curry, broccoli, chicken strips and coconut milk.", 15));
        menu.add(new Food("Pad Thai", "Pad Thai with chicken.", 12));

    }

    @Override
    public String toString(){
        int itemNum = 1;
        StringBuilder menuToString = new StringBuilder();
        for (Food item: menu) {
            menuToString
                    .append(itemNum)
                    .append(". ")
                    .append(item.toString())
                    .append("\n");
            itemNum++;
        }
        return menuToString.toString();
    }

    public Food getFood(int index){
        try {
            return menu.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Food getLowestCostFood() throws NoSuchElementException {

        // Initialize with the first element
        Food lowestCostFoodItem = menu.getFirst();

        for (Food item : menu) {
            if (item.getPrice() < lowestCostFoodItem.getPrice()) {
                lowestCostFoodItem = item;
            }
        }
        return lowestCostFoodItem;

    }
}

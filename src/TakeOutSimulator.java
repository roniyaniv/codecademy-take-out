import java.util.Scanner;

public class TakeOutSimulator {

    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, Scanner scanner){
        this.menu = new FoodMenu();
        this.customer = customer;
        this.input = scanner;
    }


    private <T> T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever){
        while (true) {
            System.out.println(userInputPrompt);
            if (input.hasNextInt()) { // check if there's anything to grab from the input
                int userInput = input.nextInt();
                input.nextLine(); // clear the input queue
                try {
                    return intUserInputRetriever.produceOutputOnIntUserInput(userInput);
                } catch (IllegalArgumentException e) {
                    System.out.println(userInput + " is not a valid option. Please try again.\n");
                }
            }else{
                System.out.println("Input needs to be of 'int' type.\n");
                input.next();
            }
        }
    }


    public boolean shouldSimulate(){
        String userPrompt = "\nPlease enter: (1) to proceed with simulation, or (0) to EXIT the program: ";

        IntUserInputRetriever<Boolean> intUserInputRetriever = s -> {
            if (s == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
                return true;
            } else if (s == 0) {
                System.out.println("\nThank you for eating with us! Hope to see you soon!");
                return false;
            } else {
                throw new IllegalArgumentException();
            }
        };

        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public Food getMenuSelection(){
        System.out.println("\nOur menu selection:\n");
        System.out.println(menu);

        String userPrompt = "Choose a menu item (Enter the corresponding number): ";
        IntUserInputRetriever<Food> intUserInputRetriever = s -> {
          if (menu.getFood(s) != null) { // user selection exists in menu
              return menu.getFood(s);
          } else {
              throw new IllegalArgumentException();
          }
        };

        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public boolean isStillOrderingFood(){
        String userPrompt = "\nPlease enter 1 to CONTINUE SHOPPING, or 0 to CHECKOUT: ";

        IntUserInputRetriever<Boolean> intUserInputRetriever = s -> {
            if (s == 1) {
                return true;
            } else if (s == 0) {
                System.out.println("\nChecking out...\n");
                return false;
            } else {
                throw new IllegalArgumentException();
            }

        };

        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }


    public void checkoutCustomer(ShoppingBag<Food> shoppingBag){
        System.out.println("\nProcessing payment...\n");

        int remainingMoney = customer.getMoney() - shoppingBag.getTotalPrice();
        customer.setMoney(remainingMoney);

        System.out.println("Your remaining money is $" + customer.getMoney());
        System.out.println("\nThank you and enjoy your food!");

    }

    public void takeOutPrompt(){
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();
        boolean stillOrdering = true;

        while (stillOrdering) {
            System.out.println("You have $" + customerMoneyLeft + " left to spend.\n");
            Food item = this.getMenuSelection();
            if (customerMoneyLeft >= item.getPrice()) {
                customerMoneyLeft -= item.getPrice();
                shoppingBag.addItem(item);
            } else {
                System.out.println("\nOoops! Looks like you don't have enough money to buy that. Choose another item of check out.");
            }
            stillOrdering = isStillOrderingFood();
            if (!stillOrdering) {
                checkoutCustomer(shoppingBag);
            }

        }
    }

    public void startTakeOutSimulator(){
        boolean continueSim = true;

        while (continueSim){
            System.out.println("Hello, and welcome to my restaurant!");

            System.out.println("Welcome " + customer.getName() + "!");

            this.takeOutPrompt();
            continueSim = this.shouldSimulate();
        }

    }



}

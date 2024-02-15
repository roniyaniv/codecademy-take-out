import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner input = new Scanner(System.in);

        System.out.printf("Hello and welcome! Please enter your name:");
        String customerName = input.nextLine();

        int money = 0;
        boolean validInput = false;
        while (!validInput){
            System.out.printf("Please specify how much money you have (whole numbers):");
            try {
                money = input.nextInt();
                if (money <= 0) {
                    throw new InputMismatchException();
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                input.nextLine();
            }
        }

        Customer customer = new Customer(customerName, money);
        TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, input);

        takeOutSimulator.startTakeOutSimulator();

        input.close();
    }
}
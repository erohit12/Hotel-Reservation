package UI;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void run() {
        try {
            boolean countinuemenu = true;
            int mainSelection = 0;
            while (countinuemenu) {
                displayMenu();
                mainSelection = takeInput();
                goToOption(mainSelection);
                if (mainSelection == 5){
                    countinuemenu = false;
                }
            }
        } catch (Exception ex){
            ex.getLocalizedMessage();
        } finally {
            scanner.close();
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Hotel Reservation Application\n");
        System.out.println("_____________________________________________________");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("___________________________________________________");
        System.out.println("Select a number for the menu option ");
    }

    public static int takeInput() {
        int result = 0;
        boolean keepRunning = true;
            while (keepRunning) {
                try {
                    System.out.println("Your Option: ");
                    int input = Integer.parseInt(scanner.nextLine());
                    if (input < 6) {
                        result = input;
                        keepRunning = false;
                    } else {
                        System.out.println("Please enter your choice within the options (1-5).");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Please Enter a number");
                }
            }

        return result;
    }

    public static void goToOption(int x){
        switch (x){
            case 1:
                FindAndReserveARoom.run(scanner);
                break;
            case 2:
                SeeMyReservations.run(scanner);
                break;
            case 3:
                CreateAnAccount.run(scanner);
                break;
            case 4:
                Admin.run(scanner);
                break;
            case 5:
                System.out.println("Exiting");
                break;
        }
    }
}
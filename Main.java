package Java_Project;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        boolean loggedIn = false;
        int loginIndex = -1;

        while (true) {

            if (!loggedIn) {

                System.out.println("\n------ BANK MENU ------");
                System.out.println("1. Create Account");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                System.out.print("Choose option: ");
                int op = sc.nextInt();
                sc.nextLine();

                switch (op) {

                    case 1:
                        bank.createAccount(sc);
                        break;

                    case 2:
                        loginIndex = bank.login(sc);

                        if (loginIndex != -1) {
                            loggedIn = true;
                            System.out.println("Login successful.");
                        }
                        break;

                    case 3:
                        return;
                }
            }

            else {

                Account current = bank.accounts.get(loginIndex);

                System.out.println("\n------ ACCOUNT MENU ------");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. View Balance");
                System.out.println("4. Transfer Money");
                System.out.println("5. View Transactions");
                System.out.println("6. View Profile");
                System.out.println("7. Change PIN");
                System.out.println("8. Logout");
                System.out.println("9. Exit");

                System.out.print("Choose option: ");
                int op = sc.nextInt();

                switch (op) {

                    case 1:
                        bank.deposit(current, sc);
                        break;

                    case 2:
                        bank.withdraw(current, sc);
                        break;

                    case 3:
                        bank.showBalance(current);
                        break;

                    case 4:
                        bank.transfer(current, sc);
                        break;

                    case 5:
                        bank.showTransactions(current);
                        break;

                    case 6:
                        bank.showProfile(current);
                        break;

                    case 7:
                        bank.changePin(current, sc);
                        break;

                    case 8:
                        loggedIn = false;
                        loginIndex = -1;
                        System.out.println("Logged out.");
                        break;

                    case 9:
                        return;
                }
            }
        }
    }
}
import java.util.*;

public class BankProject {

    // ACCOUNT CLASS
    static class Account {

        String id;
        String name;
        int pin;
        double balance;

        Account(String id, String name, int pin) {
            this.id = id;
            this.name = name;
            this.pin = pin;
            this.balance = 0;
        }
    }
    // ARRAYLIST TO STORE ACCOUNTS
    static ArrayList<Account> accounts = new ArrayList<>();

    // ID GENERATOR
    static int idCount = 1001;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n------ BANK MENU ------");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Choose Option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {

                case 1:
                    createAccount(sc);
                    break;

                case 2:
                    login(sc);
                    break;

                case 3:
                    System.out.println("Thank You");
                    return;

                default:
                    System.out.println("Invalid Option");
            }
        }
    }
     // CREATE ACCOUNT
    static void createAccount(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter 4 Digit PIN: ");
        int pin = sc.nextInt();
        sc.nextLine();

        if (pin < 1000 || pin > 9999) {
            System.out.println("PIN must be 4 digits");
            return;
        }

        String id = "HDFC" + idCount++;

        Account acc = new Account(id, name, pin);

        accounts.add(acc);

        System.out.println("Account Created Successfully");
        System.out.println("Your Account ID: " + id);
    }
    // LOGIN
    static void login(Scanner sc) {

        System.out.print("Enter Account ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        Account current = null;

        // SEARCH ACCOUNT
        for (Account acc : accounts) {

            if (acc.id.equals(id) && acc.pin == pin) {
                current = acc;
                break;
            }
        }
        if (current == null) {
            System.out.println("Invalid ID or PIN");
            return;
        }

        System.out.println("Login Successful");

        // ACCOUNT MENU
        while (true) {

            System.out.println("\n------ ACCOUNT MENU ------");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Profile");
            System.out.println("5. Logout");

            System.out.print("Choose Option: ");
            int option = sc.nextInt();

            switch (option) {

             case 1:
                    deposit(current, sc);
                    break;

                case 2:
                    withdraw(current, sc);
                    break;

                case 3:
                    showBalance(current);
                    break;

                case 4:
                    showProfile(current);
                    break;

                case 5:
                    System.out.println("Logged Out Successfully");
                    return;

                default:
                    System.out.println("Invalid Option");
            }
        }
    }
     // DEPOSIT
    static void deposit(Account acc, Scanner sc) {

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return;
        }

        acc.balance += amount;

        System.out.println("Deposit Successful");
    }
 // WITHDRAW
    static void withdraw(Account acc, Scanner sc) {

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return;
        }

        if (amount > acc.balance) {
            System.out.println("Insufficient Balance");
            return;
        }

        acc.balance -= amount;

        System.out.println("Withdrawal Successful");
    }

    // SHOW BALANCE
    static void showBalance(Account acc) {

        System.out.println("Current Balance: " + acc.balance);
    }

    // SHOW PROFILE
    static void showProfile(Account acc) {

        System.out.println("\n------ PROFILE ------");
        System.out.println("Account ID : " + acc.id);
        System.out.println("Name       : " + acc.name);
        System.out.println("Balance    : " + acc.balance);
    }
}

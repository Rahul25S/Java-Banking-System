package Java_Project;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class BankService {

    ArrayList<Account> accounts = new ArrayList<>();
    int idCount = 1001;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String getTime() {
        return LocalDateTime.now().format(format);
    }

    int findAccount(String id) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).id.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    boolean nameExists(String name) {
        for (Account acc : accounts) {
            if (acc.name.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    void createAccount(Scanner sc) {
        
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        if (nameExists(name)) {
            System.out.println("Account with this name already exists.");
            return;
        }

        System.out.print("Enter PIN (4 digits): ");
        int pin = sc.nextInt();
        sc.nextLine();

        if (pin < 1000 || pin > 9999) {
            System.out.println("PIN must be 4 digits.");
            return;
        }

        String id = "hdfc" + idCount++;

        Account acc = new Account(id, name, pin);

        accounts.add(acc);

        System.out.println("Account created successfully!");
        System.out.println("Your ID: " + id);
    }

    int login(Scanner sc) {

        System.out.print("Enter Login ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        sc.nextLine();

        int index = findAccount(id);

        if (index == -1) {
            System.out.println("Account not found.");
            return -1;
        }

        if (accounts.get(index).pin != pin) {
            System.out.println("Wrong PIN.");
            return -1;
        }

        return index;
    }

    void deposit(Account acc, Scanner sc) {

        try {

            System.out.print("Enter amount to deposit: ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new InvalidAmountException("Amount must be positive.");
            }

            acc.balance += amt;

            acc.history.add("[" + getTime() + "] Deposited: " + amt);

            System.out.println("Deposit successful.");

        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    void withdraw(Account acc, Scanner sc) {

        try {

            System.out.print("Enter amount to withdraw: ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new InvalidAmountException("Amount must be positive.");
            }

            if (amt > acc.balance) {
                throw new InsufficientBalanceException("Insufficient balance.");
            }

            acc.balance -= amt;

            acc.history.add("[" + getTime() + "] Withdrawn: " + amt);

            System.out.println("Withdrawal successful.");

        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }

    void transfer(Account sender, Scanner sc) {

        try {

            sc.nextLine();

            System.out.print("Enter receiver account ID: ");
            String id = sc.nextLine();

            int index = findAccount(id);

            if (index == -1) {
                System.out.println("Receiver not found.");
                return;
            }

            System.out.print("Enter amount: ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new InvalidAmountException("Amount must be positive.");
            }

            if (amt > sender.balance) {
                throw new InsufficientBalanceException("Insufficient balance.");
            }

            Account receiver = accounts.get(index);

            sender.balance -= amt;
            receiver.balance += amt;

            sender.history.add("[" + getTime() + "] Sent " + amt + " to " + receiver.id);
            receiver.history.add("[" + getTime() + "] Received " + amt + " from " + sender.id);

            System.out.println("Transfer successful.");

        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }

    void showBalance(Account acc) {
        System.out.println("Current Balance: " + acc.balance);
    }

    void showTransactions(Account acc) {

        if (acc.history.size() == 0) {
            System.out.println("No transactions found.");
            return;
        }

        for (String s : acc.history) {
            System.out.println(s);
        }
    }

    void showProfile(Account acc) {

        System.out.println("------ Account Details ------");
        System.out.println("Account ID : " + acc.id);
        System.out.println("Name       : " + acc.name);
        System.out.println("Balance    : " + acc.balance);
    }

    void changePin(Account acc, Scanner sc) {

        try {

            System.out.print("Enter old PIN: ");
            int oldPin = sc.nextInt();

            if (oldPin != acc.pin) {
                throw new InvalidPinException("Incorrect old PIN.");
            }

            System.out.print("Enter new PIN: ");
            int newPin = sc.nextInt();

            if (newPin < 1000 || newPin > 9999) {
                throw new InvalidPinException("PIN must be 4 digits.");
            }

            acc.pin = newPin;

            System.out.println("PIN changed successfully.");

        } catch (InvalidPinException e) {
            System.out.println(e.getMessage());
        }
    }
}
class InvalidAmountException extends Exception {
    InvalidAmountException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidPinException extends Exception {
    InvalidPinException(String message) {
        super(message);
    }
}

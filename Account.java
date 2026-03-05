package Java_Project;
import java.util.ArrayList;

class Account {

    String id;
    String name;
    int pin;
    double balance;
    ArrayList<String> history;

    Account(String id, String name, int pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.balance = 0;
        this.history = new ArrayList<>();
    }
}
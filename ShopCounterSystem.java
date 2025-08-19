import java.util.Scanner;

public class ShopCounterSystem {
    private static Items[] inventory = new Items[100];
    private static int itemCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Shop Counter System ===");
        System.out.println("1. Admin");
        System.out.println("2. Counterperson");
        System.out.print("Choose role: ");
    }

    void addItemtoInventory(Items item) {
        if (itemCount < inventory.length) {
            inventory[itemCount] = item;
            itemCount++;
            System.out.println("Item added");
        } else {
            System.out.println("Inventory is full.");
        }
    }

    void displayInventory() {
        System.out.println("=== Inventory ===");
        if (itemCount == 0) {
            System.out.println("No items in inventory.");
            return;
        }
        System.out.println("Inventory:");
        for (int i = 0; i < itemCount; i++) {
            Items item = inventory[i];
            System.out.printf("%d. %s - $%.2f (Quantity: %d)\n", i + 1, item.getName(), item.getPrice(), item.getQuantity());
        }
    }
}

class Items {
    private String name;
    private double price;
    private int quantity;

    public Items() {
        this.name = "Unknown";
        this.price = 0.0;
        this.quantity = 0;
    }

    public Items(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    public Items(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price >=0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Quantity cannot be negative.");
        }
    }
}


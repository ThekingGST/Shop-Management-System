import java.util.Scanner;

public class ShopCounterSystem {
    private static Items[] inventory = new Items[100];
    private static int itemCount = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Shop Counter System ===");
        System.out.println("1. Admin");
        System.out.println("2. Counterperson");
        System.out.print("Choose role: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Welcome Admin!");
                while (true) {
                    new ShopCounterSystem().adminMenu();
                }
            case 2:
                System.out.println("Welcome Counterperson!");
                // Implement counterperson functionality here
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }

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


    void adminMenu() {
        System.out.println("=== Admin Menu ===");
        System.out.println("1. View Inventory");
        System.out.println("2. Update Inventory");
        System.out.println("3. Manage Counterpersons");
        System.out.println("4. Switch User");
        System.out.println("5. Exit");
        System.out.print("Enter Your Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                displayInventory();
                break;
            case 2:
                System.out.println("1. Add Item");
                System.out.println("2. Remove Item");
                System.out.println("3. Update Item");
                System.out.print("Choose an option: ");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        addItem(new Items());
                        break;
                    case 2:
                        removeItem();
                        break;
                    case 3:
                        updateItem();
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
                break;
            case 3:
                System.out.println("Implementing Soon.");
                break;
            case 4:
                System.out.println("Switching user...");
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
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

    void addItem(Items item) {
        if (itemCount >= inventory.length) {
            System.out.println("Inventory is full. Cannot add more items.");
            return;
        } else {
            System.out.print("Enter item name: ");
            String name = sc.nextLine();
            System.out.print("Enter item price: ");
            double price = sc.nextDouble();
            System.out.print("Enter item quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();
            item.setName(name);
            item.setPrice(price);
            item.setQuantity(quantity);
            addItemtoInventory(item);
        }
    }

    void removeItem()
    {
        System.out.print("Enter item number to remove: ");
        if (itemCount == 0) {
            System.out.println("No items to remove.");
            return;
        }
        int itemNumber = sc.nextInt();
        sc.nextLine();
        if (itemNumber < 1 || itemNumber > itemCount) {
            System.out.println("Invalid item number.");
            return;
        }
        for (int i = itemNumber - 1; i < itemCount - 1; i++) {
            inventory[i] = inventory[i + 1];
        }
        inventory[itemCount - 1] = null;
        itemCount--;
        System.out.println("Item removed successfully.");
    }

    void updateItem()
    {
        if (itemCount == 0) {
            System.out.println("No items to update.");
            return;
        }
        System.out.print("Enter item number to update: ");
        int itemNumber = sc.nextInt();
        sc.nextLine();
        if (itemNumber < 1 || itemNumber > itemCount) {
            System.out.println("Invalid item number.");
            return;
        }
        Items item = inventory[itemNumber - 1];
        System.out.println("1. Update Price");
        System.out.println("2. Update Quantity");
        System.out.println("3. Update Both");
        System.out.print("Choose what to update: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter new price: ");
                double newPrice = sc.nextDouble();
                sc.nextLine();
                item.setPrice(newPrice);
                break;
            case 2:
                System.out.print("Enter new quantity: ");
                int newQuantity = sc.nextInt();
                sc.nextLine();
                item.setQuantity(newQuantity);
                break;
            case 3:
                System.out.print("Enter new price: ");
                newPrice = sc.nextDouble();
                sc.nextLine();
                item.setPrice(newPrice);
                System.out.print("Enter new quantity: ");
                newQuantity = sc.nextInt();
                sc.nextLine();
                item.setQuantity(newQuantity);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
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
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Quantity cannot be negative.");
        }
    }
}


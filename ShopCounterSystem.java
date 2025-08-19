import java.util.Scanner;

public class ShopCounterSystem {
    private static Items[] inventory = new Items[100];
    private static int itemCount = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ShopCounterSystem system = new ShopCounterSystem();
        system.initializeSampleData();
        while (true) {
            system.login();
        }
    }

    void initializeSampleData() {
        addItemtoInventory(new Items("Laptop", 999.99, 5));
        addItemtoInventory(new Items("Mouse", 25.50, 10));
        addItemtoInventory(new Items("Keyboard", 75.00, 8));
        addItemtoInventory(new Items("Monitor", 299.99, 3));
    }
    void login()
    {
        System.out.println("=== Shop Counter System ===");
        System.out.println("1. Admin");
        System.out.println("2. Counterperson");
        System.out.print("Choose role: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("=== Login ===");
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                if (username.equals("admin") && password.equals("admin123")) {
                    System.out.println("Login successful!");
                    while (true) {
                        adminMenu();
                    }
                } else {
                    System.out.println("Invalid credentials. Exiting...");
                }
                break;

            case 2:
                while (true) {
                    counterPersonMenu();
                }
            default:
                System.out.println("Invalid choice. Exiting...");
        }


    }

    void addItemtoInventory(Items item) {
        if (itemCount < inventory.length) {
            inventory[itemCount] = item;
            itemCount++;
        } else {
            System.out.println("Inventory is full.");
        }
    }


    void adminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View Inventory");
        System.out.println("2. Update Inventory");
        System.out.println("3. View Low Stock Items");
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
                viewLowStockItems();
                break;
            case 4:
                System.out.println("Switching user...");
                login();
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    void displayInventory() {
        System.out.println("\n=== Inventory ===");
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

    void removeItem() {
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

    void updateItem() {
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

    void viewLowStockItems() {
        System.out.println("\n=== Low Stock Alert (Quantity <= 5) ===");
        boolean foundLowStock = false;

        for (int i = 0; i < itemCount; i++) {
            if (inventory[i].getQuantity() <= 5) {
                System.out.printf("%d. %s - Quantity: %d\n", i + 1, inventory[i].getName(), inventory[i].getQuantity());
                foundLowStock = true;
            }
        }
        if (!foundLowStock) {
            System.out.println("No low stock items found.");
        }
    }

    void counterPersonMenu() {
        System.out.println("\n=== Counterperson Menu ===");
        System.out.println("1. Start Billing");
        System.out.println("2. Switch User");
        System.out.println("3. Exit");
        System.out.print("Enter Your Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                billProcess();
                break;
            case 2:
                System.out.println("Switching user...");
                login();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }

    void billProcess() {
        Items[] broughtItems = new Items[100];
        int broughtItemCount = 0;
        double total = 0.0;

        System.out.println("\n=== Welcome to Mega Mart ===");
        System.out.println("Please select items to purchase:");
        displayInventory();
        System.out.print("Enter item number to purchase (0 to finish): ");
        int itemNumber = sc.nextInt();
        sc.nextLine();
        while (itemNumber != 0) {
            if (itemNumber < 1 || itemNumber > itemCount) {
                System.out.println("Invalid item number. Please try again.");
            } else {
                Items selectedItem = inventory[itemNumber - 1];
                System.out.print("Enter quantity for " + selectedItem.getName() + ": ");
                int quantity = sc.nextInt();
                sc.nextLine();
                if (quantity > selectedItem.getQuantity()) {
                    System.out.println("Insufficient stock for " + selectedItem.getName() + ".");
                } else {
                    selectedItem.setQuantity(selectedItem.getQuantity() - quantity);
                    double itemTotal = selectedItem.getPrice() * quantity;
                    total += itemTotal;
                    broughtItems[broughtItemCount++] = new Items(selectedItem.getName(), selectedItem.getPrice(), quantity);
                    System.out.println("Added " + quantity + " of " + selectedItem.getName() + " to your cart.");
                }
            }
            System.out.print("Enter item number to purchase (0 to finish): ");
            itemNumber = sc.nextInt();
            sc.nextLine();
            if (itemNumber == 0) {
                takeMoney(total);
                printReceipt(broughtItems, broughtItemCount, total);
                break;
            }
        }


    }

    void printReceipt(Items[] items, int count, double total) {
        System.out.println("\n=== Receipt ===");
        for (int i = 0; i < count; i++) {
            Items item = items[i];
            System.out.printf("%s - $%.2f x %d = $%.2f\n", item.getName(), item.getPrice(), item.getQuantity(), item.getPrice() * item.getQuantity());
        }
        System.out.printf("Total: $%.2f\n", total);
        System.out.println("Thank you for shopping with us!");
    }

    void takeMoney(double total) {
        System.out.println("\n=== Cash Mode ===");
        System.out.println("Select Payment Method:");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. Card");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Please pay the total amount of $" + total);
                System.out.println("Please enter the amount paid: ");
                double amountPaid = sc.nextDouble();
                sc.nextLine();
                if (amountPaid < total) {
                    System.out.println("Insufficient amount paid. Payment failed.");
                    return;
                } else if (amountPaid >= total) {
                    double change = amountPaid - total;
                    System.out.printf("Return change $%.2f\n", change);
                }
                System.out.println("Payment successful. Thank you!");
                break;
            case 2:
                System.out.println("Please complete your UPI payment of $" + total);
                System.out.println("Payment successful. Thank you!");
                break;
            case 3:
                System.out.println("Please swipe your card to pay $" + total);
                System.out.println("Payment successful. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Payment failed.");
        }
        System.out.println("Exiting billing process...");


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
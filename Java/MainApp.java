import java.util.*;

class Customer {
    private String name;
    private String email;
    private String password;
    private String address;
    private String contactNumber;
    private int customerId;

    public Customer(String name, String email, String password, String address, String contactNumber, int customerId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void displayRegistrationSuccess() {
        System.out.println("Customer Registration is successful for " + customerId);
    }

    public void displayUpdateSuccess() {
        System.out.println("Your Details updated successfully");
    }

    public void displayCustomerDetails() {
        System.out.println("\nCustomer Details:");
        System.out.println("Customer ID    : " + customerId);
        System.out.println("Name           : " + name);
        System.out.println("Email          : " + email);
        System.out.println("Address        : " + address);
        System.out.println("Contact Number : " + contactNumber);
    }
}

class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private int quantity;
    public double price;

    public Product(String productId, String productName, String productDescription, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateDetails(String desc, int qty, double price) {
        this.productDescription = desc;
        this.quantity = qty;
        this.price = price;
    }

    public void displayAddSuccess() {
        System.out.println("Product added successfully");
    }

    public void displayUpdateSuccess() {
        System.out.println("Product updated successfully");
    }

    public void displayProduct() {
        System.out.println("\nProduct Details:");
        System.out.println("ID       : " + productId);
        System.out.println("Name     : " + productName);
        System.out.println("Desc     : " + productDescription);
        System.out.println("Quantity : " + quantity);
        System.out.println("Price    : " + price);
    }
}

public class MainApp {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    static Customer[] customers = new Customer[100];
    static Product[] products = new Product[100];
    static int customerCount = 0;
    static int productCount = 0;
    static Set<String> usedEmails = new HashSet<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Register Customer (US001)");
            System.out.println("2. Update Customer (US002)");
            System.out.println("3. Search Customer by Email (US004)");
            System.out.println("4. Add Product (US003)");
            System.out.println("5. Update Product (US003)");
            System.out.println("6. View All Products");
            System.out.println("7. Find Costliest Product (US005)");
            System.out.println("8. Sort Products by Quantity (US006)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: registerCustomer(); break;
                case 2: updateCustomer(); break;
                case 3: searchCustomerByEmail(); break;
                case 4: addProduct(); break;
                case 5: updateProduct(); break;
                case 6: viewAllProducts(); break;
                case 7: findCostliestProduct(); break;
                case 8: sortProductsByQuantity(); break;
                case 9: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // US001 - Register Customer
    public static void registerCustomer() {
        System.out.println("\n--- Register Customer ---");
        String name, email, password, address, contactNumber;

        while (true) {
            System.out.print("Enter Name (max 50): ");
            name = sc.nextLine();
            if (name.length() <= 50) break;
        }

        while (true) {
            System.out.print("Enter Unique Email: ");
            email = sc.nextLine();
            if (!usedEmails.contains(email)) {
                usedEmails.add(email); break;
            }
            System.out.println("Email already exists.");
        }

        while (true) {
            System.out.print("Enter Password (6-12): ");
            password = sc.nextLine();
            if (password.length() >= 6 && password.length() <= 12) break;
        }

        while (true) {
            System.out.print("Enter Address (max 100): ");
            address = sc.nextLine();
            if (address.length() <= 100) break;
        }

        while (true) {
            System.out.print("Enter Contact Number (10 digits): ");
            contactNumber = sc.nextLine();
            if (contactNumber.matches("\\d{10}")) break;
        }

        int id = 10000 + rand.nextInt(90000);
        Customer c = new Customer(name, email, password, address, contactNumber, id);
        customers[customerCount++] = c;
        c.displayRegistrationSuccess();
    }

    // US002 - Update Customer
    public static void updateCustomer() {
        System.out.print("Enter Email to update: ");
        String email = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getEmail().equalsIgnoreCase(email)) {
                found = true;
                System.out.print("New Name: ");
                customers[i].setName(sc.nextLine());
                System.out.print("New Password: ");
                customers[i].setPassword(sc.nextLine());
                System.out.print("New Address: ");
                customers[i].setAddress(sc.nextLine());
                System.out.print("New Contact Number: ");
                customers[i].setContactNumber(sc.nextLine());
                customers[i].displayUpdateSuccess();
                break;
            }
        }
        if (!found) System.out.println("No Such Customer Exist with the Given Email");
    }

    // US004 - Search Customer by Email
    public static void searchCustomerByEmail() {
        System.out.print("Enter Email to search: ");
        String email = sc.nextLine();
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getEmail().equalsIgnoreCase(email)) {
                customers[i].displayCustomerDetails();
                return;
            }
        }
        System.out.println("No Such Customer Exist with the Given Email");
    }

    // US003 - Add Product
    public static void addProduct() {
        System.out.println("\n--- Add Product ---");
        String name, desc;
        int qty;
        double price;

        while (true) {
            System.out.print("Enter Product Name (max 50): ");
            name = sc.nextLine();
            if (name.length() <= 50) break;
        }

        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductName().equalsIgnoreCase(name)) {
                System.out.println("Product already exists. Use update.");
                return;
            }
        }

        while (true) {
            System.out.print("Enter Description (max 100): ");
            desc = sc.nextLine();
            if (desc.length() <= 100) break;
        }

        System.out.print("Enter Quantity: ");
        qty = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Price: ");
        price = Double.parseDouble(sc.nextLine());

        String pid = generateProductId();
        Product p = new Product(pid, name, desc, qty, price);
        products[productCount++] = p;
        p.displayAddSuccess();
    }

    // US003 - Update Product
    public static void updateProduct() {
        System.out.print("Enter Product Name to update: ");
        String name = sc.nextLine();
        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductName().equalsIgnoreCase(name)) {
                System.out.print("New Description: ");
                String desc = sc.nextLine();
                System.out.print("New Quantity: ");
                int qty = Integer.parseInt(sc.nextLine());
                System.out.print("New Price: ");
                double price = Double.parseDouble(sc.nextLine());
                products[i].updateDetails(desc, qty, price);
                products[i].displayUpdateSuccess();
                return;
            }
        }
        System.out.println("Product not found.");
    }

    // View all products
    public static void viewAllProducts() {
        if (productCount == 0) {
            System.out.println("No products found.");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            products[i].displayProduct();
        }
    }

    // US005 - Find Costliest Product
    public static void findCostliestProduct() {
        if (productCount == 0) {
            System.out.println("Product List is Empty");
            return;
        }
        Product max = products[0];
        for (int i = 1; i < productCount; i++) {
            if (products[i].price > max.price) {
                max = products[i];
            }
        }
        System.out.println("\n--- Costliest Product ---");
        max.displayProduct();
    }

    // US006 - Sort Products by Quantity
    public static void sortProductsByQuantity() {
        if (productCount == 0) {
            System.out.println("Product List is Empty");
            return;
        }
        Product[] sorted = Arrays.copyOf(products, productCount);
        Arrays.sort(sorted, Comparator.comparingInt(Product::getQuantity));
        System.out.println("\n--- Products Sorted by Quantity ---");
        for (Product p : sorted) {
            p.displayProduct();
        }
    }

    public static String generateProductId() {
        return (rand.nextInt(9) + 1) + "-" + (1000 + rand.nextInt(9000)) + "-" +
               (1000 + rand.nextInt(9000)) + "-" + rand.nextInt(10);
    }
}

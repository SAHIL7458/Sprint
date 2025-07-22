import java.util.*;
import java.util.regex.*;

class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String contactNumber;

    public Customer(String customerId, String fullName, String email, String password, String address, String contactNumber) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setAddress(String address) { this.address = address; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}

class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;

    public Product(String productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setProductName(String productName) { this.productName = productName; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

public class OnlineGroceryApp {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Customer> customers = new HashMap<>();
    static Map<String, Product> products = new HashMap<>();
    static String adminUsername = "admin";
    static String adminPassword = "admin123";

    public static void main(String[] args) {
        if (login()) {
            while (true) {
                showMenu();
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline
                switch (choice) {
                    case 1 -> customerRegistration();
                    case 2 -> updateCustomerDetails();
                    case 3 -> getCustomerOrderDetails();
                    case 4 -> searchCustomerByName();
                    case 5 -> searchProductByName();
                    case 6 -> productRegistration();
                    case 7 -> updateProduct();
                    case 8 -> deleteProduct();
                    case 9 -> {
                        System.out.println("Good Bye User!!. Terminating the Program.");
                        return;
                    }
                    default -> System.out.println("You have selected an inappropriate option. Kindly select an appropriate option.");
                }
            }
        } else {
            System.out.println("Please Enter Correct UserName and Password");
        }
    }

    static boolean login() {
        System.out.print("Enter Username: ");
        String uname = sc.nextLine();
        System.out.print("Enter Password: ");
        String pwd = sc.nextLine();
        return uname.equals(adminUsername) && pwd.equals(adminPassword);
    }

    static void showMenu() {
        System.out.println("""
                \n----- MENU -----
                1) Customer Registration
                2) Update Customer Details
                3) Get Customer Order Details
                4) Customer Search
                5) Product Search
                6) Register Product
                7) Update Product
                8) Delete Product
                9) Exit
                Enter your choice:
                """);
    }

    static void customerRegistration() {
        System.out.print("Full Name: ");
        String name = sc.nextLine();

        String email;
        while (true) {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") || emailExists(email)) {
                System.out.println("Invalid or duplicate email. Try again.");
            } else break;
        }

        String password;
        while (true) {
            System.out.print("Password: ");
            password = sc.nextLine();
            if (!isValidPassword(password)) {
                System.out.println("Password must be 8+ chars, upper/lower/special.");
            } else break;
        }

        System.out.print("Address: ");
        String address = sc.nextLine();

        String contact;
        while (true) {
            System.out.print("Contact Number: ");
            contact = sc.nextLine();
            if (!contact.matches("\\d{10}")) {
                System.out.println("Contact must be exactly 10 digits.");
            } else break;
        }

        String id = generateCustomerId();
        Customer customer = new Customer(id, name, email, password, address, contact);
        customers.put(id, customer);
        System.out.println("Customer registered with ID: " + id);
    }

    static void updateCustomerDetails() {
        System.out.print("Enter Customer ID to update: ");
        String id = sc.nextLine();
        Customer c = customers.get(id);
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("""
                What do you want to update?
                1) Name
                2) Email
                3) Password
                4) Address
                5) Contact Number
                """);
        int ch = sc.nextInt(); sc.nextLine();

        switch (ch) {
            case 1 -> {
                System.out.print("New Name: ");
                c.setFullName(sc.nextLine());
            }
            case 2 -> {
                while (true) {
                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();
                    if (!newEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") || emailExists(newEmail)) {
                        System.out.println("Invalid or duplicate email.");
                    } else {
                        c.setEmail(newEmail);
                        break;
                    }
                }
            }
            case 3 -> {
                while (true) {
                    System.out.print("New Password: ");
                    String newPass = sc.nextLine();
                    if (!isValidPassword(newPass)) {
                        System.out.println("Invalid password.");
                    } else {
                        c.setPassword(newPass);
                        break;
                    }
                }
            }
            case 4 -> {
                System.out.print("New Address: ");
                c.setAddress(sc.nextLine());
            }
            case 5 -> {
                while (true) {
                    System.out.print("New Contact: ");
                    String newCon = sc.nextLine();
                    if (!newCon.matches("\\d{10}")) {
                        System.out.println("Invalid contact.");
                    } else {
                        c.setContactNumber(newCon);
                        break;
                    }
                }
            }
            default -> System.out.println("Invalid choice.");
        }

        System.out.println("Customer details updated.");
    }

    static void getCustomerOrderDetails() {
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();
        if (!customers.containsKey(id)) {
            System.out.println("Invalid Customer ID.");
            return;
        }
        System.out.println("Order history for Customer ID: " + id + " (feature not implemented yet)");
    }

    static void searchCustomerByName() {
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Customer c : customers.values()) {
            if (c.getFullName().toLowerCase().contains(name)) {
                System.out.println("ID: " + c.getCustomerId() + ", Name: " + c.getFullName() +
                        ", Email: " + maskEmail(c.getEmail()) + ", Address: " + c.getAddress() +
                        ", Contact: " + c.getContactNumber());
                found = true;
            }
        }

        if (!found) System.out.println("Customer not found.");
    }

    static void searchProductByName() {
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Product p : products.values()) {
            if (p.getProductName().toLowerCase().contains(name)) {
                System.out.println("Product: " + p.getProductName() + ", Price: " + p.getPrice() +
                        ", Quantity: " + p.getQuantity());
                found = true;
            }
        }

        if (!found) System.out.println("Product not found.");
    }

    static void productRegistration() {
        System.out.print("Enter Product ID: ");
        String pid = sc.nextLine();
        if (products.containsKey(pid)) {
            System.out.println("Product ID already exists.");
            return;
        }

        System.out.print("Product Name: ");
        String name = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Quantity: ");
        int qty = sc.nextInt(); sc.nextLine();

        if (qty < 0 || price < 0) {
            System.out.println("Price or quantity cannot be negative.");
            return;
        }

        Product p = new Product(pid, name, price, qty);
        products.put(pid, p);
        System.out.println("Product registered.");
    }

    static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        String pid = sc.nextLine();
        Product p = products.get(pid);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("New Name: ");
        p.setProductName(sc.nextLine());

        System.out.print("New Price: ");
        p.setPrice(sc.nextDouble());

        System.out.print("New Quantity: ");
        p.setQuantity(sc.nextInt()); sc.nextLine();

        System.out.println("Product updated.");
    }

    static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        String pid = sc.nextLine();
        if (products.remove(pid) != null)
            System.out.println("Product deleted.");
        else
            System.out.println("Product not found.");
    }

    // Helper methods
    static String generateCustomerId() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    static boolean emailExists(String email) {
        return customers.values().stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
    }

    static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\W.*");
    }

    static String maskEmail(String email) {
        int at = email.indexOf("@");
        if (at <= 1) return "***@***";
        return email.substring(0, 1) + "*****" + email.substring(at);
    }
}

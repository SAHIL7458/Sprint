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

    public int getCustomerId() {
        return customerId;
    }

    public void displaySuccess() {
        System.out.println("Customer Registration is successful for " + customerId);
    }
}

public class CustomerRegistration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer[] customers = new Customer[100];  // Assuming max 100 registrations
        int count = 0;

        System.out.print("Enter number of customers to register: ");
        int total = Integer.parseInt(sc.nextLine());

        Set<String> usedEmails = new HashSet<>();  // To ensure unique emails
        Random rand = new Random();

        for (int i = 0; i < total; i++) {
            System.out.println("\nRegistering Customer " + (i + 1));
            String name, email, password, address, contactNumber;

            // Name input
            while (true) {
                System.out.print("Enter Customer Name (Max 50 characters): ");
                name = sc.nextLine();
                if (name.length() <= 50) break;
                System.out.println("Name too long. Try again.");
            }

            // Email input
            while (true) {
                System.out.print("Enter Email (Unique): ");
                email = sc.nextLine();
                if (!usedEmails.contains(email)) {
                    usedEmails.add(email);
                    break;
                }
                System.out.println("Email already exists. Try another.");
            }

            // Password input
            while (true) {
                System.out.print("Enter Password (6-12 characters): ");
                password = sc.nextLine();
                if (password.length() >= 6 && password.length() <= 12) break;
                System.out.println("Invalid password length. Try again.");
            }

            // Address input
            while (true) {
                System.out.print("Enter Address (Max 100 characters): ");
                address = sc.nextLine();
                if (address.length() <= 100) break;
                System.out.println("Address too long. Try again.");
            }

            // Contact number input
            while (true) {
                System.out.print("Enter Contact Number (10 digits): ");
                contactNumber = sc.nextLine();
                if (contactNumber.length() == 10 && contactNumber.matches("\\d+")) break;
                System.out.println("Invalid contact number. Must be 10 digits.");
            }

            // Generate random 5-digit Customer ID
            int customerId = 10000 + rand.nextInt(90000);

            // Create and store customer
            Customer newCustomer = new Customer(name, email, password, address, contactNumber, customerId);
            customers[count++] = newCustomer;
            newCustomer.displaySuccess();
        }

        sc.close();
    }
}

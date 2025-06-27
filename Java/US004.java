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

public class CustomerManagement {
    static Customer[] customers = new Customer[100];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);
    static Set<String> usedEmails = new HashSet<>();
    static Random rand = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- Customer Management Menu ----");
            System.out.println("1. Register Customer");
            System.out.println("2. Update Customer Details");
            System.out.println("3. Search Customer by Email");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: registerCustomer(); break;
                case 2: updateCustomer(); break;
                case 3: searchCustomerByEmail(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    public static void registerCustomer() {
        System.out.println("\nRegistering Customer");
        String name, email, password, address, contactNumber;

        while (true) {
            System.out.print("Enter Customer Name (Max 50 characters): ");
            name = sc.nextLine();
            if (name.length() <= 50) break;
            System.out.println("Name too long. Try again.");
        }

        while (true) {
            System.out.print("Enter Email (Unique): ");
            email = sc.nextLine();
            if (!usedEmails.contains(email)) {
                usedEmails.add(email);
                break;
            }
            System.out.println("Email already exists. Try another.");
        }

        while (true) {
            System.out.print("Enter Password (6-12 characters): ");
            password = sc.nextLine();
            if (password.length() >= 6 && password.length() <= 12) break;
            System.out.println("Invalid password length. Try again.");
        }

        while (true) {
            System.out.print("Enter Address (Max 100 characters): ");
            address = sc.nextLine();
            if (address.length() <= 100) break;
            System.out.println("Address too long. Try again.");
        }

        while (true) {
            System.out.print("Enter Contact Number (10 digits): ");
            contactNumber = sc.nextLine();
            if (contactNumber.length() == 10 && contactNumber.matches("\\d+")) break;
            System.out.println("Invalid contact number. Must be 10 digits.");
        }

        int customerId = 10000 + rand.nextInt(90000);
        Customer newCustomer = new Customer(name, email, password, address, contactNumber, customerId);
        customers[count++] = newCustomer;
        newCustomer.displayRegistrationSuccess();
    }

    public static void updateCustomer() {
        System.out.print("\nEnter your registered Email to update details: ");
        String emailToUpdate = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (customers[i].getEmail().equalsIgnoreCase(emailToUpdate)) {
                found = true;

                String name, password, address, contactNumber;

                while (true) {
                    System.out.print("Enter New Customer Name (Max 50 characters): ");
                    name = sc.nextLine();
                    if (name.length() <= 50) break;
                    System.out.println("Name too long. Try again.");
                }

                while (true) {
                    System.out.print("Enter New Password (6-12 characters): ");
                    password = sc.nextLine();
                    if (password.length() >= 6 && password.length() <= 12) break;
                    System.out.println("Invalid password length. Try again.");
                }

                while (true) {
                    System.out.print("Enter New Address (Max 100 characters): ");
                    address = sc.nextLine();
                    if (address.length() <= 100) break;
                    System.out.println("Address too long. Try again.");
                }

                while (true) {
                    System.out.print("Enter New Contact Number (10 digits): ");
                    contactNumber = sc.nextLine();
                    if (contactNumber.length() == 10 && contactNumber.matches("\\d+")) break;
                    System.out.println("Invalid contact number. Must be 10 digits.");
                }

                customers[i].setName(name);
                customers[i].setPassword(password);
                customers[i].setAddress(address);
                customers[i].setContactNumber(contactNumber);
                customers[i].displayUpdateSuccess();
                break;
            }
        }

        if (!found) {
            System.out.println("No Such Customer Exist with the Given Email");
        }
    }

    public static void searchCustomerByEmail() {
        System.out.print("\nEnter Email to search: ");
        String emailSearch = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (customers[i].getEmail().equalsIgnoreCase(emailSearch)) {
                customers[i].displayCustomerDetails();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No Such Customer Exist with the Given Email");
        }
    }
}

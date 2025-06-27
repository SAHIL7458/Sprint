import java.util.*;

class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private int quantity;
    public double price;  // For internal comparison

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

public class ProductManagement {
    static Product[] products = new Product[100];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- Product Management Menu ----");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. View All Products");
            System.out.println("4. Find Costliest Product");
            System.out.println("5. Sort Products by Quantity");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: addProduct(); break;
                case 2: updateProduct(); break;
                case 3: viewProducts(); break;
                case 4: findHighestPricedProduct(); break;
                case 5: sortProductsByQuantity(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    public static String generateProductId() {
        int part1 = rand.nextInt(9) + 1; // 1-9
        int part2 = 1000 + rand.nextInt(9000);
        int part3 = 1000 + rand.nextInt(9000);
        int part4 = rand.nextInt(10);
        return part1 + "-" + part2 + "-" + part3 + "-" + part4;
    }

    public static void addProduct() {
        System.out.println("\n--- Add New Product ---");
        String name, desc;
        int qty;
        double price;

        // Product Name
        while (true) {
            System.out.print("Enter Product Name (Max 50 characters): ");
            name = sc.nextLine();
            if (name.length() <= 50) break;
            System.out.println("Name too long. Try again.");
        }

        // Check for duplicate
        for (int i = 0; i < count; i++) {
            if (products[i].getProductName().equalsIgnoreCase(name)) {
                System.out.println("Product already exists. Use Update option.");
                return;
            }
        }

        // Description
        while (true) {
            System.out.print("Enter Product Description (Max 100 characters): ");
            desc = sc.nextLine();
            if (desc.length() <= 100) break;
            System.out.println("Description too long. Try again.");
        }

        // Quantity
        while (true) {
            System.out.print("Enter Available Quantity: ");
            try {
                qty = Integer.parseInt(sc.nextLine());
                if (qty >= 0) break;
            } catch (Exception e) {}
            System.out.println("Invalid quantity. Try again.");
        }

        // Price
        while (true) {
            System.out.print("Enter Price (up to 2 decimal places): ");
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price >= 0) break;
            } catch (Exception e) {}
            System.out.println("Invalid price. Try again.");
        }

        String productId = generateProductId();
        Product p = new Product(productId, name, desc, qty, price);
        products[count++] = p;
        p.displayAddSuccess();
    }

    public static void updateProduct() {
        System.out.print("\nEnter Product Name to update: ");
        String name = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (products[i].getProductName().equalsIgnoreCase(name)) {
                String desc;
                int qty;
                double price;

                // Description
                while (true) {
                    System.out.print("Enter New Product Description (Max 100 characters): ");
                    desc = sc.nextLine();
                    if (desc.length() <= 100) break;
                    System.out.println("Description too long. Try again.");
                }

                // Quantity
                while (true) {
                    System.out.print("Enter New Quantity: ");
                    try {
                        qty = Integer.parseInt(sc.nextLine());
                        if (qty >= 0) break;
                    } catch (Exception e) {}
                    System.out.println("Invalid quantity.");
                }

                // Price
                while (true) {
                    System.out.print("Enter New Price: ");
                    try {
                        price = Double.parseDouble(sc.nextLine());
                        if (price >= 0) break;
                    } catch (Exception e) {}
                    System.out.println("Invalid price.");
                }

                products[i].updateDetails(desc, qty, price);
                products[i].displayUpdateSuccess();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public static void viewProducts() {
        System.out.println("\n--- All Products ---");
        if (count == 0) {
            System.out.println("No products found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            products[i].displayProduct();
        }
    }

    public static void findHighestPricedProduct() {
        if (count == 0) {
            System.out.println("Product List is Empty");
            return;
        }

        Product costliest = products[0];
        for (int i = 1; i < count; i++) {
            if (products[i].price > costliest.price) {
                costliest = products[i];
            }
        }

        System.out.println("\n--- Costliest Product ---");
        costliest.displayProduct();
    }

    public static void sortProductsByQuantity() {
        if (count == 0) {
            System.out.println("Product List is Empty");
            return;
        }

        // Copy products to new array for sorting
        Product[] sorted = Arrays.copyOf(products, count);

        // Sort by quantity in ascending order
        Arrays.sort(sorted, Comparator.comparingInt(Product::getQuantity));

        System.out.println("\n--- Products Sorted by Quantity (Ascending) ---");
        for (Product p : sorted) {
            p.displayProduct();
        }
    }
}

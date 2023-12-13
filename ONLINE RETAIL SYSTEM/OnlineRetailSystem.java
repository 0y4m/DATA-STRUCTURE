import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class User {
    private int userID;
    private String username;
    private String email;

    public User(int userID, String username, String email) {
        this.userID = userID;
        this.username = username;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void login() {
        System.out.println("User logged in.");
        System.out.println("<==========================================>");
    }

    public void logout() {
        System.out.println("User logged out.");
        System.out.println("<==========================================>");
    }
}
class Admin extends User {
    private int adminID;
    private String department;

    public Admin(int userID, String username, String email, int adminID, String department) {
        super(userID, username, email);
        this.adminID = adminID;
        this.department = department;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addProduct() {
        System.out.println("Product added.");
    }

    public void removeProduct() {
        System.out.println("Product removed.");
    }

    public void manageInventory() {
        System.out.println("Inventory managed.");
    }
}


class Customer extends User {
    private int customerID;
    private String address;
    private List<Order> orders;

    public Customer(int userID, String username, String email, int customerID, String address) {
        super(userID, username, email);
        this.customerID = customerID;
        this.address = address;
        this.orders = new ArrayList<>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void placeOrder() {
        Order newOrder = new Order(customerID);
        orders.add(newOrder);
        System.out.println("Order placed");
        System.out.println("<==========================================>");
    }

    public void viewOrderHistory() {
        System.out.println("Order History:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("Order Status: " + order.getOrderStatus());
            displayOrderProducts(order.getProducts());
            System.out.println();
        }
    }

    public void addProductToOrder(int orderIndex, Product product) {
        if (orderIndex >= 0 && orderIndex < orders.size()) {
            Order order = orders.get(orderIndex);
            order.addProduct(product);
            System.out.println("Product added to the order.");
        } else {
            System.out.println("<==========================================>");
            System.out.println("Invalid order index");
            System.out.println("<==========================================>");
        }
    }

    public void viewCurrentOrder(int orderIndex) {
        if (orderIndex >= 0 && orderIndex < orders.size()) {
            Order order = orders.get(orderIndex);
            System.out.println("Current Order:");
            displayOrderProducts(order.getProducts());
            System.out.println("Total Amount: $" + order.getTotalAmount());
        } else {
            System.out.println("<==========================================>");
            System.out.println("Invalid order index");
            System.out.println("<==========================================>");
        }
    }

    private static void displayOrderProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Product Price: $" + product.getPrice());
            System.out.println();
        }
    }
}


class Product {
    private int productID;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productID, String name, double price, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void updatePrice(double newPrice) {
        System.out.println("Price updated");
        System.out.println("<==========================================>");
    }

    public void updateStock(int newStockQuantity) {
        System.out.println("Stock quantity updated");
        System.out.println("<==========================================>");
    }
}

class Order {
    private int orderID;
    private int customerID;
    private List<Product> products;
    private double totalAmount;
    private Date orderDate;
    private String orderStatus;

    public Order(int customerID) {
        this.orderID = generateOrderID();
        this.customerID = customerID;
        this.products = new ArrayList<>();
        this.totalAmount = 0;
        this.orderDate = new Date();
        this.orderStatus = "Pending";
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void calculateTotalAmount() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        this.totalAmount = total;
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
            calculateTotalAmount();
        }
    }

    private int generateOrderID() {
        return (int) (Math.random() * 10000);
    }
}

public class OnlineRetailSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "PERFUME", 50.29, 2250));
        products.add(new Product(2, "DEODORANT", 65.17, 1250));
        products.add(new Product(3, "TIDE", 35.12, 2501));
        products.add(new Product(4, "DOVE", 45.36, 2502));
        products.add(new Product(5, "BEARBRAND", 350.50, 1250));
        products.add(new Product(6, "SHOES", 799.89, 2510));
        products.add(new Product(7, "TOYS", 105.35, 2530));
        products.add(new Product(8, "JEWELRIES", 999.00, 2350));
        products.add(new Product(9, "ALCOHOL", 225.31, 2950));
        products.add(new Product(10, "PHONE", 25000.00, 2950));


        System.out.println("<===WELCOME TO THE ONLINE RETAIL SYSTEM!===>");

        System.out.println("<==========================================>");
        System.out.println("              SELECT YOUR ROLE");
        System.out.println("<==========================================>");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        int roleChoice = scanner.nextInt();

        if (roleChoice == 1) { 
            System.out.println("<==========================================>");
            System.out.println("            Enter customer ID");
            int customerID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("<==========================================>");
            System.out.println("          Enter customer username");
            String customerUsername = scanner.nextLine();
            System.out.println("<==========================================>");
            System.out.println("           Enter customer email");
            String customerEmail = scanner.nextLine();
            System.out.println("<==========================================>");
            System.out.println("          Enter customer address");
            String customerAddress = scanner.nextLine();
            Customer customer = new Customer(customerID, customerUsername, customerEmail, customerID, customerAddress);
            System.out.println("<==========================================>");

            while (true) {
                System.out.println("<==========================================>");
                System.out.println("CUSTOMER ACTIONS");
                System.out.println("1. Place Order");
                System.out.println("2. View Order History");
                System.out.println("3. View Products");
                System.out.println("4. View Current Order");
                System.out.println("5. Add Product to Current Order");
                System.out.println("6. Logout");
                int customerChoice = scanner.nextInt();
                System.out.println("<==========================================>");

                if (customerChoice == 1) {
                    customer.placeOrder();
                } else if (customerChoice == 2) {
                    customer.viewOrderHistory();
                } else if (customerChoice == 3) {
                    System.out.println("Available Products:");
                    displayProducts(products);
                } else if (customerChoice == 4) {
                    System.out.println("Enter the order index:");
                    int orderIndex = scanner.nextInt();
                    customer.viewCurrentOrder(orderIndex);
                } else if (customerChoice == 5) {
                    System.out.println("Enter the order index:");
                    int orderIndex = scanner.nextInt();
                } else if (customerChoice == 6) {
                    System.out.println("Enter the order index:");
                    int orderIndex = scanner.nextInt();
                } else if (customerChoice == 7) {
                    System.out.println("Enter the order index:");
                    int orderIndex = scanner.nextInt();
                } else if (customerChoice == 8) {
                    System.out.println("Enter the order index:");
                    int orderIndex = scanner.nextInt();
                } else if (customerChoice == 9) {
                    System.out.println("Enter the order index:");
                    int orderIndex = scanner.nextInt();
                } else if (customerChoice == 10) {
                    System.out.println("Enter the order index:");
                    int orderIndex = scanner.nextInt();
                    System.out.println("Enter the product ID to add:");
                    int productIDToAdd = scanner.nextInt();
                    Product productToAdd = getProductById(products, productIDToAdd);
                    if (productToAdd != null) {
                        customer.addProductToOrder(orderIndex, productToAdd);
                    } else {
                        System.out.println("<==========================================>");
                        System.out.println("Product not found");
                        System.out.println("<==========================================>");
                    }
                } else if (customerChoice == 6) {
                    customer.logout();
                    break;
                } else {
                    System.out.println("<==========================================>");
                    System.out.println("Invalid choice");
                    System.out.println("<==========================================>");
                }
            }
        } else if (roleChoice == 2) {
            System.out.println("<==========================================>");
            System.out.println("             Enter admin ID");
            int adminID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("<==========================================>");
            System.out.println("          Enter admin username");
            String adminUsername = scanner.nextLine();
            System.out.println("<==========================================>");
            System.out.println("            Enter admin email");
            String adminEmail = scanner.nextLine();
            System.out.println("<==========================================>");
            System.out.println("          Enter admin department");
            String adminDepartment = scanner.nextLine();
            Admin admin = new Admin(adminID, adminUsername, adminEmail, adminID, adminDepartment);
            System.out.println("<==========================================>");

            while (true) {
                System.out.println("<==========================================>");
                System.out.println("ADMIN ACTIONS");
                System.out.println("1. Add Product");
                System.out.println("2. Remove Product");
                System.out.println("3. Manage Inventory");
                System.out.println("4. Logout");
                int adminChoice = scanner.nextInt();
                System.out.println("<==========================================>");

                if (adminChoice == 1) {
                    Product newProduct = addProduct(scanner);
                    products.add(newProduct);
                    System.out.println("<==========================================>");
                    System.out.println("Product added");
                    System.out.println("<==========================================>");
                } else if (adminChoice == 2) {
                    System.out.println("Enter the product ID to remove:");
                    int productIDToRemove = scanner.nextInt();
                    removeProduct(products, productIDToRemove);
                } else if (adminChoice == 3) {
                } else if (adminChoice == 4) {
                    admin.logout();
                    break;
                } else {
                    System.out.println("<==========================================>");
                    System.out.println("Invalid choice");
                    System.out.println("<==========================================>");
                }
            }
        } else {
            System.out.println("<==========================================>");
            System.out.println("Invalid role selection");
            System.out.println("<==========================================>");
        }

        scanner.close();
    }

    private static void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Product Price: $" + product.getPrice());
            System.out.println("Product Stock Quantity: " + product.getStockQuantity());
            System.out.println();
        }
    }

    private static Product addProduct(Scanner scanner) {
        System.out.println("Enter Product ID:");
        int productID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Product Name:");
        String productName = scanner.nextLine();
        System.out.println("Enter Product Price:");
        double productPrice = scanner.nextDouble();
        System.out.println("Enter Product Stock Quantity:");
        int stockQuantity = scanner.nextInt();
        return new Product(productID, productName, productPrice, stockQuantity);
    }

    private static void removeProduct(List<Product> products, int productID) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == productID) {
                products.remove(i);
                System.out.println("<==========================================>");
                System.out.println("Product removed");
                System.out.println("<==========================================>");
                return;
            }
        }
        System.out.println("<==========================================>");
        System.out.println("Product not found");
        System.out.println("<==========================================>");
    }

    private static Product getProductById(List<Product> products, int productID) {
        for (Product product : products) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }
}
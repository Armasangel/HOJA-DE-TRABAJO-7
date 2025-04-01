import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RetailPriceFinder {
    private BinarySearchTree<Product> productBST;
    private List<Product> productList;  

    public RetailPriceFinder() {
        productBST = new BinarySearchTree<>();
        productList = new ArrayList<>();
    }

    /**
     * Cargar datos del CSV
     * @param filePath Path para el archivo CSV
     */
    public void loadProductsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                //Linea Parseada CSV
                String[] values = line.split(",");
                
                //Verificar si los valores son correctos
                if (values.length >= 5) {
                    try {
                        String sku = values[0].trim();
                        double priceRetail = Double.parseDouble(values[1].trim());
                        double priceCurrent = Double.parseDouble(values[2].trim());
                        String productName = values[3].trim();
                        String category = values[4].trim();
                        
                        Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                        productBST.insert(product);
                        productList.add(product);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing numeric value in line: " + line);
                    }
                }
            }
            
            System.out.println("Loaded " + productList.size() + " products.");
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
     * Buscar productos por SKU
     * @param sku El SKU que se busca 
     * @return Producto encontrado o null
     */
    public Product searchBySKU(String sku) {
        Product searchKey = new Product(sku, 0, 0, "", "");
        return productBST.search(searchKey);
    }

    public void listProductsByPriceAscending() {
        System.out.println("\n--- Products Ordered by Price (Ascending) ---");
        productList.sort(new ProductByPriceComparator());
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void listProductsByPriceDescending() {
        System.out.println("\n--- Products Ordered by Price (Descending) ---");
        productList.sort(new ProductByPriceComparator().reversed());
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void listProductsBySKU() {
        System.out.println("\n--- Products Ordered by SKU ---");
        productBST.inOrderTraversal(product -> System.out.println(product));
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n--- Retail Price Finder ---");
            System.out.println("1. Search product by SKU");
            System.out.println("2. List products by price (ascending)");
            System.out.println("3. List products by price (descending)");
            System.out.println("4. List products by SKU");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter SKU to search: ");
                    String sku = scanner.nextLine();
                    Product found = searchBySKU(sku);
                    if (found != null) {
                        System.out.println("\nFound product:");
                        System.out.println("SKU: " + found.getSku());
                        System.out.println("Name: " + found.getProductName());
                        System.out.println("Category: " + found.getCategory());
                        System.out.println("Retail Price: $" + found.getPriceRetail());
                        System.out.println("Current Price: $" + found.getPriceCurrent() + " (Best price)");
                    } else {
                        System.out.println("Product not found with SKU: " + sku);
                    }
                    break;
                    
                case 2:
                    listProductsByPriceAscending();
                    break;
                    
                case 3:
                    listProductsByPriceDescending();
                    break;
                    
                case 4:
                    listProductsBySKU();
                    break;
                    
                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        
        scanner.close();
    }

    public static void main(String[] args) {
        RetailPriceFinder finder = new RetailPriceFinder();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el path del archivo CSV: ");
        String filePath = scanner.nextLine();
        
        finder.loadProductsFromCSV(filePath);
        finder.runMenu();
        
        scanner.close();
    }
}
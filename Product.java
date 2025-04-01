public class Product implements Comparable<Product> {
    private String sku;
    private double priceRetail;
    private double priceCurrent;
    private String productName;
    private String category;

    public Product(String sku, double priceRetail, double priceCurrent, String productName, String category) {
        this.sku = sku;
        this.priceRetail = priceRetail;
        this.priceCurrent = priceCurrent;
        this.productName = productName;
        this.category = category;
    }

    // Getters
    public String getSku() {return sku;}
    public double getPriceRetail() {return priceRetail;}
    public double getPriceCurrent() {return priceCurrent;}
    public String getProductName() {return productName;}
    public String getCategory() {return category;}

    @Override
    public int compareTo(Product other) {
        return this.sku.compareTo(other.sku);
    }

    @Override
    public String toString() {
        return "Product{" +
                "SKU='" + sku + '\'' +
                ", Retail Price=" + priceRetail +
                ", Current Price=" + priceCurrent +
                ", Name='" + productName + '\'' +
                ", Category='" + category + '\'' +
                '}';
    }
}

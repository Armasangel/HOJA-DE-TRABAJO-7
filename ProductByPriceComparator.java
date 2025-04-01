import java.util.Comparator;

public class ProductByPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPriceCurrent(), p2.getPriceCurrent());
    }
}
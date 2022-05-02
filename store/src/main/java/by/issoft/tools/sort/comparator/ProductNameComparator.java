package by.issoft.tools.sort.comparator;
import by.issoft.domain.Product;
import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product> {

    public int compare(Product a, Product b){

        return a.getName().compareTo(b.getName());
    }

}

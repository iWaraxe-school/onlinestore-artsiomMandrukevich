package by.issoft.tools.sort.comparator;
import by.issoft.domain.Product;
import java.util.Comparator;

public class ProductRateCompator  implements Comparator<Product> {

    public int compare(Product a, Product b){

        return a.getRate().compareTo(b.getRate());
    }

}

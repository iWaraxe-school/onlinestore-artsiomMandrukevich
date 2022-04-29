package by.issoft.tools.sort;

import by.issoft.domain.Product;
import by.issoft.tools.sort.comparator.ProductNameComparator;
import by.issoft.tools.sort.comparator.ProductPriceComparator;
import by.issoft.tools.sort.comparator.ProductRateCompator;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StoreComparator {

    private static final int TOP_PRICE = 5;
    private static final String PATH_TO_XML_SORT_CONFIG = "store/src/main/resources/config.xml";

    public static List<Comparator<Product>> buildListOfComparators(Map<String, SortValues> sortedmap){

        List<Comparator<Product>> listComparator = new ArrayList<>();

//       I left a predicate here, as lambda example for me.
//        Predicate<SortValues> isAsc = x -> x == SortValues.ASC;

        System.out.println("!!!!!! Sort products !!!!!!");
        for(Map.Entry<String, SortValues> sm : sortedmap.entrySet()){
            SortValues val = sm.getValue();
            System.out.println("key=" + sm.getKey() + " value=" + sm.getValue());
            switch (sm.getKey()){
                case "name" :
                    listComparator.add(isAsc(val) ? new ProductNameComparator() : new ProductNameComparator().reversed());
                    break;
                case "price" :
                    listComparator.add(isAsc(val) ? new ProductPriceComparator() : new ProductPriceComparator().reversed());
                    break;
                case "rate" :
                    listComparator.add(isAsc(val) ? new ProductRateCompator() : new ProductRateCompator().reversed());
                    break;
                default:
                    System.out.println("Oooops, something wrong !");
            }
        }
        return listComparator;
    }

    private Comparator<Product> getComparator(List<Comparator<Product>> listComparator){
        switch (listComparator.size()){
            case 1 : return listComparator.get(0);
            case 2 : return listComparator.get(0).thenComparing(listComparator.get(1));
            case 3 : return listComparator.get(0).thenComparing(listComparator.get(1)).thenComparing(listComparator.get(2));
            default:
                throw new IllegalStateException("Oooops, something wrong !");
        }
    }

    public List<Product> sortProduct(List<Product> products){
        XmlParser xmlParser = new XmlParser();
        xmlParser.xmlReadAndParse(PATH_TO_XML_SORT_CONFIG);
        Map<String, SortValues> sortMap = xmlParser.getSortMap();

        products.sort(getComparator(buildListOfComparators(sortMap)));
        return products;
    }

    public List<Product> top5ProductPrice(List<Product> products){
        System.out.println("!!!!!! TOP " + TOP_PRICE + " products by price !!!!!!");
        return products.stream().sorted(new ProductPriceComparator().reversed()).limit(TOP_PRICE ).collect(Collectors.toList());
    }

    private static boolean isAsc(SortValues sortType){
        return sortType == SortValues.ASC;
    }


}

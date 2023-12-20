package org.example.sevice;

import org.example.product.Product;

import java.util.*;

public class ProductService {
    public List<Product> findProductsByCategoryAndPriceMoreThen(List<Product> productList, String category, double price) {
        return productList.stream().filter(prod -> prod.getCategory().equals(category) && prod.getPrice() > price).toList();
    }

    public List<Product> findProductsByCategoryAndDiscount(List<Product> productList, String category, double discount) {
        return productList.stream()
                .filter(Product::isDiscount)
                .filter(prod -> prod.getCategory().equals(category))
                .map(prod -> new Product(prod.getCategory(), prod.getPrice() * (1 - discount), prod.isDiscount(), prod.getDateTime()))
                .toList();
    }

    public Product findCheapestProductByCategory(List<Product> productList, String category) {
        return productList.stream()
                .filter(prod -> prod.getCategory().equals(category))
                .min((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()))
                .orElseThrow(() -> new NoSuchElementException(category));
    }

    public List<Product> findLastThreeAddedProducts(List<Product> productList) {
        return productList.stream()
                .sorted(Comparator.comparing(Product::getDateTime))
                .limit(3)
                .toList();
    }

    public double getTotalPriceProductsByCategoryAndByYearAndPriceLessThen(List<Product> productList, String category, double price, int year) {
        return productList.stream()
                .filter(prod -> prod.getDateTime().getYear() == year)
                .filter(prod -> prod.getCategory().equals(category))
                .map(Product::getPrice).filter(prodPrice -> prodPrice < price)
                .reduce(0.0, Double::sum);
    }

    public Map<String, List<Product>> groupProductsInMapByCategory(List<Product> productList) {
        return productList.stream()
                .reduce(new HashMap<>(),
                        (hashMap, prod) -> {
                            String key = prod.getCategory();
                            List<Product> prodList;
                            if (hashMap.containsKey(key)) {
                                prodList = hashMap.get(key);
                                prodList.add(prod);
                            } else {
                                prodList = new LinkedList<>();
                                prodList.add(prod);
                                hashMap.put(key, prodList);
                            }
                            return hashMap;
                        },
                        (m, m2) -> {
                            m.putAll(m2);
                            return m;
                        });
    }
}

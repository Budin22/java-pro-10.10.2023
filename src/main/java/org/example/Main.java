package org.example;

import org.example.product.Product;
import org.example.sevice.ProductService;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("book", 250, true, LocalDate.now().minusDays(1));
        Product product2 = new Product("toy", 105, false, LocalDate.now());
        Product product3 = new Product("book", 555, true, LocalDate.now().minusDays(4));
        Product product4 = new Product("book", 12, true, LocalDate.now().minusDays(2));
        Product product5 = new Product("food", 250, false, LocalDate.now().minusYears(2));
        Product product6 = new Product("book", 347, false, LocalDate.now());
        Product product7 = new Product("food", 88, true, LocalDate.now().minusYears(1));
        Product product8 = new Product("book", 999, false, LocalDate.now().minusDays(3));

        List<Product> listProd = List.of(product1, product2, product3, product4, product5, product6, product7, product8);
        ProductService service = new ProductService();

        System.out.println("Book with price more than 250: " + service.findProductsByCategoryAndPriceMoreThen(listProd, "book", 250));
        System.out.println("Product with discount 10%: " + service.findProductsByCategoryAndDiscount(listProd,"book", 0.1 ));
        System.out.println("Get product with min price: " + service.findCheapestProductByCategory(listProd,"book"));
        System.out.println("Get last 3 added products: " + service.findLastThreeAddedProducts(listProd));
        System.out.println("Get total price of needed products: " + service.getTotalPriceProductsByCategoryAndByYearAndPriceLessThen(listProd, "book", 90, 2023));
        System.out.println("map: " + service.groupProductsInMapByCategory(listProd));
    }
}
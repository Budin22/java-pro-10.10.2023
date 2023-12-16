package org.example;

import org.example.product.Product;

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

//        1.2 Реалізувати метод отримання всіх продуктів у вигляді списку, категорія яких еквівалентна “Book” та ціна більш ніж 250.
//        List<Product> filteredProducts = listProd.stream().filter(prod -> prod.getCategory().equals("book") && prod.getPrice() > 250).toList();
//        System.out.println("Book with price more than 250: " + filteredProducts1);


//        2.2 Реалізувати метод отримання всіх продуктів як списку, категорія яких еквівалентна “Book” і з можливістю застосування знижки. Фінальний список повинен містити продукти з застосованою знижкою 10%.
//        List<Product> filteredProducts2 = listProd.stream().filter(Product::isDiscount).peek(prod -> prod.setPrice(prod.getPrice() * 0.9)).toList();
//        System.out.println("Product with discount 10%: " + filteredProducts2);


//        3.2 Реалізувати метод отримання найдешевшого продукту з категорії “Book”
//        String category = "book";
//        Product filteredProducts3 = listProd.stream()
//                .filter(prod -> prod.getCategory().equals(category))
//                .min((o1, o2)-> o1.getPrice() > o2.getPrice() ? 1 : 0)
//                .orElseThrow(() -> new NoSuchElementException(category));
//        System.out.println("Get product with min price: " + filteredProducts3);


//        4.2 Реалізувати метод отримання трьох останніх доданих продуктів
//        List<Product> filteredProducts = listProd.stream().sorted(Comparator.comparing(Product::getDateTime)).limit(3).toList();
//        System.out.println("Get last 3 added products: " + filteredProducts);


//        5.2 Реалізувати метод калькуляції загальної вартості продуктів, які відповідають наступним критеріям:
//        String category = "book";
//        int year = 2023;
//        double price = 75.0;
//        double filteredProducts = listProd.stream().filter(prod -> prod.getDateTime().getYear() == year).filter(prod -> prod.getCategory().equals(category)).map(Product::getPrice).filter(prodPrice -> prodPrice < price).reduce(0.0, Double::sum);
//        System.out.println("Get total price of needed products: " + filteredProducts);


//** 6.2 Реалізувати метод групування об'єктів за типом продукту. Таким чином результатом виконання методу буде тип даних “Словник”, що зберігає пару ключ-значення: {тип: список_продуктів}
        Map<String, List<Product>> productsMap = listProd.stream()
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

        System.out.println("map: " + productsMap);
    }
}
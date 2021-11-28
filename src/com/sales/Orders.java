package com.sales;

import com.sales.entities.Product;
import com.sales.entities.Customer;
import com.sales.entities.Item;
import com.sales.entities.Order;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Orders {

    static List<Order> orders = new ArrayList<>();
    static Product[] products;
    static int numProducts = 20;
    static int maxNumItems = 5;
    static int numOrders = 1000;
    static int minYear = 2018;
    static int maxYear = 2021;
    static int percentageOfCurrentYearProducts = 90;

    public static void main(String[] args) {
        createRandomOrders();

        if(args.length != 2) {
            System.out.println("The program takes two arguments (StartDate and EndDate)");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            LocalDateTime startDate = LocalDateTime.parse(args[0], formatter);
            LocalDateTime endDate = LocalDateTime.parse(args[1], formatter);

            if (startDate.isEqual(endDate)) {
                System.out.println("StartDate and EndDate should be different.");
                System.out.println(startDate + " == " + endDate);
                return;
            }

            HashMap<String, List<Product>> olderProductsMap = new HashMap<>();

            countOrders(startDate, endDate, olderProductsMap);

            if (olderProductsMap.isEmpty()) {
                System.out.println("No older products in this range");
                return;
            }
            for (String key : olderProductsMap.keySet()) {
                System.out.println(key + " months: " + olderProductsMap.get(key).size());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Arguments of Date and Time should follow the format: \"yyyy-MM-dd HH:mm:ss\"");
        }
    }

    private static void countOrders(LocalDateTime startDate, LocalDateTime endDate, HashMap<String, List<Product>> olderProductsMap) {
        for (Order order : orders) {
            if (order.getDatePlaced().isAfter(startDate) && order.getDatePlaced().isBefore(endDate)) {
                Product oldestProduct = null;
                Period oldestPeriod = null;
                int months = 0;

                for (Item item : order.getItems()) {
                    Period diff = Period.between(LocalDate.now(), item.getProduct().getCreationDate().toLocalDate());
                    if (diff.getYears() < 0) {
                        oldestProduct = item.getProduct();
                        months = 13;
                        break;
                    } else if (diff.getMonths() < -6) {
                        if (oldestProduct == null) {
                            oldestProduct = item.getProduct();
                            oldestPeriod = diff;
                            months = oldestPeriod.getMonths() * (-1);
                            break;
                        }
                        if (diff.getMonths() < oldestPeriod.getMonths()) {
                            oldestProduct = item.getProduct();
                            oldestPeriod = diff;
                            months = oldestPeriod.getMonths() * (-1);
                        }
                    } else if (diff.getMonths() < -3) {
                        if (oldestProduct == null) {
                            oldestProduct = item.getProduct();
                            oldestPeriod = diff;
                            months = oldestPeriod.getMonths() * (-1);
                            break;
                        }
                        if (diff.getMonths() < oldestPeriod.getMonths()) {
                            oldestProduct = item.getProduct();
                            oldestPeriod = diff;
                            months = oldestPeriod.getMonths() * (-1);

                        }
                    } else if (diff.getDays() < 0 || diff.getMonths() < 0) {
                        oldestProduct = item.getProduct();
                        oldestPeriod = diff;
                        months = 1;
                    } else {
                        System.out.println("Incorrect Period");
                    }
                }
                if (months > 12) {
                    if (!olderProductsMap.containsKey(">12")) {
                        olderProductsMap.put(">12", new ArrayList<>());
                        olderProductsMap.put("7-12", new ArrayList<>());
                        olderProductsMap.put("4-6", new ArrayList<>());
                        olderProductsMap.put("1-3", new ArrayList<>());
                    }
                    olderProductsMap.get(">12").add(oldestProduct);
                } else if (months > 6) {
                    if (!olderProductsMap.containsKey("7-12")) {
                        olderProductsMap.put("7-12", new ArrayList<>());
                        olderProductsMap.put("4-6", new ArrayList<>());
                        olderProductsMap.put("1-3", new ArrayList<>());
                    }
                    olderProductsMap.get("7-12").add(oldestProduct);
                } else if (months > 3) {
                    if (!olderProductsMap.containsKey("4-6")) {
                        olderProductsMap.put("4-6", new ArrayList<>());
                        olderProductsMap.put("1-3", new ArrayList<>());
                    }
                    olderProductsMap.get("4-6").add(oldestProduct);
                } else if (months > 0) {
                    if (!olderProductsMap.containsKey("1-3"))
                        olderProductsMap.put("1-3", new ArrayList<>());
                    olderProductsMap.get("1-3").add(oldestProduct);
                }
            }
        }
    }

    public static void createRandomOrders() {
        products = new Product[numProducts];
        Random random = new Random();

        String[] productNamesArray = new String[] {"Desktop", "Laptop", "Desktop Gamer", "Mouse", "Keyboard"};
        String[] productCategoriesArray = new String[] {"Computer", "Hardware"};
        for (int i = 0; i < numProducts; i++) {
            int year, month, day;
            if (random.nextInt(100) < percentageOfCurrentYearProducts) year = maxYear;
            else year = random.nextInt(4) + minYear;
            month = year == 2021 ? random.nextInt(11) + 1 : random.nextInt(12) + 1;
            day = year == 2021 && month == 11 ? random.nextInt(27) + 1 : random.nextInt(28) + 1;

            Product product = new Product(
                    productNamesArray[random.nextInt(productNamesArray.length)],
                    productCategoriesArray[random.nextInt(productCategoriesArray.length)],
                    random.nextInt(3),
                    random.nextInt(2000),
                    LocalDateTime.of(year, month, day,                                                   //date
                            random.nextInt(24),                                                    //hour
                            random.nextInt(60),                                                    //minute
                            random.nextInt(60))                                                    //second
                    );
            products[i] = product;
        }

        String[] customerNamesArray = new String[] {"John", "Mary", "Joseph", "Anna"};
        String[] customerTelephoneArray = new String[] {"999888777", "999777666", "999666555", "999555444"};
        String[] customerAddressArray = new String[] {"Street St", "Avenue Av", "Boulevard Blvd", "Road Rd"};
        String[] customerEmailArray = new String[] {"john@gmail.com", "mary@gmail.com", "joseph@hotmail.com", "anna@sapo.pt"};

        for (int i = 0; i < numOrders; i++) {

            List<Item> items = new ArrayList<>();
            for (int j = 0; j < random.nextInt(maxNumItems) + 1; j++) {
                items.add(new Item(products[random.nextInt(numProducts)]));
            }

            int customerId = random.nextInt(customerNamesArray.length);
            Customer customer = new Customer(
                    customerNamesArray[customerId],
                    customerEmailArray[customerId],
                    customerTelephoneArray[customerId],
                    customerAddressArray[customerId]
            );

            LocalDateTime datePlaced;
            LocalDateTime newestProduct = null;
            for (Item item : items) {
                if (newestProduct == null || item.getProduct().getCreationDate().isAfter(newestProduct))
                    newestProduct = item.getProduct().getCreationDate();
            }
            do {
                int year, month, day;
                if (random.nextInt(100) < percentageOfCurrentYearProducts) year = maxYear;
                else year = random.nextInt(4) + minYear;
                month = year == 2021 ? random.nextInt(11) + 1 : random.nextInt(12) + 1;
                day = year == 2021 && month == 11 ? random.nextInt(27) + 1 : random.nextInt(28) + 1;

                datePlaced = LocalDateTime.of(year, month, day,                                       //date
                        random.nextInt(24),                                                    //hour
                        random.nextInt(60),                                                    //minute
                        random.nextInt(60)                                                     //second
                    );
            } while (!datePlaced.isAfter(Objects.requireNonNull(newestProduct)));

            Order order = new Order(
                    customer,
                    datePlaced,
                    items
            );

            orders.add(order);
        }
    }

    /*public static void createOrders() {
        Product p1 = new Product("Desktop", "Computer", 2.0, 500.0,
                LocalDateTime.of(2019, Month.APRIL, 1, 19, 30, 1));
        Product p2 = new Product("Laptop", "Computer", 1.0, 1000.0,
                LocalDateTime.of(2021, Month.OCTOBER, 10, 20, 30, 1));
        Product p3 = new Product("Desktop Gamer", "Computer", 3.0, 1500.0,
                LocalDateTime.of(2021, Month.JULY, 16, 19, 30, 1));
        Product p4 = new Product("Mouse", "Hardware", 0.1, 10.0,
                LocalDateTime.of(2021, Month.MARCH, 1, 0, 0, 1));
        Product p5 = new Product("Keyboard", "Hardware", 0.3, 20.0,
                LocalDateTime.of(2018, Month.JANUARY, 1, 0, 0, 0));

        Item i1 = new Item(p1);
        Item i2 = new Item(p2);
        Item i3 = new Item(p3);
        Item i4 = new Item(p4);
        Item i5 = new Item(p5);

        List<Item> items1 = new ArrayList<>();
        items1.add(i1);
        items1.add(i2);
        Order o1 = new Order(new Customer("John", "john@gmail.com", "999888777", "Street St"),
                LocalDateTime.of(2021, 11, 10, 10, 10, 10), items1);

        List<Item> items2 = new ArrayList<>();
        items2.add(i2);
        items2.add(i3);
        items2.add(i4);
        Order o2 = new Order(new Customer("John", "john@gmail.com", "999888777", "Street St"),
                LocalDateTime.of(2021, 11, 10, 10, 10, 10), items2);

        List<Item> items3 = new ArrayList<>();
        items3.add(i2);
        Order o3 = new Order(new Customer("John", "john@gmail.com", "999888777", "Street St"),
                LocalDateTime.now(), items3);

        List<Item> items4 = new ArrayList<>();
        items4.add(i3);
        Order o4 = new Order(new Customer("John", "john@gmail.com", "999888777", "Street St"),
                LocalDateTime.of(2021, 9, 9, 9, 9), items4);

        List<Item> items5 = new ArrayList<>();
        items5.add(i5);
        Order o5 = new Order(new Customer("John", "john@gmail.com", "999888777", "Street St"),
                LocalDateTime.of(2019, 8, 8, 8, 8), items5);

        List<Item> items6 = new ArrayList<>();
        items6.add(i4);
        Order o6 = new Order(new Customer("John", "john@gmail.com", "999888777", "Street St"),
                LocalDateTime.of(2021, 4, 4, 4, 4), items6);

        orders.add(o1);
        orders.add(o2);
        orders.add(o3);
        orders.add(o4);
        orders.add(o5);
        orders.add(o6);
    }*/
}

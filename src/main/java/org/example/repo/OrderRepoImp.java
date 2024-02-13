package org.example.repo;

import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Order;
import org.example.model.Product;
import org.example.util.MyLocalDateTime;
import org.jvnet.hk2.annotations.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderRepoImp implements OrderRepo {
    @Inject
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(OrderRepoImp.class);


    @Override
    public Order getOrderById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from t_order as r join t_order_product as op on op.order_id = r.id join t_product as p on p.id = op.product_id where r.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("select * from t_order as r join t_order_product as op on op.order_id = r.id join t_product as p on p.id = op.product_id where r.id = ?");
            logger.debug("where id: {}", id);

            Order order = null;
            List<Product> products = new LinkedList<>();
            while (resultSet.next()) {
                if (order == null) {
                    order = new Order();
                    String orderDate = resultSet.getString("r.order_date");
                    int totalCost = resultSet.getInt("r.total_cost");
                    order.setId(id);
                    order.setDate(MyLocalDateTime.getLocalDateTimeFromString(orderDate));
                    order.setTotalCost(totalCost);
                }

                String name = resultSet.getString("p.name");
                int productId = resultSet.getInt("p.id");
                int cost = resultSet.getInt("p.cost");
                Product product = new Product(productId, name, cost);
                products.add(product);
            }

            if (order != null) order.setProducts(products);
            logger.debug("find order: {}", order);
            return order;
        } catch (SQLException e) {
            logger.error("SQLException in method getOrderById error : {}", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from t_order");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("select * from t_order");

            List<Order> orders = new LinkedList<>();
            while (resultSet.next()) {
                String orderDate = resultSet.getString("t_order.order_date");
                int id = resultSet.getInt("t_order.id");
                int totalCost = resultSet.getInt("t_order.total_cost");
                Order order = new Order(id, MyLocalDateTime.getLocalDateTimeFromString(orderDate), totalCost);
                orders.add(order);
            }
            logger.debug("find orders: {}", orders);
            return orders;
        } catch (SQLException e) {
            logger.error("SQLException in method getAllOrders error : {}", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order addOrder(Order order) {
        try {
            if (order == null) throw new RuntimeException("addOrder should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (order_date, total_cost) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            logger.info("INSERT INTO t_order (order_date, total_cost) VALUES (?, ?)");
            logger.debug("where order to add: {}", order);

            preparedStatement.setObject(1, LocalDateTime.now());
            preparedStatement.setInt(2, order.getTotalCost());
            int rowEffected = preparedStatement.executeUpdate();
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                if (genKey.next()) {
                    int id = genKey.getInt(1);
                    order.setId(id);
                    order.setDate(LocalDateTime.now());
                }
            }
            if (order.getId() != null) {
                List<Product> products = order.getProducts();
                products.forEach(prod -> {
                    try {
                        PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO t_order_product (order_id, product_id) VALUES (?, ?)");

                        preparedStatement1.setInt(1, order.getId());
                        preparedStatement1.setInt(2, prod.getId());
                        preparedStatement1.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                Order addedOrder = getOrderById(order.getId());
                logger.debug("added order: {}", addedOrder);
                return addedOrder;
            }
            return order;
        } catch (SQLException e) {
            logger.error("SQLException in method addOrder error : {}", e);
            throw new RuntimeException("Got SQLException in addOrder: " + e.getMessage());
        }
    }
}

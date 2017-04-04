package com.movie.fileLayer;


import com.movie.model.Order;

import java.util.List;

public interface IOrderFileDao {
    String buildString(Order order);

    List<Order> getOrders(String orderPath);

    Order getOrder(Long id, String orderPath);

    void writeOrders(List<Order> orders, String path);
}

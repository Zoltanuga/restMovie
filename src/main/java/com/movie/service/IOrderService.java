package com.movie.service;

import com.movie.model.Order;


public interface IOrderService {

    Order getOrder(Long id);

    Long book(long seanceId, Integer... seats);

    boolean cancel(long orderId);

}
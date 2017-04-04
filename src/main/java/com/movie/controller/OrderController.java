package com.movie.controller;

import com.movie.controller.exceptions.DataException;
import com.movie.model.Order;
import com.movie.model.responseObjects.OrderStatus;
import com.movie.model.responseObjects.OrderStatusResponse;
import com.movie.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public Order get(@PathVariable Long orderId) throws DataException {
        Order order = orderService.getOrder(orderId);
        if (order == null) {
            throw new DataException("order Id out of range");
        }
        return order;
    }


    @RequestMapping(value = "/orders/book", method = RequestMethod.GET)
    public OrderStatusResponse book(@RequestParam Long seanceId, @RequestParam Integer[] seats) throws DataException {
        Long orderId = orderService.book(seanceId, seats);
        if (orderId == null) {
            throw new DataException("error! possible causes: empty seats, not free seats");
        } else {
            return new OrderStatusResponse(OrderStatus.ADDED, orderId);
        }
    }

    @RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.GET)
    public OrderStatusResponse cancel(@PathVariable Long orderId) throws DataException {
        if (orderService.cancel(orderId)) {
            return new OrderStatusResponse(OrderStatus.DELETED, orderId);
        } else {
            throw new DataException("order with orderId =" + orderId + " is absent");
        }
    }
}

package com.movie.model.responseObjects;


public class OrderStatusResponse {
    private OrderStatus status;
    private Long orderId;

    public OrderStatusResponse() {
    }

    public OrderStatusResponse(OrderStatus status, Long orderId) {
        this.status = status;
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

package com.movie.model;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Order {
    private static AtomicLong idCounter = new AtomicLong(0);
    private long orderId;
    private List<Integer> reservedSeats;
    private double price;
    private Seance seance;

    public Order() {
        orderId = idCounter.incrementAndGet();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Integer> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (reservedSeats != null ? !reservedSeats.equals(order.reservedSeats) : order.reservedSeats != null)
            return false;
        return seance != null ? seance.equals(order.seance) : order.seance == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (reservedSeats != null ? reservedSeats.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (seance != null ? seance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", reservedSeats=" + reservedSeats +
                ", price=" + price +
                ", seance=" + seance +
                '}';
    }
}

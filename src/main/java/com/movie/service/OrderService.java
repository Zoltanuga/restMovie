package com.movie.service;

import com.movie.fileLayer.OrderFileDao;
import com.movie.model.Order;
import com.movie.model.Seance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private static final String ORDERS_FILE_PATH = "D:\\statistic\\data\\orders.txt";
    private OrderFileDao orderFileDao = new OrderFileDao();
    private SeanceService seanceService = new SeanceService();

    public Order getOrder(Long id) {
        return orderFileDao.getOrder(id, ORDERS_FILE_PATH);
    }

    public Long book(long seanceId, Integer... seats) {
        Order order = new Order();
        if (seats.length == 0) {
            return null;
        }
        List<Order> orders = orderFileDao.getOrders(ORDERS_FILE_PATH);
        List<Seance> seances = seanceService.getSeances();
        if (!performBooking(seanceId, order, orders, seances, seats)) {
            return null;
        }
        orderFileDao.writeOrders(orders, ORDERS_FILE_PATH);
        seanceService.saveSeances(seances);
        return order.getOrderId();
    }

    private boolean performBooking(long seanceId, Order order, List<Order> orders, List<Seance> seances, Integer[] seats) {
        for (Seance seance : seances) {
            if (seanceId == seance.getSeanceId()) {
                order.setSeance(seance);
                int counter = 0;
                List<Integer> reservedSeats = new ArrayList<>();
                for (Integer seat : seats) {
                    if (seance.isFree(seat)) {
                        seance.takeSeat(seat);
                        reservedSeats.add(seat);
                        counter++;
                    } else {
                        for (Integer integer : reservedSeats) {
                            seance.freeSeat(integer);
                        }
                        return false;
                    }
                }
                order.setReservedSeats(reservedSeats);
                order.setPrice(counter * seance.getPrice());
                orders.add(order);
            }
        }
        return true;
    }

    public boolean cancel(long orderId) {
        List<Order> orders = orderFileDao.getOrders(ORDERS_FILE_PATH);
        List<Seance> seances = seanceService.getSeances();
        Order order = orderFileDao.getOrder(orderId, ORDERS_FILE_PATH);
        if (order == null) {
            return false;
        }
        performCancellation(orders, seances, order);
        orderFileDao.writeOrders(orders, ORDERS_FILE_PATH);
        seanceService.saveSeances(seances);
        return true;
    }

    private void performCancellation(List<Order> orders, List<Seance> seances, Order order) {
        for (Seance seance : seances) {
            if (order.getSeance().getSeanceId() == seance.getSeanceId()) {
                List<Integer> reservedSeats = order.getReservedSeats();
                for (Integer integer : reservedSeats) {
                    seance.freeSeat(integer);
                }
            }
        }
        for (int i = 0; i < orders.size(); i++)
            if (orders.get(i).getOrderId() == order.getOrderId()) {
                orders.remove(order);
            }
    }

}

package com.movie.fileLayer;


import com.movie.model.Order;
import com.movie.model.Seance;
import com.movie.service.SeanceService;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderFileDao implements IOrderFileDao {
    private SeanceService seanceService = new SeanceService();

    public String buildString(Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(order.getOrderId()).append(" ")
                .append(order.getPrice()).append(" ")
                .append(order.getSeance().getSeanceId()).append(" ");

        for (Integer reserved : order.getReservedSeats()) {
            stringBuilder.append(reserved).append(" ");
        }
        return stringBuilder.toString().trim();
    }


    public List<Order> getOrders(String orderPath) {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(orderPath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] sData = currentLine.split(" ");
                Order order = new Order();
                initOrder(order, sData, Long.parseLong(sData[0]));
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrder(Long id, String orderPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderPath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] sData = currentLine.split(" ");
                long currId = Long.parseLong(sData[0]);
                if (id == currId) {
                    Order order = new Order();
                    initOrder(order, sData, currId);
                    return order;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initOrder(Order order, String[] sData, long currId) {
        order.setOrderId(currId);
        order.setPrice(Double.parseDouble(sData[1]));
        long seanceId = Long.parseLong(sData[2]);
        Seance seance = seanceService.getSeance(seanceId);
        order.setSeance(seance);
        List<Integer> reserved = new ArrayList<>();
        for (int i = 3; i < sData.length; i++) {
            reserved.add(Integer.parseInt(sData[i]));
        }
        order.setReservedSeats(reserved);
    }

    public void writeOrders(List<Order> orders, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, false))) {
            for (Order currOrder : orders) {
                bufferedWriter.write(buildString(currOrder));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

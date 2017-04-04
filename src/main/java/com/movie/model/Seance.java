package com.movie.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Seance {
    private static AtomicLong idCounter = new AtomicLong(0);
    private long seanceId;
    private SeanceName name;
    private String date;
    private double price;
    private Map<Integer, Boolean> seats = new HashMap<>();


    public Seance(int seatsQuantity) {
        seanceId = idCounter.incrementAndGet();
        for (int i = 0; i < seatsQuantity; i++) {
            seats.put(i, true);
        }
    }

    public Seance() {
    }

    public Map<Integer, Boolean> getSeats() {
        return seats;
    }

    public void setSeats(Map<Integer, Boolean> seats) {
        this.seats = seats;
    }

    public int obtainFreeSeatsNumber() {
        int counter = 0;
        for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
            if (entry.getValue()) {
                counter++;
            }
        }
        return counter;
    }

    public int getFullSeatsNumber() {
        return seats.size();
    }

    public boolean freeSeat(int seatNumber) {
        if (seatNumber >= seats.size()) {
            return false;
        }
        seats.put(seatNumber, true);
        return true;
    }

    public boolean isFree(int seatNumber) {
        if (seatNumber >= seats.size()) {
            return false;
        }
        return seats.get(seatNumber);
    }

    public boolean takeSeat(int seatNumber) {
        if (seatNumber >= seats.size()) {
            return false;
        }
        if (seats.get(seatNumber)) {
            seats.put(seatNumber, false);
            return true;
        } else {
            return false;
        }
    }

    public long getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(long seanceId) {
        this.seanceId = seanceId;
    }

    public SeanceName getName() {
        return name;
    }

    public void setName(SeanceName name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seance seance = (Seance) o;

        if (seanceId != seance.seanceId) return false;
        if (Double.compare(seance.price, price) != 0) return false;
        if (name != seance.name) return false;
        if (date != null ? !date.equals(seance.date) : seance.date != null) return false;
        return seats != null ? seats.equals(seance.seats) : seance.seats == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (seanceId ^ (seanceId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "seanceId=" + seanceId +
                ", name=" + name +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", seats=" + seats +
                '}';
    }
}

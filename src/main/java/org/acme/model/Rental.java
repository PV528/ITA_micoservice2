package org.acme.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(database="rentals", collection = "rentals")
public class Rental extends PanacheMongoEntity {
    private String rentStart;
    private String rentEnd;
    private int price;
    private String carId;

    public Rental(String rentStart, String rentEnd, int price, String carId) {
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
        this.price = price;
        this.carId = carId;
    }
    public Rental(){

    }

    // Getters and setters
    public String getRentStart() {
        return rentStart;
    }

    public void setRentStart(String rentStart) {
        this.rentStart = rentStart;
    }

    public String getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(String rentEnd) {
        this.rentEnd = rentEnd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}



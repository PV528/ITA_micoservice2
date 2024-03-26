package org.acme.Repository;

import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Rental;
import org.bson.types.ObjectId;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class RentalRepository {
    public RentalRepository() {
    }

    public Uni<org.acme.Rental.RentalResponse> createRental(org.acme.Rental.RentalRequest request) {
        Rental rental = new Rental(
                request.getRentStart(),
                request.getRentEnd(),
                request.getPrice(),
                request.getCarId()
        );
        rental.persist();
        String message = "Najem uspešno vstavljen";
        return Uni.createFrom().item(org.acme.Rental.RentalResponse.newBuilder().setMessage(message).build());
    }

    public Uni<org.acme.Rental.RentalGetResponse> getRental(org.acme.Rental.RentalGetRequest request) {
        ObjectId id = new ObjectId(request.getRentalId());
        Rental rental = Rental.findById(id);
        if (rental != null) {
            org.acme.Rental.RentalGetResponse response = org.acme.Rental.RentalGetResponse.newBuilder()
                    .setRentStart(rental.getRentStart())
                    .setRentEnd(rental.getRentEnd())
                    .setPrice(rental.getPrice())
                    .setCarId(rental.getCarId())
                    .build();
            return Uni.createFrom().item(response);
        } else {
            System.out.println("Dokument s tem Id-jem ne obstaja.");
            return Uni.createFrom().nullItem();
        }
    }
    public Uni<org.acme.Rental.RentalResponse> deleteRental(org.acme.Rental.RentalGetRequest request) {
        ObjectId id = new ObjectId(request.getRentalId());
        boolean rental = Rental.deleteById(id);
        if (rental) {
            String message = "Najem uspešno izbrisan";
            return Uni.createFrom().item(org.acme.Rental.RentalResponse.newBuilder().setMessage(message).build());
        } else {
            System.out.println("Dokument s tem Id-jem ne obstaja.");
            return Uni.createFrom().nullItem();
        }
    }
    public Uni<org.acme.Rental.RentalPutResponse> updateRental(org.acme.Rental.RentalPutRequest request) {
        ObjectId id = new ObjectId(request.getRentalId());
        Rental rental = Rental.findById(id);
        if (rental != null) {
            rental.setRentStart(request.getRentStart());
            rental.setRentEnd(request.getRentEnd());
            rental.setPrice(request.getPrice());
            rental.setCarId(request.getCarId());
            rental.update();
            org.acme.Rental.RentalPutResponse response = org.acme.Rental.RentalPutResponse.newBuilder()
                    .setRentStart(rental.getRentStart())
                    .setRentEnd(rental.getRentEnd())
                    .setPrice(rental.getPrice())
                    .setCarId(rental.getCarId())
                    .build();
            return Uni.createFrom().item(response);
        } else {
            System.out.println("Dokument s tem Id-jem ne obstaja.");
            return Uni.createFrom().nullItem();
        }

    }
    public Multi<org.acme.Rental.RentalGetResponse> allRental(org.acme.Rental.RentalGetAllRequest request) {
        PanacheQuery<Rental> query = Rental.findAll(Sort.by("rentStart"));
        Multi<org.acme.Rental.RentalGetResponse> multi = Multi.createFrom().items(
                query.stream()
                        .map(rental -> org.acme.Rental.RentalGetResponse.newBuilder()
                                .setRentStart(rental.getRentStart())
                                .setRentEnd(rental.getRentEnd())
                                .setPrice(rental.getPrice())
                                .setCarId(rental.getCarId())
                                .build())
        );
        return Multi.createFrom().publisher(multi);
    }


}


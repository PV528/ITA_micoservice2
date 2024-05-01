package org.acme.service;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.acme.MutinyRentalServiceGrpc;
import org.acme.Rental;
import org.acme.Repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@GrpcService
public class RentalService extends MutinyRentalServiceGrpc.RentalServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalService.class);
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Uni<org.acme.Rental.HealthCheckResponse> check(org.acme.Rental.HealthCheckRequest request) {
        LOGGER.info("Health check");
        return rentalRepository.check(request);
    }
    @Override
    public Uni<org.acme.Rental.RentalResponse> createRental(org.acme.Rental.RentalRequest request) {
        LOGGER.info("Ustvarjanje najema: {}", request);
        return rentalRepository.createRental(request);
    }

    @Override
    public Uni<org.acme.Rental.RentalGetResponse> getRental(org.acme.Rental.RentalGetRequest request) {
        LOGGER.info("Pridobivanje najema z id: {}", request);
        return rentalRepository.getRental(request);
    }

    @Override
    public Uni<org.acme.Rental.RentalResponse> deleteRental(org.acme.Rental.RentalGetRequest request) {
        LOGGER.info("Brisanje najema z id: {}", request);
        return rentalRepository.deleteRental(request);
    }

    @Override
    public Uni<org.acme.Rental.RentalPutResponse> updateRental(org.acme.Rental.RentalPutRequest request) {
        LOGGER.info("Posodabljanje najema: {}", request);
        return rentalRepository.updateRental(request);
    }

    @Override
    public Multi<org.acme.Rental.RentalGetResponse> allRental(org.acme.Rental.RentalGetAllRequest request) {
        LOGGER.info("Pridobivanje vseh najemmov");
        return rentalRepository.allRental(request);
    }
}





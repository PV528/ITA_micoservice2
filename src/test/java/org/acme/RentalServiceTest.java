package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.acme.Repository.RentalRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.acme.service.RentalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService = new RentalService();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void integrationTestCreateRental() {
        Rental.RentalRequest request = Rental.RentalRequest.newBuilder()
                .setRentStart("2024-03-19")
                .setRentEnd("2024-03-25")
                .setPrice(500)
                .setCarId("ABC123")
                .build();

 //       when(rentalRepository.createRental(any())).thenReturn(Uni.createFrom().item(createMockRentalResponse()));
        Uni<Rental.RentalResponse> responseUni = rentalService.createRental(request);
        responseUni.subscribe().with(response -> {
            assertEquals("Najem uspešno vstavljen", response.getMessage());
        });
    }
    /*private Rental.RentalResponse createMockRentalResponse() {
        return Rental.RentalResponse.newBuilder()
                .setMessage("Najem uspešno vstavljen")
                .build();
    }*/

    @Test
    public void unitTestGetRental() {
        ObjectId id = new ObjectId("65fb62cc11679c251f6b3ed1");
        String rentalIdString = id.toHexString(); // Convert ObjectId to String
        Rental.RentalGetRequest request = Rental.RentalGetRequest.newBuilder()
                .setRentalId(rentalIdString)
                .build();
        when(rentalRepository.getRental(request)).thenReturn(Uni.createFrom().item(createMockRentalGetResponse()));
        Uni<Rental.RentalGetResponse> responseUni = rentalService.getRental(request);
        responseUni.subscribe().with(response ->{
            assertEquals("2024-03-19", response.getRentStart());
            assertEquals("2024-03-25", response.getRentEnd());
            assertEquals(500, response.getPrice());
            assertEquals("ABC123", response.getCarId());
        });
    }
    private Rental.RentalGetResponse createMockRentalGetResponse() {
        return Rental.RentalGetResponse.newBuilder()
                .setRentStart("2024-03-19")
                .setRentEnd("2024-03-25")
                .setPrice(500)
                .setCarId("ABC123")
                .build();
    }
    /*@Test
    public void integrationTestGetRental() {
        ObjectId id = new ObjectId("65fb62cc11679c251f6b3ed1");
        String rentalIdString = id.toHexString();
        Rental.RentalGetRequest request = Rental.RentalGetRequest.newBuilder()
                .setRentalId(rentalIdString)
                .build();
        Rental.RentalGetResponse response = rentalService.getRental(request).await().indefinitely();
        assertEquals("2024-03-19", response.getRentStart());
        assertEquals("2024-03-25", response.getRentEnd());
        assertEquals(500, response.getPrice());
        assertEquals("ABC123", response.getCarId());
    }*/

   /* @Test
    public void integrationTestDeleteRental() {
        // Prepare test data
        ObjectId id = new ObjectId("65fb61ef230bea4faa5a8ba2");
        String rentalIdString = id.toHexString();
        Rental.RentalGetRequest request = Rental.RentalGetRequest.newBuilder()
                .setRentalId(rentalIdString)
                .build();
        Rental.RentalResponse expectedResponse = createMockRentalDeleteResponse();
        when(rentalRepository.deleteRental(any())).thenReturn(Uni.createFrom().item(expectedResponse));
        Uni<Rental.RentalResponse> responseUni = rentalService.deleteRental(request);
        responseUni.subscribe().with(response -> {
            assertEquals(expectedResponse.getMessage(), response.getMessage());
        });
    }*/

    /*@Test
    public void unitTestDeleteRental() {
        RentalRepository rentalRepository = mock(RentalRepository.class);
        ObjectId id = new ObjectId("65fb61ef230bea4faa5a8ba2");
        String rentalIdString = id.toHexString();
        Rental.RentalGetRequest request = Rental.RentalGetRequest.newBuilder()
                .setRentalId(rentalIdString)
                .build();
        Rental.RentalResponse expectedResponse = createMockRentalDeleteResponse();
        when(rentalRepository.deleteRental(request)).thenReturn(Uni.createFrom().item(expectedResponse));
        RentalService rentalService = new RentalService();
        Uni<Rental.RentalResponse> responseUni = rentalService.deleteRental(request);
        responseUni.subscribe().with(response -> {
            assertEquals(expectedResponse.getMessage(), response.getMessage());
        });
    }
    private Rental.RentalResponse createMockRentalDeleteResponse() {
        return Rental.RentalResponse.newBuilder()
                .setMessage("Najem uspešno izbrisan")
                .build();
    }*/
}


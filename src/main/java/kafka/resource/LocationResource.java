package kafka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import kafka.producer.LocationProducer;

@Path("/produce-location")
public class LocationResource {

    @Inject
    LocationProducer locationProducer;

    @GET
    public String produceLocations() {
        locationProducer.sendLocationData();
        return "Location data sent!";
    }
}

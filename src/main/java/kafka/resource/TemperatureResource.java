package kafka.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import kafka.producer.TemperatureProducer;

@Path("/produce-temperature")
public class TemperatureResource {

    @Inject
    TemperatureProducer temperatureProducer;

    @GET
    public String produceTemperatureData() {
        temperatureProducer.sendTemperatureData();
        return "Temperature data sent!";
    }
}

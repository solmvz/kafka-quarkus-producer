package kafka.producer;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
import kafka.schema.Location;

import java.util.Random;
import java.util.UUID;

@ApplicationScoped
public class LocationProducer {

    @Inject
    Logger logger;

    @Channel("location-topic")
    Emitter<Location> emitter;

    private final String[] deviceIds = {"001", "002", "003", "004", "005"};
    private final Random random = new Random();

    public void sendLocationData() {
        for (String deviceId : deviceIds) {

            double latitude = random.nextDouble() * 180 - 90;  // Random latitude (-90 to 90)
            double longitude = random.nextDouble() * 360 - 180; // Random longitude (-180 to 180)

            Location deviceLocation = Location.newBuilder()
                    .setDeviceId(deviceId)
                    .setLatitude(latitude)
                    .setLongitude(longitude)
                    .build();

            emitter.send(KafkaRecord.of(deviceId, deviceLocation));
            logger.infof("Produced location for vehicle %s: %s", deviceId, deviceLocation);
        }
    }
}

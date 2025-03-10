package kafka.producer;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
import kafka.schema.Temperature;

import java.util.Random;
import java.util.UUID;

@ApplicationScoped
public class TemperatureProducer {

    @Inject
    Logger logger;

    @Channel("temperature-topic")
    Emitter<Temperature> emitter;

    private final String[] deviceIds = {"001", "002", "003", "004", "005"};
    private final Random random = new Random();

    public void sendTemperatureData() {
        for (String deviceId : deviceIds) {
            double temperature = -30 + (random.nextDouble() * 80); // Random temperature between -30 and 50°C

            Temperature deviceTemperature = Temperature.newBuilder()
                    .setDeviceId(deviceId)
                    .setTemperature(temperature)
                    .build();

            emitter.send(KafkaRecord.of(deviceId, deviceTemperature));
            logger.infof("Produced temperature event for device %s: %s", deviceId, deviceTemperature);
        }
    }
}

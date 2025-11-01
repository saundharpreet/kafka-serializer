package com.harpreetsaund.kafkaserializer.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ConsumerRecordStringSerializer implements Serializer<ConsumerRecord<?, ?>> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, ConsumerRecord<?, ?> consumerRecord) {
        if (consumerRecord == null)
            return null;

        try {
            var recordMap = Map.of("topic", consumerRecord.topic(), //
                    "partition", consumerRecord.partition(), //
                    "offset", consumerRecord.offset(), //
                    "key", String.valueOf(consumerRecord.key()), //
                    "value", String.valueOf(consumerRecord.value()), //
                    "timestamp", consumerRecord.timestamp()); //
            return objectMapper.writeValueAsBytes(recordMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize ConsumerRecord", e);
        }
    }
}

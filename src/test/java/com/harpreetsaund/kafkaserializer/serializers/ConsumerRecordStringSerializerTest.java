package com.harpreetsaund.kafkaserializer.serializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerRecordStringSerializerTest {

    private final ConsumerRecordStringSerializer serializer = new ConsumerRecordStringSerializer();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void serialize_nonNullRecord_returnsJsonWithExpectedFields() throws Exception {
        ConsumerRecord<String, String> record = new ConsumerRecord<>("my-topic", 1, 42L, "myKey", "myValue");

        byte[] bytes = serializer.serialize(null, record);
        assertNotNull(bytes);

        Map<String, Object> map = objectMapper.readValue(bytes, new TypeReference<>() {
        });

        assertEquals("my-topic", map.get("topic"));
        assertEquals(1, ((Number) map.get("partition")).intValue());
        assertEquals(42L, ((Number) map.get("offset")).longValue());
        assertEquals("myKey", map.get("key"));
        assertEquals("myValue", map.get("value"));
        assertTrue(map.containsKey("timestamp"));
    }

    @Test
    void serialize_nullRecord_returnsNull() {
        assertNull(serializer.serialize("any", null));
    }

    @Test
    void serialize_nullKeyAndValue_becomeStringLiteralNull() throws Exception {
        ConsumerRecord<String, String> record = new ConsumerRecord<>("topic", 0, 1L, null, null);

        byte[] bytes = serializer.serialize(null, record);
        assertNotNull(bytes);

        Map<String, Object> map = objectMapper.readValue(bytes, new TypeReference<>() {
        });

        assertEquals("null", map.get("key"));
        assertEquals("null", map.get("value"));
    }
}

# 🧩 kafka-serializer

**kafka-serializer** is a lightweight Java library that provides custom Kafka serializers and deserializers for complex or non-POJO objects — such as `ConsumerRecord`, `GenericRecord`, or other custom Avro messages.

It’s especially useful in **Spring Integration** or **Spring Kafka** projects where messages need to be safely serialized for **Dead Letter Queue (DLQ)** processing or logging.

---

## 🚀 Features

- ✅ Custom serializer for `ConsumerRecord`
- ✅ Jackson-based serialization (JSON-friendly)
- ✅ Works seamlessly with Spring Boot & Spring Kafka
- ✅ Ideal for DLQ and error handling flows
- ✅ Easily extendable for your own types

---

## 🧱 Installation

### Maven

```xml
<dependency>
  <groupId>com.harpreetsaund</groupId>
  <artifactId>kafka-serializer</artifactId>
  <version>1.0.0</version>
</dependency>

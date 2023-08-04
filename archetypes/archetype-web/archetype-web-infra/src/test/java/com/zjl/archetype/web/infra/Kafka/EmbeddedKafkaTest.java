package com.zjl.archetype.web.infra.Kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * kafka 单元测试
 *
 * @link {https://blog.mimacom.com/embeddedkafka-kafka-auto-configure-springboottest-bootstrapserversproperty/}
 */

@Ignore // 默认不进行容器启动的测试，infra层常年不会更改。除了dao

@EmbeddedKafka(
    bootstrapServersProperty = "spring.kafka.bootstrap-servers",
    topics = EmbeddedKafkaTest.TOPIC_NAME
)
@TestPropertySource(properties = "spring.kafka.consumer.auto-offset-reset = earliest")
@TestInstance(Lifecycle.PER_CLASS)
public class EmbeddedKafkaTest implements KafkaApplicationTest{

    static final String TOPIC_NAME = "topic";

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @Autowired
    private ProducerFactory<String, String> producerFactory;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private BlockingQueue<ConsumerRecord<String, String>> consumptionQueue = new LinkedBlockingDeque<>();

    @KafkaListener(topics = TOPIC_NAME, groupId = "listener")
    private void listen(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
        consumptionQueue.put(consumerRecord);
    }

    @Test
    public void testProducerAndConsumer() throws Exception {
        final String KEY = "key1", VALUE = "value1";
        try (
            Consumer<String, String> consumer = consumerFactory.createConsumer("consumer", null);
            Producer<String, String> producer = producerFactory.createProducer();
        ) {
            consumer.subscribe(asList(TOPIC_NAME));

            producer.send(new ProducerRecord<>(TOPIC_NAME, KEY, VALUE), (metadata, exception) -> {

            }).get();
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));


            assertThat(records).hasSize(1).allMatch(singleRecord -> {
                assertThat(singleRecord.key()).isEqualTo(KEY);
                assertThat(singleRecord.value()).isEqualTo(VALUE);
                return true;
            });
            consumer.commitSync();
            consumer.unsubscribe();
        }
    }

    @Test
    public void testTemplateAndListener() throws Exception {
        final String KEY = "key2", VALUE = "value2";
        consumptionQueue.clear();

        kafkaTemplate.send(TOPIC_NAME, KEY, VALUE).get();
        ConsumerRecord<String, String> consumerRecord = consumptionQueue.poll(3, TimeUnit.SECONDS);

        assertThat(consumerRecord.key()).isEqualTo(KEY);
        assertThat(consumerRecord.value()).isEqualTo(VALUE);
        assertThat(consumptionQueue).isEmpty();
    }
}

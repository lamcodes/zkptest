package com.example.zkptest.config;


import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author zkp
 * @Date 2022-10-06 15:28
 */
@Slf4j
public class testsa {

    public static void main(String[] args) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
                .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.WARN);

        Map<String, Object> map = new HashMap<>();
        map.put("bootstrap.servers", "101.43.55.200:9092");
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> producer = new KafkaProducer<>(map);

        ProducerRecord<String,String> record = new ProducerRecord<>(
                "topic1",
                0,
                "a",
                "fasdfadf"
        );
        //消息的同步发送
//        Future<RecordMetadata> send = producer.send(record);
//        try {
//            RecordMetadata recordMetadata = send.get();
//            System.out.println("消息的主题"+recordMetadata.topic());
//            System.out.println("消息的分区"+recordMetadata.partition());
//            System.out.println("消息的偏移量"+recordMetadata.offset());
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        消息的异步发送
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e==null) {
                    System.out.println("消息的主题"+recordMetadata.topic());
                    System.out.println("消息的分区"+recordMetadata.partition());
                    System.out.println("消息的偏移量"+recordMetadata.offset());
                }else{
                    System.out.println("消息发送失败，异常信息是"+e.getMessage());
                }
            }
        });
        producer.close();
    }
}

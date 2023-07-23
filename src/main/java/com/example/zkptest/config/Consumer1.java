package com.example.zkptest.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Description TODO
 * @Author zkp
 * @Date 2022-10-06 15:58
 */
public class Consumer1 {
    public static void main(String[] args) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
                .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(ch.qos.logback.classic.Level.WARN);

        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"101.43.55.200:9092");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        map.put(ConsumerConfig.GROUP_ID_CONFIG,"testzkp");
        //latest拉取最新的,earliest表示从头开始，
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<>(map);

        kafkaConsumer.subscribe(Arrays.asList("topic1"));


        ConsumerRecords<String, String> poll = kafkaConsumer.poll(3000);
        for (ConsumerRecord<String, String> record : poll) {
            System.out.println("主题"+record.topic() +"偏移量"+record.offset()+"内容"+record.value());

        }


//        poll.forEach(new Consumer<ConsumerRecord<String,String>>(){
//
//            @Override
//            public void accept(ConsumerRecord<String, String> consumerRecord) {
//                System.out.println("主题"+consumerRecord.topic() +"偏移量"+consumerRecord.offset()+"内容"+consumerRecord.value());
//            }
//        });

        kafkaConsumer.close();

    }
}

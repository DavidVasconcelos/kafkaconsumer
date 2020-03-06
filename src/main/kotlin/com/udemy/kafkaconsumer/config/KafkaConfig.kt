package com.udemy.kafkaconsumer.config

import com.google.gson.Gson
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
@EnableKafka
class KafkaConfig {

    @Value("\${kafka.server}")
    private lateinit var kafkaServer: String

    @Value("\${kafka.group.id}")
    private lateinit var groupId: String


    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val config = hashMapOf<String, Any>()

        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaServer
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.GROUP_ID_CONFIG] = groupId

        return DefaultKafkaConsumerFactory(config, StringDeserializer(), StringDeserializer())
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val concurrentKafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()

        concurrentKafkaListenerContainerFactory.consumerFactory = consumerFactory()

        return concurrentKafkaListenerContainerFactory
    }

    @Bean
    fun jsonConverter(): Gson {
        return Gson()
    }
}
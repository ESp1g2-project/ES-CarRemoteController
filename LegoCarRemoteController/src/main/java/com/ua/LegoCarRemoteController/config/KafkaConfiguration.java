package com.ua.LegoCarRemoteController.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import org.apache.kafka.common.serialization.StringSerializer;
import com.ua.LegoCarRemoteController.model.Command;

@Configuration
public class KafkaConfiguration 
{
	@Bean
	public ProducerFactory<String, Command> producerFactory() 
	{
		Map<String, Object> config = new HashMap<>();
		config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,  "192.168.160.210:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<> (config);
	}
	
	@Bean
	public KafkaTemplate<String, Command> kafkaTemplate()
	{
		return new KafkaTemplate<String, Command>(producerFactory());
	}
}

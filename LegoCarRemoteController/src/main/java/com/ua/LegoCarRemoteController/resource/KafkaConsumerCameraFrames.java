package com.ua.LegoCarRemoteController.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.ua.LegoCarRemoteController.model.Command;

@Service
public class KafkaConsumerCameraFrames 
{
	//@Autowired
	//private KafkaTemplate<String, String> kafkaTemplate;
	private static final String TOPIC = "p1g2camera";
	
    @KafkaListener(topics = TOPIC, groupId = "my-group")
    public void consume(@Payload String data) throws IOException 
    {
    	
    	System.out.println("\n------------------------\n");
		
    	System.out.println(data);
		UltrasoundDataController.cameraFrames.add(data);
    }
    
}

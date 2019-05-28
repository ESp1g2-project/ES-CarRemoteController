package com.ua.LegoCarRemoteController.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.ua.LegoCarRemoteController.model.Command;

@Service
public class KafkaConsumerUltrasoundSensor 
{
	@Autowired
	private KafkaTemplate<String, Command> kafkaTemplate;
	private static final String TOPIC = "p1g2drive";
	
    @KafkaListener(topics = "p1g2ultrasonic", groupId = "my-group")
    public void consume(@Payload String data) throws IOException 
    {
    	System.out.printf("%s\n", data);
    	
    	int dataInt;
    	
		try {  
			dataInt = Integer.parseInt(data);
			
			UltrasoundDataController.ultrasoundData.add(dataInt);
	    	
	    	if (dataInt < 10)
	    		kafkaTemplate.send(TOPIC, new Command("stop"));
		    
		  } catch(NumberFormatException e){  
		    System.out.println("Distance is not a number");
		  }  
    }
    
    
}

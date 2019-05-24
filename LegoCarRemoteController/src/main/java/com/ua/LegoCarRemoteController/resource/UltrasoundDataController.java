package com.ua.LegoCarRemoteController.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.*;

import com.ua.LegoCarRemoteController.model.Command;
import com.ua.LegoCarRemoteController.model.DistanceData;


@RestController
@CrossOrigin(origins = "http://localhost:8099")
public class UltrasoundDataController 
{
	public static List<Integer> ultrasoundData = new ArrayList<Integer>();
	
    /*@GetMapping(path = "/ultrasounddata", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public int feed() 
    {
        return ultrasoundData.get(ultrasoundData.size() - 1);
    }*/
    /*
    @Autowired
	private KafkaTemplate<String, Command> kafkaTemplate;
	private static final String TOPIC = "p1g2drive";
	
    @KafkaListener(topics = "p1g2ultrasonic", groupId = "my-group")
    public void consume(@Payload String data) throws IOException 
    {
    	
    	// stop car from moving if it's going in any of the forward directions, and there's an obstacule in front of it 
    	System.out.printf("---- %s\n", data);
    	
    	ultrasoundData.add(Integer.parseInt(data));
    	
    	if (ultrasoundData.size() == 0)
    		System.out.println("eedede");
    	
    	if (ultrasoundData.get(ultrasoundData.size() - 1) < 10)
    		kafkaTemplate.send(TOPIC, new Command("stop"));
    }*/
    
    
    @CrossOrigin
    @RequestMapping("/getString")
    public DistanceData getString()
    {
    	if(UltrasoundDataController.ultrasoundData.size() == 0)
    		return new DistanceData(-1);
    	
        return new DistanceData(UltrasoundDataController.ultrasoundData.get(UltrasoundDataController.ultrasoundData.size()-1));
    }
}

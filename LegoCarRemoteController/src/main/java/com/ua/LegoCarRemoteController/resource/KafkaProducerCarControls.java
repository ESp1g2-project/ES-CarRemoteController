package com.ua.LegoCarRemoteController.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ua.LegoCarRemoteController.model.Command;

@RestController
@RequestMapping("kafka")
public class KafkaProducerCarControls {

	@Autowired
	private KafkaTemplate<String, Command> kafkaTemplate;
	private static final String TOPIC = "p1g2car";
	
	/*@GetMapping("/publish/{message}")
	public String post(@PathVariable("message") final String message)
	{
		kafkaTemplate.send(TOPIC, message);
		
		return "Published successfully";
	}*/
	
	@GetMapping("/publish/{message}")
	public void post(@PathVariable("message") final String command)
	{
		kafkaTemplate.send(TOPIC, new Command(command));
	}
	
	
}

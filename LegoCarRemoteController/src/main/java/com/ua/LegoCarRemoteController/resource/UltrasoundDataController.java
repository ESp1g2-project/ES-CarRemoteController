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
import com.ua.LegoCarRemoteController.model.FrameData;


@RestController
@CrossOrigin(origins = "http://localhost:8099")
public class UltrasoundDataController 
{
	public static List<Integer> ultrasoundData = new ArrayList<Integer>();
	
	public static List<String> cameraFrames = new ArrayList<String>();
	
	@CrossOrigin
    @RequestMapping("/getString")
    public DistanceData getString()
    {
        if(UltrasoundDataController.ultrasoundData.size() == 0)
            return new DistanceData(-1);
            
        return new DistanceData(UltrasoundDataController.ultrasoundData.get(UltrasoundDataController.ultrasoundData.size()-1));
    }
    
    @CrossOrigin
    @RequestMapping("/getFrames")
    public FrameData getFrames()
    {
    	if(UltrasoundDataController.cameraFrames.size() == 0)
    		return new FrameData("");
    	
        return new FrameData(UltrasoundDataController.cameraFrames.get(UltrasoundDataController.cameraFrames.size()-1));
    }
}

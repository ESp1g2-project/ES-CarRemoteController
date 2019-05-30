package com.ua.LegoCarRemoteController.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private static final String TOPIC = "p1g2drive";
	
	 private static final String PORT = "8097";
     private static final String SERVER = "192.168.160.73";
	 private static final String USER = "postgres";
	 private static final String PASSWORD = "mysecretpassword";
	 private static final String DB_NAME = "car";
	
	/*@GetMapping("/publish/{message}")
	public String post(@PathVariable("message") final String message)
	{
		kafkaTemplate.send(TOPIC, message);
		
		return "Published successfully";
	}*/

    @GetMapping("/publish/{message}")
    public void post(@PathVariable("message") final String command)
    {
        // Load driver
	    try 
	    {
	        Class.forName("org.postgresql.Driver");
	    }
	    catch (ClassNotFoundException e) 
	    {
	        System.out.println("No PostgreSQL JDBC Driver found");
	        e.printStackTrace();
	    }
	    
	    kafkaTemplate.send(TOPIC, new Command(command));
	            
	    String cmd = command;                		// The command given
        sendToDB(cmd);
            
            
    }
	
    private static void sendToDB (String cmd)
    {
        Connection con = getConnection();
        
        Statement stmt = null;
        
        String sql = "INSERT INTO car_cmd_history (cmd) "
            + "VALUES ('"+cmd+"') ; ";
        
        try
        {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static ResultSet readDB ()
    {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM car_cmd_history;" );
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        
        return rs;
    }
    
    
    private static Connection getConnection()
    {
        Connection connection = null;
        try 
        {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://"+SERVER+":"+PORT+"/"+DB_NAME, 
                USER,
                PASSWORD);
        }
        catch (SQLException e) 
        {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            System.exit(1);
        }
        if (connection == null) 
        {
            System.err.println("Connection failed!");
            System.exit(1);
        }
        return connection;
    }
}

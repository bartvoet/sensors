package org.b.v.hub;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jms.core.JmsTemplate;

public class SensorHubEvents implements MessageListener {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String string = ((TextMessage) message).getText();
            	@SuppressWarnings("resource")
				Scanner scanner = new Scanner(string).useDelimiter(";");
            	
            	DateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        		Map<String,Object> parameters = new HashMap<String,Object>();
        		parameters.put("sensorId",scanner.next());
        		parameters.put("measurementTime", format.parse(scanner.next()));
        		parameters.put("functionality", scanner.next());
        		parameters.put("measurementValue", scanner.next());
        		jdbcTemplate.update("insert into SensorMeassurement "
        				+ "(sensorId,measurementTime,functionality,measurementValue) "
        				+ "values(:sensorId,:measurementTime,:functionality,:measurementValue)"
        				, parameters);
        		
        		scanner.close();
                
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
                //TODO store problems
            } catch (ParseException e) {
            	throw new RuntimeException(e);
            	//TODO store problems
			}
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}